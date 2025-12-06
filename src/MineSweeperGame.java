import edu.macalester.graphics.*;
import edu.macalester.graphics.events.Key;
import java.awt.Color;


public class MineSweeperGame {
    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 800;
    private static final int OX = 100;
    private static final int OY = 100;
    private static final int IMAGE_SIZE = new Image("0.png").getImageWidth();
    private static boolean FLAG_MODE = false;


    private static CanvasWindow canvas;
    private static Board board ;
    private static boolean gameStarted = false;
    private static boolean gameOver = false;
    private static int totalMines = 0;
    private static int flagsPlaced = 0;
    private static GraphicsText mineCounterLabel;

    public MineSweeperGame() {
        canvas = new CanvasWindow("Mine Sweeper", CANVAS_WIDTH, CANVAS_HEIGHT);
        showStartMenu();
        addMenuKeyHandler();
    }

    public static void main(String[] args) {
        new MineSweeperGame();
    }

    private static void showStartMenu() {
        canvas.removeAll(); 
        GraphicsText startText = new GraphicsText("Select Difficulty: Pressing key (E)easy, (M)edium, (H)ard to start");
        startText.setFontSize(20);
        startText.setPosition(CANVAS_WIDTH / 4.0 - 150, 120);
        canvas.setBackground(new Color(200, 200, 255));
        canvas.add(startText);

        Image cuteEmoji = new Image("innocent-and-pretty-blue-emoji-blue.png");
        cuteEmoji.setPosition(CANVAS_WIDTH / 2.0 + 100, 200);
        cuteEmoji.setMaxWidth(150);
        cuteEmoji.setMaxHeight(150);
        canvas.add(cuteEmoji);

        double rectWidth = 100;
        double rectHeight = 40;
        double startX = CANVAS_WIDTH / 4.0 - rectWidth / 2;
        double startY = 180;
        double gap = 20;

        // easy
        Rectangle easyRect = new Rectangle(startX, startY, rectWidth, rectHeight);
        easyRect.setFillColor(Color.LIGHT_GRAY);
        canvas.add(easyRect);
        GraphicsText easyText = new GraphicsText("Easy (E)");;   
        easyText.setPosition(startX + 10, startY + 25);
        canvas.add(easyText);
        easyText.setFontSize(20);

        // medium
        Rectangle mediumRect = new Rectangle(startX, startY + rectHeight + gap, rectWidth*1.4, rectHeight);
        mediumRect.setFillColor(Color.LIGHT_GRAY);
        canvas.add(mediumRect); 
        GraphicsText mediumText = new GraphicsText("Medium (M)");;   
        mediumText.setPosition(startX + 10, startY + rectHeight + gap + 25);
        canvas.add(mediumText);
        mediumText.setFontSize(20);

        // hard
        Rectangle hardRect = new Rectangle(startX, startY + 2 * (rectHeight + gap), rectWidth, rectHeight);
        hardRect.setFillColor(Color.LIGHT_GRAY);
        canvas.add(hardRect);
        GraphicsText hardText = new GraphicsText("Hard (H)");;   
        hardText.setPosition(startX + 10, startY + 2 * (rectHeight + gap) + 25);
        canvas.add(hardText);
        hardText.setFontSize(20);
    }

    private static void startGame(int rows, int cols, int mines) {
        board = new Board(rows, cols, mines);
        board.initialize();

        gameStarted = true;
        FLAG_MODE = false;  
        totalMines = mines;
        flagsPlaced = 0;

        canvas.removeAll();
        drawBoard();
        addMouseHandler();
        addKeyHandler();
    }


    private static void toggleFlag(int row, int col) {
        Cell cell = board.getCell(row, col);
        if (cell.isRevealed) {
            return;
        }
        if (cell.isFlaged) {
            cell.isFlaged = false;
            flagsPlaced--;
        } else {
            cell.isFlaged = true;
            flagsPlaced++;
        }
    }


