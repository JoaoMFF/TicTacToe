package pt.ipbeja.po2.tictactoe.model;

import javafx.scene.control.Alert;
import pt.ipbeja.po2.tictactoe.gui.TicTacToeBoard;

import static java.lang.System.exit;

public class TicTacToeGame {
    private Place[][] boardData;
    public static final int SIZE = 3;
    private int turnCounter = 0;

    public int player;

    public boolean isFree(int line, int col) {
        if (boardData[line][col] == Place.EMPTY){
            return true;
        }
        else {
            return false;
        }
    }

    public TicTacToeGame() {
        boardData = new Place[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.boardData[j][i] = Place.EMPTY;
            }
        }
    }

    public void placeClicked(int line, int col) {

        if( isFree(line, col) == true)
        {
            turnCounter++;
            if(turnCounter % 2 == 1) {
                this.boardData[line][col] = Place.X;
            }
            else{
                this.boardData[line][col] = Place.O;
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Jogada Invalida!");
            alert.showAndWait();
        }
    }

    public int checkBoard(){

        // Check Lines
        if(this.boardData[0][0] == Place.X && this.boardData[1][0] == Place.X && this.boardData[2][0] == Place.X ) {
            this.player = 1;
            return player;
        }

        if(this.boardData[0][0] == Place.O && this.boardData[1][0] == Place.O && this.boardData[2][0] == Place.O ) {
            this.player = 2;
            return player;
        }

        if(this.boardData[0][1] == Place.X && this.boardData[1][1] == Place.X && this.boardData[2][1] == Place.X ) {
            this.player = 1;
            return player;
        }

        if(this.boardData[0][1] == Place.O && this.boardData[1][1] == Place.O && this.boardData[2][1] == Place.O ) {
            this.player = 2;
            return player;
        }

        if(this.boardData[0][2] == Place.X && this.boardData[1][2] == Place.X && this.boardData[2][2] == Place.X ) {
            this.player = 1;
            return player;
        }

        if(this.boardData[0][2] == Place.O && this.boardData[1][2] == Place.O && this.boardData[2][2] == Place.O ) {
            this.player = 2;
            return player;
        }

        //Check Colunas

        if(this.boardData[0][0] == Place.X && this.boardData[0][1] == Place.X && this.boardData[0][2] == Place.X ) {
            this.player = 1;
            return player;
        }

        if(this.boardData[0][0] == Place.O && this.boardData[0][1] == Place.O && this.boardData[0][2] == Place.O ) {
            this.player = 2;
            return player;
        }

        if(this.boardData[1][0] == Place.X && this.boardData[1][1] == Place.X && this.boardData[1][2] == Place.X ) {
            this.player = 1;
            return player;
        }

        if(this.boardData[1][0] == Place.O && this.boardData[1][1] == Place.O && this.boardData[1][2] == Place.O ) {
            this.player = 2;
            return player;
        }

        if(this.boardData[2][0] == Place.X && this.boardData[2][1] == Place.X && this.boardData[2][2] == Place.X ) {
            this.player = 1;
            return player;
        }

        if(this.boardData[2][0] == Place.O && this.boardData[2][1] == Place.O && this.boardData[2][2] == Place.O ) {
            this.player = 2;
            return player;
        }

        //Check diagonal

        if(this.boardData[0][0] == Place.X && this.boardData[1][1] == Place.X && this.boardData[2][2] == Place.X ) {
            this.player = 1;
            return player;
        }

        if(this.boardData[0][0] == Place.O && this.boardData[1][1] == Place.O && this.boardData[2][2] == Place.O ) {
            this.player = 2;
            return player;
        }

        if(this.boardData[2][0] == Place.X && this.boardData[1][1] == Place.X && this.boardData[0][2] == Place.X ) {
            this.player = 1;
            return player;
        }

        if(this.boardData[2][0] == Place.O && this.boardData[1][1] == Place.O && this.boardData[0][2] == Place.O ) {
            this.player = 2;
            return player;
        }

        return player;
    }

    public boolean checkDraw(){
        if(turnCounter == 9) {
            return true;
        }
        return false;
    }

    public Place getValue(int col, int row){
        return this.boardData[col][row];
    }
}
