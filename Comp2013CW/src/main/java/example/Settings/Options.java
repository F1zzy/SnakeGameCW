    package example.Settings;

    import example.ImageUtil;
    import example.MainMenu;
    import example.Settings.Settings;
    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import javafx.geometry.Insets;
    import javafx.geometry.Pos;
    import javafx.scene.Scene;
    import javafx.scene.control.*;
    import javafx.scene.image.ImageView;
    import javafx.scene.layout.*;
    import javafx.stage.Stage;

    import java.util.ArrayList;
    import java.util.List;

    import static example.ImageUtil.changeSnakeBodyImage;
    import static example.ImageUtil.changeSnakeHeadImage;

    public class Options {
        private static final int FRAME_WIDTH = 900;
        private static final int FRAME_HEIGHT = 600;
        public static void display(Stage stage){
            VBox gameSettingsLayout = createGameSettingsLayout();
            VBox uiSettingsLayout = createUISettingsLayout();

            // Create a tab pane to switch between game and UI settings
            TabPane tabPane = new TabPane();
            Tab gameSettingsTab = new Tab("Game Settings", gameSettingsLayout);
            Tab uiSettingsTab = new Tab("UI Settings", uiSettingsLayout);
            tabPane.getTabs().addAll(gameSettingsTab, uiSettingsTab);

            // Set up the scene with the tab pane
            Scene scene = new Scene(tabPane, FRAME_WIDTH, FRAME_HEIGHT);
            stage.setScene(scene);
            stage.show();
        }

        private static VBox createUISettingsLayout() {
            // Create layout for UI settings
            VBox uiSettingsLayout = new VBox(10);
            uiSettingsLayout.setPadding(new Insets(10));

            // UI settings components
            Label primaryColorLabel = new Label("Primary Color");
            ColorPicker primaryColorPicker = new ColorPicker();

            Label secondaryColorLabel = new Label("Secondary Color");
            ColorPicker secondaryColorPicker = new ColorPicker();

            ComboBox<String> fontComboBox = new ComboBox<>();
            fontComboBox.setPromptText("Fonts");

            Label textSizeLabel = new Label("Text Size");
            Slider textSizeSlider = new Slider(10, 30, 10);
            textSizeSlider.setShowTickMarks(true);
            textSizeSlider.setSnapToTicks(true);

            CheckBox boldCheckBox = new CheckBox("Bold");

            // Add UI settings controls to the layout
            uiSettingsLayout.getChildren().addAll(
                    primaryColorLabel, primaryColorPicker,
                    secondaryColorLabel, secondaryColorPicker,
                    fontComboBox, textSizeLabel, textSizeSlider, boldCheckBox
            );

            return uiSettingsLayout;
        }


        public static VBox  createGameSettingsLayout() {
            VBox layout = new VBox(10);
            layout.setPadding(new Insets(10));

            //game preview
            StackPane gamePreview = createGamePreview(new ImageView(ImageUtil.images.get("snake-head-right")) , new ImageView(ImageUtil.images.get("snake-body")));
            layout.getChildren().add(gamePreview);


            ComboBox<String> headComboBox;
            ObservableList<String> comboBoxData = FXCollections.observableArrayList(
                    "default",
                    "helmet",
                    "alien",
                    "frog",
                    "ninja",
                    "deer"
            );
            headComboBox = Settings.createComboBox(comboBoxData);
            headComboBox.setValue(Settings.ReturnSnakeHeadName());

            // Add event handler for headComboBox
            headComboBox.setOnAction(event -> {
                String selectedHead = headComboBox.getValue();
                System.out.println("Selected Head: " + selectedHead);
                ImageUtil.changeTempSnakeHeadImage("snake-head-" + selectedHead + ".png");
                StackPane updatedGamePreview = createGamePreview(new ImageView(ImageUtil.images.get("temp-snake-head")) , new ImageView(ImageUtil.images.get("temp-snake-body")) );
                layout.getChildren().set(0, updatedGamePreview);

            });



            ComboBox<String> bodyComboBox;
            comboBoxData = FXCollections.observableArrayList(
                    "default",
                    "crystal",
                    "bubble",
                    "moon",
                    "diamond"
            );
            bodyComboBox = Settings.createComboBox(comboBoxData);
            bodyComboBox.setValue(Settings.ReturnSnakeBodyName());

            // Add event handler for bodyComboBox
            bodyComboBox.setOnAction(event -> {
                String selectedBody = bodyComboBox.getValue();
                System.out.println("Selected Body: " + selectedBody);
                ImageUtil.changeTempSnakeBodyImage("snake-body-"+ selectedBody +".png");
                StackPane updatedGamePreview = createGamePreview(new ImageView(ImageUtil.images.get("temp-snake-head")) , new ImageView(ImageUtil.images.get("temp-snake-body")) );
                layout.getChildren().set(0, updatedGamePreview);


            });

            Button Apply =  Settings.createStyledButton("Apply");
            Apply.setOnAction(e ->{
                changeSnakeHeadImage("snake-head-"+ headComboBox.getValue() +".png");
                changeSnakeBodyImage("snake-body-"+ bodyComboBox.getValue()+ ".png");
            });
            // Create "Go Back" button
            Button goBackButton = new Button("Go Back");
            goBackButton.setOnAction(e -> {
                MainMenu.display();
            });
            goBackButton.setStyle(Settings.CSSFormat);
            // Add controls to change head and body
            layout.getChildren().addAll(headComboBox, bodyComboBox,goBackButton,Apply);

            layout.setBackground(Settings.ReturnBackgroundFill());
            return layout;
        }




        private static StackPane createGamePreview(ImageView snakeHead, ImageView snakeBody) {
            List<ImageView> bodyImageViews = new ArrayList<>();
            ImageView backgroundImageView = new ImageView(ImageUtil.images.get("UI-background"));

            for (int i = 0; i < 3; i++) {
                ImageView bodyImageView = new ImageView(snakeBody.getImage());  // Create a new instance
                bodyImageView.setPreserveRatio(true);
                bodyImageView.setFitWidth(30);
                bodyImageViews.add(bodyImageView);
            }

            // Set the preserve ratio for images
            backgroundImageView.setPreserveRatio(true);
            snakeHead.setPreserveRatio(true);

            // Set the fit width and height for images
            backgroundImageView.setFitWidth(300);  // Adjust the width as needed
            backgroundImageView.setFitHeight(400);  // Adjust the height as needed
            snakeHead.setFitWidth(30);

            StackPane stackPane = new StackPane();

            // Add background and position it at the bottom
            stackPane.getChildren().add(backgroundImageView);
            StackPane.setAlignment(backgroundImageView, Pos.BOTTOM_CENTER);

            // Add bodies
            stackPane.getChildren().addAll(bodyImageViews);

            // Add head and translate it up to avoid overlap with the body
            stackPane.getChildren().add(snakeHead);
            StackPane.setAlignment(snakeHead, Pos.CENTER);

            snakeHead.setTranslateX(30);  // Adjust the translation based on the head size and layout

            // Set translations for bodies
            for (int i = 0; i < bodyImageViews.size(); i++) {
                ImageView bodyImageView = bodyImageViews.get(i);
                bodyImageView.setTranslateX(-30 * i);
            }

            return stackPane;
        }




        private static void goBack() {
            MainMenu.display();
        }


}
