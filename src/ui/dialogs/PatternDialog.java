package ui.dialogs;

import core.ConfigManager;
import java.awt.BorderLayout;
import javax.swing.*;
import ui.BackgroundPanel;

public class PatternDialog extends JDialog {
    public PatternDialog(JFrame parent, JLabel statusBar, BackgroundPanel backgroundPanel) {
        super(parent, "Padrão de Movimento", true);
        setSize(350, 200);
        setLocationRelativeTo(parent);

        String[] options = {"Para Baixo", "Diagonal Direita", "Diagonal Esquerda"};
        JComboBox<String> combo = new JComboBox<>(options);

        JButton ok = new JButton("OK");
        ok.addActionListener(e -> {
            switch (combo.getSelectedIndex()) {
                case 0 -> ConfigManager.setDirection(ConfigManager.Direction.DOWN);
                case 1 -> ConfigManager.setDirection(ConfigManager.Direction.DIAGONAL_RIGHT);
                case 2 -> ConfigManager.setDirection(ConfigManager.Direction.DIAGONAL_LEFT);
            }
            statusBar.setText(" Direção alterada");
            backgroundPanel.refreshAnimation();
            dispose();
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Escolha a direção:"), BorderLayout.NORTH);
        
        JPanel comboPanel = new JPanel();
        comboPanel.add(combo);

        panel.add(comboPanel, BorderLayout.CENTER);
        panel.add(ok, BorderLayout.SOUTH);

        add(panel);
    }
}