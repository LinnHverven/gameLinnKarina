
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class gameLinnKarina extends JFrame implements ActionListener {
    GameGrid gameGrid;
    JButton newGameButton;

    public gameLinnKarina() {
    gameGrid = new GameGrid(this);

    newGameButton = new JButton("New game");
    newGameButton.addActionListener(this);

    add(gameGrid.getPanel(), BorderLayout.CENTER);
    add(newGameButton, BorderLayout.SOUTH);







        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(this);

        setTitle("Game");
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGameButton) { //reset the game
            gameGrid.panel.removeAll();
            gameGrid.createButtons(this);
            gameGrid.panel.revalidate();
           gameGrid.panel.repaint();
        } else {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (e.getSource() == gameGrid.gameNumbersButton[i][j]){
                        if((Math.abs(gameGrid.emptyRows - i) == 1 && gameGrid.emptyCols == j || (Math.abs(gameGrid.emptyCols - j) == 1 && gameGrid.emptyRows == i))) {
                            gameGrid.gameNumbersButton[gameGrid.emptyRows][gameGrid.emptyCols].setText(gameGrid.gameNumbersButton[i][j].getText());
                            gameGrid.gameNumbersButton[i][j].setText("");


                            gameGrid.emptyRows = i;
                            gameGrid.emptyCols = j;


                            if(gameGrid.createWin()){
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


