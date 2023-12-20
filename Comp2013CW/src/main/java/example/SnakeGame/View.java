package example.SnakeGame;

import example.MainMenu;
import example.SnakeGame.Model.GameObjects.EnemyObject;
import example.Utilities.ImageUtil;
import example.Settings.Settings;
import example.SnakeGame.Model.GameObjects.FoodObjects.Food;
import example.SnakeGame.Model.GameObjects.Snake;
import example.SnakeGame.Model.LevelManager.LevelState;
import example.SnakeGame.Model.Model;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static example.LeaderBoard.LeaderBoardUtil.GreaterThanHighScore;
/**
 * The View class represents the user interface and handles the rendering of the Snake Game.
 * It observes the Model for changes and updates the UI accordingly.
 */
public class View implements Observer {

    public Image background = ImageUtil.images.get("UI-background");
    public Image fail = ImageUtil.images.get("Fail-Scene");

    private static final int FRAME_WIDTH = MainMenu.FRAME_WIDTH;
    private static final int FRAME_HEIGHT = MainMenu.FRAME_HEIGHT;

    private Model model;
    private Controller controller;
    private StackPane root;
    private Canvas canvas;
    private GraphicsContext gc;

    private final Stage stage;

    private VBox pauseMenu;
    private StackPane pauseMenuOverlay;
    private Label levelStateLabel;
    private Label scoreLabel;
    /**
     * Constructs a new View instance with the provided Model and Controller.
     *
     * @param model      The Model instance to observe for changes.
     * @param controller The Controller instance to handle user input.
     */
    public View(Model model, Controller controller) {
        this.stage = MainMenu.getMainStage();
        this.model = model;
        this.controller = controller;

        initializeUI();

        initPauseMenu();
        model.addObserver(this);
    }
    /**
     * Initializes the user interface components, including the canvas, buttons, and labels.
     */
    private void initializeUI() {
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
        pauseButton.setId("pauseButton");
        pauseButton.setOnAction(e -> controller.pauseGame());
        StackPane.setAlignment(pauseButton, Pos.TOP_RIGHT);
        root.getChildren().add(pauseButton);

        root.requestFocus();

        scoreLabel = Settings.createLabel("Score: ");
        levelStateLabel = Settings.createLabel(model.getLevelState().getName());

        root.getChildren().addAll(scoreLabel , levelStateLabel);
        StackPane.setAlignment(scoreLabel, Pos.TOP_LEFT);
        StackPane.setAlignment(levelStateLabel , Pos.TOP_CENTER);
        pauseMenuOverlay = new StackPane(); // Initialize pauseMenuOverlay
        pauseMenuOverlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);"); // Adjust the alpha value as needed
        pauseMenuOverlay.setAlignment(Pos.CENTER);

        // Add the scene to the stage
        stage.setTitle("Snake Game");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    @Override
    public void update(Observable o, Object arg) {
        draw();
    }

    /**
     * Draws the game elements on the canvas, including the background, snake, foods, and score
     * And Level Specifc Objects eg, Enenmy.
     */
    public void draw() {
        //Clear Screen
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());


