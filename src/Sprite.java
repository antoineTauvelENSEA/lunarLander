import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite {
    protected double x;
    protected double y;
    private double speed=1;
    private final Image spriteSheet;
    private final double interFrameTime;        // in milliseconds
    private final int numberOfFrame;
    private final int offsetX;
    private final int offsetY;
    private final int sizeX;
    private final int sizeY;
    private final int interFrameX;


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    private ImageView spriteView;

    public Sprite(double x, double y, String filePath, double iFT, int numberOfFrame,
                  int offsetX, int offsetY, int interFrameX, int sizeX, int sizeY) {
        this.x = x;
        this.y = y;
        this.spriteSheet = new Image("file:"+filePath);
        spriteView = new ImageView(spriteSheet);
        this.numberOfFrame=numberOfFrame;
        this.interFrameTime=iFT;
        this.offsetX=offsetX;
        this.offsetY=offsetY;
        this.interFrameX=interFrameX;
        this.sizeX=sizeX;
        this.sizeY=sizeY;
    }

    public ImageView getSpriteView() {
        return spriteView;
    }
    int old_i;
    public void update (long time){
        int i;
        i=(int)(time/interFrameTime)%numberOfFrame;
        if (old_i!=i)
            {
                this.spriteView.setViewport(
                            new Rectangle2D(offsetX+i*interFrameX,offsetY,sizeX,sizeY));
                old_i=i;
            }
        x=x+speed;
        if (x>750) speed=-speed;
        if (x<50) speed=-speed;

    }

    boolean collidedWith(Sprite s){
       return this.spriteView.intersects(
               s.getSpriteView().getBoundsInLocal());
    }
}
