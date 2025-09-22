package br.univ.projeto.ui;

import javax.swing.*;
import java.awt.*;

public class About extends JDialog {

    public About(JFrame parent) {
        super(parent, "Sobre", true);
        setSize(300, 200);
        setLocationRelativeTo(parent);

        JLabel info = new JLabel("<html><center>Versão 1.0<br/>Autores: Grupo 6<br/>2025</center></html>", SwingConstants.CENTER);

        add(info, BorderLayout.CENTER);
    }
}
