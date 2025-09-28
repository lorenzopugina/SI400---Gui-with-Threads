package ui.dialogs;

import core.ConfigManager;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ColorDialog extends JDialog {
    public ColorDialog(JFrame parent, JLabel statusBar) {
        super(parent, "Configurar Cor", true);
        setSize(350, 200);
        setLocationRelativeTo(parent);

        String[] options = {"Vermelho", "Azul", "Aleatório"};
        JComboBox<String> combo = new JComboBox<>(options);

        JButton ok = new JButton("OK");
        ok.addActionListener(e -> {
            int index = combo.getSelectedIndex();
            switch (index) {
                case 0 -> ConfigManager.setBackgroundColor(Color.RED);
                case 1 -> ConfigManager.setBackgroundColor(Color.BLUE);
                case 2 -> ConfigManager.setBackgroundColor(null); // random  
            }
            statusBar.setText("Cor de fundo alterada.");
            dispose();
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Escolha a cor:"), BorderLayout.NORTH);

        JPanel comboPanel = new JPanel();
        comboPanel.add(combo);

        panel.add(comboPanel, BorderLayout.CENTER);
        panel.add(ok, BorderLayout.SOUTH);

        add(panel);
    }
}

