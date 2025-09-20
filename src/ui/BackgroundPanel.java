package br.univ.projeto.ui;

import java.awt.*;
import javax.swing.*;

public class BackgroundPanel extends JPanel implements Runnable {

    private Thread animationThread;
    private boolean running = true;

    public BackgroundPanel() {
        startAnimation();
    }

    private void startAnimation() {
        animationThread = new Thread(this);
        animationThread.start();
    }

    @Override
    public void run() {
        while (running) {
            repaint();
            try {
                Thread.sleep(100); // velocidade inicial
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // TODO: desenhar algo dinâmico aqui
        g.setColor(Color.BLUE);
        g.fillOval(50, 50, 100, 100);
    }
}
