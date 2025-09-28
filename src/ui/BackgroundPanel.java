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
        int dx, dy; // velocidade em X e Y
        Color color;
        ConfigManager.ShapeType shapeType; // tipo fixo para esta forma

        public Shape(int x, int y, int size, int dx, int dy, Color color, ConfigManager.ShapeType shapeType) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.dx = dx;
            this.dy = dy;
            this.color = color;
            this.shapeType = shapeType;
        }
    }

    private Thread animationThread;
    private boolean running = true;
    private List<Shape> shapes;
    private Random random = new Random();

    public BackgroundPanel() {
        // NÃO definir como opaque=false aqui, pois queremos que o fundo seja visível
        setBackground(Color.WHITE); // define um fundo branco padrão
        shapes = new ArrayList<>();
        initShapes();
        startAnimation();
    }

    private void initShapes() {
        shapes.clear();
        // Cria formas iniciais - usa valores padrão se painel ainda não tem tamanho
        int panelWidth = getWidth() > 0 ? getWidth() : 800;
        int panelHeight = getHeight() > 0 ? getHeight() : 600;
        
        // Prepara tipos de forma disponíveis
        ConfigManager.ShapeType currentConfigType = ConfigManager.getShapeType();
        ConfigManager.ShapeType[] availableTypes = {
            ConfigManager.ShapeType.CIRCLE,
            ConfigManager.ShapeType.SQUARE,
            ConfigManager.ShapeType.TRIANGLE
        };
        
        for (int i = 0; i < 15; i++) {
            int x = random.nextInt(Math.max(1, panelWidth));
            int y = random.nextInt(Math.max(1, panelHeight));
            int size = 15 + random.nextInt(25); // tamanho entre 15 e 40
            int dx = 1 + random.nextInt(3); // velocidade horizontal
            int dy = 1 + random.nextInt(3); // velocidade vertical
            
            // Cor aleatória mais visível
            Color color = new Color(
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256),
                150 // menos transparência para ser mais visível
            );
            
            // Define o tipo de forma para esta shape específica
            ConfigManager.ShapeType shapeType;
            if (currentConfigType == ConfigManager.ShapeType.RANDOM) {
                // Se configurado como aleatório, escolhe um tipo aleatório FIXO para esta forma
                shapeType = availableTypes[random.nextInt(availableTypes.length)];
            } else {
                // Se não é aleatório, usa o tipo configurado
                shapeType = currentConfigType;
            }
            
            shapes.add(new Shape(x, y, size, dx, dy, color, shapeType));
        }
    }

    private void startAnimation() {
        // Para thread anterior se existir
        if (animationThread != null && animationThread.isAlive()) {
            running = false;
            try {
                animationThread.join(1000); // espera até 1 segundo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        running = true;
        animationThread = new Thread(this, "BackgroundAnimation");
        animationThread.setDaemon(true); // não impede fechamento da aplicação
        animationThread.start();
    }

    @Override
    public void run() {
        while (running && !Thread.currentThread().isInterrupted()) {
            updateShapes();
            SwingUtilities.invokeLater(this::repaint);
            
            try {
                // Inverte a lógica: valor maior no slider = animação mais rápida
                // Fórmula: 110 - velocidadeDoSlider
                // Slider 10 -> delay 100ms (lento)
                // Slider 100 -> delay 10ms (rápido)
                int delay = 110 - ConfigManager.getAnimationSpeed();
                Thread.sleep(Math.max(5, delay)); // mínimo 5ms para evitar problemas
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private void updateShapes() {
        int width = getWidth() > 0 ? getWidth() : 800;
        int height = getHeight() > 0 ? getHeight() : 600;

        for (Shape shape : shapes) {
            // Movimento diagonal simples
            shape.x += shape.dx;
            shape.y += shape.dy;

            // Reinicia forma quando sai da tela
            if (shape.x > width + shape.size || shape.y > height + shape.size) {
                shape.x = -shape.size;
                shape.y = random.nextInt(height / 2);
                // Varia velocidade na reinicialização
                shape.dx = 1 + random.nextInt(3);
                shape.dy = 1 + random.nextInt(3);
            }
        }
    }

    private void drawShape(Graphics2D g2d, Shape shape) {
        // Usa cor configurada ou cor individual da forma
        Color drawColor;
        Color configColor = ConfigManager.getBackgroundColor();
        
        if (configColor != null) {
            // Aplica transparência à cor configurada
            drawColor = new Color(
                configColor.getRed(),
                configColor.getGreen(),
                configColor.getBlue(),
                150 // menos transparência para ser mais visível
            );
        } else {
            drawColor = shape.color;
        }
        
        g2d.setColor(drawColor);

        // Determina que tipo de forma desenhar
        ConfigManager.ShapeType currentConfig = ConfigManager.getShapeType();
        ConfigManager.ShapeType shapeType;
        
        if (currentConfig == ConfigManager.ShapeType.RANDOM) {
            // Se configuração atual é aleatória, usa o tipo fixo da forma
            shapeType = shape.shapeType;
        } else {
            // Se configuração não é aleatória, usa a configuração atual (permite mudança dinâmica)
            shapeType = currentConfig;
        }

        switch (shapeType) {
            case CIRCLE:
                g2d.fillOval(shape.x, shape.y, shape.size, shape.size);
                break;
            case SQUARE:
                g2d.fillRect(shape.x, shape.y, shape.size, shape.size);
                break;
            case TRIANGLE:
                int[] xPoints = {
                    shape.x + shape.size / 2,  // ponto superior
                    shape.x,                   // ponto inferior esquerdo
                    shape.x + shape.size       // ponto inferior direito
                };
                int[] yPoints = {
                    shape.y,                   // ponto superior
                    shape.y + shape.size,      // ponto inferior esquerdo
                    shape.y + shape.size       // ponto inferior direito
                };
                g2d.fillPolygon(xPoints, yPoints, 3);
                break;
            case RANDOM:
                // Este caso nunca será executado pois é tratado no initShapes
                break;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Ativa antialiasing para desenho mais suave
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        // Desenha todas as formas
        synchronized (shapes) {
            for (Shape shape : shapes) {
                drawShape(g2d, shape);
            }
        }
        
        g2d.dispose();
    }

    /**
     * Reinicia a animação com novas formas
     * Útil quando configurações são alteradas
     */
    public void refreshAnimation() {
        synchronized (shapes) {
            initShapes();
        }
    }

    /**
     * Atualiza apenas os tipos das formas existentes sem resetar posições
     * Útil para mudanças entre modo aleatório e fixo
     */
    public void updateShapeTypes() {
        synchronized (shapes) {
            ConfigManager.ShapeType currentConfigType = ConfigManager.getShapeType();
            ConfigManager.ShapeType[] availableTypes = {
                ConfigManager.ShapeType.CIRCLE,
                ConfigManager.ShapeType.SQUARE,
                ConfigManager.ShapeType.TRIANGLE
            };
            
            for (Shape shape : shapes) {
                if (currentConfigType == ConfigManager.ShapeType.RANDOM) {
                    // Se mudou para aleatório, atribui tipo aleatório para cada forma
                    shape.shapeType = availableTypes[random.nextInt(availableTypes.length)];
                } else {
                    // Se mudou para tipo fixo, todas as formas usam o mesmo tipo
                    shape.shapeType = currentConfigType;
                }
            }
        }
    }

    /**
     * Para a animação completamente
     */
    public void stopAnimation() {
        running = false;
        if (animationThread != null) {
            animationThread.interrupt();
        }
    }

    /**
     * Inicia/reinicia a animação
     */
    public void restartAnimation() {
        stopAnimation();
        try {
            Thread.sleep(50); // pequena pausa
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        startAnimation();
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        // Não reinicializa automaticamente - só quando chamado explicitamente
    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
        // Não reinicializa automaticamente - só quando chamado explicitamente
    }
}
