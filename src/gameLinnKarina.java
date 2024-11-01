import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class gameLinnKarina extends JFrame implements ActionListener {

    JPanel panel;
    JButton[][] gameNumbersButton = new JButton[4][4];
    JButton newGameButton; // Button to start i new game
    int emptyRows, emptyCols; //empty button

    public gameLinnKarina() {

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        newGameButton = new JButton("New Game");
        newGameButton.setPreferredSize(new Dimension(80, 50));
        newGameButton.setFont(new Font("Calibri", Font.BOLD, 25));
        newGameButton.addActionListener(this);

        createButtons();

        add(panel, BorderLayout.CENTER);
        add(newGameButton, BorderLayout.SOUTH);

        setTitle("Game");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void createButtons() { // Creates buttons in random order.
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

                gameNumbersButton[i][j].addActionListener(this);
                panel.add(gameNumbersButton[i][j]);

                if (gameNumbers.get(k).equals("0")) { //regeln för hur den ska flyttas
                    emptyRows = i;
                    emptyCols = j;
                    gameNumbersButton[i][j].setText("");
                    gameNumbersButton[i][j].setBackground(Color.WHITE);
                }
                k++;
            }
        }

    }

    public boolean createWin() {
        List<String> win1 = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "0");
        List<String> win2 = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15");

        List<String> currentGame = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                String value = gameNumbersButton[i][j].getText();
                currentGame.add(value.isEmpty() ? "0" : value);
            }
        }
        return currentGame.equals(win1) || currentGame.equals(win2);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGameButton) { //reset the game
            panel.removeAll();
            createButtons();
            panel.revalidate();
            panel.repaint();
        } else {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (e.getSource() == gameNumbersButton[i][j]) {
                        if ((Math.abs(emptyRows - i) == 1 && emptyCols == j || (Math.abs(emptyCols - j) == 1 && emptyRows == i))) {
                            gameNumbersButton[emptyRows][emptyCols].setText(gameNumbersButton[i][j].getText());
                            gameNumbersButton[i][j].setText("");

                            emptyRows = i;
                            emptyCols = j;

                            if (createWin()) {
                                JOptionPane.showMessageDialog(null, "You win!");
                            }
                            return;
                        }
                    }
                }

            }
        }
    }

    public static void main(String[] args) {
        new gameLinnKarina();
    }
}