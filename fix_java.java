import java.io.*;
import java.net.URL;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.regex.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class fix_java {
    public static void main(String[] args) throws Exception {
        String json1 = new String(Files.readAllBytes(Paths.get("app/src/main/res/raw/champions_part1.json")));
        String json2 = new String(Files.readAllBytes(Paths.get("app/src/main/res/raw/champions_part2.json")));
        
        List<String> ids = new ArrayList<>();
        List<String> urls = new ArrayList<>();
        
        extract(json1, ids, urls);
        extract(json2, ids, urls);
        
        System.out.println("Processing " + ids.size() + " champs...");
        StringBuilder out = new StringBuilder("package com.example.util\n\nobject ChampionHashes {\n    val map = mapOf<String, Long>(\n");
        
        for (int i = 0; i < ids.size(); i++) {
            String id = ids.get(i);
            String urlStr = urls.get(i);
            try {
                URL url = new URL(urlStr);
                java.net.HttpURLConnection con = (java.net.HttpURLConnection)url.openConnection();
                con.setRequestProperty("User-Agent", "Mozilla/5.0");
                con.setConnectTimeout(5000);
                con.setReadTimeout(5000);
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
                if (i % 20 == 0) System.out.println("Progress: " + i);
            } catch (Exception e) {
                System.out.println("Fail " + id + ": " + e.getMessage());
            }
        }
        out.append("    )\n}\n");
        Files.write(Paths.get("app/src/main/java/com/example/util/ChampionHashes.kt"), out.toString().getBytes());
        System.out.println("Done Writing");
    }
    
    static void extract(String content, List<String> ids, List<String> urls) {
        String[] parts = content.split("\"id\"\\s*:\\s*\"");
        for (int i=1; i<parts.length; i++) {
            String p = parts[i];
            int endId = p.indexOf("\"");
            String id = p.substring(0, endId);
            
            int urlStartIdx = p.indexOf("\"avatarUrl\"");
            if (urlStartIdx == -1) continue;
            int q1 = p.indexOf("\"", urlStartIdx + 11);
            int q2 = p.indexOf("\"", q1 + 1);
            String url = p.substring(q1 + 1, q2);
            ids.add(id);
            urls.add(url);
        }
    }
}
