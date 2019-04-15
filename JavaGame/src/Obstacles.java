import java.awt.Graphics;

public abstract class Obstacles {
    private int px;
    private int py;
    private int width;
    private int height;
    private int vx = 10;
    public static final int VY = 200;
    public Obstacles(int px, int py, int width, int height)  {
        this.px = px;
        this.py = py;
        this.width = width;
        this.height = height;
    }
    
    public int getPx() {
        return this.px;
    }
    public int getPy() {
        return this.py;
    }
    public int getVx() {
    	return this.vx;
    }
    public int getWidth() {
        return this.width;
    }
    public int getHeight() {
        return this.height;
    }
    public void setPx(int px) {
        this.px = px;
    }
    
    public abstract void draw(Graphics g);
    public abstract boolean isInstance(Obstacles o);
    public abstract void effects(GameCourt c);
    
}
