import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame {

    Panel panel;
    // Vytvoříme frame, který bude "obalovat" panel
    Frame(){

        panel = new Panel();
        this.add(panel);
        this.setTitle("Pong"); //Nejsem kreativní..
        this.setResizable(false);
        this.setBackground(Color.darkGray);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //pack použijeme, abychom nemuseli pevně vložit velikosti framu.
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);




    }

}
