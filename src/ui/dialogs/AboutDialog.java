package ui.dialogs;

import java.awt.*;
import javax.swing.*;

public class AboutDialog extends JDialog {

    public AboutDialog(JFrame parent) {
        super(parent, "Sobre", true);
        setSize(400, 400);
        setLocationRelativeTo(parent);
        setResizable(false);

        String aboutText = "<html><center>" +
                           "<h1>Aplicação GUI com Threads</h1>" +
                           "<p><b>Versão:</b> 1.0</p>" +
                           "<p><b>Autores:</b><br/>" +
                           "Adriano Baumgarte<br/>" +
                           "Lorenzo Pugina<br/>" +
                           "Ana Vitória Queiroz<br/>" +
                           "Bianca Oshiro<br/>" +
                           "João Vitor Anacleto<br/>" +
                           "Maria Clara Muharem</p>" +
                           "<p>Este projeto demonstra uma aplicação GUI em Java com animações de fundo utilizando threads.</p>" +
                           "</center></html>";

        JLabel info = new JLabel(aboutText, SwingConstants.CENTER);
        add(info, BorderLayout.CENTER);

        JButton closeButton = new JButton("Fechar");
        closeButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}

