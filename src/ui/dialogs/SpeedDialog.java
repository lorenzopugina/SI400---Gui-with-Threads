package ui.dialogs;

//falta import
public class SpeedDialog extends JDialog {
    public SpeedDialog(JFrame parent) {
        super(parent, "Configurar Velocidade", true);
        setSize(300, 150);
        setLocationRelativeTo(parent);

        JSlider slider = new JSlider(10, 100, ConfigManager.getAnimationSpeed());
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        JButton ok = new JButton("OK");
        ok.addActionListener(e -> {
            ConfigManager.setAnimationSpeed(slider.getValue());
            dispose();
        });

        setLayout(new BorderLayout());
        add(new JLabel("Velocidade da animação:"), BorderLayout.NORTH);
        add(slider, BorderLayout.CENTER);
        add(ok, BorderLayout.SOUTH);
    }
}

