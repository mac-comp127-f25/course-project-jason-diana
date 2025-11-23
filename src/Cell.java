public class Cell {
    public boolean isMine;
    public boolean isRevealed;
    public boolean isFlaged;
    public int mineNum;

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean m) {
        isMine = m;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public int getMineNum() {
        return mineNum;
    }

    public void setMineNum(int n) {
        mineNum = n;
    }
    
}
