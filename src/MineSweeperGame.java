import edu.macalester.graphics.*;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class MineSweeperGame {
    private static final int CANVAS_WIDTH = 500;
    private static final int CANVAS_HEIGHT = 500;
    private static final int OX = 100;
    private static final int OY = 100;
    private static final int IMAGE_SIZE = new Image("0.png").getImageWidth();


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

        Image imgii = new Image("0.png");
        for (int i = 0; i < board.getRow(); i++) {
            for (int j = 0; j < board.getCol(); j++) {
                double x = OX + i*imgii.getHeight();
                double y = OY + j*imgii.getWidth();

                Cell cell = board.getCell(i, j);

                if (cell.isRevealed) {
                    if (!cell.isMine) {
                        if (cell.getMineNum() == 0) {
                        Image img = new Image(x, y, "0.png");
                        canvas.add(img);
                        }
                        if (cell.getMineNum() == 1) {
                            Image img = new Image(x, y, "1.png");
                            canvas.add(img);
                        }
                        if (cell.getMineNum() == 2) {
                            Image img = new Image(x, y, "2.png");
                            canvas.add(img);
                        }
                        if (cell.getMineNum() == 3) {
                            Image img = new Image(x, y, "3.png");
                            canvas.add(img);
                        }
                        if (cell.getMineNum() == 4) {
                            Image img = new Image(x, y, "4.png");
                            canvas.add(img);
                        }
                        if (cell.getMineNum() == 5) {
                            Image img = new Image(x, y, "5.png");
                            canvas.add(img);
                        }
                        if (cell.getMineNum() == 6) {
                            Image img = new Image(x, y, "6.png");
                            canvas.add(img);
                        }
                        if (cell.getMineNum() == 7) {
                            Image img = new Image(x, y, "7.png");
                            canvas.add(img);
                        }
                        if (cell.getMineNum() == 8) {
                            Image img = new Image(x, y, "8.png");
                            canvas.add(img);
                        }
                    }
                    else {
                        Image img = new Image(x, y, "mine.png");
                        canvas.add(img); 
                    }
                } else {
                    Image img = new Image(x, y, "10.png");
                    canvas.add(img); 
                }
            }
        }
    }

    private void jLabellMouseClicked(MouseEvent evt){
        if(evt.getButton() == MouseEvent.BUTTON1) {
            int x = evt.getX();
            int y = evt.getY();
        }
        else if (evt.getButton() == MouseEvent.BUTTON2) {

        }
    }

    private void tracingClickLocation(int x, int y) {
        if (x < OX && x > OX + IMAGE_SIZE * board.getRow())
    }
 
    
   
}

