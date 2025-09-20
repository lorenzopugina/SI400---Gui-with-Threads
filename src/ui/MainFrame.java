package br.univ.projeto.ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JTextArea textArea;
    private JLabel statusBar;
    private BackgroundPanel backgroundPanel;

    public MainFrame() {
        initUI();
    }

    private void initUI() {
        setTitle("Basic GUI with Threads");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Área de texto
        textArea = new JTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Barra de status
        statusBar = new JLabel("Status: pronto");
        add(statusBar, BorderLayout.SOUTH);

        // Painel de fundo animado
        backgroundPanel = new BackgroundPanel();
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        backgroundPanel.add(statusBar, BorderLayout.SOUTH);

        // Menus
        setJMenuBar(createMenuBar());
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Arquivo
        JMenu menuArquivo = new JMenu("Arquivo");
        JMenuItem abrir = new JMenuItem("Abrir Arquivo");
        JMenuItem fechar = new JMenuItem("Fechar Arquivo");
        JMenuItem sair = new JMenuItem("Sair");
        menuArquivo.add(abrir);
        menuArquivo.add(fechar);
        menuArquivo.addSeparator();
        menuArquivo.add(sair);

        // Configuração
        JMenu menuConfig = new JMenu("Configuração");
        JMenuItem padroes = new JMenuItem("Padrões");
        JMenuItem cores = new JMenuItem("Cores");
        JMenuItem velocidade = new JMenuItem("Velocidade");
        menuConfig.add(padroes);
        menuConfig.add(cores);
        menuConfig.add(velocidade);

        // Ajuda
        JMenu menuAjuda = new JMenu("Ajuda");
        JMenuItem ajuda = new JMenuItem("Ajuda");
        JMenuItem sobre = new JMenuItem("Sobre");
        menuAjuda.add(ajuda);
        menuAjuda.add(sobre);

        menuBar.add(menuArquivo);
        menuBar.add(menuConfig);
        menuBar.add(menuAjuda);

        return menuBar;
    }
}
