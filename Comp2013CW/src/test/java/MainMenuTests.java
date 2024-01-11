import example.Main;
import example.MainMenu;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

public class MainMenuTests extends ApplicationTest {

    private Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        Main.setPrimary(stage); // Close the main stage if already open
        MainMenu.display();
    }

    @Test
    public void shouldOpenOptionsWhenOptionsButtonClicked() {
        assertEquals("Main Menu" , stage.getTitle());
        // Click the "Options" button
        clickOn("#optionsButton");
        // Verify that the Options window is open
        assertEquals( "Options",stage.getTitle() );
    }

    @Test
    public void shouldStartGameWhenPlayButtonClicked() {
        assertEquals("Main Menu" , stage.getTitle());
        // Click the "Options" button
        clickOn("#playButton");
        // Verify that the Options window is open
        assertEquals( "Snake Game",stage.getTitle() );
    }

    @Test
    public void shouldOpenLeaderboardWhenLeaderboardButtonClicked() {
        assertEquals("Main Menu" , stage.getTitle());
        // Click the "Options" button
        clickOn("#leaderboardsButton");
        // Verify that the Options window is open
        assertEquals( "Leaderboard",stage.getTitle() );
    }

    @Test
    public void shouldCloseApplicationWhenExitButtonClicked() {
        // Click the "Exit" button
        clickOn("#exitButton");
        assertFalse(Main.getPrimary().isShowing());
    }

    @Override
    public void stop() throws Exception {
        FxToolkit.hideStage();
    }
}
