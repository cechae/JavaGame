
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Heart {
	public static final String IMG_FILE = "heart.png";
	public static final int PY = 10;
	private int px;
	public static BufferedImage img;
	
	public Heart(int px) {
		this.px = px;
		try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
	}
	public void setPx(int px) {
		this.px = px;
	}
	public int getPx() {
		return this.px;
	}
	public void draw(Graphics g) {
		g.drawImage(img, px, PY, 20, 20, null);
	}
	
}
