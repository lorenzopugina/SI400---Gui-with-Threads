package ui.dialogs;

import core.ConfigManager;
import ui.BackgroundPanel;
import java.awt.BorderLayout;
import javax.swing.*;

public class ShapeDialog extends JDialog {
    public ShapeDialog(JFrame parent, JLabel statusBar, BackgroundPanel backgroundPanel) {
        super(parent, "Configurar Forma", true);
        setSize(300, 150);
        setLocationRelativeTo(parent);

        String[] options = {"Círculo", "Quadrado", "Triângulo", "Aleatório"};
        JComboBox<String> combo = new JComboBox<>(options);

        // Seleciona o item atual
        switch (ConfigManager.getShapeType()) {
            case CIRCLE -> combo.setSelectedIndex(0);
            case SQUARE -> combo.setSelectedIndex(1);
            case TRIANGLE -> combo.setSelectedIndex(2);
            case RANDOM -> combo.setSelectedIndex(3);
        }

        JButton ok = new JButton("OK");
        ok.addActionListener(e -> {
            switch (combo.getSelectedIndex()) {
                case 0 -> ConfigManager.setShapeType(ConfigManager.ShapeType.CIRCLE);
                case 1 -> ConfigManager.setShapeType(ConfigManager.ShapeType.SQUARE);
                case 2 -> ConfigManager.setShapeType(ConfigManager.ShapeType.TRIANGLE);
                case 3 -> ConfigManager.setShapeType(ConfigManager.ShapeType.RANDOM);
            }

            // Atualiza as formas existentes - SEM VERIFICAÇÃO COMPLEXA
            if (backgroundPanel != null) {
                backgroundPanel.updateShapeTypes();
            }

            statusBar.setText(" Forma alterada para " + combo.getSelectedItem());
            dispose();
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Escolha a forma:"), BorderLayout.NORTH);
        panel.add(combo, BorderLayout.CENTER);
        panel.add(ok, BorderLayout.SOUTH);

        add(panel);
    }
}