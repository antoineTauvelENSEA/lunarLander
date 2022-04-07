import java.util.ArrayList;
enum Live {ALIVE, COLLIDED, CRASHED, LANDED};

public class Rocket extends Sprite{
    private Live live=Live.ALIVE;
    private double fuel = 10000;
    private double speedX=0;
    private double speedY=0;
    private double aX = 0;
    private double aY = 0;
    private long lastTime=0;
    private final double gravity = 0.1e-3;
    private final double rearMotor = -0.2e-3;
    private final double sideMotor = 0.05e-3;
    private final double instantFuelConsumptionRear = 0.3;
    private final double instantFuelConsumptionSide = 0.1;
    private boolean hasCollided = false;

    public Rocket(){
        super(200,10, "img/rocket.png", 100, 1,
        0,0, 0, 30,86);
    }

    public double getFuel() {
        return fuel;
    }

    public double getSpeedX() {
        return speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void collide() {
        hasCollided=true;
    }

    public void update(long time, ArrayList<String> input){
        if (lastTime!=0) {
            long deltaTime = lastTime - time;
            if ((LunarLander.getInput().contains("LEFT")) && (fuel >0)){
                aX= -sideMotor;
                fuel=fuel-instantFuelConsumptionSide;
            }
            else{
                if ((LunarLander.getInput().contains("RIGHT")) && (fuel >0)){
                    aX= sideMotor;
                    fuel=fuel-instantFuelConsumptionSide;
                }
                else {
                    aX = 0;
                }
            }
            if ((LunarLander.getInput().contains("UP")) && (fuel >0)){
                aY=gravity + rearMotor;
                fuel=fuel-instantFuelConsumptionRear;
            }
            else { aY=gravity; }
            if (y>650){
                aY=0;
                speedY=0;
            }
            speedX=speedX+aX*deltaTime;
            speedY=speedY+aY*deltaTime;
            x=x+speedX*deltaTime;
            y=y+speedY*deltaTime;
        }
        lastTime=time;
        switch (live){
            case ALIVE :
                if((y>650) && Math.abs(speedX)<0.01 &&
                        Math.abs(speedY)<0.1) { live=Live.LANDED;}
                else{
                    if (y>650) live=Live.CRASHED;
                    else {
                        if (hasCollided) live=Live.COLLIDED;
                    }
                }
                break;
            case CRASHED :
            case LANDED :
            case COLLIDED:
                break;
        }
    }
}
