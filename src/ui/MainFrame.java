package ui;

import core.FileManager;
import java.awt.*;
import javax.swing.*;
import ui.dialogs.AboutDialog;
import ui.dialogs.ColorDialog;
import ui.dialogs.HelpDialog;
import ui.dialogs.PatternDialog;
import ui.dialogs.ShapeDialog;
import ui.dialogs.SpeedDialog;

public class MainFrame extends JFrame {

    private JTextArea textArea;
    private JLabel statusBar;
    private BackgroundPanel backgroundPanel;

    public MainFrame() {
        initUI(); // inicializa a interface
    }

    private void initUI() {
        setTitle("Basic GUI with Threads"); // titulo da janela
        setSize(800, 600); // tamanho da janela
        setDefaultCloseOperation(EXIT_ON_CLOSE); // fecha programa quando clica no X
        setLocationRelativeTo(null);

        // icone da aplicacao/programa
        try {
            setIconImage(new ImageIcon("src/util/icon_star.png").getImage());
        } catch (Exception e) {
            System.out.println("Ícone não encontrado, usando padrão.");
        }

        // area de texto
        textArea = new JTextArea();

        // barra de status
        statusBar = new JLabel("Status: pronto");

        // painel de fundo animado
        backgroundPanel = new BackgroundPanel();
        setContentPane(backgroundPanel); 
        backgroundPanel.setLayout(new BorderLayout()); 
        backgroundPanel.add(new JScrollPane(textArea), BorderLayout.CENTER); 
        backgroundPanel.add(statusBar, BorderLayout.SOUTH);

        // menus
        setJMenuBar(createMenuBar());
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // menu Arquivo
        JMenu menuArquivo = new JMenu("Arquivo");
        JMenuItem abrir = new JMenuItem("Abrir Arquivo");
        JMenuItem fechar = new JMenuItem("Fechar Arquivo");
        JMenuItem sair = new JMenuItem("Sair");

        // Abrir Arquivo
        abrir.addActionListener(e -> {
            String content = FileManager.openFile(this); // delega ao FileManager
            if (content != null) {
                textArea.setText(content);
                statusBar.setText("Arquivo carregado com sucesso.");
            }
        });

        // Fechar Arquivo
        fechar.addActionListener(e -> FileManager.closeFile(textArea, statusBar));

        // Sair
        sair.addActionListener(e -> System.exit(0));

        menuArquivo.add(abrir);
        menuArquivo.add(fechar);
        menuArquivo.addSeparator();
        menuArquivo.add(sair);

        // menu Configuração
        JMenu menuConfig = new JMenu("Configuração");
        JMenuItem padroes = new JMenuItem("Padrões");
        JMenuItem cores = new JMenuItem("Cores");
        JMenuItem velocidade = new JMenuItem("Velocidade");
        JMenuItem formas = new JMenuItem("Forma");

        velocidade.addActionListener(e -> new SpeedDialog(this).setVisible(true));
        cores.addActionListener(e -> new ColorDialog(this).setVisible(true));
        formas.addActionListener(e -> new ShapeDialog(this).setVisible(true));
        padroes.addActionListener(e -> new PatternDialog(this).setVisible(true));

        menuConfig.add(velocidade);
        menuConfig.add(cores);
        menuConfig.add(formas);
        menuConfig.add(padroes);

        // menu Ajuda
        JMenu menuAjuda = new JMenu("Ajuda");
        JMenuItem ajuda = new JMenuItem("Ajuda");
        JMenuItem sobre = new JMenuItem("Sobre");

        ajuda.addActionListener(e -> new HelpDialog(this).setVisible(true));
        sobre.addActionListener(e -> new AboutDialog(this).setVisible(true));

        menuAjuda.add(ajuda);
        menuAjuda.add(sobre);

        // adiciona menus na barra
        menuBar.add(menuArquivo);
        menuBar.add(menuConfig);
        menuBar.add(menuAjuda);

        return menuBar;
    }
}
