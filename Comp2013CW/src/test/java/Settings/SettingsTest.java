package Settings;

import example.LeaderBoard.ScoreEntry;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import example.Settings.Settings;

import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.control.*;


class SettingsTest {

    @BeforeEach
    void setUp() {
        // Set test settings before each test
        Settings.setTestSettings();
    }

    @BeforeAll
    static void setUpJavaFX() {

    }

    @Test
    void returnBackgroundFill() {
        // Test the creation of BackgroundFill
        Background background = Settings.returnBackgroundFill();
        assertNotNull(background);
    }

    @Test
    void createStyledButton() {
        // Test the creation of a styled button
        Button button = Settings.createStyledButton("Test Button");
        assertNotNull(button);
        assertEquals("Test Button", button.getText());
    }

    @Test
    void createComboBox() {
        // Test the creation of a styled ComboBox
         ComboBox<String> comboBox = Settings.createComboBox(null);
        assertNotNull(comboBox);
    }

    @Test
    void createLabel() {
        // Test the creation of a styled label
        Label label = Settings.createLabel("Test Label");
        assertNotNull(label);
        assertEquals("Test Label", label.getText());
    }

    @Test
    void createColorPicker() {
        // Test the creation of a styled ColorPicker
        ColorPicker colorPicker = Settings.createColorPicker(Color.BLUE);
        assertNotNull(colorPicker);
        assertEquals(Color.BLUE, colorPicker.getValue());
    }

    @Test
    void createTextSizeSlider() {
        // Test the creation of a text size slider
        Slider slider = Settings.createTextSizeSlider();
        assertNotNull(slider);
    }

    @Test
    void createTableView() {
        // Test the creation of a styled TableView
        TableView<ScoreEntry> tableView = Settings.createTableView(null);
        assertNotNull(tableView);
    }

    @Test
    void createTableColumn() {
        // Test the creation of a styled TableColumn
        TableColumn<ScoreEntry, String> column = Settings.createTableColumn("Username");
        assertNotNull(column);
        assertEquals("Username", column.getText());
    }

    @Test
    void getHex() {
        // Test the conversion of Color to hexadecimal representation
        String hex = Settings.getHex(Color.GREEN);
        assertEquals("#008000", hex);
    }

    @Test
    void getPrimaryColor() {
        // Test getting the primary color
        Color primaryColor = Settings.getPrimaryColor();
        assertEquals(Color.RED, primaryColor);
    }

    @Test
    void getSecondaryColor() {
        // Test getting the secondary color
        Color secondaryColor = Settings.getSecondaryColor();
        assertEquals(Color.BLACK, secondaryColor);
    }

    @Test
    void getFont() {
        // Test getting the font
        Font font = Settings.getFont();
        assertEquals(Font.font("Calibri"), font);
    }

    @Test
    void getTextSize() {
        // Test getting the text size
        int textSize = Settings.getTextSize();
        assertEquals(19, textSize);
    }

    @Test
    void getFontWeight() {
        // Test getting the font weight
        Settings.FontWeightEnum fontWeight = Settings.getFontWeight();
        assertEquals(Settings.FontWeightEnum.NORMAL, fontWeight);
    }
}

