package ui.dialogs;

import java.awt.*;
import javax.swing.*;

public class HelpDialog extends JDialog {

    public HelpDialog(JFrame parent) {
        super(parent, "Ajuda", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);

        JTextArea helpText = new JTextArea("Aqui vai o texto de ajuda...");
        helpText.setLineWrap(true);
        helpText.setWrapStyleWord(true);
        helpText.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(helpText);

        JButton closeButton = new JButton("Fechar");
        closeButton.addActionListener(e -> dispose());

        add(scrollPane, BorderLayout.CENTER);
        add(closeButton, BorderLayout.SOUTH);
    }
}
