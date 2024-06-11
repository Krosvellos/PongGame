import java.awt.*;


public class Score extends Rectangle {

    static int WIDTH_GAME;
    static int HEIGHT_GAME;

    int player1Score;
    int player2Score;

    Score(int WIDTH_GAME,int HEIGHT_GAME){
        Score.WIDTH_GAME = WIDTH_GAME;
        Score.HEIGHT_GAME = HEIGHT_GAME;
}

public void draw(Graphics g){
        g.setColor(Color.ORANGE);
        g.setFont(new Font("IMPACT",Font.PLAIN, 55));
        g.drawString(String.valueOf(player1Score/10)+String.valueOf(player1Score%10), (WIDTH_GAME/2)-85, 52);
        g.drawString(String.valueOf(player2Score/10)+String.valueOf(player2Score%10), (WIDTH_GAME/2)+28, 52);
        g.drawString("JAVA| |PONG",(WIDTH_GAME/2)-122,550);
        g.drawLine(WIDTH_GAME/2,0,WIDTH_GAME/2,HEIGHT_GAME);
}

}
