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
        // canvas.setBackground(Color.BLACK);
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
                double x = SPACING + j * CELL_SIZE;
                double y = SPACING + i * CELL_SIZE;
                Rectangle cells = new Rectangle(x, y, CELL_SIZE, CELL_SIZE);
                cells.setStrokeColor(Color.BLACK);

                Cell cell = board.getCell(i, j);

                if (cell.isRevealed) {
                    cells.setFillColor(Color.LIGHT_GRAY);
                } else {
                    cells.setFillColor(Color.WHITE);
                }
                canvas.add(cells);
            }
        }
    }
}

