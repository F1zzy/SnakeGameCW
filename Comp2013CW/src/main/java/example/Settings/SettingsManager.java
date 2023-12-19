

package example.Settings;

import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javafx.scene.text.Font;


public class SettingsManager {
    private static final String SAVE_FILE_PATH = "src/main/resources/SaveFiles";
    private static final String SETTINGS_FILE_PATH = "src/main/resources/SaveFiles/settings.properties";
    private static final String CSS_FILE_PATH = "/SaveFiles/settings.properties"; // Update with your actual path

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
            Settings.PrimaryColor = Color.web(primaryColor);
            Settings.SecondaryColor = Color.web(secondaryColor);
            Settings.font = Font.font(font);
            Settings.TextSize = Integer.parseInt(textSize);
            Settings.fontWeight = getFontWeightEnum(isBold);

            System.out.println("Loaded Head: " + head);
            System.out.println("Loaded Body: " + body);
            System.out.println("Loaded Primary Color: " + Settings.PrimaryColor);
            System.out.println("Loaded Secondary Color: " + Settings.SecondaryColor);
            System.out.println("Loaded Font: " + Settings.font.getFamily());
            System.out.println("Loaded Text Size: " + Settings.TextSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Settings.FontWeightEnum getFontWeightEnum(String isBold) {
        return switch (isBold) {
            case "BOLD" -> Settings.FontWeightEnum.BOLD;
            case "NORMAL" -> Settings.FontWeightEnum.NORMAL;
            case "BOLDER" -> Settings.FontWeightEnum.BOLDER;
            case "LIGHTER" -> Settings.FontWeightEnum.LIGHTER;
            default -> null;
        };
    }



    // Existing method to convert Color to hexadecimal
    private static String toHex(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255)
        );
    }


}

