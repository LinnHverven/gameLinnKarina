import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameGrid {
    JPanel panel; //grid of buttons
    JButton[][] gameNumbersButton = new JButton[4][4]; //2D-array where the buttons are
    int emptyRows, emptyCols; //empty button


    public GameGrid(ActionListener listener) {//constructor calls createButtons(listener) method
        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));
        createButtons(listener);
    }

    public void createButtons(ActionListener listener) { // Creates buttons in random order.
            ArrayList<String> gameNumbers = new ArrayList<>();
            for (int i = 1; i < 4 * 4; i++) {
                gameNumbers.add(String.valueOf(i)); // Adding number 1-15
            }
            gameNumbers.add("0"); // Adding the empty "number"
            Collections.shuffle(gameNumbers); // Mix the numbers

            int k = 0;  // Creating a button for each number
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    gameNumbersButton[i][j] = new JButton(gameNumbers.get(k));
                    gameNumbersButton[i][j].setFont(new Font("Calibri", Font.BOLD, 25));

                    gameNumbersButton[i][j].setBackground(Color.WHITE);
                    gameNumbersButton[i][j].setForeground(Color.BLUE);

                    gameNumbersButton[i][j].addActionListener(listener);
                    panel.add(gameNumbersButton[i][j]);

                    if (gameNumbers.get(k).equals("0")) { //the rule for how the empty button is being moves
                        emptyRows = i;
                        emptyCols = j;
                        gameNumbersButton[i][j].setText("");
                        gameNumbersButton[i][j].setBackground(Color.WHITE);
                    }
                    k++;
                }
            }
        }

    public boolean createWin(){//method checks if the buttons are in the following two orders
        List<String> win1 = Arrays.asList("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","0");
       List<String> win2 = Arrays.asList("0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15");


        List<String> currentGame = new ArrayList<>(); //collects buttons and thir numbers into the list currentGame
        for(int i = 0; i<4; i++){
            for (int j = 0; j < 4; j++){
                String value = gameNumbersButton[i][j].getText();
                currentGame.add(value.isEmpty() ? "0" : value); //replaces empty button with "0"
            }
        }
        return currentGame.equals(win1) || currentGame.equals(win2); //checks if currentGame matches the number order
    }

    public JPanel getPanel(){ //panel object is added to main game
        return panel;
    }
}