import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Panel extends JPanel implements Runnable {

    // Velikost panelu (Height_Game by mělé odpovídat reálnému pong stolu - proto ten násobek :D)

    static final int WIDTH_GAME = 1000;
    static final int HEIGHT_GAME = (int)(WIDTH_GAME * (0.55555));

    static final Dimension SCREEN_SIZE = new Dimension(WIDTH_GAME, HEIGHT_GAME);


    //Hodnoty pro ball a paddle kontruktory ---
    static final int BALL_DIAM = 20;

    static final int WIDTH_PADDLE = 25;
    static final int HEIGHT_PADDLE = 100;
    // ---

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
        this.addKeyListener(new ActListener());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void createBall(){
     //   random = new Random();
       ball = new Ball((WIDTH_GAME/2)-(BALL_DIAM/2),(HEIGHT_GAME/2)-(BALL_DIAM/2),BALL_DIAM,BALL_DIAM);
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
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }

    public void move(){
        paddle1.move();
        paddle2.move();
        ball.move();
    }

    public void checkCollision(){

        // Bounce the balls off window edges (Míček nemůže překročit na y-axis vrchní a spodní hranici.)

        if (ball.y <= 0){
            ball.setYDirection(-ball.yVelocityBall);
        }
        if (ball.y >= HEIGHT_GAME-BALL_DIAM){
            ball.setYDirection(-ball.yVelocityBall);
        }

        // Bounces ball off paddles - (Při každém dotyku se znásobí rychlost)
        // intersect je již v awt (díky bohu) - tedy rectangle ball intersectuje s rectangle paddle

        if(ball.intersects(paddle1)) {
            ball.xVelocityBall = Math.abs(ball.xVelocityBall);
            ball.xVelocityBall++;
            if (ball.yVelocityBall>0)
                ball.yVelocityBall++;
            else{
                ball.yVelocityBall--;
            }
            ball.setXDirection(ball.xVelocityBall);
            ball.setYDirection(ball.yVelocityBall);
        }
        if(ball.intersects(paddle2)) {
            ball.xVelocityBall = Math.abs(ball.xVelocityBall);
            ball.xVelocityBall++;
            if (ball.yVelocityBall>0)
                ball.yVelocityBall++;
            else{
                ball.yVelocityBall--;
            }
            ball.setXDirection(-ball.xVelocityBall);
            ball.setYDirection(-ball.yVelocityBall);
        }

        // Stops paddles at window edges. - (Bez následujících if statements by míček proletěl skrz paddles.)
        if(paddle1.y<=0){
            paddle1.y=0;
        }
        if(paddle1.y>= (HEIGHT_GAME-HEIGHT_PADDLE)){
            paddle1.y= HEIGHT_GAME-HEIGHT_PADDLE;
        }
        if(paddle2.y<=0){
            paddle2.y=0;
        }
        if(paddle2.y>= (HEIGHT_GAME-HEIGHT_PADDLE)){
            paddle2.y= HEIGHT_GAME-HEIGHT_PADDLE;
        }

        //Give players a point and create new paddles with ball - (Při každém připočtení skóre se vytvoří nové
        // paddles a míček - tedy jede se dál)
        if(ball.x<=0){
            score.player2Score++;
            createPaddles();
            createBall();
            System.out.println("player2: "+score.player2Score);
        }
        if(ball.x>= WIDTH_GAME-BALL_DIAM){
            score.player1Score++;
            createPaddles();
            createBall();
            System.out.println("player1: "+score.player1Score);
        }
    }

    // 60 fps - převzato z minecraftu + tutoriálů (v rámci možností)
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

    public class ActListener extends KeyAdapter{
        public void keyPressed(KeyEvent e){

            paddle1.keyPressed(e);
            paddle2.keyPressed(e);

        }
        public void keyReleased(KeyEvent e){
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);

        }
    }

}
