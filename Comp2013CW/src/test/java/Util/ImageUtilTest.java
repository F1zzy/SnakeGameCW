package Util;

import Settings.SettingsDisplayTest;
import example.Main;
import example.MainMenu;
import example.Settings.Settings;
import example.Utilities.ImageUtil;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ImageUtilTest extends ApplicationTest {
    public void start(Stage stage) {

    }
    @Test
    public void testLoadSnakeImages() {
        assertNotNull(ImageUtil.images.get("snake-head-right"));
        assertNotNull(ImageUtil.images.get("snake-body"));
        assertNotNull(ImageUtil.images.get("temp-snake-head"));
        assertNotNull(ImageUtil.images.get("temp-snake-body"));
        assertNotNull(ImageUtil.images.get("Enemy"));
    }

    @Test
    public void testLoadObstacleImages() {


        // Assuming these images exist in your resources
        assertNotNull(ImageUtil.images.get("0"));
        assertNotNull(ImageUtil.images.get("1"));
        assertNotNull(ImageUtil.images.get("2"));
        assertNotNull(ImageUtil.images.get("3"));
        assertNotNull(ImageUtil.images.get("4"));
        assertNotNull(ImageUtil.images.get("5"));
        assertNotNull(ImageUtil.images.get("6"));
        assertNotNull(ImageUtil.images.get("7"));
        assertNotNull(ImageUtil.images.get("8"));
        assertNotNull(ImageUtil.images.get("9"));
        assertNotNull(ImageUtil.images.get("10"));
        assertNotNull(ImageUtil.images.get("11"));
        assertNotNull(ImageUtil.images.get("12"));
        assertNotNull(ImageUtil.images.get("13"));
        assertNotNull(ImageUtil.images.get("14"));
        assertNotNull(ImageUtil.images.get("15"));
        assertNotNull(ImageUtil.images.get("16"));
        assertNotNull(ImageUtil.images.get("raindrop"));
        assertNotNull(ImageUtil.images.get("Poo"));
    }

    @Test
    public void testLoadUIImages() {


        assertNotNull(ImageUtil.images.get("MainMenu-background"));
        assertNotNull(ImageUtil.images.get("DefaultLevelState-background"));

    }

    @Test
    public void testChangeSnakeHeadImage() {


        //Random image ( The logo )
        String newImagePath = "/Images/logo/snake-logo.png";
        Image defaultIm = new Image(String.valueOf(SettingsDisplayTest.class.getResource("/Images/logo/snake-logo.png")));
        ImageUtil.changeSnakeHeadImage(newImagePath);



        // Verify that the new snake body image is loaded
        assertNotNull(ImageUtil.images.get("snake-head-right"));
        assertEquals(defaultIm.getUrl(), ImageUtil.images.get("snake-head-right").getUrl());
    }

    @Test
    public void testChangeSnakeBodyImage() {


        //Random image ( The logo )
        String newImagePath = "/Images/logo/snake-logo.png";
        ImageUtil.changeSnakeBodyImage(newImagePath);

        Image bodyIm = new Image(String.valueOf(SettingsDisplayTest.class.getResource("/Images/logo/snake-logo.png")));

        // Verify that the new snake body image is loaded
        assertNotNull(ImageUtil.images.get("snake-body"));
        assertEquals(bodyIm.getUrl(), ImageUtil.images.get("snake-body").getUrl());
    }

    @Test
    void rotateImage_shouldRotateImageCorrectly() {
        // Create a sample image for testing
        Image originalImage = new Image(String.valueOf(ImageUtil.class.getResource("/Images/logo/snake-logo.png")));

        // Rotate the image by 90 degrees (clockwise)
        Image rotatedImage = ImageUtil.rotateImage(originalImage, 90);

        // Assert that the rotatedImage is not null
        assertThat(rotatedImage).isNotNull();

        // Assert that the width and height are swapped after rotation
        assertThat(rotatedImage.getWidth()).isEqualTo(originalImage.getHeight());
        assertThat(rotatedImage.getHeight()).isEqualTo(originalImage.getWidth());

        // You can add more assertions based on your specific requirements
    }

    // Additional tests can be added for other methods and edge cases
}
