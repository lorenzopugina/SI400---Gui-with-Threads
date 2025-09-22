package ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import core.ConfigManager;

public class BackgroundPanel extends JPanel implements Runnable {

    private static class Shape {
        int x, y, size;
        int dx, dy;   // velocidade em X e Y
        Color color;

        public Shape(int x, int y, int size, int dx, int dy, Color color) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.dx = dx;
            this.dy = dy;
            this.color = color;
        }
    }

    private Thread thread;
    private boolean running = true;
    private List<Shape> shapes;
    private Random rand = new Random();

    public BackgroundPanel() {
        shapes = new ArrayList<>();
        initShapes();
        startAnimation();
    }

    private void initShapes() {
        for (int i = 0; i < 20; i++) { // 20 formas
            int x = rand.nextInt(800);
            int y = rand.nextInt(600);
            int size = 20 + rand.nextInt(30);
            int dx = 1 + rand.nextInt(3); // velocidade em X
            int dy = 2 + rand.nextInt(4); // velocidade em Y
            Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            shapes.add(new Shape(x, y, size, dx, dy, color));
        }
    }

    private void startAnimation() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (running) {
            updateShapes();
            repaint();
            try {
                Thread.sleep(ConfigManager.getAnimationSpeed());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateShapes() {
        int width = getWidth();
        int height = getHeight();

        for (Shape s : shapes) {
            switch (ConfigManager.getDirection()) {
                case DOWN -> { s.y += s.dy; }
                case DIAGONAL_RIGHT -> { s.x += s.dx; s.y += s.dy; }
                case DIAGONAL_LEFT -> { s.x -= s.dx; s.y += s.dy; }
            }

            // Reinicia se sair da tela
            if (s.y > height || s.x > width || s.x < -s.size) {
                s.x = rand.nextInt(width);
                s.y = -s.size;
            }
        }
    }
    private void drawShape(Graphics g, Shape s) {
        g.setColor(ConfigManager.getBackgroundColor() != null 
                ? ConfigManager.getBackgroundColor() 
                : s.color);

        ConfigManager.ShapeType type = ConfigManager.getShapeType();
        if (type == ConfigManager.ShapeType.RANDOM) {
            type = ConfigManager.ShapeType.values()[rand.nextInt(3)]; // pega aleatório
        }

        switch (type) {
            case CIRCLE -> g.fillOval(s.x, s.y, s.size, s.size);
            case SQUARE -> g.fillRect(s.x, s.y, s.size, s.size);
            case TRIANGLE -> {
                int[] xPoints = {s.x, s.x + s.size/2, s.x - s.size/2};
                int[] yPoints = {s.y, s.y + s.size, s.y + s.size};
                g.fillPolygon(xPoints, yPoints, 3);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape s : shapes) {
            drawShape(g, s);
        }
    }

}
