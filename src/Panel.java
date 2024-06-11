import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Panel extends JPanel implements Runnable {


    static final int WIDTH_GAME = 1000;
    static final int HEIGHT_GAME = (int)(WIDTH_GAME * (0.55555));

    static final Dimension SCREEN_SIZE = new Dimension(WIDTH_GAME, HEIGHT_GAME);

    static final int BALL_DIAM = 20;

    static final int WIDTH_PADDLE = 25;
    static final int HEIGHT_PADDLE = 25;

    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;

    Panel(){
        createPaddles();
        createBall();
        score = new Score(WIDTH_GAME,HEIGHT_GAME);
        this.setFocusable(true);
        this.addKeyListener(new ActionListener());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void createBall(){

    }
    public void createPaddles(){

        paddle1 = new Paddle(0,(HEIGHT_GAME/2)-(HEIGHT_PADDLE/2),WIDTH_PADDLE,HEIGHT_PADDLE,1);
        paddle2 = new Paddle(WIDTH_GAME-WIDTH_PADDLE,(HEIGHT_GAME/2)-(HEIGHT_PADDLE/2),WIDTH_PADDLE,HEIGHT_PADDLE,2);

    }
    public void paint(Graphics g){
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);


    }
    public void draw(Graphics g){

    }

    public void move(){

    }

    public void checkCollision(){

    }
    public void run(){
        long lastTime = System.nanoTime();
        double ticksAmount = 60.0;
        double nanosec = 1000000000 / ticksAmount;
        double delta = 0;
        while(true){
            long now = System.nanoTime();
            delta += (now -lastTime)/nanosec;
            lastTime = now;
            if(delta >=1){
                move();
                checkCollision();
                repaint();
                delta--;

            }
        }
    }

    public class ActionListener extends KeyAdapter{
        public void keyPressed(KeyEvent e){

        }
        public void keyReleased(KeyEvent e){

        }
    }

}
