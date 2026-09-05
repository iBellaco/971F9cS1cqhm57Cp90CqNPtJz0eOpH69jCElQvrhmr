import java.io.*;
import java.net.URL;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.nio.file.Files;
import java.nio.file.Paths;

public class generate_role_hashes {
    public static void main(String[] args) throws Exception {
        String[][] roles = {
            {"TOP", "https://raw.communitydragon.org/latest/plugins/rcp-fe-lol-static-assets/global/default/images/icon-position-top.png"},
            {"JUNGLE", "https://raw.communitydragon.org/latest/plugins/rcp-fe-lol-static-assets/global/default/images/icon-position-jungle.png"},
            {"MID", "https://raw.communitydragon.org/latest/plugins/rcp-fe-lol-static-assets/global/default/images/icon-position-middle.png"},
            {"ADC", "https://raw.communitydragon.org/latest/plugins/rcp-fe-lol-static-assets/global/default/images/icon-position-bottom.png"},
            {"SUPPORT", "https://raw.communitydragon.org/latest/plugins/rcp-fe-lol-static-assets/global/default/images/icon-position-utility.png"}
        };
        
        StringBuilder out = new StringBuilder("package com.example.util\n\nimport com.example.model.LaneRole\n\nobject RoleHashes {\n    val map = mapOf<LaneRole, Long>(\n");

        for (String[] role : roles) {
            try {
                URL url = new URL(role[1]);
                java.net.HttpURLConnection con = (java.net.HttpURLConnection)url.openConnection();
                con.setRequestProperty("User-Agent", "Mozilla/5.0");
                BufferedImage img = ImageIO.read(con.getInputStream());
                
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
                out.append(String.format("        LaneRole.%s to %dL,\n", role[0], hash));
                System.out.println("Downloaded " + role[0] + ": " + hash);
            } catch (Exception e) {
                System.out.println("Fail " + role[0] + ": " + e.getMessage());
            }
        }
        
        out.append("    )\n}\n");
        Files.write(Paths.get("app/src/main/java/com/example/util/RoleHashes.kt"), out.toString().getBytes());
        System.out.println("Done Writing");
    }
}
