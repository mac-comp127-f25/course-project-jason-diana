import edu.macalester.graphics.*;
import java.awt.Color;


public class MineSweeperGame {
    private static final int CANVAS_WIDTH = 700;
    private static final int CANVAS_HEIGHT = 800;
    private static final int CELL_SIZE = 30;
    private static final int SPACING = 5;

    private CanvasWindow canvas;
    private Board board = new Board(10,10, 15);

    public MineSweeperGame() {
        canvas = new CanvasWindow("Mine Sweeper", CANVAS_WIDTH, CANVAS_HEIGHT);
        
        board.initialize();
        drawBoard();

        addMineCounterLabel();
    }

    public static void main(String[] args) {
        new MineSweeperGame();
    }

    private void addMineCounterLabel() {
        GraphicsText label = new GraphicsText("Mines left:");
        label.setPosition(20, 30);
        label.setFontSize(20);
        canvas.add(label);
    }

    private void drawBoard() {
        for (int i = 0; i < board.getRow(); i++) {
            for (int j = 0; j < board.getCol(); j++) {
                double x = SPACING + i * CELL_SIZE;
                double y = SPACING + j * CELL_SIZE;

                Cell cell = board.getCell(i, j);

                if (cell.isRevealed) {
                    if (!cell.isMine) {
                        if (cell.getMineNum() == 0) {
                        Image img = new Image(x, y, "res/0.png");
                        canvas.add(img);
                        }
                        if (cell.getMineNum() == 1) {
                            Image img = new Image(x, y, "res/1.png");
                            canvas.add(img);
                        }
                        if (cell.getMineNum() == 2) {
                            Image img = new Image(x, y, "res/2.png");
                            canvas.add(img);
                        }
                        if (cell.getMineNum() == 3) {
                            Image img = new Image(x, y, "res/3.png");
                            canvas.add(img);
                        }
                        if (cell.getMineNum() == 4) {
                            Image img = new Image(x, y, "res/4.png");
                            canvas.add(img);
                        }
                        if (cell.getMineNum() == 5) {
                            Image img = new Image(x, y, "res/5.png");
                            canvas.add(img);
                        }
                        if (cell.getMineNum() == 6) {
                            Image img = new Image(x, y, "res/6.png");
                            canvas.add(img);
                        }
                        if (cell.getMineNum() == 7) {
                            Image img = new Image(x, y, "res/7.png");
                            canvas.add(img);
                        }
                        if (cell.getMineNum() == 8) {
                            Image img = new Image(x, y, "res/8.png");
                            canvas.add(img);
                        }
                    }
                    else {
                        Image img = new Image(x, y, "res/mine.png");
                        canvas.add(img); 
                    }
                } else {
                    Image img = new Image(x, y, "res/10.png");
                    canvas.add(img); 
                }
            }
        }
    }
}