    /* Drawing the Board   ------------------------------------------------------------
     */
    private static void drawBoard() {
        canvas.removeAll();

        Image imgii = new Image("0.png");

        addReplayKeyHandler();
        Rectangle backRectangle = new Rectangle (CANVAS_WIDTH / 2.0 - 130, CANVAS_HEIGHT / 2 + 10, 290, 40);
        backRectangle.setFillColor(new Color(25, 25, 25));
        canvas.add(backRectangle);
        GraphicsText replayText = new GraphicsText("Press (R) to Replay");
        replayText.setPosition(CANVAS_WIDTH / 2.0 - 120, CANVAS_HEIGHT / 2 + 40);
        replayText.setFontSize(30);
        replayText.setFillColor(Color.white);
        canvas.add(replayText);

        GraphicsText flagModeText = new GraphicsText("Hold (F) to Flag");
        flagModeText.setFontSize(15);
        flagModeText.setPosition(CANVAS_WIDTH - 200, 80);
        canvas.add(flagModeText);

        GraphicsText unFlagModText = new GraphicsText("Hold (F) again to Unflag");
        unFlagModText.setFontSize(15);
        unFlagModText.setPosition(CANVAS_WIDTH - 200, 110);
        canvas.add(unFlagModText);

        GraphicsText clickText = new GraphicsText("Click to Reveal Cell");
        clickText.setFontSize(15);
        clickText.setPosition(CANVAS_WIDTH - 200, 140);
        canvas.add(clickText);

        GraphicsText quitText = new GraphicsText("Close window to Quit");
        quitText.setFontSize(15);
        quitText.setPosition(CANVAS_WIDTH - 200, 170);
        canvas.add(quitText);

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
                    if (cell.isFlaged) {
                        Image img = new Image(x, y, "flag.png");
                        canvas.add(img); 
                    }
                    else {
                        Image img = new Image(x, y, "10.png");
                        canvas.add(img); 
                    }
                    if (board.isGameOver()) {
                        Rectangle overlay = new Rectangle (CANVAS_WIDTH / 2.0 - 130, CANVAS_HEIGHT / 2 - 30, 190, 40);
                        overlay.setFillColor(new Color(25, 25, 25));
                        canvas.add(overlay);
                        GraphicsText gameOverText = new GraphicsText("Game Over!");
                        board.revealAllMines();
                        gameOverText.setPosition(CANVAS_WIDTH / 2.0 - 120, CANVAS_HEIGHT / 2);
                        gameOverText.setFontSize(30);
                        gameOverText.setFillColor(Color.RED);
                        canvas.add(gameOverText);
                    }
                    if (board.isGameWon()) {
                        Rectangle overlay = new Rectangle (CANVAS_WIDTH / 2.0 - 130, CANVAS_HEIGHT / 2 - 30, 170, 40);
                        overlay.setFillColor(new Color(25, 25, 25));
                        canvas.add(overlay);
                        GraphicsText gameWonText = new GraphicsText("You Win!");
                        gameWonText.setPosition(CANVAS_WIDTH / 2.0 - 120, CANVAS_HEIGHT / 2);
                        gameWonText.setFontSize(30);
                        gameWonText.setFillColor(Color.GREEN);
                        canvas.add(gameWonText);
                    }
                    
                }
            }
        }
        drawMineCounter();
    }

    private static void drawMineCounter() {
        int remaining = totalMines - flagsPlaced;
        if (remaining < 0) {
            remaining = 0;
        }
        if (mineCounterLabel == null) {
            mineCounterLabel = new GraphicsText();
            mineCounterLabel.setPosition(50, 50);
            mineCounterLabel.setFontSize(20);
        }

        mineCounterLabel.setText("Mines Remaining: " + remaining);

        canvas.add(mineCounterLabel);
    }




    /* Mouse and Key Handlers   ------------------------------------------------------------
     */
    private static void addMouseHandler() {

        canvas.onMouseDown(event -> {
            double x = event.getPosition().getX();
            double y = event.getPosition().getY();

            // convert pixels to grid coordinates
            int row = (int)((x - OX) / IMAGE_SIZE);
            int col = (int)((y-OY)/ IMAGE_SIZE);

            if (!board.inBounds(row, col)) return;
            if (FLAG_MODE) {
                if (!board.getCell(row, col).isRevealed) {
                    toggleFlag(row, col);
                }
            }
            else {
                if (!board.getCell(row, col).isFlaged){
                    board.revealCell(row, col);
                }
            }
            drawBoard();  
        });
    }

    private static boolean addKeyHandler() {
        canvas.onKeyDown(event -> {
            Key key = event.getKey();
            if (FLAG_MODE == false && key == Key.F) {
                FLAG_MODE = true;
                // System.out.println("Flag mode activated");
            }
        });
        canvas.onKeyUp(event -> {
            Key key = event.getKey();
            if (key == Key.F) {
                FLAG_MODE = false;
                // System.out.println("Flag mode deactivated");
            }
        });
        return FLAG_MODE;
    }


    private static void addMenuKeyHandler() {
        canvas.onKeyDown(event -> {
            Key key = event.getKey();
            if (key == Key.E) {
                startGame(9,9,10);
            } else if (key == Key.M) {
                startGame(16, 16, 40);
            } else if (key == Key.H) {
                startGame(30, 30, 70);
            }
        });

    }

    private static void addReplayKeyHandler() {
        canvas.onKeyDown(event -> {
            Key key = event.getKey();
            if (key == Key.R) {
                showStartMenu();
            }
        });
    }
   
}

