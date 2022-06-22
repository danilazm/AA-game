package middlewareManager.middlewares;

import config.Config;
import elementManager.ElementManager;
import elementManager.elements.SmallBall;
import frameManager.FrameManager;
import utils.calculations.Angle;;

//This middleware create a SmallBall instance.
//Then it will add it to "rotatingSmallBalls" Group.
//The constructor accepts String so it can work around "LoadGame" shortcomings.
//The us of "n" variable is to ensure that we won't use duplicate id.

public class DrawSmallBall extends Middleware{
    static int n = 0;   //to keep track how many times this middleware has been called.
    final int id;       // to store n before n changes as a result of this middleware being called again. 
    Angle angle;
    ElementManager elementManager = Config.getElementManager();
    FrameManager frameManager = Config.getFrameManager();

    DrawSmallBall(String angle){
        super("AddSmallBall"+n);
        id = n;
        n++;
        this.angle = new Angle(Double.parseDouble(angle));
    }

    @Override
    public void init(){
        //creatin SmallBall instance
        SmallBall smallBall = new SmallBall(frameManager.getAPanel("game"),angle);
        smallBall.setNumber(id);
        //adding smallBall to elementManager
        elementManager.addElement("smallBall"+id, smallBall);
        //adding SmallBall to "rotatingSmallBalls" Group.
        elementManager.joinGroup("rotatingSmallBalls", "smallBall"+id);
        this.remove();
    }
    
}
