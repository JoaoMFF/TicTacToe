package pt.ipbeja.po2.tictactoe.gui;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import pt.ipbeja.po2.tictactoe.model.Place;
import pt.ipbeja.po2.tictactoe.model.TicTacToeGame;

import static java.lang.System.exit;


public class TicTacToeBoard extends GridPane {

    private TicTacToeGame gameModel = new TicTacToeGame();

    public TicTacToeBoard() {

        this.createBoard();
    }

    /**
     * Creates the game board with a grid of TicTacToeButtons
     */
    private void createBoard() {

        TicTacToeButtonHandler handler = new TicTacToeButtonHandler();

        for (int i = 0; i < this.gameModel.SIZE; i++) {
            for (int j = 0; j < this.gameModel.SIZE; j++) {
                TicTacToeButton btn = new TicTacToeButton(j, i);
                btn.setOnAction(handler);
                this.add(btn, j, i);
            }
        }
    }

    /**
     * Click handler for the TicTacToeButtons
     */
    class TicTacToeButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {

            TicTacToeButton button = (TicTacToeButton) event.getSource();
            //System.out.println(button.getLine());
            gameModel.placeClicked(button.getLine() ,button.getCol());

            if(gameModel.getValue(button.getLine(), button.getCol()) == Place.X)
            {
                button.setTic();
            }

            if(gameModel.getValue(button.getLine(), button.getCol()) == Place.O)
            {
                button.setTac();
            }

            gameModel.checkDraw();
            gameModel.checkBoard();

            if(gameModel.checkDraw()){
                draw();
            }
            else if (gameModel.checkBoard() == 1 || gameModel.checkBoard() == 2)
            {
                playerWins(gameModel.checkBoard());
            }
        }
    }

    void draw(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Empate!");
        alert.showAndWait();
        exit(0);
    }


    void playerWins(int player){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Jogador " + player + " Ganhou!");
        alert.showAndWait();
        exit(0);
    }
}