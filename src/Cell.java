/**
 * Represents a single cell in the Minesweeper board.
 * A cell may contain a mine, be revealed or hidden,
 * and store the number of neighboring mines.
 */
public class Cell {
    public boolean isMine;
    public boolean isRevealed;
    public boolean isFlagged;
    public int mineNum;

    /**
     * Returns whether this cell contains a mine.
     */
    public boolean isMine() {
        return isMine;
    }

    /**
     * Sets whether this cell contains a mine.
     */
    public void setMine(boolean m) {
        isMine = m;
    }

    /**
     * Returns whether this cell has been revealed.
     */
    public boolean isRevealed() {
        return isRevealed;
    }

    /**
     * Returns the number of mines in neighboring cells.
     */
    public int getMineNum() {
        return mineNum;
    }

    /**
     * Sets the number of neighboring mines for this cell.
     */
    public void setMineNum(int n) {
        mineNum = n;
    }
    
}
