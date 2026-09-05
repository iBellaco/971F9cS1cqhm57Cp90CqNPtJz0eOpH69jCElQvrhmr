import java.io.*;
import java.net.URL;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class test_one {
    public static void main(String[] args) throws Exception {
        System.out.println("Starting download...");
        URL url = new URL("https://ddragon.leagueoflegends.com/cdn/14.16.1/img/champion/Aatrox.png");
        java.net.HttpURLConnection con = (java.net.HttpURLConnection)url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        System.out.println("Connecting...");
        BufferedImage img = ImageIO.read(con.getInputStream());
        System.out.println("Downloaded: " + (img != null));
    }
}
