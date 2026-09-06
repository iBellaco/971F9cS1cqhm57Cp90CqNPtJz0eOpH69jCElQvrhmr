import java.io.*;
import java.net.URL;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.regex.*;
import java.util.*;
import java.util.concurrent.*;

public class HashGenerator {

    static long computeHash(BufferedImage img) {
        BufferedImage resized = new BufferedImage(8, 8, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g2 = resized.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(img, 0, 0, 8, 8, null);
        g2.dispose();
        
        long total = 0;
        int[] pixels = new int[64];
        int idx = 0;
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                int p = resized.getRGB(x, y) & 0xFF;
                pixels[idx++] = p;
                total += p;
            }
        }
        
        long avg = total / 64;
        long hash = 0;
        for (int j = 0; j < 64; j++) {
            if (pixels[j] >= avg) {
                hash |= (1L << (63 - j));
            }
        }
        return hash;
    }

    static BufferedImage getCrop(BufferedImage img, double scale) {
        int w = img.getWidth();
        int h = img.getHeight();
        int tw = (int)(w * scale);
        int th = (int)(h * scale);
        int sx = (w - tw) / 2;
        int sy = (h - th) / 2;
        return img.getSubimage(sx, sy, tw, th);
    }

    public static void main(String[] args) throws Exception {
        Map<String, String> champUrls = new LinkedHashMap<>();
        
        File[] files = {
            new File("app/src/main/res/raw/champions_part1.json"),
            new File("app/src/main/res/raw/champions_part2.json")
        };
        
        for (File file : files) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) sb.append(line);
            br.close();
            
            String content = sb.toString();
            int p1 = 0;
            while (true) {
                int start = content.indexOf("\"id\":", p1);
                if (start == -1) break;
                int idStart = content.indexOf("\"", start + 5) + 1;
                int idEnd = content.indexOf("\"", idStart);
                String id = content.substring(idStart, idEnd);
                
                int urlStartIdx = content.indexOf("\"avatarUrl\":", idEnd);
                if (urlStartIdx == -1) break;
                int urlStart = content.indexOf("\"", urlStartIdx + 12) + 1;
                int urlEnd = content.indexOf("\"", urlStart);
                String url = content.substring(urlStart, urlEnd);
                
                champUrls.put(id, url);
                p1 = urlEnd;
            }
        }
        
        System.out.println("Processing " + champUrls.size() + " champions in parallel...");
        Map<String, Long> resultMap = new ConcurrentHashMap<>();
        ExecutorService executor = Executors.newFixedThreadPool(16);
        List<Future<?>> futures = new ArrayList<>();

        for (Map.Entry<String, String> entry : champUrls.entrySet()) {
            final String id = entry.getKey();
            final String urlStr = entry.getValue();
            
            futures.add(executor.submit(() -> {
                try {
                    URL url = new URL(urlStr);
                    java.net.HttpURLConnection con = (java.net.HttpURLConnection)url.openConnection();
                    con.setRequestProperty("User-Agent", "Mozilla/5.0");
                    con.setConnectTimeout(8000);
                    con.setReadTimeout(8000);
                    BufferedImage img = ImageIO.read(con.getInputStream());
                    if (img != null) {
                        long hFull = computeHash(img);
                        long hCrop75 = computeHash(getCrop(img, 0.75));
                        long hCrop60 = computeHash(getCrop(img, 0.60));
                        
                        resultMap.put(id, hFull);
                        resultMap.put(id + "_crop75", hCrop75);
                        resultMap.put(id + "_crop60", hCrop60);
                        System.out.println("Done: " + id);
                    }
                } catch (Exception e) {
                    System.err.println("Failed " + id + ": " + e.getMessage());
                }
            }));
        }

        for (Future<?> f : futures) {
            try { f.get(); } catch (Exception ignored) {}
        }
        executor.shutdown();

        StringBuilder out = new StringBuilder();
        out.append("package com.example.util\n\n");
        out.append("object ChampionHashes {\n");
        out.append("    val map = mapOf<String, Long>(\n");
        
        List<String> sortedKeys = new ArrayList<>(resultMap.keySet());
        Collections.sort(sortedKeys);
        for (String k : sortedKeys) {
            out.append(String.format("        \"%s\" to %dL,\n", k, resultMap.get(k)));
        }
        out.append("    )\n");
        out.append("}\n");
        
        FileWriter fw = new FileWriter("app/src/main/java/com/example/util/ChampionHashes.kt");
        fw.write(out.toString());
        fw.close();
        System.out.println("Successfully generated " + resultMap.size() + " hashes in ChampionHashes.kt");
    }
}
