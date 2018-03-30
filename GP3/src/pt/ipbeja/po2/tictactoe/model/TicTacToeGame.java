package pt.ipbeja.po2.tictactoe.model;

import javafx.scene.control.Alert;
import pt.ipbeja.po2.tictactoe.gui.TicTacToeBoard;

public class TicTacToeGame {
    private Place[][] boardData;
    public static final int SIZE = 3;
    private int turnCounter;

    private final TicTacToeBoard VIEW;

    public int player;

    public boolean isFree(int line, int col) {
        if (boardData[line][col] == Place.EMPTY){
            return true;
        }
        else {
            return false;
        }
    }

    public TicTacToeGame(TicTacToeBoard view) {

        this.VIEW = view;
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

        this.checkBoardState(line, col);
    }

    private void checkBoardState(int line, int col) {
        if (inWinnableTurn(this.turnCounter)) {
            // Só vale a pena começar a verificar condições de vitória a partir do número mínimo de jogadas para algum dos jogadores vencer
            // Ex. para um tabuleiro 3x3, é necessário haver pelo menos 5 jogadas para vencer

            if (isWinPosition(line, col)) {
                this.VIEW.playerWins((this.turnCounter - 1) % 2); // 0 ou 1
            } else if (isDrawPosition()) {
                this.VIEW.draw();
            }
        }
    }

    private boolean inWinnableTurn(int turn)
    {
        return turn >= (this.SIZE * 2 - 1);
    }

    private boolean isWinPosition(int line, int col) {
        // Verificar se a jogada resultou numa sequência vencedora
        // Os métodos são invocados sequencialmente e quando um deles devolve 'true' os seguintes já não são invocados
        return (hasWinningLine(line) ||
                hasWinningColumn(col) ||
                hasWinningMainDiagonal(line, col) ||
                hasWinnningAntiDiagonal(line, col));
        // Por exemplo:: Se hasWinningLine -> false ...continua... se hasWinningColumn -> true,
        // termina aqui e devolve true.
    }

    private boolean isDrawPosition() {
        return turnCounter == this.SIZE * this.SIZE;
    }

    private boolean hasWinningLine(int line) {
        // Verificar se a linha onde a jogada ocorreu têm uma sequência vencedora
        for (int i = 0; i < SIZE - 1; i++) {
            if (boardData[line][i] != boardData[line][i + 1]) {
                return false; // Se os valores diferem, podemos terminar aqui e devolver false
            }
        }
        return true; // Só chegamos a este ponto se de facto todos os valores forem iguais
    }


    private boolean hasWinningColumn(int col) {
        // Verificar se a coluna onde a jogada ocorreu têm uma sequência vencedora
        for (int i = 0; i < SIZE - 1; i++) {
            if (boardData[i][col] != boardData[i + 1][col]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check diagonal for winning condition
     * @param line
     * @param col
     * @return true if a winning condition was found
     */
    private boolean hasWinningMainDiagonal(int line, int col) {
        // Se jogada ocorreu na diagonal, verificar se a linha onde a jogada ocorreu têm uma sequência vencedora
        if (this.inMainDiagonal(line, col)) {
            for (int i = 0; i < SIZE - 1; i++) {
                if (boardData[i][i] != boardData[i + 1][i + 1]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Check antidiagonal for winning condition
     * @param line
     * @param col
     * @return true if a winning condition was found
     */
    private boolean hasWinnningAntiDiagonal(int line, int col) {
        // Se jogada ocorreu na antidiagonal, verificar se a linha onde a jogada ocorreu têm uma sequência vencedora
        if (this.inAntiDiagonal(line, col)) { // Se a soma dos valores da linha e da coluna for igual ao tamanho da grelha -1, estamos na antidiagonal
            for (int i = 0; i < SIZE - 1; i++) {
                int j = SIZE - i - 1;

                if (boardData[i][j] != boardData[i + 1][j - 1]) {
                    return false;
                }

            }
            return true;
        }
        return false;
    }

    /**
     * Se os valores da linha e coluna forem os mesmos, sabemos estar numa diagonal
     * @param line line value
     * @param col column value
     * @return true if (line, col) is in main diagonal false otherwise
     */
    private boolean inMainDiagonal(int line, int col)
    {
        return line == col;
    }

    /**
     * Se os valores da linha e coluna forem os mesmos, sabemos estar numa diagonal
     * @param line line value
     * @param col column value
     * @return true if (line, col) is in anti diagonal false otherwise
     */
    private boolean inAntiDiagonal(int line, int col)
    {
        return (line + col) == (SIZE - 1);
    }

    public Place getValue(int col, int row){
        return this.boardData[col][row];
    }
}
