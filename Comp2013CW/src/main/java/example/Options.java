package example;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Options {

    public static void display(Stage Stage) {

        Stage.setTitle("Options");
        Stage.setResizable(false);

        // Create controls for the Options window
        Label colorLabel = new Label("Color Scheme:");
        ComboBox<String> colorComboBox = new ComboBox<>();
        colorComboBox.getItems().addAll("Default", "Dark Mode", "Light Mode" , "Green");
        colorComboBox.setValue("Default");

        CheckBox soundCheckBox = new CheckBox("Sound On/Off");

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> Stage.close());


        Button goBackButton = new Button("Go Back");
        goBackButton.setOnAction(e -> Options.goBack());

        // Create layout
        GridPane layout = new GridPane();
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10));

        layout.add(colorLabel, 0, 0);
        layout.add(colorComboBox, 1, 0);
        layout.add(soundCheckBox, 0, 1, 2, 1);
        layout.add(goBackButton, 0, 2);
        layout.add(closeButton, 1, 2);

        // Set up the scene
        Scene scene = new Scene(layout, 300, 200);
        Stage.setScene(scene);
        Stage.show();
    }

    private static void goBack() {
        MainMenu.display();
    }


}
