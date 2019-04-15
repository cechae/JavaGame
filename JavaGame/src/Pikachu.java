

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pikachu {
    private static final int px = 50;
    private static final int PY = 150;
    private static final String IMG_FILE = "Pikachu.png";
    private static BufferedImage img;

    public Pikachu() {
        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }
    public void draw(Graphics g) {
    	g.drawImage(img, px, PY, 100, 100, null);
    }
}