import java.util.Random;

    /**
     * Constructs a board of the given dimensions and configured mine count.
     * @param r number of rows (must be > 0)
     * @param c number of columns (must be > 0)
     * @param m number of mines to place (should be between 0 and r*c)
     */
public class Board {
    Cell[][] grid;
    int row;
    int col;
    int mine;

    /**
     * Creates a Minesweeper board with given size and number of mines.
     */
    public Board (int r, int c, int m) {
        col = c;
        row = r;
        mine = m;
        grid = new Cell[row][col];
    }

    /**
     * Sets up the board for a new game.
     * Creates all cells, places mines, and calculates nearby mine numbers.
     */
    public void initialize() {
        createCells();       
        placeMines();        
        mineCalculator();  
    }

    private void createCells() {
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                grid[r][c] = new Cell();
            }
        }
    }

    /**
     * Checks if the given (r, c) position is within the board bounds.
     */
    public boolean inBounds(int r, int c) {
        return r >= 0 && r < row && c >= 0 && c < col;
    }

    private void placeMines() {
        Random rand = new Random();
        int placed = 0;
        while (placed <= mine) {
            int r = rand.nextInt(row);
            int c = rand.nextInt(col);
            if (!grid[r][c].isMine) {
                grid[r][c].setMine(true);
            }
            placed ++;
        }
    }

    private void mineCalculator() {
        for (int i = 0; i < row; i ++) {
            for (int j = 0; j < col ; j++) {
                if (grid[i][j].isMine){
                    grid[i][j].setMineNum(-1);
                    continue;
                }
                int count = mineNeighborCalculator(i, j);
                grid[i][j].setMineNum(count);
            }
        }
    }

    private int mineNeighborCalculator(int r, int c) {
        int num = 0;
        for (int i = -1; i <= 1; i ++) {
            for (int j = -1; j <= 1 ; j++) {
                if (inBounds(r+i, c+j) && grid[r+i][c+j].isMine) {
                    num ++;
                }
            }
        }
        return num;
    }

    /**
     * Reveals the cell at (r, c).
     * If the cell has no nearby mines, neighboring cells are revealed automatically.
     */
    public void revealCell(int r, int c) {
        if (!inBounds(r, c)) {
            return;
        }
        Cell cell = grid[r][c];

        if (cell.isRevealed) {
            return;
        }
        cell.isRevealed = true;

        if (cell.isMine) {
            return;
        }
        if (cell.getMineNum() == 0) {
            for (int dr = -1; dr <= 1; dr++) {
                for (int dc = -1; dc <= 1; dc++) {
                    if (dr == 0 && dc == 0) continue;
                    revealCell(r + dr, c + dc);
                }
            }
        }
    }

    /**
     * Checks whether the game is over.
     * The game ends when a mine is revealed.
     */
    public boolean isGameOver() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Cell cell = grid[i][j];
                if (cell.isMine && cell.isRevealed) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks whether the player has won the game.
     * The game is won when all non-mine cells are revealed.
     */
    public boolean isGameWon() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Cell cell = grid[i][j];
                if (!cell.isMine && !cell.isRevealed) {
                    return false;
                }
                if (!cell.isMine && !cell.isRevealed) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Reveals all mines on the board.
     * Usually called when the game is over.
     */
    public void revealAllMines() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Cell cell = grid[i][j];
                if (cell.isMine) {
                    cell.isRevealed = true;
                }
            }
        }
    }


    /**
     * Returns the number of rows in the board.
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the number of columns in the board.
     */
    public int getCol() {
        return col;
    }

    /**
     * Returns the Cell at the specified (r, c) position.
     */
    public Cell getCell(int r, int c) {
        return grid[r][c];
    }
}