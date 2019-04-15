import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Background {
    public static final String IMG_FILE = "field.png";
    public static final int SIZE = 40;
    private static BufferedImage img;
    public Background () {

        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }
    public void draw(Graphics g) {
        g.drawImage(img, 0, 0, 900, 300, null);
    }
}