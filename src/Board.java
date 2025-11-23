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
                if (grid[i][j].isMine){
                    num ++;
                }
            }
        }
        return num;
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