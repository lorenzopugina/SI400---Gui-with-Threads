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
        initUI();
    }

    private void initUI() {
        setTitle("Basic GUI with Threads");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            setIconImage(new ImageIcon("src/util/icon_star.png").getImage());
        } catch (Exception e) {
            System.out.println("Icone não encontrado, usando padrão.");
        }

        textArea = new JTextArea();
        textArea.setOpaque(false); // transparent for animation
        textArea.setBackground(new Color(255, 255, 255, 200)); // semi-transparent background

        statusBar = new JLabel("Status: pronto");

        backgroundPanel = new BackgroundPanel();
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);

        backgroundPanel.add(scrollPane, BorderLayout.CENTER);
        backgroundPanel.add(statusBar, BorderLayout.SOUTH);

        setJMenuBar(createMenuBar());
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Arquivo");
        JMenuItem openItem = new JMenuItem("Abrir Arquivo");
        JMenuItem closeItem = new JMenuItem("Fechar Arquivo");
        JMenuItem exitItem = new JMenuItem("Sair");

        openItem.addActionListener(e -> {
            String content = FileManager.openFile(this);
            if (content != null) {
                textArea.setText(content);
                statusBar.setText("Arquivo carregado com sucesso.");
            }
        });

        closeItem.addActionListener(e -> FileManager.closeFile(textArea, statusBar));
        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(openItem);
        fileMenu.add(closeItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JMenu settingsMenu = new JMenu("Configuração");
        JMenuItem patternItem = new JMenuItem("Padrões");
        JMenuItem colorItem = new JMenuItem("Cores");
        JMenuItem speedItem = new JMenuItem("Velocidade");
        JMenuItem shapeItem = new JMenuItem("Formas");

        speedItem.addActionListener(e -> new SpeedDialog(this, statusBar).setVisible(true));
        colorItem.addActionListener(e -> new ColorDialog(this, statusBar).setVisible(true));
        shapeItem.addActionListener(e -> new ShapeDialog(this, statusBar, backgroundPanel).setVisible(true));
        patternItem.addActionListener(e -> new PatternDialog(this, statusBar, backgroundPanel).setVisible(true));

        settingsMenu.add(patternItem);
        settingsMenu.add(colorItem);
        settingsMenu.add(shapeItem);
        settingsMenu.add(speedItem);

        JMenu helpMenu = new JMenu("Ajuda");
        JMenuItem helpItem = new JMenuItem("Ajuda");
        JMenuItem aboutItem = new JMenuItem("Sobre");

        helpItem.addActionListener(e -> new HelpDialog(this).setVisible(true));
        aboutItem.addActionListener(e -> new AboutDialog(this).setVisible(true));

        helpMenu.add(helpItem);
        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(settingsMenu);
        menuBar.add(helpMenu);

        return menuBar;
    }
}
