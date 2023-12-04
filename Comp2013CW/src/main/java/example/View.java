package example;

import javafx.animation.FadeTransition;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.Font;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


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
            //System.out.println("Drawing Bodies");
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

    public void drawFailScene() {

        gc.drawImage(fail, 0, 0, canvas.getWidth(), canvas.getHeight());

        // Add "Go Back" button
        Button goBackButton = createStyledButton("Go Back", "-fx-background-color: #45A049; -fx-text-fill: white; -fx-font-size: 18px;");
        goBackButton.setOnAction(e -> {
            controller.goBack();
        });
        goBackButton.setFocusTraversable(true);

        // Add "Retry" button
        Button retryButton = createStyledButton("Retry", "-fx-background-color: #45A049; -fx-text-fill: white; -fx-font-size: 18px;");
        retryButton.setOnAction(e -> controller.retry());
        root.setFocusTraversable(true);

        // Create layout for buttons
        HBox buttonsLayout = new HBox(10);
        buttonsLayout.setAlignment(Pos.BOTTOM_CENTER);
        buttonsLayout.getChildren().addAll(goBackButton, retryButton);

        // Draw buttons
        root.getChildren().add(buttonsLayout);
        root.requestFocus();
    }

    private Button createStyledButton(String text, String inlineStyle) {
        Button button = new Button(text);
        button.setStyle(inlineStyle);
        return button;
    }

    @Override
    public void update(Observable o, Object arg) {
        draw();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void drawCountdown(int i) {
        // Create a Text node with the countdown number or "GO"
        Text countdownText = new Text(i == 4 ? "GO" : Integer.toString(i));
        countdownText.setFont(new Font("Arial" , 200.0));

        countdownText.setFill(i == 4 ? Color.GREEN : Color.WHITE);

        // Set the initial opacity
        countdownText.setOpacity(1.0);

        // Add the Text node to the canvas
        root.getChildren().add(countdownText);

        // Create a FadeTransition with a duration of 1 second
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1.0), countdownText);

        // Set the start and end values for opacity
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);

        // Set up an event handler to remove the Text node when the transition finishes
        fadeTransition.setOnFinished(event -> root.getChildren().remove(countdownText));

        // Play the fade transition
        fadeTransition.play();
    }
}
