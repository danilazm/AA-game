package middlewareManager.middlewares;

import config.Config;
import frameManager.*;
import frameManager.panels.GamePanel;
import groovyjarjarantlr4.v4.runtime.atn.Transition;
import middlewareManager.MiddlewareLocation;
import frameManager.panels.GamePanel;
import middlewareManager.MiddlewareManager;
import utils.writer.ProfileHandler;
import javax.swing.JLabel;
import frameManager.panels.GamePanel;

import java.awt.Color;
import java.util.Objects;

public class FinishLevel extends Middleware {
    MiddlewareManager middlewareManager = Config.getMiddlewareManager();
    String groupId = "game";
    private String USER_IN = middlewareManager.getMiddlewareValue("userName");
    private String CURRENT_LEVEL = middlewareManager.getMiddlewareValue("currentLevel");
    private String LEVEL_END_TIME = middlewareManager.getMiddlewareValue("levelEndTime");
    private String LEVEL_NUMBER = middlewareManager.getMiddlewareValue("levelNumber");

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
        GamePanel g = (GamePanel)panel;
        g.setButtonColor(Color.GREEN);
        //setting rotation speed to 0
        GamePanel g = (GamePanel)frameManager.getAPanel("game");
        g.setButtonColor(Color.GREEN);
        this.setValue("rotationSpeed", "0");
    }

    @Override
    public void run(){

        //creating an animation for when the game is finished.
        if((Config.getLineLength())<(Config.getFrameHeight())) {
            Config.setLineLength(Config.getLineLength()+5);
            LevelTimer.updateTimeLabel.setText("0");
        }
        //stopping processes and showing finishLevel panel then removing self.
        else{
             //pausing middlewares
            LevelTimer.updateTimeLabel.setText("0");
            middlewareManager.setPausedMiddlewaresByGroup(groupId, true);

            if (Objects.equals(LEVEL_NUMBER, "10")) {
                middlewareManager.addMiddleware(new TransitionPanels("game", "menu", true), new MiddlewareLocation());
                ProfileHandler.putData(USER_IN, Integer.parseInt(CURRENT_LEVEL), Integer.parseInt(LEVEL_END_TIME));
            } else {
                middlewareManager.addMiddleware(new TransitionPanels("game", "win", true), new MiddlewareLocation());
                ProfileHandler.putData(USER_IN, Integer.parseInt(CURRENT_LEVEL), Integer.parseInt(LEVEL_END_TIME));
            }

            this.remove();
        }

       
    }
}
