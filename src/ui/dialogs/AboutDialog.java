package ui.dialogs;

import java.awt.*;
import javax.swing.*;

public class AboutDialog extends JDialog {

    public AboutDialog(JFrame parent) {
        super(parent, "Sobre", true);
        setSize(300, 200);
        setLocationRelativeTo(parent);

        JLabel info = new JLabel("<html><center>Versão 1.0<br/>Autores: Grupo 6<br/>2025</center></html>", SwingConstants.CENTER);

        add(info, BorderLayout.CENTER);
    }
}
