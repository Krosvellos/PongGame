import java.awt.*;

import java.util.*;


public class Ball extends Rectangle {


    Random random;
    int xVelocityBall;
    int yVelocityBall;
    int initSpeed = 2;

    Ball(int x, int y, int width, int height){
        super(x,y,width,height);
        random = new Random();

        // ball direction at spawn
        int randXDirection = random.nextInt(2);
        if (randXDirection == 0) {
            randXDirection--;
        }
            setXDirection(randXDirection*initSpeed);

        int randYDirection = random.nextInt(2);
        if (randYDirection == 0){
            randYDirection--;
        }
            setYDirection(randYDirection);


    }

    public void setXDirection(int randXDirection){
         xVelocityBall = randXDirection;
    }
    public void setYDirection(int randYDirection){
        yVelocityBall = randYDirection;
    }

    public void move(){
        x += xVelocityBall;
        y += yVelocityBall;
    }

    public void draw(Graphics g){
        g.setColor(Color.white);
        g.fillOval(x,y,height,width);
    }



}