        //If Game has not ended Draw to Screen
        if (!model.endGame()) {

            drawBackground();
            drawSnake();
            drawFoods();
            drawScore();
            drawLevelState();
            if(model.getLevelState().getType() == LevelState.LevelStageType.ENENMY) drawEnemy();
            if(model.getLevelState().getType() == LevelState.LevelStageType.NEGATIVEFOOD) drawNegativeFood();
        }
    }
    /**
     * Draws the enemy object
     * Only Used in AIEnemy Level State .
     */
    private void drawEnemy() {
        EnemyObject enemy = model.getEnemyObject();
        gc.drawImage(enemy.getImage(), enemy.getX(), enemy.getY());
    }

    /**
     * Draws the current level state on the canvas.
     */
    private void drawLevelState() {
        // Get the current level state name
        levelStateLabel.setText(model.getLevelState().getName());
    }
    /**
     * Draws the background image on the canvas.
     */
    private void drawBackground() {

        Image background = model.getLevelState().getLevelBackground();

        gc.drawImage(background, 0, 0, canvas.getWidth(), canvas.getHeight());
    }
    /**
     * Draws the snake on the canvas, including the snake head and body segments.
     */
    private void drawSnake() {

        Snake snake = model.getSnake();
        if(!snake.getVisible()) return;
        Image snakeHead = ImageUtil.images.get("snake-head-right");
        Image newImgSnakeHead = snakeHead;
        List<Point> bodyPoints = snake.getBodyPoints();
        bodyPoints.add(new Point(snake.getX(), snake.getY()));

        if (bodyPoints.size() == (snake.getLength() + 1) * snake.getNumOfBodies()) {
            bodyPoints.remove(0);
        }

        switch (snake.Direction) {
            case 1:
                newImgSnakeHead = ImageUtil.rotateImage(snakeHead, -90);
                break;
            case 3:
                newImgSnakeHead = ImageUtil.rotateImage(snakeHead, 90);
                break;
            case 4:
                newImgSnakeHead = ImageUtil.rotateImage(snakeHead, -180);
                break;
            default:
                break;
        }

        gc.drawImage(newImgSnakeHead, snake.getX(), snake.getY());

        // Draw snake body
        drawBody();
    }
    /**
     * Draws the snake body on the canvas.
     */
    private void drawBody() {

        Snake snake = model.getSnake();
        if(!snake.getVisible()) return;
        Image snakeBody = ImageUtil.images.get("snake-body");
        int length = snake.getBodyPoints().size() - 1 - snake.getNumOfBodies();

        for (int i = length; i >= snake.getNumOfBodies(); i -= snake.getNumOfBodies()) {
            Point point = snake.getBodyPoints().get(i);
            gc.drawImage(snakeBody, point.x, point.y);
        }
    }
    /**
     * Draws the food objects on the canvas
     */
    private void drawFoods() {
        List<Food> foods = model.getFoodsList();
        for (Food food : foods) {
            gc.drawImage(food.getFoodImage(), food.getX(), food.getY());
        }


    }
    /**
     * Draws the Negative food objects
     * Only called if Level State is Negative Food
     */
    private  void drawNegativeFood(){
        List<Food> foods = model.getNegativeFoodsList();
        for (Food food : foods) {
            gc.drawImage(food.getFoodImage(), food.getX(), food.getY());
        }
    }

    /**
     * Draws the current score on the canvas, highlighting a new high score if achieved.
     */
    private void drawScore() {
        boolean isHighScore = GreaterThanHighScore(model.getScore());

        String scoreMessage = isHighScore
                ? "NEW HIGH SCORE: " + model.getScore()
                : "SCORE: " + model.getScore();

        // Update the text of the scoreLabel
        scoreLabel.setText(scoreMessage);
    }
    /**
     * Displays the game over scene, including buttons for "Go Back" and "Retry," and an input box for submitting scores.
     */
    public void gameOverScene() {
        drawBackground();
        model.getSnake().setVisible(true);
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

        Label endGameText = Settings.createLabel("You Died" , 90);
        StackPane.setAlignment(endGameText, Pos.TOP_CENTER);
        endGameText.setTranslateY(100);
        root.getChildren().add(endGameText);

        // Create layout for buttons
        HBox buttonsLayout = new HBox(10);
        buttonsLayout.setAlignment(Pos.CENTER);
        buttonsLayout.getChildren().addAll(usernameInput,submitButton, goBackButton, retryButton);

        // Create layout for the entire scene
        VBox allLayout = new VBox(20);
        allLayout.setAlignment(Pos.CENTER);

        allLayout.getChildren().addAll(buttonsLayout);

        // Draw buttons And Score
        drawScore();
        root.getChildren().add(buttonsLayout);
    }




    public Canvas getCanvas() {
        return canvas;
    }
    /**
     * Updates the countdown display with the given number or "GO."
     *
     * @param i The countdown number.
     */
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

    /**
     * Initializes the pause menu layout with buttons such as "Resume," "Retry," and "Go Back."
     */
    public void initPauseMenu() {

        // Create "Resume" button
        Button resumeButton = Settings.createStyledButton("Resume");
        resumeButton.setOnAction(e -> controller.resumeGame());
        resumeButton.setId("resumeButton");

        // Create "Retry" button
        Button retryButton = Settings.createStyledButton("Retry");
        retryButton.setOnAction(e -> controller.retry());
        retryButton.setId("retryButton");

        // Create "Go Back" button
        Button goBackButton = Settings.createStyledButton("Go Back");
        goBackButton.setOnAction(e -> controller.goBack());
        goBackButton.setId("goBackButton");

        // Create layout for the pause menu
        pauseMenu = new VBox(20);
       pauseMenu.setAlignment(Pos.CENTER);
        pauseMenu.getChildren().addAll(resumeButton, retryButton, goBackButton);
    }

    /**
     * Displays the pause menu in the center of the screen.
     * With A Dimmed overlay
     */
    public void showPauseMenu() {
        // Show the pause menu in the center of the screen
        root.getChildren().add(pauseMenuOverlay);
        root.getChildren().add(pauseMenu);
    }
    /**
     * Hides the pause menu.
     */
    public void hidePauseMenu() {
        // Hide the pause menu
       root.getChildren().removeAll(pauseMenu , pauseMenuOverlay);
        root.requestFocus();
    }
}
