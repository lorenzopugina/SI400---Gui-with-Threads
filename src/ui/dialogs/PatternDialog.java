package ui.dialogs;

import core.ConfigManager;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PatternDialog extends JDialog{
    public PatternDialog(JFrame parent, JLabel statusBar) {
        super(parent, "Restaurar Padrão", true);
        setSize(350, 200);
        setLocationRelativeTo(parent);

        JLabel label = new JLabel("<html><center>Deseja restaurar as configurações para o padrão?</center></html>");
        JButton ok = new JButton("OK");
        
        ok.addActionListener( e -> {
            ConfigManager.resetDefaults();
            statusBar.setText("Configurações resetadas para o padrão.");
            dispose();
        });

        JPanel panel = new JPanel (new BorderLayout());
        panel.add(label, BorderLayout.CENTER);
        panel.add(ok, BorderLayout.SOUTH);
        add(panel);
    }
}
