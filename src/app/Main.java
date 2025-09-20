package br.univ.projeto.app;

import javax.swing.SwingUtilities;
import br.univ.projeto.ui.MainFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
