package vkaretko;

import java.util.ArrayList;
import java.util.List;

/**
 * Frog class.
 * @author Karetko Victor
 * @version 1.00
 * @since 16.12.2016
 */
public class Frog {
    /**
     * Start frog position.
     */
    private Position startPos;
    /**
     * Final frog position.
     */
    private Position finalPos;
    /**
     * List of trees.
     */
    private List<Position> trees;
    /**
     * Minimum count of jumps.
     */
    private int minCount = 50;
    /**
     * Width of circle.
     */
    private final int width = 10;
    /**
     * Length of circle.
     */
    private final int length = 16;
    /**
     * Shortest path.
     */
    private String shortestPath = "Path:";

    /**
     * Constructor of frog class.
     * @param startPos start frog psotion.
     * @param finalPos final frog position.
     * @param trees list of trees.
     */
    public Frog(Position startPos, Position finalPos, List<Position> trees) {
        this.startPos = startPos;
        this.finalPos = finalPos;
        this.trees = trees;
    }

    /**
     * Init method for executing recursive search.
     */
    public void init() {
        recursiveJump(startPos.getX(), startPos.getY(), 0, 0, -1, shortestPath);
        System.out.println(String.format("Minimum length: %s", minCount));
        System.out.println(shortestPath);
    }

    /**
     * Method for recursive search shortest path.
     * @param x x coordinate.
     * @param y y coordinate.
     * @param stepX offset along the x-axis.
     * @param stepY offset along the y-axis.
     * @param count count of steps.
     * @param prevPath path of steps.
     */
    private void recursiveJump(int x, int y, int stepX, int stepY, int count, String prevPath) {
        count++;
        y += stepY;
        x = (x + stepX) % this.length;
        if (this.finalPos.getX() == x && this.finalPos.getY() == y) {
            setMinimumPath(count, prevPath, x, y);
            return;
        }
        if (y >= 0 && y < width && !isTree(x, y) && count < this.minCount) {
            recursiveJump(x, y, 3, 0, count, lineFormat(prevPath, x, y));
            recursiveJump(x, y, 2, 1, count, lineFormat(prevPath, x, y));
            recursiveJump(x, y, 1, 2, count, lineFormat(prevPath, x, y));
            recursiveJump(x, y, 2, -1, count, lineFormat(prevPath, x, y));
            recursiveJump(x, y, 1, -2, count, lineFormat(prevPath, x, y));
        }
    }

    /**
     * Method set minimum path.
     * @param count count of steps.
     * @param prevPath path of steps.
     * @param x x coordinate.
     * @param y y coordinate.
     */
    private void setMinimumPath(int count, String prevPath, int x, int y) {
        if (count < this.minCount) {
            this.minCount = count;
            this.shortestPath = lineFormat(prevPath, x, y);
        }
    }

    /**
     * Format method for paths.
     * @param prevPath steps path.
     * @param x x coordinate.
     * @param y y coordinate.
     * @return formatted line.
     */
    public String lineFormat(String prevPath, int x, int y) {
        return String.format("%s(%s,%s)", prevPath, x, y);
    }

    /**
     * Method checks if there is tree on the frog path.
     * @param x x coordinate of frog.
     * @param y y coordinate of frog.
     * @return true if there is tree, false otherwise.
     */
    private boolean isTree(int x, int y) {
        boolean result = false;
        for (Position tree : trees) {
            if (tree.getX() == x && tree.getY() == y) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ArrayList<Position> trees = new ArrayList<>();
        trees.add(new Position(13, 8));
        trees.add(new Position(4, 7));
        new Frog(new Position(10, 6), new Position(8, 9), trees).init();
    }
}
