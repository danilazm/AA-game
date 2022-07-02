package middlewareManager.middlewares;

import config.Config;
import frameManager.*;
import middlewareManager.MiddlewareLocation;
import middlewareManager.MiddlewareManager;
import utils.writer.ProfileHandler;
import javax.swing.JLabel;
import frameManager.panels.GamePanel;

import java.awt.Color;

public class FinishLevel extends Middleware {
    MiddlewareManager middlewareManager = Config.getMiddlewareManager();
    String groupId = "game";
    private String USER_IN = Config.getEnteredUser();

    public FinishLevel(){
        super("finishLevel");
    }

    public FinishLevel(String middlewaresGroupId){
        super("finishLevel");
        this.groupId = middlewaresGroupId;
    }

    @Override
    public void init(){
        APanel panel = this.frameManager.getAPanel(groupId);
        //setting green background
        panel.setBackground(Color.GREEN);
        //setting rotation speed to 0
        this.setValue("rotationSpeed", "0");
    }

    @Override
    public void run(){

        //creating an animation for when the game is finished.
        if((Config.getLineLength())<(Config.getFrameHeight()))
            Config.setLineLength(Config.getLineLength()+5);
        //stopping processes and showing finishLevel panel then removing self.
        else{
             //pausing middlewares
            middlewareManager.setPausedMiddlewaresByGroup(groupId, true);

            this.middlewareManager.addMiddleware(new TransitionPanels("game", "win", true), new MiddlewareLocation());
            ProfileHandler.putData(USER_IN, 0, 0); // TODO getting level number and endTime.
            //TODO mark this level as finished in profile
            JLabel lastTimeGame;
            lastTimeGame = ((GamePanel) Config.getFrameManager().getAPanel("game")).getTimerLevel();
            this.setValue("levelRecord", lastTimeGame.getText());
            this.remove();
        }

       
    }
}
