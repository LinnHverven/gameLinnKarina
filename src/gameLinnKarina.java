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

        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(this);

        createButtons();

        add(panel, BorderLayout.CENTER);
        add(newGameButton, BorderLayout.SOUTH);




        setTitle("Game");
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
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

                if (gameNumbers.get(k).equals("")) { //regeln för hur den ska flyttas
                    emptyRows = i;
                    emptyCols = j;
                }
                k++;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) { //reset the game
        if (e.getSource() == newGameButton) {
            panel.removeAll();
            createButtons();
            panel.revalidate();
            panel.repaint();
        } else {
            for (int i = 0; i < 4; i++) { //Check if a gameButton is pressed
                for (int j = 0; j < 4; j++) {
                    if (e.getSource() == gameNumbersButton[i][j]) {
                        if (isNextTo(i, j, emptyRows, emptyCols)) { //Check if the pressed gamebutton is next to ther empty "spot".
                            swapButtonPlace(i, j, emptyRows, emptyCols);
                            emptyRows = i;
                            emptyCols = j;
                            break;
                        }
                    }
                }
            }

        }
    }

public boolean isNextTo(int row1, int col1, int row2, int col2) {
    return (Math.abs(row1 - row2) == 1 && col1 == col2) ||
            (Math.abs(col1 - col2) == 1 && row1 == row2);
}

public void swapButtonPlace(int row1, int col1, int row2, int col2){//Change place with two gamebuttons
        String temp = gameNumbersButton[row1][col1].getText();
       gameNumbersButton[row1][col1].setText(gameNumbersButton[row2][col2].getText());
       gameNumbersButton[row2][col2].setText(temp);
        }


    public static void main(String[] args) {
        new gameLinnKarina();
    }
}