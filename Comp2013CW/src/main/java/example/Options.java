    package example;

    import javafx.geometry.Insets;
    import javafx.scene.Scene;
    import javafx.scene.control.Button;
    import javafx.scene.control.CheckBox;
    import javafx.scene.control.ComboBox;
    import javafx.scene.control.Label;
    import javafx.scene.image.Image;
    import javafx.scene.image.ImageView;
    import javafx.scene.layout.Background;
    import javafx.scene.layout.BackgroundFill;
    import javafx.scene.layout.GridPane;
    import javafx.scene.layout.VBox;
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
            ImageView gamePreview = createGamePreview();
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

        private static ImageView createGamePreview() {
            ImageView gamePreview = new ImageView(); // Replace with actual images
            gamePreview.setFitWidth(400);
            gamePreview.setFitHeight(200);
            gamePreview.setImage(new ImageView(ImageUtil.images.get("UI-background")).getImage());
            return gamePreview;
        }

        private static void goBack() {
            MainMenu.display();
        }


}
