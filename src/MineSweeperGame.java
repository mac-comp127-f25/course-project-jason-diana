import edu.macalester.graphics.*;
import edu.macalester.graphics.events.Key;




public class MineSweeperGame {
    private static final int CANVAS_WIDTH = 500;
    private static final int CANVAS_HEIGHT = 500;
    private static final int OX = 100;
    private static final int OY = 100;
    private static final int IMAGE_SIZE = new Image("0.png").getImageWidth();
    private static boolean FLAG_MODE = false;


    private static CanvasWindow canvas;
    private static Board board = new Board(10,10, 15);

    public MineSweeperGame() {
        canvas = new CanvasWindow("Mine Sweeper", CANVAS_WIDTH, CANVAS_HEIGHT);
        
        board.initialize();
        drawBoard();

        addMineCounterLabel();

    }

    public static void main(String[] args) {
        new MineSweeperGame();
        addMouseHandler();
        addKeyHandler();
        if (board.isGameOver()) {
            GraphicsText gameOverText = new GraphicsText("Game Over!");
            gameOverText.setPosition(CANVAS_WIDTH / 2 - 50, CANVAS_HEIGHT / 2);
            gameOverText.setFontSize(30);
            canvas.add(gameOverText);
            canvas.closeWindow();
        }
    }

    private void addMineCounterLabel() {
        GraphicsText label = new GraphicsText("Mines left:");
        label.setPosition(20, 30);
        label.setFontSize(20);
        canvas.add(label);
    }

    private static void toggleFlag(int row, int col) {
        Cell cell = board.getCell(row, col);
        cell.isFlaged = !cell.isFlaged;
        Image flag = new Image(OX + row * IMAGE_SIZE, OY + col * IMAGE_SIZE, "flag.png");
        canvas.add(flag);
    }

    private static void drawBoard() {

        Image imgii = new Image("0.png");
        for (int i = 0; i < board.getRow(); i++) {
            for (int j = 0; j < board.getCol(); j++) {
                double x = OX + i*imgii.getHeight();
                double y = OY + j*imgii.getWidth();

                Cell cell = board.getCell(i, j);

                if (cell.isFlaged) {
                    continue;
                }
                else if (cell.isRevealed) {
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

    private static void addMouseHandler() {

        canvas.onMouseDown(event -> {
            double x = event.getPosition().getX();
            double y = event.getPosition().getY();

            // convert pixels to grid coordinates
            int row = (int)((x - OX) / IMAGE_SIZE);
            int col = (int)((y-OY)/ IMAGE_SIZE);

            if (!board.inBounds(row, col)) return;
            if (FLAG_MODE) {
                toggleFlag(row, col);
                return;
            }
            else {
                board.revealCell(row, col);
            }
            drawBoard();  // refresh screen after any action
        });
    }

    private static boolean addKeyHandler() {
        canvas.onKeyDown(event -> {
            Key key = event.getKey();
            if (key == Key.F) {
                FLAG_MODE = true;
            }
        });
        canvas.onKeyUp(event -> {
            Key key = event.getKey();
            if (key == Key.F) {
                FLAG_MODE = false;
            }
        });
    return FLAG_MODE;
    }



   
}

