package ui.dialogs;

import core.ConfigManager;
import ui.BackgroundPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PatternDialog extends JDialog{
    public PatternDialog(JFrame parent, JLabel statusBar, BackgroundPanel backgroundPanel) {
        super(parent, "Restaurar Padrão", true);
        setSize(350, 200);
        setLocationRelativeTo(parent);

        JLabel label = new JLabel("<html><center>Deseja restaurar as configurações para o padrão?</center></html>");
        JButton ok = new JButton("OK");
        
        ok.addActionListener( e -> {
            ConfigManager.resetDefaults();
            statusBar.setText("Configurações resetadas para o padrão.");
            if (backgroundPanel != null) {
                backgroundPanel.refreshAnimation(); // atualiza animação com configurações padrão
            }
            dispose();
        });

        JPanel panel = new JPanel (new BorderLayout());
        panel.add(label, BorderLayout.CENTER);
        panel.add(ok, BorderLayout.SOUTH);
        add(panel);
    }
}
