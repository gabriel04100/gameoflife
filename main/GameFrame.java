package gameoflife.main;
import javax.swing.JFrame;

public class GameFrame extends JFrame {


    GameFrame(){

        this.add(new GamePanel());
        this.setTitle("Game Of Life");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);


    }
    
}
