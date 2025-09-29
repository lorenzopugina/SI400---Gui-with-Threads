package ui;

import core.ConfigManager;
import java.awt.*;
import java.util.Random;

public class FallingShape {
    private int x, y, size;
    private int speed;
    private Color color;
    private ConfigManager.ShapeType shapeType;
    private final Random random = new Random();

    public FallingShape(int panelWidth, int panelHeight) {
        reset(panelWidth, panelHeight);
    }

    public void reset(int panelWidth, int panelHeight) {
        x = random.nextInt(Math.max(1, panelWidth));
        y = -random.nextInt(100);
        size = 20 + random.nextInt(20);
        
        speed = 1 + random.nextInt(3); // base speed 

        color = new Color(
            random.nextInt(256),
            random.nextInt(256),
            random.nextInt(256),
            150
        );

        updateShapeType(ConfigManager.getShapeType(), panelWidth, panelHeight);
    }

    public void updateShapeType(ConfigManager.ShapeType configType, int panelWidth, int panelHeight) {
        if (configType == ConfigManager.ShapeType.RANDOM) {
            ConfigManager.ShapeType[] available = {
                ConfigManager.ShapeType.CIRCLE,
                ConfigManager.ShapeType.SQUARE,
                ConfigManager.ShapeType.TRIANGLE
            };
            shapeType = available[random.nextInt(available.length)];
        } else {
            shapeType = configType;
        }
    }

    public void update(int panelWidth, int panelHeight) {
        ConfigManager.Direction dir = ConfigManager.getDirection();
        
        int effectiveSpeed = speed + (ConfigManager.getAnimationSpeed() / 3);
        
        switch (dir) {
            case DOWN -> y += effectiveSpeed;
            case DIAGONAL_RIGHT -> { x += effectiveSpeed; y += effectiveSpeed; }
            case DIAGONAL_LEFT -> { x -= effectiveSpeed; y += effectiveSpeed; }
        }

        if (y > panelHeight + size) {
            reset(panelWidth, panelHeight);
        }
    }

    public void draw(Graphics2D g2d) {
        Color configColor = ConfigManager.getBackgroundColor();
        Color drawColor = (configColor != null)
                ? new Color(configColor.getRed(), configColor.getGreen(), configColor.getBlue(), 150)
                : color;

        g2d.setColor(drawColor);

        switch (shapeType) {
            case CIRCLE -> g2d.fillOval(x, y, size, size);
            case SQUARE -> g2d.fillRect(x, y, size, size);
            case TRIANGLE -> {
                int[] xPoints = {x + size / 2, x, x + size};
                int[] yPoints = {y, y + size, y + size};
                g2d.fillPolygon(xPoints, yPoints, 3);
            }
            case RANDOM -> {
                // this should never happen, just for safety
                g2d.fillOval(x, y, size, size);
            }
        }
    }
}