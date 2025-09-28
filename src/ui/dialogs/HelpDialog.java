package ui.dialogs;

import javax.swing.*;
import java.awt.*;

public class HelpDialog extends JDialog {

    public HelpDialog(JFrame parent) {
        super(parent, "Ajuda", true);
        setSize(500, 400);
        setLocationRelativeTo(parent);
        setResizable(false);

        // Painel principal para o conteúdo de ajuda
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Título
        JLabel titleLabel = new JLabel("Guia de Ajuda da Aplicação");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Texto explicativo
        String textContent = "Bem-vindo(a) ao guia de ajuda da aplicação GUI com Threads.<br><br>" +
                             "Esta aplicação demonstra o uso de interfaces gráficas em Java (Swing) " +
                             "com animações de fundo executadas em threads separadas para garantir " +
                             "uma experiência de usuário fluida.<br><br>" +
                             "<b>Funcionalidades Principais:</b><br><br>" +
                             "<b>1. Área de Texto Principal:</b> No lado superior direito da janela, há uma área de texto " +
                             "onde é possível carregar e visualizar o conteúdo de arquivos de texto. " +
                             "Utilize o menu \'Arquivo\' para abrir novos arquivos.<br><br>" +
                             "<b>2. Painel de Fundo Animado:</b> O fundo da aplicação exibe uma animação contínua. " +
                             "Esta animação é gerenciada por uma thread dedicada, que atualiza o estado " +
                             "dos elementos gráficos e redesenha o painel periodicamente.<br><br>" +
                             "<b>3. Menu \'Arquivo\':</b><br>" +
                             "    -   <b>Abrir:</b> Permite selecionar um arquivo de texto do seu sistema e " +
                             "carregar seu conteúdo na área de texto principal.<br>" +
                             "    -   <b>Sair:</b> Encerra a aplicação.<br><br>" +
                             "<b>4. Menu \'Configurações\':</b><br>" +
                             "    -   <b>Cor de Fundo:</b> Altera a cor predominante do painel de fundo animado.<br>" +
                             "    -   <b>Velocidade da Animação:</b> Ajusta a velocidade com que os elementos " +
                             "se movem no painel de fundo.<br>" +
                             "    -   <b>Padrões:</b> Permite escolher diferentes padrões ou tipos de animação " +
                             "para o fundo.<br><br>" +
                             "<b>5. Barra de Status:</b> Localizada na parte inferior da janela, exibe informações " +
                             "relevantes sobre o estado atual da aplicação, como o nome do arquivo carregado " +
                             "ou mensagens de status.<br><br>";

        // Usando JEditorPane para renderizar HTML
        JEditorPane editorPane = new JEditorPane("text/html", "<html><body style=\'font-family: Arial; font-size: 12pt;\'>" + textContent + "</body></html>");
        editorPane.setEditable(false);
        editorPane.setOpaque(false);
        editorPane.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);

        JScrollPane scrollPane = new JScrollPane(editorPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        contentPanel.add(scrollPane);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Botões
        JButton closeButton = new JButton("Fechar");
        closeButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);

        // Adiciona os componentes ao diálogo
        add(contentPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}

