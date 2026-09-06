import java.io.*;
import java.net.URL;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.regex.*;
import java.util.*;

public class HashGenerator {
    public static void main(String[] args) throws Exception {
        List<String> ids = new ArrayList<>();
        List<String> urls = new ArrayList<>();
        
        File[] files = {
            new File("app/src/main/res/raw/champions_part1.json"),
            new File("app/src/main/res/raw/champions_part2.json")
        };
        
        Pattern idPattern = Pattern.compile("\"id\":\\s*\"([^\"]+)\"");
        Pattern urlPattern = Pattern.compile("\"avatarUrl\":\\s*\"([^\"]+)\"");
        
        for (File file : files) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) sb.append(line);
            br.close();
            
            String content = sb.toString();
            
            // Simpler matching: find all objects
            Pattern objPattern = Pattern.compile("\\{.*?\\}");
            Matcher objMatcher = objPattern.matcher(content.replaceAll("\\n", ""));
            
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
                
                ids.add(id);
                urls.add(url);
                
                p1 = urlEnd;
            }
        }
        
        StringBuilder out = new StringBuilder();
        out.append("package com.example.util\n\n");
        out.append("object ChampionHashes {\n");
        out.append("    val map = mapOf<String, Long>(\n");
        
        System.out.println("Processing " + ids.size() + " champions...");
        for (int i = 0; i < ids.size(); i++) {
            String id = ids.get(i);
            String urlStr = urls.get(i);
            try {
                URL url = new URL(urlStr);
                // System.setProperty("http.agent", "Mozilla/5.0");
                java.net.HttpURLConnection con = (java.net.HttpURLConnection)url.openConnection();
                con.setRequestProperty("User-Agent", "Mozilla/5.0");
                BufferedImage img = ImageIO.read(con.getInputStream());
                if (img == null) continue;
                
                BufferedImage resized = new BufferedImage(8, 8, BufferedImage.TYPE_BYTE_GRAY);
                Graphics2D g2 = resized.createGraphics();
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
                
                out.append(String.format("        \"%s\" to %dL,\n", id, hash));
                System.out.println("Done: " + id);
            } catch (Exception e) {
                System.out.println("Failed: " + id + " - " + e.getMessage());
            }
        }
        out.append("    )\n");
        out.append("}\n");
        
        FileWriter fw = new FileWriter("app/src/main/java/com/example/util/ChampionHashes.kt");
        fw.write(out.toString());
        fw.close();
        System.out.println("Finished writing ChampionHashes.kt");
    }
}
