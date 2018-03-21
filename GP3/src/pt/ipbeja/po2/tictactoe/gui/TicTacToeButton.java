package pt.ipbeja.po2.tictactoe.gui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pt.ipbeja.po2.tictactoe.model.Place;
import pt.ipbeja.po2.tictactoe.model.TicTacToeGame;

public class TicTacToeButton extends Button {

    private int line;
    private int col;

    private static final Image PLAY_EMPTY = new Image("/resources/noplayer.png");
    private static final Image PLAY_X = new Image("/resources/player1.png");
    private static final Image PLAY_O = new Image("/resources/player2.png");

    private ImageView imageView;

    public TicTacToeButton(int line, int col) {
        this.line = line;
        this.col = col;

        this.imageView = new ImageView(PLAY_EMPTY);
        this.setGraphic(imageView);
    }

    public int getLine()
    {
        return line;
    }

    /**
     * Gets the Column of the button
     * @return
     * 	Returns Column
     */
    public int getCol()
    {
        return col;
    }

    /**
     * Sets Tic image ('X')
     */
    public void setTic() {
        this.imageView.setImage(PLAY_X);
    }

    /**
     * Sets Tac image ('O')
     */
    public void setTac() {
        this.imageView.setImage(PLAY_O);
    }
}