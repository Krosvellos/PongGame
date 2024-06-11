import java.awt.*;
import java.awt.event.*;


public class Paddle extends Rectangle {


    int id;
    int yVelocityPad;
    int speed = 7;

    Paddle(int x, int y, int WIDTH_PADDLE, int HEIGHT_PADDLE, int id) {
        super(x, y, WIDTH_PADDLE, HEIGHT_PADDLE);
        this.id = id;
    }

    public void keyPressed(KeyEvent e) {
        switch(id){
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W){
                    setYDirection(-speed);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_S){
                    setYDirection(speed);
                    move();
                }
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP){
                    setYDirection(-speed);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    setYDirection(speed);
                    move();
                }
                break;
        }
    }

    public void keyReleased(KeyEvent e) {

        switch(id){
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W){
                    setYDirection(0);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_S){
                    setYDirection(0);
                    move();
                }
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP){
                    setYDirection(0);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    setYDirection(0);
                    move();
                }
                break;
        }
    }

    public void setYDirection(int yDirection) {
        yVelocityPad = yDirection;
    }

    public void move() {
        y=y+yVelocityPad;
    }

    public void draw(Graphics g) {
        if (id == 1) {
            g.setColor(Color.cyan);
        }
        else
            g.setColor(Color.pink);
        g.fillRect(x,y,width,height);

        }
    }

