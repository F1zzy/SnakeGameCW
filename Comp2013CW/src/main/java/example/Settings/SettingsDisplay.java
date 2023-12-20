    package example.Settings;

    import example.Utilities.ImageUtil;
    import example.MainMenu;
    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import javafx.geometry.Insets;
    import javafx.geometry.Pos;
    import javafx.scene.Scene;
    import javafx.scene.control.*;
    import javafx.scene.image.ImageView;
    import javafx.scene.layout.*;
    import javafx.scene.text.Font;
    import javafx.stage.Stage;

    import java.util.ArrayList;
    import java.util.List;


    import static example.Settings.Settings.createComboBox;
    import static example.Utilities.ImageUtil.changeSnakeBodyImage;
    import static example.Utilities.ImageUtil.changeSnakeHeadImage;

    /**
     * The Display to change all the settings.
     * It allows users to configure both game settings and UI settings.
     */
    public class SettingsDisplay {

        /**
         * Displays the settings window with tabs for game and UI settings.
         *
         * @param stage The JavaFX stage to display the settings window.
         */
        public static void display(Stage stage){
            // Create layouts for game and UI settings
            VBox gameSettingsLayout = createGameObjectSettingsLayout();
            VBox uiSettingsLayout = createUISettingsLayout();

            // Create a tab pane to switch between game and UI settings
            TabPane tabPane = new TabPane();
            Tab gameSettingsTab = new Tab("Game Settings", gameSettingsLayout);
            Tab uiSettingsTab = new Tab("UI Settings", uiSettingsLayout);
            tabPane.getTabs().addAll(gameSettingsTab, uiSettingsTab);

            // Set up the scene with the tab pane
            Scene scene = new Scene(tabPane, MainMenu.FRAME_WIDTH, MainMenu.FRAME_HEIGHT);
            stage.setScene(scene);
            stage.setTitle("Options");
            stage.show();
        }

        /**
         * Creates the layout for UI settings.
         *
         * @return The VBox layout containing UI settings components.
         */
        private static VBox createUISettingsLayout() {
            // Create layout for UI settings
            VBox uiSettingsLayout = new VBox(10);
            uiSettingsLayout.setPadding(new Insets(10));

            // UI settings components
            Label primaryColorLabel = Settings.createLabel("Primary Color");
            ColorPicker primaryColorPicker = Settings.createColorPicker(Settings.primaryColor);
            primaryColorPicker.setId("primaryColorPicker");

            Label secondaryColorLabel =  Settings.createLabel("Secondary Color");
            ColorPicker secondaryColorPicker = Settings.createColorPicker(Settings.secondaryColor);
            secondaryColorPicker.setId("secondaryColorPicker");

            ComboBox<String> fontComboBox = Settings.createComboBox( FXCollections.observableArrayList(Font.getFamilies()));
            fontComboBox.setPromptText("Fonts");
            fontComboBox.setId("fontComboBox");


            Label textSizeLabel = Settings.createLabel("Text Size");
            Slider textSizeSlider = Settings.createTextSizeSlider();
            textSizeSlider.setMin(1);
            textSizeSlider.setMax(25);
            textSizeSlider.setValue(Settings.textSize);
            textSizeSlider.setId("textSizeSlider");

            ComboBox<Settings.FontWeightEnum> boldComboBox;
            ObservableList<Settings.FontWeightEnum> comboBoxData = FXCollections.observableArrayList(
                    Settings.FontWeightEnum.BOLD,
                    Settings.FontWeightEnum.BOLDER,
                    Settings.FontWeightEnum.LIGHTER,
                    Settings.FontWeightEnum.NORMAL
            );
            boldComboBox = createComboBox(comboBoxData);
            boldComboBox.setValue(Settings.fontWeight);

            boldComboBox.setId("boldComboBox");

            // Apply button
            Button Apply =  Settings.createStyledButton("Apply");
            Apply.setOnAction(e ->{
            SettingsManager.saveUISettings(primaryColorPicker.getValue() , secondaryColorPicker.getValue() ,
                    Font.font(fontComboBox.getValue()), (int) textSizeSlider.getValue() , boldComboBox.getValue());


            });
            // Create "Go Back" button
            Button goBackButton = Settings.createStyledButton("Go Back");
            goBackButton.setOnAction(e -> {
                MainMenu.display();
            });

            // Add UI settings controls to the layout
            uiSettingsLayout.getChildren().addAll(
                    primaryColorLabel, primaryColorPicker,
                    secondaryColorLabel, secondaryColorPicker,
                    fontComboBox, textSizeLabel, textSizeSlider, boldComboBox , Apply , goBackButton
            );

            //Set Background to Gradiant Background
            uiSettingsLayout.setBackground(Settings.ReturnBackgroundFill());
            return uiSettingsLayout;
        }



        public static VBox createGameObjectSettingsLayout() {
            VBox layout = new VBox(10);
            layout.setPadding(new Insets(10));

            //Generate Game Preveiw to Show Game Snake.
            StackPane gamePreview = createGamePreview(new ImageView(ImageUtil.images.get("snake-head-right")) , new ImageView(ImageUtil.images.get("snake-body")));
            layout.getChildren().add(gamePreview);

            // UI settings components

            //Generate Head Combo Box
            ComboBox<String> headComboBox;
            ObservableList<String> comboBoxData = FXCollections.observableArrayList(
                    "default",
                    "helmet",
                    "alien",
                    "frog",
                    "ninja",
                    "deer"
            );
            headComboBox = createComboBox(comboBoxData);
            headComboBox.setValue(Settings.ReturnSnakeHeadName());
            headComboBox.setId("headComboBox");
            // Add event handler for headComboBox
            headComboBox.setOnAction(event -> {
                String selectedHead = headComboBox.getValue();
                System.out.println("Selected Head: " + selectedHead);
                ImageUtil.changeTempSnakeHeadImage(Settings.SnakeHeadLocation + "snake-head-" + selectedHead + ".png");
                StackPane updatedGamePreview = createGamePreview(new ImageView(ImageUtil.images.get("temp-snake-head")) , new ImageView(ImageUtil.images.get("temp-snake-body")) );
                layout.getChildren().set(0, updatedGamePreview);

            });


            //Generate Body Combo Box
            ComboBox<String> bodyComboBox;
            comboBoxData = FXCollections.observableArrayList(
                    "default",
                    "crystal",
                    "bubble",
                    "moon",
                    "diamond"
            );
            bodyComboBox = createComboBox(comboBoxData);
            bodyComboBox.setValue(Settings.ReturnSnakeBodyName());
            bodyComboBox.setId("bodyComboBox");
            // Add event handler for bodyComboBox
            bodyComboBox.setOnAction(event -> {
                String selectedBody = bodyComboBox.getValue();
                System.out.println("Selected Body: " + selectedBody);
                ImageUtil.changeTempSnakeBodyImage(Settings.SnakeBodyLocation + "snake-body-"+ selectedBody +".png");
                StackPane updatedGamePreview = createGamePreview(new ImageView(ImageUtil.images.get("temp-snake-head")) , new ImageView(ImageUtil.images.get("temp-snake-body")) );
                layout.getChildren().set(0, updatedGamePreview);


            });

            //Create Apply button
            Button applyButton =  Settings.createStyledButton("Apply");
            applyButton.setOnAction(e ->{
                changeSnakeHeadImage(Settings.SnakeHeadLocation + "snake-head-"+ headComboBox.getValue() +".png");
                changeSnakeBodyImage(Settings.SnakeBodyLocation + "snake-body-"+ bodyComboBox.getValue()+ ".png");

                SettingsManager.saveGameSettings(headComboBox.getValue() , bodyComboBox.getValue());
            });
            applyButton.setId("applyButton");
            // Create "Go Back" button
            Button goBackButton = Settings.createStyledButton("Go Back");
            goBackButton.setOnAction(e -> {
                MainMenu.display();
            });


            goBackButton.setId("goBackButton");
            // Add controls to change head and body
            layout.getChildren().addAll(headComboBox, bodyComboBox,goBackButton,applyButton);

            layout.setBackground(Settings.ReturnBackgroundFill());
            return layout;
        }


        /**
         * Creates the game preview layout with customizable snake head and body images.
         *
         * @param snakeHead The image view for the snake head.
         * @param snakeBody The image view for the snake body.
         * @return The stack pane containing the game preview layout.
         */
        private static StackPane createGamePreview(ImageView snakeHead, ImageView snakeBody) {
            List<ImageView> bodyImageViews = new ArrayList<>();
            ImageView backgroundImageView = new ImageView(ImageUtil.images.get("DefaultLevelState-background"));

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




}
