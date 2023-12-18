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
    import javafx.scene.paint.Color;
    import javafx.scene.text.Font;
    import javafx.stage.Stage;

    import java.util.ArrayList;
    import java.util.List;


    import static example.Settings.Settings.createComboBox;
    import static example.Utilities.ImageUtil.changeSnakeBodyImage;
    import static example.Utilities.ImageUtil.changeSnakeHeadImage;

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
            Label primaryColorLabel = Settings.createLabel("Primary Color");
            ColorPicker primaryColorPicker = Settings.createColorPicker(Settings.PrimaryColor);


            Label secondaryColorLabel =  Settings.createLabel("Secondary Color");
            ColorPicker secondaryColorPicker = Settings.createColorPicker(Settings.SecondaryColor);

            ComboBox<String> fontComboBox = Settings.createComboBox( FXCollections.observableArrayList(Font.getFamilies()));
            fontComboBox.setPromptText("Fonts");



            Label textSizeLabel = Settings.createLabel("Text Size");
            Slider textSizeSlider = Settings.createTextSizeSlider();


            textSizeSlider.setShowTickMarks(true);
            textSizeSlider.setSnapToTicks(true);

            ComboBox<Settings.FontWeightEnum> boldComboBox;
            ObservableList<Settings.FontWeightEnum> comboBoxData = FXCollections.observableArrayList(
                    Settings.FontWeightEnum.BOLD,
                    Settings.FontWeightEnum.BOLDER,
                    Settings.FontWeightEnum.LIGHTER,
                    Settings.FontWeightEnum.NORMAL
            );
            boldComboBox = createComboBox(comboBoxData);
            boldComboBox.setValue(Settings.fontWeight);
            
            Button Apply =  Settings.createStyledButton("Apply");
            Apply.setOnAction(e ->{
            ApplyUI(primaryColorPicker.getValue() , secondaryColorPicker.getValue() ,
                    fontComboBox.getValue() , textSizeSlider.getValue() ,
                    boldComboBox.getValue());
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


            uiSettingsLayout.setBackground(Settings.ReturnBackgroundFill());
            return uiSettingsLayout;
        }

        private static void ApplyUI(Color value, Color value1, String value2, double value3, Settings.FontWeightEnum selected) {
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
            headComboBox = createComboBox(comboBoxData);
            headComboBox.setValue(Settings.ReturnSnakeHeadName());

            // Add event handler for headComboBox
            headComboBox.setOnAction(event -> {
                String selectedHead = headComboBox.getValue();
                System.out.println("Selected Head: " + selectedHead);
                ImageUtil.changeTempSnakeHeadImage(Settings.SnakeHeadLocation + "snake-head-" + selectedHead + ".png");
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
            bodyComboBox = createComboBox(comboBoxData);
            bodyComboBox.setValue(Settings.ReturnSnakeBodyName());

            // Add event handler for bodyComboBox
            bodyComboBox.setOnAction(event -> {
                String selectedBody = bodyComboBox.getValue();
                System.out.println("Selected Body: " + selectedBody);
                ImageUtil.changeTempSnakeBodyImage(Settings.SnakeBodyLocation + "snake-body-"+ selectedBody +".png");
                StackPane updatedGamePreview = createGamePreview(new ImageView(ImageUtil.images.get("temp-snake-head")) , new ImageView(ImageUtil.images.get("temp-snake-body")) );
                layout.getChildren().set(0, updatedGamePreview);


            });

            Button Apply =  Settings.createStyledButton("Apply");
            Apply.setOnAction(e ->{
                changeSnakeHeadImage(Settings.SnakeHeadLocation + "snake-head-"+ headComboBox.getValue() +".png");
                changeSnakeBodyImage(Settings.SnakeBodyLocation + "snake-body-"+ bodyComboBox.getValue()+ ".png");

                SettingsManager.saveGameSettings(headComboBox.getValue() , bodyComboBox.getValue());
            });
            // Create "Go Back" button
            Button goBackButton = Settings.createStyledButton("Go Back");
            goBackButton.setOnAction(e -> {
                MainMenu.display();
            });



            // Add controls to change head and body
            layout.getChildren().addAll(headComboBox, bodyComboBox,goBackButton,Apply);

            layout.setBackground(Settings.ReturnBackgroundFill());
            return layout;
        }




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




        private static void goBack() {
            MainMenu.display();
        }


}
