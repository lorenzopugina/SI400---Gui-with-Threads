package ui;

import core.ConfigManager;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class BackgroundPanel extends JPanel implements Runnable {
    private Thread animationThread;
    private boolean running = true;
    private final List<FallingShape> shapes = new ArrayList<>();

    public BackgroundPanel() {
        setBackground(Color.WHITE);
        initShapes();
        startAnimation();
    }

    private void initShapes() {
        shapes.clear();
        int width = getWidth() > 0 ? getWidth() : 800;
        int height = getHeight() > 0 ? getHeight() : 600;

        for (int i = 0; i < 25; i++) {
            shapes.add(new FallingShape(width, height));
        }
    }

    private void startAnimation() {
        if (animationThread != null) return;
        
        running = true;
        animationThread = new Thread(this);
        animationThread.setDaemon(true);
        animationThread.start();
    }

    @Override
    public void run() {
        while (running) {
            updateShapes();
            repaint();
            
            try {
                // CORREÇÃO: A velocidade agora controla o delay diretamente
                int speed = ConfigManager.getAnimationSpeed();
                int delay = 50 - (speed * 4); // 1=46ms, 5=30ms, 10=10ms
                Thread.sleep(Math.max(10, delay));
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    private void updateShapes() {
        int width = getWidth();
        int height = getHeight();
        if (width <= 0 || height <= 0) return;

        for (FallingShape shape : shapes) {
            shape.update(width, height);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (FallingShape shape : shapes) {
            shape.draw(g2d);
        }
    }

    public void updateShapeTypes() {
        int width = getWidth() > 0 ? getWidth() : 800;
        int height = getHeight() > 0 ? getHeight() : 600;
        
        for (FallingShape shape : shapes) {
            shape.updateShapeType(ConfigManager.getShapeType(), width, height);
        }
        repaint();
    }

    public void refreshAnimation() {
        initShapes();
    }

    public void stopAnimation() {
        running = false;
        if (animationThread != null) {
            animationThread.interrupt();
            animationThread = null;
        }
    }
}