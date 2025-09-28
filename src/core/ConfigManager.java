package core;

import java.awt.Color;

public class ConfigManager {
    private static Color backgroundColor = Color.RED;
    private static int animationSpeed = 100; // em ms

    public enum ShapeType {
        CIRCLE, SQUARE, TRIANGLE, RANDOM
    }

    private static ShapeType currentShape = ShapeType.CIRCLE;

    public static void setShapeType(ShapeType shape) {
        currentShape = shape;
    }

    public static ShapeType getShapeType() {
        return currentShape;
    }

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
        backgroundColor = Color.RED;
        animationSpeed = 100;
        currentShape = ShapeType.CIRCLE;
    }
}
