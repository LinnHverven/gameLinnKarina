import javax.swing.*;
import java.awt.event.ActionListener;

public class gameLinnKarina extends JFrame implements ActionListener {
    JButton button = new JButton();
    JPanel panel = new JPanel();

    //Kommentera ut ett spel som vinner för att visa att det går. Kör programmet två gånger.
    public gameLinnKarina() {
        setVisible(true);
        setTitle("Game");
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
//Testar
    //Testar igen
    // Karina testar testBranch
    public static void main(String[] args) {
        new gameLinnKarina();
    }
}
