package elementManager.elements;

import javax.swing.*;
import java.awt.*;
import elementManager.coordinate.AaPosition;
import config.Config;
import utils.calculations.*;

public class SmallBall extends Element {
    int number;
    boolean numberVisible=false;
    Color color;
    AaPosition position;
    Angle angle;
    boolean visible = true;
    public SmallBall(JPanel panel) {
        super(panel);
        this.angle = new Angle();
        this.color = Color.BLACK;
    }

    public SmallBall(JPanel panel, Angle angle) {
        super(panel);
        this.angle = angle;
    }

    public void setPosition(AaPosition position){
        this.position = position;
    }

    public void setNumber(int number){
        this.number = number;
    }

    public void setColor(Color c){
        this.color = c;
    }

    public void setVisible(boolean b){
        this.visible = b;
    }

    public boolean getVisible(){
        return this.visible;
    }

    public String getNumber(){
        return Integer.toString(this.number);
    }

    public AaPosition getPosition(){
        return this.position ;
    }


    public Angle getAngle() {
        return this.angle;
    }

    public void setAngle(Angle angle) {
        this.angle = angle;
    }

    public void setNumberVisible(boolean numberVisible){
        this.numberVisible = numberVisible;
    }

    @Override
    public void paintSelf(Graphics g){
        if(visible){
            Graphics2D g2D = (Graphics2D)g;      //Graphics2D is more feature-rich than Graphics
            if(numberVisible){                   //checking whether we want number inside the ball
                g2D.setPaint(this.color);             
                g2D.fillOval(
                    this.position.getX() - Config.getSmallBallSize().getWidth() / 2,
                    this.position.getY() - Config.getSmallBallSize().getHeight() / 2,
                    Config.getSmallBallSize().getWidth(),
                    Config.getSmallBallSize().getHeight());      //Drawing the circle with radius 15

                g2D.setPaint(Color.BLACK);
                int stringWidth = (int)g.getFontMetrics().getStringBounds(getNumber(), g).getWidth();
                int stringHeight = (int)g.getFontMetrics().getStringBounds(getNumber(), g).getHeight();
                g2D.drawString(
                    this.getNumber(), 
                    this.position.getX() + ((Config.getSmallBallSize().getWidth() - (stringWidth))/2) - Config.getSmallCircleRadios() , 
                    // the above formula alines the string to center horizentally
                    this.position.getY() + ((-Config.getSmallBallSize().getHeight() + stringHeight)/2) + Config.getSmallCircleRadios() - 2
                    //the above formula alines th string to center vertically (-2 makes it look better)
                );      //Drawing the number inside the circle. 
            }
            else{
                g2D.setPaint(this.color);
                g2D.fillOval(this.position.getX() - Config.getSmallBallSize().getWidth()/2, this.position.getY() - Config.getSmallBallSize().getHeight()/2,
                Config.getSmallBallSize().getWidth(), Config.getSmallBallSize().getHeight());
            }
        }
    }    
}
