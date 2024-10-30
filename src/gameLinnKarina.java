import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class gameLinnKarina extends JFrame implements ActionListener {

    JPanel panel;
    JButton[][] gameNumbersButton = new JButton[4][4];
    JButton newGameButton; // Button to start i new game
    int emptyRows, emptyCols; //empty button

    public gameLinnKarina() {

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));
        createButtons();

        add(panel, BorderLayout.CENTER);
        add(newGameButton, BorderLayout.SOUTH);

        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(this);

        setVisible(true);
        setTitle("Game");
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void createButtons() { // Creates buttons in random order.
        ArrayList<String> gameNumbers = new ArrayList<>();
        for (int i = 1; i < 4 * 4; i++) {
            gameNumbers.add(String.valueOf(i)); // Adding number 1-15
        }
        gameNumbers.add(""); // Adding the empty "number"
        Collections.shuffle(gameNumbers); // Mix the numbers


        int k = 0;  // Creating a button for each number
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                gameNumbersButton[i][j] = new JButton(gameNumbers.get(k));
                gameNumbersButton[i][j].addActionListener(this);
                panel.add(gameNumbersButton[i][j]);

                if (gameNumbers.get(k).equals("")) {
                    emptyRows = i;
                    emptyCols = j;
                }
                k++;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGameButton) {
            panel.removeAll();
            createButtons();
            panel.revalidate();
            panel.repaint();
        }

    }

    public static void main(String[] args) {
        new gameLinnKarina();
    }
}