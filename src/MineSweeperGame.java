import edu.macalester.graphics.*;
import java.awt.Color;


public class MineSweeperGame {
    private static final int CANVAS_WIDTH = 700;
    private static final int CANVAS_HEIGHT = 800;

    private CanvasWindow canvas;

    public MineSweeperGame() {
        canvas = new CanvasWindow("Mine Sweeper", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setBackground(Color.WHITE);

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
}

