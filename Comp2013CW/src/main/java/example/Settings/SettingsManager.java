

package example.Settings;

import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javafx.scene.text.Font;

/**
 * The SettingsManager class provides methods for saving and loading game and UI settings
 * to and from a properties file.
 */
public class SettingsManager {
    // Constants for file paths
    private static  String SETTINGS_FILE_PATH = "src/main/resources/SaveFiles/settings.properties";
    static {
        loadSettings();
    }

    /**
     * Saves game settings (head and body) to the properties file.
     *
     * @param head The selected snake head image name.
     * @param body The selected snake body image name.
     */
    public static void saveGameSettings(String head, String body) {
        Properties properties = new Properties();

        try (FileInputStream input = new FileInputStream(SETTINGS_FILE_PATH)) {
            // Load existing properties
            properties.load(input);
        } catch (IOException e) {
            // Handle or log the exception
            e.printStackTrace(); // You might want to replace this with appropriate logging
        }

        // Update only "head" and "body" properties
        properties.setProperty("head", head);
        properties.setProperty("body", body);

        try (FileOutputStream output = new FileOutputStream(SETTINGS_FILE_PATH)) {
            // Save the updated properties back to the file
            properties.store(output, null);
        } catch (IOException e) {
            // Handle or log the exception
            e.printStackTrace(); // You might want to replace this with appropriate logging
        }
    }
    /**
     * Saves UI settings (primaryColor, secondaryColor, font, textSize, bold) to the properties file.
     * Also loads the settings immediately after saving.
     *
     * @param primaryColor   The primary color selected by the user.
     * @param secondaryColor The secondary color selected by the user.
     * @param font           The selected font.
     * @param textSize       The selected text size.
     * @param bold           The selected font weight.
     */
    public static void saveUISettings(Color primaryColor, Color secondaryColor, Font font, int textSize , Settings.FontWeightEnum bold) {
        Properties properties = new Properties();

        try (FileInputStream input = new FileInputStream(SETTINGS_FILE_PATH)) {
            // Load existing properties
            properties.load(input);
        } catch (IOException e) {
            // Handle or log the exception
            e.printStackTrace(); // You might want to replace this with appropriate logging
        }

        // Update only UI  properties
        properties.setProperty("primaryColor", toHex(primaryColor));
        properties.setProperty("secondaryColor", toHex(secondaryColor));
        properties.setProperty("font", font.getFamily());
        properties.setProperty("textSize", String.valueOf(textSize));
        properties.setProperty("bold" ,String.valueOf(bold) );

        try (FileOutputStream output = new FileOutputStream(SETTINGS_FILE_PATH)) {
            // Save the updated properties back to the file
            properties.store(output, null);
        } catch (IOException e) {
            // Handle or log the exception
            e.printStackTrace(); // You might want to replace this with appropriate logging
        }
        loadSettings();
    }
    /**
     * Loads settings from the properties file and updates the Settings class to update Stage.
     */
    public static void loadSettings() {
        try (FileInputStream input = new FileInputStream(SETTINGS_FILE_PATH)) {
            Properties properties = new Properties();
            properties.load(input);

            String head = properties.getProperty("head");
            String body = properties.getProperty("body");
            String primaryColor = properties.getProperty("primaryColor");
            String secondaryColor = properties.getProperty("secondaryColor");
            String font = properties.getProperty("font");
            String textSize = properties.getProperty("textSize");
            String isBold = properties.getProperty("bold");
            Settings.SnakeHeadPath = "snake-head-"+ head + ".png";
            Settings.SnakeBodyPath = "snake-body-" + body + ".png";

            // Set the loaded values to the Settings class
            Settings.primaryColor = Color.web(primaryColor);
            Settings.secondaryColor = Color.web(secondaryColor);
            Settings.font = Font.font(font);
            Settings.textSize = Integer.parseInt(textSize);
            Settings.fontWeight = getFontWeightEnum(isBold);

            System.out.println("Loaded Head: " + head);
            System.out.println("Loaded Body: " + body);
            System.out.println("Loaded Primary Color: " + Settings.primaryColor);
            System.out.println("Loaded Secondary Color: " + Settings.secondaryColor);
            System.out.println("Loaded Font: " + Settings.font.getFamily());
            System.out.println("Loaded Text Size: " + Settings.textSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Converts a string representation of font weight to the corresponding enum value.
     *
     * @param isBold The string representation of font weight.
     * @return The corresponding FontWeightEnum value.
     */
    public static Settings.FontWeightEnum getFontWeightEnum(String isBold) {
        return switch (isBold) {
            case "BOLD" -> Settings.FontWeightEnum.BOLD;
            case "NORMAL" -> Settings.FontWeightEnum.NORMAL;
            case "BOLDER" -> Settings.FontWeightEnum.BOLDER;
            case "LIGHTER" -> Settings.FontWeightEnum.LIGHTER;
            default -> null;
        };
    }



    /**
     * Converts a Color object to its hexadecimal representation.
     *
     * @param color The Color object to convert.
     * @return The hexadecimal representation of the color.
     */
    public static String toHex(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255)
        );
    }


    //Testing Utitilities

    public static void setSettingsFilePath( String path) {
        SETTINGS_FILE_PATH = path;
    }
}

