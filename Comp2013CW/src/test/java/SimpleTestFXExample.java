import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

public class SimpleTestFXExample extends ApplicationTest {

    @Override
    public void start(Stage stage) {
        Button button = new Button("Click me");
        button.setId("myButton"); // Set an ID for easier identification in TestFX

        button.setOnAction(event -> button.setText("Clicked!"));

        Scene scene = new Scene(button, 200, 100);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testButtonClick() {
        try {


            // Verify that the button exists and has the expected text
            verifyThat("#myButton", hasText("Click me"));

            // Click the button
            clickOn("#myButton");

            // Verify that the button text has changed after clicking
            verifyThat("#myButton", hasText("Clicked!"));
        } catch (Exception e) {
            // Log or ignore the exception
            e.printStackTrace();
        }

    }
}
