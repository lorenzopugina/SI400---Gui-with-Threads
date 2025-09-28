package ui.dialogs;

import core.ConfigManager;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JPanel;
import javax.swing.event.ChangeListener;

public class SpeedDialog extends JDialog {
    private int originalSpeed; // para restaurar se cancelar
    
    public SpeedDialog(JFrame parent, JLabel statusBar) {
        super(parent, "Configurar Velocidade", true);
        setSize(300, 220);
        setLocationRelativeTo(parent);

        // Salva velocidade original para possível restauração
        originalSpeed = ConfigManager.getAnimationSpeed();
        
        // Permite cancelar com ESC
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                // Restaura velocidade original ao fechar o diálogo
                ConfigManager.setAnimationSpeed(originalSpeed);
                dispose();
            }
        });

        JSlider slider = new JSlider(10, 100, originalSpeed);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        // Label para mostrar valor atual
        JLabel valueLabel = new JLabel("Velocidade atual: " + originalSpeed + "%", JLabel.CENTER);

        // Aplica velocidade em tempo real conforme move o slider
        ChangeListener speedChangeListener = e -> {
            int newSpeed = slider.getValue();
            ConfigManager.setAnimationSpeed(newSpeed);
            valueLabel.setText("Velocidade atual: " + newSpeed + "%");
        };
        slider.addChangeListener(speedChangeListener);

        // Botões OK e Cancelar
        JPanel buttonPanel = new JPanel();
        
        JButton ok = new JButton("OK");
        ok.addActionListener(e -> {
            // Mantém a velocidade atual (já foi aplicada em tempo real)
            statusBar.setText("Velocidade definida para " + slider.getValue() + "%.");
            dispose();
        });

        JButton cancel = new JButton("Cancelar");
        cancel.addActionListener(e -> {
            // Restaura velocidade original
            ConfigManager.setAnimationSpeed(originalSpeed);
            statusBar.setText("Velocidade mantida em " + originalSpeed + "%.");
            dispose();
        });

        buttonPanel.add(ok);
        buttonPanel.add(cancel);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Velocidade da animação (10=lento, 100=rápido):"), BorderLayout.NORTH);
        panel.add(slider, BorderLayout.CENTER);
        panel.add(valueLabel, BorderLayout.SOUTH);
        panel.add(buttonPanel, BorderLayout.PAGE_END);
        
        add(panel);
    }
}

