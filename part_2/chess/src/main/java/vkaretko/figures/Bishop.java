package vkaretko.figures;

import vkaretko.game.ChessFigure;

/**
 * Class of Bishop figure.
 *
 * @author Karetko Victor
 * @version 1.00
 * @since 04.11.2016
 */
public class Bishop extends ChessFigure {

    /**
     * Name of figure.
     */
    private String name = "Bishop";
    /**
     * Color of figure.
     */
    private Boolean white;
    /**
     * Constructor of Bishop class.
     * @param white - white or black color
     */
    public Bishop(Boolean white) {
        this.white = white;
    }
    /**
     * Getter of name value.
     * @return name of figure
     */
    @Override
    public String getName() {
        return this.name;
    }
    /**
     * Getter of white value.
     * @return color of figure (White = true)
     */
    public boolean isWhite() {
        return this.white;
    }
}
