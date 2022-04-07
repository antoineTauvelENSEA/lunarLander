import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

enum State{WELCOME, PLAY, WIN, LOST};

public class GameEngine {
    private State state=State.WELCOME;
    public GameEngine(){

    }

    public State getState(){
        return state;
    }

    public void update(long time, ArrayList<String> input, Scene playScene, Stage stage){
        System.out.println(state);
        switch (state){
            case WELCOME :
                if(!input.isEmpty())
                {   state = State.PLAY;
                 //   stage.setScene(playScene);

                }
                break;
            case PLAY :


                break;

        }
    }
}
