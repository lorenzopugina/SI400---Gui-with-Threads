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

        // Application icon
        try {
            setIconImage(new ImageIcon("src/util/icon_star.png").getImage());
        } catch (Exception e) {
            System.out.println("Icon not found, using default.");
        }

        // Text area
        textArea = new JTextArea();
        textArea.setOpaque(false); // transparent for animation
        textArea.setBackground(new Color(255, 255, 255, 200)); // semi-transparent background

        // Status bar
        statusBar = new JLabel("Status: ready");

        // Background panel with animation
        backgroundPanel = new BackgroundPanel();
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);

        backgroundPanel.add(scrollPane, BorderLayout.CENTER);
        backgroundPanel.add(statusBar, BorderLayout.SOUTH);

        // Menus
        setJMenuBar(createMenuBar());
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open File");
        JMenuItem closeItem = new JMenuItem("Close File");
        JMenuItem exitItem = new JMenuItem("Exit");

        openItem.addActionListener(e -> {
            String content = FileManager.openFile(this);
            if (content != null) {
                textArea.setText(content);
                statusBar.setText("File loaded successfully.");
            }
        });

        closeItem.addActionListener(e -> FileManager.closeFile(textArea, statusBar));
        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(openItem);
        fileMenu.add(closeItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Settings menu
        JMenu settingsMenu = new JMenu("Settings");
        JMenuItem patternItem = new JMenuItem("Patterns");
        JMenuItem colorItem = new JMenuItem("Colors");
        JMenuItem speedItem = new JMenuItem("Speed");
        JMenuItem shapeItem = new JMenuItem("Shapes");

        speedItem.addActionListener(e -> new SpeedDialog(this).setVisible(true));
        colorItem.addActionListener(e -> new ColorDialog(this).setVisible(true));
        shapeItem.addActionListener(e -> new ShapeDialog(this).setVisible(true));
        patternItem.addActionListener(e -> new PatternDialog(this).setVisible(true));

        settingsMenu.add(patternItem);
        settingsMenu.add(colorItem);
        settingsMenu.add(shapeItem);
        settingsMenu.add(speedItem);

        // Help menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem helpItem = new JMenuItem("Help");
        JMenuItem aboutItem = new JMenuItem("About");

        helpItem.addActionListener(e -> new HelpDialog(this).setVisible(true));
        aboutItem.addActionListener(e -> new AboutDialog(this).setVisible(true));

        helpMenu.add(helpItem);
        helpMenu.add(aboutItem);

        // Add menus to menu bar
        menuBar.add(fileMenu);
        menuBar.add(settingsMenu);
        menuBar.add(helpMenu);

        return menuBar;
    }
}
