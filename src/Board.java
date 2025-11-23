import java.util.Random;

public class Board {
    Cell[][] grid;
    int row;
    int col;
    int mine;

    public Board (int r, int c, int m) {
        col = c;
        row = r;
        mine = m;
        grid = new Cell[row][col];
    }

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

    public boolean inBounds(int r, int c) {
        return r >= 0 && r < row && c >= 0 && c < col;
    }

    public void placeMines() {
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

    public void mineCalculator() {
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

    public int mineNeighborCalculator(int r, int c) {
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


    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Cell getCell(int r, int c) {
        return grid[r][c];
    }
}