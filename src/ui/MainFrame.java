package br.univ.projeto.ui;

//onde a classe esta 
import javax.swing.*;

import br.univ.projeto.core.ConfigManager;
import br.univ.projeto.core.FileManager;
import ui.Help;
//importando classes q serao usadas aqui, como help, about, arquivos, etc
import java.awt.*;

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

        // ouvintes/acoes
        abrir.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION)
            // ve se o usuario selecionou o arquivo
            {
                String path = fileChooser.getSelectedFile().getAbsolutePath();
                String content = FileManager.readFile(path);// le o conteudo do arquivo
                textArea.setText(content);// e exibe o conteudo do arquivo na tela
                statusBar.setText("Arquivo aberto: " + path);// muda status para arquivo aberto
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

        // ouvintes/acoes
        padroes.addActionListener(e -> System.out.println("Clicou em Padrões"));
        // *precisa estar feito no configmanager para fazer a chamada aqui
        cores.addActionListener(e -> System.out.println("Clicou em Cores"));
        // *precisa estar feito no configmanager.java, para conseguir fazer a chamada

        // * = alem disso precsa d decisões/logica de como a pessoa responsavel vai
        // fazer

        velocidade.addActionListener(e -> {
            // cria um painel para o controle deslizante de velocidade
            JPanel panel = new JPanel(new BorderLayout());

            // cria o slider (controle deslizante de velocidade)
            JSlider slider = new JSlider(JSlider.HORIZONTAL, 10, 100, ConfigManager.getAnimationSpeed());
            slider.setMajorTickSpacing(10);// marcadores grandes a cada 100
            slider.setMinorTickSpacing(5); // marcadores pequenos a cada 50
            slider.setPaintTicks(true); // mostra os tracinhos
            slider.setPaintLabels(true); // mostra os números
            slider.setSnapToTicks(true); // gruda os numeros nos marcadores

            // Rótulo explicativo
            JLabel label = new JLabel("Velocidade da animação:");
            panel.add(label, BorderLayout.NORTH);// fica acima do controle d velocidade
            panel.add(slider, BorderLayout.CENTER);// e tambem centralizado

            // Mostra o diálogo com o slider
            int result = JOptionPane.showConfirmDialog(
                    this,
                    panel,
                    "Ajustar Velocidade",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            // caso o usuário tenha clicado em OK
            if (result == JOptionPane.OK_OPTION) {
                int novaVelocidade = slider.getValue();// o programa captura a velocidade que o usuario colocou
                ConfigManager.setAnimationSpeed(novaVelocidade);
                statusBar.setText("Velocidade alterada para " + novaVelocidade + " ms"); // e salva essa velocidade
                /*
                 * //parte para atualizar a animação em tempo real
                 * if (backgroundPanel != null) {
                 * backgroundPanel.atualizarVelocidade();
                 * }
                 */
            }
        });

        menuConfig.add(padroes);
        menuConfig.add(cores);
        menuConfig.add(velocidade);

        // Manu Ajuda ou submenu de ajuda
        JMenu menuAjuda = new JMenu("Ajuda"); // titulo do menu ajuda
        JMenuItem ajuda = new JMenuItem("Ajuda");
        JMenuItem sobre = new JMenuItem("Sobre");

        ajuda.addActionListener(e -> {
            Help helpDialog = new Help(this);
            helpDialog.setVisible(true); // mostra o conteudo de ajuda
        });

        sobre.addActionListener(e -> {
            About aboutDialog = new About(this);
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
