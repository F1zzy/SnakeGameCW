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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.Font;
import javafx.scene.control.TextField;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static example.LeaderBoard.GreaterThanHighScore;

public class View implements Observer {

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

    private VBox pauseMenu;
    private StackPane pauseMenuOverlay;
    public View(Model model, Controller controller, Stage stage) {
        this.model = model;
        this.controller = controller;

        initializeUI(stage);
        initPauseMenu();

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

        Button pauseButton = Settings.createStyledButton("Pause");
        pauseButton.setOnAction(e -> controller.pauseGame());
        StackPane.setAlignment(pauseButton, Pos.TOP_RIGHT);
        root.getChildren().add(pauseButton);

        pauseMenuOverlay = new StackPane();
        pauseMenuOverlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);"); // Adjust the alpha value as needed
        pauseMenuOverlay.setAlignment(Pos.CENTER);

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
            drawFoods();
            drawScore();
        }
    }

    private void drawBackground() {
        Image background = ImageUtil.images.get("UI-background");
        gc.drawImage(background, 0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void drawSnake() {

        Snake snake = model.getSnake();
        if(!snake.isVisible) return;
        Image snakeHead = ImageUtil.images.get("snake-head-right");
        Image newImgSnakeHead = snakeHead;
        List<Point> bodyPoints = snake.getBodyPoints();
        bodyPoints.add(new Point(snake.x, snake.y));

        if (bodyPoints.size() == (snake.getLength() + 1) * snake.getNumOfBodies()) {
            bodyPoints.remove(0);
        }

        switch (snake.Direction) {
            case 1:
                newImgSnakeHead = rotateImage(snakeHead, -90);
                break;
            case 3:
                newImgSnakeHead = rotateImage(snakeHead, 90);
                break;
            case 4:
                newImgSnakeHead = rotateImage(snakeHead, -180);
                break;
            default:
                break;
        }

        gc.drawImage(newImgSnakeHead, snake.x, snake.y);

        // Draw snake body
        drawBody();
    }

    private void drawBody() {

        Snake snake = model.getSnake();
        if(!snake.isVisible) return;
        Image snakeBody = ImageUtil.images.get("snake-body");
        int length = snake.getBodyPoints().size() - 1 - snake.getNumOfBodies();

        for (int i = length; i >= snake.getNumOfBodies(); i -= snake.getNumOfBodies()) {
            Point point = snake.getBodyPoints().get(i);
            gc.drawImage(snakeBody, point.x, point.y);
        }
    }

    private void drawFoods() {
        List<Food> foods = model.getFruits();
        for (Food food : foods) {
            gc.drawImage(food.getFoodImage(), food.x, food.y);
        }
    }

    private void drawScore() {
        Color Magenta = Color.MAGENTA;
        gc.setFill(Magenta);
        gc.setFont(new javafx.scene.text.Font("Arial", 20));
        GreaterThanHighScore(model.getScore());
        if (GreaterThanHighScore(model.getScore())) {
            gc.fillText("NEW HIGH SCORE: " + model.getScore(), 20, 30);
        } else {
            gc.fillText("SCORE: " + model.getScore(), 20, 30);
        }
    }

    public void gameOverScene() {
        drawBackground();
        drawSnake();
        drawBody();
        // Add "Go Back" button
        Button goBackButton = Settings.createStyledButton("Go Back");
        goBackButton.setOnAction(e -> controller.goBack());
        goBackButton.setFocusTraversable(true);

        // Add "Retry" button
        Button retryButton = Settings.createStyledButton("Retry");
        retryButton.setOnAction(e -> controller.retry());
        root.setFocusTraversable(true);

        // Add Username input box
        TextField usernameInput = new TextField();
        usernameInput.setText("");

        Button submitButton = Settings.createStyledButton("Submit");
        submitButton.setOnAction(e -> {
            String username = usernameInput.getText();
            controller.submitScore(username, model.getScore());
            submitButton.setText("ADDED");
            submitButton.setDisable(true);
        });

        Color Magenta = Color.RED;
        gc.setFill(Magenta);
        gc.setFont(new javafx.scene.text.Font("Arial", 120));
        gc.fillText("YOU DIED :(", 140, 200);

        VBox userInputLayout = new VBox(10);
        userInputLayout.setAlignment(Pos.CENTER);
        userInputLayout.getChildren().addAll(usernameInput);

        // Create layout for buttons
        HBox buttonsLayout = new HBox(10);
        buttonsLayout.setAlignment(Pos.BOTTOM_CENTER);
        buttonsLayout.getChildren().addAll(submitButton, goBackButton, retryButton);

        // Create layout for the entire scene
        VBox allLayout = new VBox(20);
        allLayout.setAlignment(Pos.CENTER);

        allLayout.getChildren().addAll(userInputLayout, buttonsLayout);

        // Draw buttons And Score
        drawScore();
        root.getChildren().add(userInputLayout);
        root.getChildren().add(buttonsLayout);
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
        countdownText.setFont(new Font("Arial", 200.0));

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

    public void initPauseMenu() {


        // Create "Resume" button
        Button resumeButton = Settings.createStyledButton("Resume");
        resumeButton.setOnAction(e -> controller.resumeGame());

        // Create "Retry" button
        Button retryButton = Settings.createStyledButton("Retry");
        retryButton.setOnAction(e -> controller.retry());

        // Create "Go Back" button
        Button goBackButton = Settings.createStyledButton("Go Back");
        goBackButton.setOnAction(e -> controller.goBack());

        // Create layout for the pause menu
        pauseMenu = new VBox(20);
        pauseMenu.setAlignment(Pos.CENTER);
        pauseMenu.getChildren().addAll(resumeButton, retryButton, goBackButton);
    }
    public void showPauseMenu() {
        // Show the pause menu in the center of the screen
        root.getChildren().add(pauseMenuOverlay);
        root.getChildren().add(pauseMenu);


    }

    public void hidePauseMenu() {
        // Hide the pause menu
        root.getChildren().removeAll(pauseMenu, pauseMenuOverlay);
    }
}
