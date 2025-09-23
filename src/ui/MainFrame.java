package ui;

//onde a classe esta 
import core.ConfigManager;
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
    // herda jframe

    private JTextArea textArea;
    private JLabel statusBar;
    private BackgroundPanel backgroundPanel;

    public MainFrame() {
        initUI();
        // inicializa a interface
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
        setContentPane(backgroundPanel); // sera o painel principal
        backgroundPanel.setLayout(new BorderLayout()); // layout
        backgroundPanel.add(new JScrollPane(textArea), BorderLayout.CENTER); // scroll/rolagem
        backgroundPanel.add(statusBar, BorderLayout.SOUTH);// barra de status

        // menus
        setJMenuBar(createMenuBar());
    }

    private JMenuBar createMenuBar() {// cria a barra de menu
        JMenuBar menuBar = new JMenuBar();

        // menu arquivo ou submenu de arquivo
        JMenu menuArquivo = new JMenu("Arquivo");// titulo do menu arquivos
        JMenuItem abrir = new JMenuItem("Abrir Arquivo");
        JMenuItem fechar = new JMenuItem("Fechar Arquivo");
        JMenuItem sair = new JMenuItem("Sair");

        abrir.addActionListener(e -> {
            String content = FileManager.openFile(this); // delega ao FileManager
            if (content != null) {
                textArea.setText(content);
                statusBar.setText("Arquivo carregado com sucesso.");
            }
        });


        fechar.addActionListener(e -> System.out.println("Clicou em Fechar Arquivo"));
        // quando o usuario clica aqui, aparece essa frase no terminal
        sair.addActionListener(e -> System.exit(0)); // fecha a aplicacao/programa

        menuArquivo.add(abrir);
        menuArquivo.add(fechar);
        menuArquivo.addSeparator();
        menuArquivo.add(sair);

        // menu configuracao ou submenu de configuracao
        JMenu menuConfig = new JMenu("Configuração");// titulo do menu configurações
        JMenuItem padroes = new JMenuItem("Padrões");
        JMenuItem cores = new JMenuItem("Cores");
        JMenuItem velocidade = new JMenuItem("Velocidade");
        JMenuItem formas = new JMenuItem("Forma");

        //erros pq ainda n foram implementados
        velocidade.addActionListener(e -> new SpeedDialog(this).setVisible(true));
        cores.addActionListener(e -> new ColorDialog(this).setVisible(true));
        formas.addActionListener(e -> new ShapeDialog(this).setVisible(true));
        padroes.addActionListener(e -> new PatternDialog(this).setVisible(true));

        menuConfig.add(velocidade);
        menuConfig.add(cores);
        menuConfig.add(formas);
        menuConfig.add(padroes);

        // Manu Ajuda ou submenu de ajuda
        JMenu menuAjuda = new JMenu("Ajuda"); // titulo do menu ajuda
        JMenuItem ajuda = new JMenuItem("Ajuda");
        JMenuItem sobre = new JMenuItem("Sobre");

        ajuda.addActionListener(e -> {
            HelpDialog helpDialog = new HelpDialog(this);
            helpDialog.setVisible(true); // mostra o conteudo de ajuda
        });

        sobre.addActionListener(e -> {
            AboutDialog aboutDialog = new AboutDialog(this);
            aboutDialog.setVisible(true);// mostra o conteudo de sobre
        });

        menuAjuda.add(ajuda);
        menuAjuda.add(sobre);

        menuBar.add(menuArquivo);
        menuBar.add(menuConfig);
        menuBar.add(menuAjuda);

        return menuBar;
    }
}
