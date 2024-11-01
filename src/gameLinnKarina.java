import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class gameLinnKarina extends JFrame implements ActionListener {
    GameGrid gameGrid;
    JButton newGameButton;

    public gameLinnKarina() {
        gameGrid = new GameGrid(this); //initializes gameGrid, this is listening for button

        newGameButton = new JButton("New game"); //button for resetting the game
        newGameButton.setPreferredSize(new Dimension(80, 50));
        newGameButton.setFont(new Font("Calibri", Font.BOLD, 25));
        newGameButton.addActionListener(this);

        add(gameGrid.getPanel(), BorderLayout.CENTER); //the grid of buttons in the center
        add(newGameButton, BorderLayout.SOUTH); //"new game" button below the numbers.

        setTitle("Game");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) { //method being called when a button is being clicked
        if (e.getSource() == newGameButton) { //reset the game
            gameGrid.panel.removeAll();
            gameGrid.createButtons(this);
            gameGrid.panel.revalidate();
            gameGrid.panel.repaint();
        } else {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) { //for-loop checks the grid
                    if (e.getSource() == gameGrid.gameNumbersButton[i][j]){ //if the button is close to the empty space:
                        if((Math.abs(gameGrid.emptyRows - i) == 1 && gameGrid.emptyCols == j || (Math.abs(gameGrid.emptyCols - j) == 1 && gameGrid.emptyRows == i))) { //if a button is clicked next to the empty space,
                            gameGrid.gameNumbersButton[gameGrid.emptyRows][gameGrid.emptyCols].setText(gameGrid.gameNumbersButton[i][j].getText()); //it changes the buttons text with the empty button and updates the position
                            gameGrid.gameNumbersButton[i][j].setText("");


                            gameGrid.emptyRows = i;
                            gameGrid.emptyCols = j;


                            if(gameGrid.createWin()){ //calls this if the numbers are in configuration 0-15 or 15-0.
                                JOptionPane.showMessageDialog(null, "You win! Congratulations!");
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