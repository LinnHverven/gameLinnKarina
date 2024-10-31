import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class gameLinnKarina extends JFrame {

    JPanel panel;
    JButton[][] gameNumbersButton = new JButton[4][4];
    JButton newGameButton; // Button to start i new game
    int emptyRows, emptyCols; //empty button

    public gameLinnKarina() {

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        newGameButton = new JButton("New Game");
        buttonsAction Buttonspressed = new buttonsAction(this);
        newGameButton.addActionListener(Buttonspressed);

        createButtons(Buttonspressed);

        add(panel, BorderLayout.CENTER);
        add(newGameButton, BorderLayout.SOUTH);

        setTitle("Game");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void createButtons(buttonsAction Buttonspressed) { // Creates buttons in random order.
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
                gameNumbersButton[i][j].setFont(new Font("Calibri", Font.BOLD, 15));

                gameNumbersButton[i][j].setBackground(Color.WHITE);
                gameNumbersButton[i][j].setForeground(Color.BLACK);

                gameNumbersButton[i][j].addActionListener(Buttonspressed);
                panel.add(gameNumbersButton[i][j]);

                if (gameNumbers.get(k).equals("")) { // Rule for how the gamebutton is moved.
                    emptyRows = i;
                    emptyCols = j;
                }
                k++;
            }
        }
    }

    public boolean isNextTo(int row1, int col1, int row2, int col2) { // Checks if two buttons are next to eachother.
        return (Math.abs(row1 - row2) == 1 && col1 == col2) || // Up and down
                (Math.abs(col1 - col2) == 1 && row1 == row2); // next to
    }

    public void swapButtonPlace(int row1, int col1, int row2, int col2) { // Change place with two gamebuttons.
        String temp = gameNumbersButton[row1][col1].getText();
        gameNumbersButton[row1][col1].setText(gameNumbersButton[row2][col2].getText());
        gameNumbersButton[row2][col2].setText(temp);
    }

    public void resetGame(){
        panel.removeAll();
        createButtons(new buttonsAction(this));
        panel.revalidate();
        panel. repaint();
    }

    public static void main(String[] args) {
        new gameLinnKarina();
    }
}