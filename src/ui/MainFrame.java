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
        JMenu menuArchive = new JMenu("Arquivo");// titulo do menu arquivos
        JMenuItem open = new JMenuItem("Abrir Arquivo");
        JMenuItem close = new JMenuItem("Fechar Arquivo");
        JMenuItem exit = new JMenuItem("Sair");

        open.addActionListener(e -> {
            String content = FileManager.openFile(this); // delega ao FileManager
            if (content != null) {
                textArea.setText(content);
                statusBar.setText("Arquivo carregado com sucesso.");
            }
        });


        close.addActionListener(e -> System.out.println("Clicou em Fechar Arquivo"));
        // quando o usuario clica aqui, aparece essa frase no terminal
        exit.addActionListener(e -> System.exit(0)); // fecha a aplicacao/programa

        menuArchive.add(open);
        menuArchive.add(close);
        menuArchive.addSeparator();
        menuArchive.add(exit);

        // menu configuracao ou submenu de configuracao
        JMenu menuConfig = new JMenu("Configuração");// titulo do menu configurações
        JMenuItem colors = new JMenuItem("Cores");
        JMenuItem shapes = new JMenuItem("Formas");
        JMenuItem patterns = new JMenuItem("Padrões");
        JMenuItem speed = new JMenuItem("Velocidade");

        //erros pq ainda n foram implementados
        speed.addActionListener(e -> new SpeedDialog(this, statusBar).setVisible(true));
        colors.addActionListener(e -> new ColorDialog(this, statusBar).setVisible(true));
        shapes.addActionListener(e -> new ShapeDialog(this, statusBar).setVisible(true));
        patterns.addActionListener(e -> new PatternDialog(this, statusBar).setVisible(true));

        menuConfig.add(speed);
        menuConfig.add(colors);
        menuConfig.add(shapes);
        menuConfig.add(patterns);

        // Manu Ajuda ou submenu de ajuda
        JMenu menuHelp = new JMenu("Ajuda"); // titulo do menu ajuda
        JMenuItem help = new JMenuItem("Ajuda");
        JMenuItem about = new JMenuItem("Sobre");

        help.addActionListener(e -> {
            HelpDialog helpDialog = new HelpDialog(this);
            helpDialog.setVisible(true); // mostra o conteudo de ajuda
        });

        about.addActionListener(e -> {
            AboutDialog aboutDialog = new AboutDialog(this);
            aboutDialog.setVisible(true);// mostra o conteudo de sobre
        });

        menuHelp.add(help);
        menuHelp.add(about);

        menuBar.add(menuArchive);
        menuBar.add(menuConfig);
        menuBar.add(menuHelp);

        return menuBar;
    }
}
