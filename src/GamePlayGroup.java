import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import java.util.ArrayList;

public class GamePlayGroup extends Group {
    Rocket rocket = new Rocket();
    Camera camera = new Camera (100,0,rocket);
    Image background = new Image("file:img/lunarLanderBackground.png");
    ArrayList<Sprite> ufos = new ArrayList<Sprite>();
    ImageView backgroundView = new ImageView(background);
    Image fuel = new Image("file:img/fuel_gauge_small.png");
    ImageView fuelView = new ImageView(fuel);
    Image meter = new Image("file:img/Meter.png");
    ImageView meterViewVertical = new ImageView(meter);
    ImageView meterViewHorizontal = new ImageView(meter);
    Image indicator = new Image("file:img/indicator.png");
    ImageView indicatorViewVertical = new ImageView(indicator);
    ImageView indicatorViewHorizontal = new ImageView(indicator);
    Rotate hRotate = new Rotate(0,25,395);
    Rotate vRotate = new Rotate(0,375,395);

    public GamePlayGroup (){
        ufos.add(new Sprite(100,100,"img/ufoSpriteSheet.png",100,
            4,10, 50, 64,35, 20));
        ufos.add(new Sprite(300,250,"img/ufoSpriteSheet.png",100,
                4,10, 50, 64,35, 20));
        ufos.add(new Sprite(500,400,"img/ufoSpriteSheet.png",100,
                4,10, 50, 64,35, 20));
        ufos.add(new Sprite(700,550,"img/ufoSpriteSheet.png",100,
                4,10, 50, 64,35, 20));
        this.getChildren().add(backgroundView);
        for (Sprite ufo : ufos){
            this.getChildren().add(ufo.getSpriteView());
        }
        this.getChildren().add(rocket.getSpriteView());
        this.getChildren().add(fuelView);
        this.getChildren().add(meterViewHorizontal);
        this.getChildren().add(meterViewVertical);
        this.getChildren().add(indicatorViewHorizontal);
        this.getChildren().add(indicatorViewVertical);
        fuelView.setX(375);
        fuelView.setY(15);
        meterViewHorizontal.setY(370);
        meterViewHorizontal.setX(0);
        meterViewVertical.setY(370);
        meterViewVertical.setX(350);
        indicatorViewHorizontal.setY(370);
        indicatorViewHorizontal.setX(0);
        indicatorViewVertical.setY(370);
        indicatorViewVertical.setX(350);
        indicatorViewVertical.getTransforms().add(vRotate);
        indicatorViewHorizontal.getTransforms().add(hRotate);

    }

    public void start(){
        at.start();
    }

    AnimationTimer at = new AnimationTimer() {
        @Override
        public void handle(long l) {
            for (Sprite ufo : ufos){
                ufo.update(l/1000000);
                if(rocket.collidedWith(ufo)) rocket.collide();
            }
            rocket.update(l/1000000, LunarLander.getInput());
            camera.update();
            render(l);
        }
    };

    public void render(long l){
        backgroundView.setViewport(new Rectangle2D(camera.getX(),camera.getY(),400,400 ));
        for (Sprite ufo : ufos){
            ufo.getSpriteView().setX(ufo.getX()-camera.getX());
            ufo.getSpriteView().setY(ufo.getY()-camera.getY());
        }
        rocket.getSpriteView().setX(rocket.getX()-camera.getX());
        rocket.getSpriteView().setY(rocket.getY()-camera.getY());
        int fuelGauge = 11-(int) ((rocket.getFuel()/100.0)*11);
        fuelView.setViewport(new Rectangle2D(fuelGauge*31,0,20,50));
        hRotate.setAngle(-45-(rocket.getSpeedX()/0.08)*90);
        vRotate.setAngle(-45+(rocket.getSpeedY()/0.4)*90);
        if (LunarLander.getInput().contains("UP"))
        {
            rocket.getSpriteView().setViewport(new Rectangle2D(0,0,30,86));
        }
        else {
            rocket.getSpriteView().setViewport(new Rectangle2D(0,0,30,55));
        }
    }
}
