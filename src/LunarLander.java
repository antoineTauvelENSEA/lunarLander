import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.ArrayList;


public class LunarLander extends Application {
    public void LunarLander(){
    }
    GamePlayGroup gamePlayGroup = new GamePlayGroup();
    GameEngine gameEngine= new GameEngine();
    Group welcome = new Group();
    Image welcomeScreen = new Image("file:img/welcomeScreen.png");
    ImageView welcomeScreenView = new ImageView (welcomeScreen);
    Stage stage = new Stage();
    Scene mainScene = new Scene(gamePlayGroup,400,400);
    Scene welcomeScene = new Scene(welcome,400,400);

    static ArrayList<String> input = new ArrayList<>();

    AnimationTimer gameEngineTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            gameEngine.update(l, input, mainScene, stage);
        }
    };


    @Override
    public void start(Stage stage) throws Exception {
        welcome.getChildren().add(welcomeScreenView);
        stage.setScene(mainScene);
        gamePlayGroup.start();
        gameEngineTimer.start();
        welcomeScene.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
                    if (!input.contains(code)) {
                        input.add(code);
                    }
                }
        );
        mainScene.setOnKeyPressed(
                e->{
                    String code = e.getCode().toString();
                    if (!input.contains(code)){
                        input.add(code);
                    }
                }
        );
        mainScene.setOnKeyReleased(
                e->{
                    String code = e.getCode().toString();
                    input.remove(code);
                }
        );
        stage.show();
    }

    public static ArrayList<String> getInput(){
        return input;
    }

}
