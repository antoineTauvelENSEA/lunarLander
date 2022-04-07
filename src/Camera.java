public class Camera {
    private double x;
    private double y;
    private Rocket rocket;

    public Camera(double x, double y,Rocket r) {
        this.x = x;
        this.y = y;
        this.rocket=r;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void update(){
        x = rocket.getX()-200;
        if (x<0) x=0;
        if (x>400) x=400;
        y = rocket.getY()-20;
        if (y<0) y=0;
        if (y>400) y=400;
    }
}
