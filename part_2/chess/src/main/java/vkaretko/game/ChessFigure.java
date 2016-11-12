package vkaretko.game;

/**
 * Abstract class of figures.
 *
 * @author Karetko Victor
 * @version 1.00
 * @since 04.11.2016
 */
public abstract class ChessFigure {
    /**
     * Getter of name value.
     * @return name of figure
     */
    public abstract String getName();
    /**
     * Getter of white value.
     * @return color of figure (White = true)
     */
    public abstract boolean isWhite();

}
