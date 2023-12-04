package example;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.awt.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.paint.Color;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;


public class View  implements Observer {


    public Image background = ImageUtil.images.get("UI-background");
    public Image fail = ImageUtil.images.get("Fail-Scene");

    private static final int FRAME_WIDTH = 900;
    private static final int FRAME_HEIGHT = 600;

    private Model model;
    private Controller controller;
    private StackPane root;
    private Canvas canvas;
    private GraphicsContext gc;

    private Stage stage;



    public View(Model model , Controller controller , Stage stage) {
        this.model = model;
        this.controller = controller;

        initializeUI(stage);

        model.addObserver(this);
    }

    private void initializeUI(Stage stage) {

        root = new StackPane();

        canvas = new Canvas(FRAME_WIDTH, FRAME_HEIGHT);
        gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        drawBackground();
        // Set up key event handling
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(controller.keyPressed());
        root.setFocusTraversable(true);

        // Add the scene to the stage

        stage.setTitle("Snake Game");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void draw() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        if (!model.EndGame) {
            drawBackground();
            drawSnake();
            drawFood();
            drawScore();
        } else {
            drawFailScene();
        }
    }

    private void drawBackground() {
        Image background = ImageUtil.images.get("UI-background");
        gc.drawImage(background, 0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void drawSnake() {
        Snake snake = model.getSnake();
        Image snakeHead = ImageUtil.images.get("snake-head-right");
        Image newImgSnakeHead = snakeHead;
        List<Point> bodyPoints = snake.getBodyPoints();
        bodyPoints.add(new Point(snake.x, snake.y));

        if (bodyPoints.size() == (snake.getLength() + 1) * snake.getNumOfBodies())
        {
            bodyPoints.remove(0);
        }

        switch (snake.Direction){
            case 1:
                newImgSnakeHead = rotateImage(snakeHead, -90);
                break;
            case 3:
                newImgSnakeHead = rotateImage(snakeHead, 90);
                break;
            case 4:
                newImgSnakeHead = rotateImage(snakeHead,  -180);
                break;
           default:
                break;
        }

        gc.drawImage(newImgSnakeHead, snake.x, snake.y);
        // Draw snake body
        drawBody();
    }

    private Image rotateImage(Image image, int degrees) {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Create a Graphics2D object from the BufferedImage
        java.awt.Graphics2D graphics = bufferedImage.createGraphics();

        // Rotate the image
        AffineTransform transform = AffineTransform.getRotateInstance(Math.toRadians(degrees), width / 2, height / 2);
        graphics.setTransform(transform);

        // Draw the rotated image onto the BufferedImage
        graphics.drawImage(SwingFXUtils.fromFXImage(image, null), 0, 0, null);

        // Dispose the Graphics2D object
        graphics.dispose();

        // Convert the rotated BufferedImage back to JavaFX Image
        return SwingFXUtils.toFXImage(bufferedImage, null);
    }
    public void drawBody()
    {
        Snake snake  = model.getSnake();
        Image snakeBody = ImageUtil.images.get("snake-body");
        int length = snake.getBodyPoints().size() - 1 - snake.getNumOfBodies();

        for (int i = length; i >= snake.getNumOfBodies(); i -= snake.getNumOfBodies())
        {
            System.out.println("Drawing Bodies");
            Point point = snake.getBodyPoints().get(i);
            gc.drawImage(snakeBody, point.x, point.y);
        }
    }

    private void drawFood() {
        Food food = model.getFood();
        gc.drawImage(food.getFoodImage(), food.x, food.y);
    }

    private void drawScore() {
        Color Magenta = Color.MAGENTA;
        gc.setFill(Magenta);
        gc.setFont(new javafx.scene.text.Font("Arial", 20));
        gc.fillText("SCORE: " + model.getScore(), 20, 30);


    }

    private void drawFailScene() {
        Image failScene = ImageUtil.images.get("Fail-Scene");
        gc.drawImage(failScene, 0, 0, canvas.getWidth(), canvas.getHeight());
    }
    @Override
    public void update(Observable o, Object arg) {
        draw();
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
