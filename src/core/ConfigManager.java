package core;

import java.awt.Color;

public class ConfigManager {
    private static Color backgroundColor = Color.BLUE;
    private static int animationSpeed = 100; // em ms

    public static Color getBackgroundColor() {
        return backgroundColor;
    }

    public static void setBackgroundColor(Color color) {
        backgroundColor = color;
    }

    public static int getAnimationSpeed() {
        return animationSpeed;
    }

    public static void setAnimationSpeed(int speed) {
        animationSpeed = speed;
    }

    public static void resetDefaults() {
        backgroundColor = Color.BLUE;
        animationSpeed = 100;
    }
}
