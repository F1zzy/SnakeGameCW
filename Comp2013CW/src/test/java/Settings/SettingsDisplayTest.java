package Settings;

import example.Main;
import example.Settings.Settings;
import example.Settings.SettingsDisplay;
import example.Utilities.ImageUtil;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;

public class SettingsDisplayTest extends ApplicationTest {
    @Override
    public void start(Stage stage) {
        Main.setPrimary(stage);
        Settings.setTestSettings();
        SettingsDisplay.display(stage);
    }
    @Test
    public void testUISettings() {
        // Assuming UI settings components are displayed
        ColorPicker primaryColorPicker = lookup("#primaryColorPicker").query();
        ColorPicker secondaryColorPicker = lookup("#secondaryColorPicker").query();
        ComboBox<String> fontComboBox = lookup("#fontComboBox").query();
        Slider textSizeSlider = lookup("#textSizeSlider").query();
        ComboBox<Settings.FontWeightEnum> boldComboBox = lookup("#boldComboBox").query();

        // Test setting values
        clickOn(primaryColorPicker).type(KeyCode.RIGHT).type(KeyCode.ENTER);
        clickOn(secondaryColorPicker).type(KeyCode.LEFT).type(KeyCode.ENTER);
        clickOn(fontComboBox).type(KeyCode.DOWN).type(KeyCode.ENTER);
        //drag(textSizeSlider).by(10);
        clickOn(boldComboBox).type(KeyCode.DOWN).type(KeyCode.ENTER);

        // Assuming there is an "Apply" button
        clickOn("#applyButton");

        // Add assertions based on the expected behavior of applying settings
        // ...

        // Assuming there is a "Go Back" button
        clickOn("#goBackButton");
        // Verify that the main menu is displayed
        // ...
    }

    @Test
    public void testGameObjectSettings() {
        //Change to Random
        ImageUtil.changeSnakeHeadImage(Settings.SnakeHeadLocation + "snake-head-deer.png");
        ImageUtil.changeSnakeBodyImage(Settings.SnakeBodyLocation + "snake-body-moon.png");

        ImageUtil.changeTempSnakeHeadImage(Settings.SnakeHeadLocation + "snake-head-deer.png");
        ImageUtil.changeTempSnakeBodyImage(Settings.SnakeBodyLocation + "snake-body-moon.png");

        // Assuming game object settings components are displayed
        ComboBox<String> headComboBox = lookup("#headComboBox").query();
        ComboBox<String> bodyComboBox = lookup("#bodyComboBox").query();

        // Test setting values to Default
        clickOn(headComboBox).type(KeyCode.UP).type(KeyCode.UP).type(KeyCode.UP).type(KeyCode.UP).type(KeyCode.UP).type(KeyCode.ENTER);
        clickOn(bodyComboBox).type(KeyCode.UP).type(KeyCode.UP).type(KeyCode.UP).type(KeyCode.UP).type(KeyCode.UP).type(KeyCode.ENTER);

        Assertions.assertEquals("default", headComboBox.getValue());
        Assertions.assertEquals("default", bodyComboBox.getValue());

        clickOn("#applyButton");
        //System.out.println(ImageUtil.images.get("snake-head-right").getUrl());
        Image defaultHeadIm = new Image(String.valueOf(SettingsDisplayTest.class.getResource(Settings.SnakeHeadLocation + "snake-head-default.png")));
        Image defaultBodyIm = new Image(String.valueOf(SettingsDisplayTest.class.getResource(Settings.SnakeBodyLocation  + "snake-body-default.png")));

        Assertions.assertEquals(ImageUtil.images.get("snake-head-right").getUrl() , defaultHeadIm.getUrl() );
        Assertions.assertEquals(ImageUtil.images.get("snake-body").getUrl() , defaultBodyIm.getUrl());

    }

    @Test
    public void testGoBackButton() {
        clickOn("#goBackButton");
        verifyThat("#mainMenuTitle", hasText("Snake Game"));
    }

    @Override
    public void stop() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{}); // release all held keys
        release(new MouseButton[]{}); // release all held mouse buttons
    }

}
