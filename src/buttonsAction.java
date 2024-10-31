import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class buttonsAction implements ActionListener {

    public gameLinnKarina game;
    public buttonsAction(gameLinnKarina game) {
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == game.newGameButton) { //reset the game
            game.resetGame();
        } else {
            for (int i = 0; i < 4; i++) { //Check if a gameButton is pressed
                for (int j = 0; j < 4; j++) {
                    if (e.getSource() == game.gameNumbersButton[i][j]) {
                        if (game.isNextTo(i, j, game.emptyRows, game.emptyCols)) { //Check if the pressed gamebutton is next to ther empty "spot".
                            game.swapButtonPlace(i, j, game.emptyRows, game.emptyCols);
                            game.emptyRows = i;
                            game.emptyCols = j;
                            break;
                        }
                    }
                }
            }
        }
    }
}