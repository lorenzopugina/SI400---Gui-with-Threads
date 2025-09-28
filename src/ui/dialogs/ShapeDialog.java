package ui.dialogs;

import core.ConfigManager;
import ui.BackgroundPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing. JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ShapeDialog extends JDialog {
    public ShapeDialog (JFrame parent, JLabel statusBar, BackgroundPanel backgroundPanel) {
        super(parent, "Configurar Forma", true);
        setSize(350, 200);
        setLocationRelativeTo(parent);

        String[] options = {"Círculo", "Quadrado", "Triângulo", "Aleatório"};
        JComboBox<String> combo = new JComboBox<>(options);

        JButton ok = new JButton("OK");
        ok.addActionListener(e -> {
            int index = combo.getSelectedIndex();
            ConfigManager.ShapeType oldType = ConfigManager.getShapeType();
            
            switch (index) {
                case 0 -> ConfigManager.setShapeType(ConfigManager.ShapeType.CIRCLE);
                case 1 -> ConfigManager.setShapeType(ConfigManager.ShapeType.SQUARE);
                case 2 -> ConfigManager.setShapeType(ConfigManager.ShapeType.TRIANGLE);
                case 3 -> ConfigManager.setShapeType(ConfigManager.ShapeType.RANDOM);
            }
            
            ConfigManager.ShapeType newType = ConfigManager.getShapeType();
            
            // Atualiza tipos das formas se mudou PARA aleatório ou DE aleatório
            if (backgroundPanel != null && 
                (newType == ConfigManager.ShapeType.RANDOM || oldType == ConfigManager.ShapeType.RANDOM)) {
                backgroundPanel.updateShapeTypes(); // mantém posições, só muda tipos
            }
            
            statusBar.setText("Forma alterada.");
            dispose();
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Escolha a forma:"), BorderLayout.NORTH);

        JPanel comboPanel = new JPanel();
        comboPanel.add(combo);

        panel.add(comboPanel, BorderLayout.CENTER);
        panel.add(ok, BorderLayout.SOUTH);
        
        add(panel);
        }
}
