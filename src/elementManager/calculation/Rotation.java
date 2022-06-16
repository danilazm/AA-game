package elementManager.calculation;

import java.lang.Math;
import elementManager.coordinate.Position;
import elementManager.elements.*;

public class Rotation{
    private Integer rotationSpeed; //should be in degree
    private Position origin; //coordinates of center of the Big Ball 
    private Integer r; // radius of the Main Circle

    Rotation(Integer rotationSpeed , BigBall bigBall){
        this.rotationSpeed = rotationSpeed;
        this.origin = bigBall.getPosition();
        this.r = bigBall.getR();
    }

    Position rotate(SmallBall smallBall){
        Angle angle = new Angle(smallBall.getAngle());
        angle.add(this.rotationSpeed);
        double a = angle.getInRadian();
        Integer x = (int) (this.origin.getX() + (Math.cos(a) * r));
        Integer y = (int) (this.origin.getY() + (Math.sin(a) * r));
        return new Position(x,y);
    }
}