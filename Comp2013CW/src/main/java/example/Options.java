    package example;

    import javafx.geometry.Insets;
    import javafx.geometry.Pos;
    import javafx.scene.Scene;
    import javafx.scene.control.Button;
    import javafx.scene.control.CheckBox;
    import javafx.scene.control.ComboBox;
    import javafx.scene.control.Label;
    import javafx.scene.image.Image;
    import javafx.scene.image.ImageView;
    import javafx.scene.layout.*;
    import javafx.scene.paint.Color;
    import javafx.scene.paint.LinearGradient;
    import javafx.scene.paint.Stop;
    import javafx.stage.Modality;
    import javafx.stage.Stage;

    public class Options {
        private static final int FRAME_WIDTH = 900;
        private static final int FRAME_HEIGHT = 600;

        public static void display(Stage stage) {
            stage.setTitle("Options");
            stage.setResizable(false);


            VBox layout = new VBox(10);
            layout.setPadding(new Insets(10));

            //game preview
            StackPane gamePreview = createGamePreview();
            layout.getChildren().add(gamePreview);


            ComboBox<String> headComboBox = new ComboBox<>();
            headComboBox.getItems().addAll("Head1", "Head2", "Head3");
            headComboBox.setValue("Current Head"); // Set the default value
            ComboBox<String> bodyComboBox = new ComboBox<>();
            bodyComboBox.getItems().addAll("Body1", "Body2", "Body3"); // Add your body options
            bodyComboBox.setValue("Current Body"); // Set the default value

            // Add controls to change head and body
            layout.getChildren().addAll(headComboBox, bodyComboBox);



            Scene scene = new Scene(layout, FRAME_WIDTH, FRAME_HEIGHT);
            stage.setScene(scene);
            stage.show();
        }

        private static StackPane createGamePreview() {
            // Create ImageViews for each image
            ImageView backgroundImageView = new ImageView(ImageUtil.images.get("UI-background"));
            ImageView headImageView = new ImageView(ImageUtil.images.get("snake-head-right"));
            ImageView bodyImageView1 = new ImageView(ImageUtil.images.get("snake-body"));
            ImageView bodyImageView2 = new ImageView(ImageUtil.images.get("snake-body"));
            ImageView bodyImageView3 = new ImageView(ImageUtil.images.get("snake-body"));

            // Set the preserve ratio for images
            backgroundImageView.setPreserveRatio(true);
            headImageView.setPreserveRatio(true);
            bodyImageView1.setPreserveRatio(true);
            bodyImageView2.setPreserveRatio(true);
            bodyImageView3.setPreserveRatio(true);

            // Set the fit width and height for images
            backgroundImageView.setFitWidth(300);  // Adjust the width as needed
            backgroundImageView.setFitHeight(400);  // Adjust the height as needed
            headImageView.setFitWidth(30);
            bodyImageView1.setFitWidth(30);
            bodyImageView2.setFitWidth(30);
            bodyImageView3.setFitWidth(30);

            StackPane stackPane = new StackPane();

            // Add background and position it at the bottom
            stackPane.getChildren().add(backgroundImageView);
            StackPane.setAlignment(backgroundImageView, Pos.BOTTOM_CENTER);

            // Add bodies
            stackPane.getChildren().addAll(bodyImageView3, bodyImageView2, bodyImageView1);

            // Add head and translate it up to avoid overlap with the body
            stackPane.getChildren().add(headImageView);
            StackPane.setAlignment(headImageView, Pos.CENTER);

            headImageView.setTranslateX(30);  // Adjust the translation based on the head size and layout
            bodyImageView1.setTranslateX(0);
            bodyImageView2.setTranslateX(-30);
            bodyImageView3.setTranslateX(-60);

            return stackPane;
        }



        private static void goBack() {
            MainMenu.display();
        }


}
