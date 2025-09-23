package ui.dialogs;

//falta import
public class ColorDialog extends JDialog {
    public ColorDialog(JFrame parent) {
        super(parent, "Configurar Cor", true);
        setSize(300, 150);
        setLocationRelativeTo(parent);

        String[] options = {"Vermelho", "Azul", "Aleatório"};
        JComboBox<String> combo = new JComboBox<>(options);

        JButton ok = new JButton("OK");
        ok.addActionListener(e -> {
            int index = combo.getSelectedIndex();
            switch (index) {
                case 0 -> ConfigManager.setBackgroundColor(Color.RED);
                case 1 -> ConfigManager.setBackgroundColor(Color.BLUE);
                case 2 -> ConfigManager.setBackgroundColor(null); // aleatório
            }
            dispose();
        });

        setLayout(new BorderLayout());
        add(new JLabel("Escolha a cor:"), BorderLayout.NORTH);
        add(combo, BorderLayout.CENTER);
        add(ok, BorderLayout.SOUTH);
    }
}

