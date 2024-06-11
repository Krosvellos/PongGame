import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame {

    Panel panel;

    Frame(){

        panel = new Panel();
        this.add(panel);
        this.setTitle("Pong");
        this.setResizable(false);
        this.setBackground(Color.darkGray);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);




    }

}
