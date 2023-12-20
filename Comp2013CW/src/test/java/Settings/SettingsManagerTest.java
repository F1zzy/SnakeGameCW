package Settings;

import example.Settings.Settings;
import example.Settings.SettingsManager;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SettingsManagerTest {
    private static final String TEST_SETTINGS_FILE_PATH = "src/test/test_resources/Settings/test-settings.properties";
    private static final String TEST_SAVE_FILE_PATH = "src/test/test_resources/Settings";
    @BeforeEach
    void setUp() {
        // Create a test settings file with initial values
        Properties properties = new Properties();
        properties.setProperty("head", "initialHead");
        properties.setProperty("body", "initialBody");
        properties.setProperty("primaryColor", "#FF0000");
        properties.setProperty("secondaryColor", "#00FF00");
        properties.setProperty("font", "Arial");
        properties.setProperty("textSize", "14");
        properties.setProperty("bold", "NORMAL");

        try (OutputStream output = new FileOutputStream(TEST_SETTINGS_FILE_PATH)) {
            properties.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
        // Delete the test settings file after each test
        File testSettingsFile = new File(TEST_SETTINGS_FILE_PATH);
        if (testSettingsFile.exists()) {
            testSettingsFile.delete();
        }
    }

    @Test
    void saveUISettings() {
        // Set the test settings file path
        SettingsManager.setSettingsFilePath(TEST_SETTINGS_FILE_PATH) ;

        // Save UI settings
        SettingsManager.saveUISettings(Color.RED, Color.BLUE, Font.font("Arial"), 16, Settings.FontWeightEnum.BOLD);

        // Load settings and verify
        SettingsManager.loadSettings();
        assertEquals(Color.RED, Settings.getPrimaryColor());
        assertEquals(Color.BLUE, Settings.getSecondaryColor());
        assertEquals(Font.font("Arial"), Settings.getFont());
        assertEquals(16, Settings.getTextSize());
        assertEquals(Settings.FontWeightEnum.BOLD, Settings.getFontWeight());
    }

    @Test
    void loadSettings() {
        // Set the test settings file path
        SettingsManager.setSettingsFilePath(TEST_SETTINGS_FILE_PATH) ;
        //Tear Settings Properties
        /*bold=BOLD
         font=Arial
         primaryColor=\#FF0000
         secondaryColor=\#0000FF
         textSize=16
         head=initialHead
         body=initialBody*/

        // Load settings and verify
        SettingsManager.loadSettings();
        assertEquals("initialHead", Settings.returnSnakeHeadName());
        assertEquals("initialBody", Settings.returnSnakeBodyName());
        assertEquals(Color.web("#FF0000"), Settings.getPrimaryColor());
        assertEquals(Color.web("#0000FF"), Settings.getSecondaryColor());
        assertEquals(Font.font("Arial"), Settings.getFont());
        assertEquals(16, Settings.getTextSize());
        assertEquals(Settings.FontWeightEnum.BOLD, Settings.fontWeight);
    }



    @Test
    void getFontWeightEnum() {
        assertEquals(Settings.FontWeightEnum.BOLD, SettingsManager.getFontWeightEnum("BOLD"));
        assertEquals(Settings.FontWeightEnum.NORMAL, SettingsManager.getFontWeightEnum("NORMAL"));
        assertEquals(Settings.FontWeightEnum.BOLDER, SettingsManager.getFontWeightEnum("BOLDER"));
        assertEquals(Settings.FontWeightEnum.LIGHTER, SettingsManager.getFontWeightEnum("LIGHTER"));
        assertNull(SettingsManager.getFontWeightEnum("INVALID"));
    }

    @Test
    void toHex() {
        assertEquals("#FF0000", SettingsManager.toHex(Color.RED));
        assertEquals("#008000", SettingsManager.toHex(Color.GREEN));
        assertEquals("#0000FF", SettingsManager.toHex(Color.BLUE));
        assertEquals("#FFFFFF", SettingsManager.toHex(Color.WHITE));
        assertEquals("#000000", SettingsManager.toHex(Color.BLACK));
    }



}
