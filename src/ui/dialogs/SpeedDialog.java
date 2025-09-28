package ui.dialogs;

import core.ConfigManager;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

public class SpeedDialog extends JDialog {
    private int originalSpeed;
    
    public SpeedDialog(JFrame parent, JLabel statusBar) {
        super(parent, "Configurar Velocidade", true);
        setSize(350, 200);
        setLocationRelativeTo(parent);

        originalSpeed = ConfigManager.getAnimationSpeed();
        
        JSlider slider = new JSlider(1, 10, originalSpeed); // Alterado para 1-10 (mais perceptível)
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        JLabel valueLabel = new JLabel("Velocidade: " + slider.getValue(), JLabel.CENTER);

        ChangeListener speedChangeListener = e -> {
            int newSpeed = slider.getValue();
            ConfigManager.setAnimationSpeed(newSpeed);
            valueLabel.setText("Velocidade: " + newSpeed);
        };
        slider.addChangeListener(speedChangeListener);

        JButton ok = new JButton("OK");
        ok.addActionListener(e -> {
            statusBar.setText(" Velocidade alterada para " + slider.getValue());
            dispose();
        });

        JButton cancel = new JButton("Cancelar");
        cancel.addActionListener(e -> {
            ConfigManager.setAnimationSpeed(originalSpeed);
            statusBar.setText(" Velocidade mantida");
            dispose();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(ok);
        buttonPanel.add(cancel);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Velocidade da animação (1=lento, 10=rápido):"), BorderLayout.NORTH);
        panel.add(slider, BorderLayout.CENTER);
        panel.add(valueLabel, BorderLayout.SOUTH);
        panel.add(buttonPanel, BorderLayout.PAGE_END);
        
        add(panel);
    }
}