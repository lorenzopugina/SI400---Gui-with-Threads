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

public class SpeedDialog extends JDialog {
    public SpeedDialog(JFrame parent, JLabel statusBar) {
        super(parent, "Configurar Velocidade", true);
        setSize(300, 200);
        setLocationRelativeTo(parent);

        JSlider slider = new JSlider(10, 100, ConfigManager.getAnimationSpeed());
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        JButton ok = new JButton("OK");
        ok.addActionListener(e -> {
            ConfigManager.setAnimationSpeed(slider.getValue());
            statusBar.setText("Velocidade alterada para " + slider.getValue() + " ms.");
            dispose();
        });

        JPanel panel = new JPanel (new BorderLayout());
        panel.add(new JLabel("Velocidade da animação:"), BorderLayout.NORTH);
        panel.add(slider, BorderLayout.CENTER);
        panel.add(ok, BorderLayout.SOUTH);
        add(panel);
    }
}

