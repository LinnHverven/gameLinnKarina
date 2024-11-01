import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameGrid {
    JPanel panel;
    JButton[][] gameNumbersButton = new JButton[4][4];
    JButton newGameButton; // Button to start i new game
    int emptyRows, emptyCols; //empty button


    public GameGrid(ActionListener listener) {
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
            //Collections.shuffle(gameNumbers); // Mix the numbers


            int k = 0;  // Creating a button for each number
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    gameNumbersButton[i][j] = new JButton(gameNumbers.get(k));
                    gameNumbersButton[i][j].addActionListener(listener);
                    panel.add(gameNumbersButton[i][j]);


                    if (gameNumbers.get(k).equals("0")) { //regeln för hur den ska flyttas
                        emptyRows = i;
                        emptyCols = j;
                        gameNumbersButton[i][j].setText("");
                        gameNumbersButton[i][j].setOpaque(false);


                    }
                    k++;
                }
            }


        }


    public boolean createWin(){
        List<String> win1 = Arrays.asList("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","0");
       List<String> win2 = Arrays.asList("0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15");


        List<String> currentGame = new ArrayList<>();
        for(int i = 0; i<4; i++){
            for (int j = 0; j < 4; j++){
                String value = gameNumbersButton[i][j].getText();
                currentGame.add(value.isEmpty() ? "0" : value);
            }
        }
        return currentGame.equals(win1) || currentGame.equals(win2);


    }

    public JPanel getPanel(){
        return panel;
    }

}

