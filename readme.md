Main → inicializa a aplicação e mostra a janela principal.
Responsabilidade: inicializar o Swing e abrir a janela principal (MainFrame).

MainFrame → classe que herda de JFrame e monta toda a interface gráfica (menus, barra de status, área de texto, painel de fundo animado).
Responsabilidade: montar a interface → menus, status bar, área de texto, painel de fundo.

BackgroundPanel → painel personalizado que cuida da animação no fundo (roda numa thread separada).
Responsabilidade: desenhar elementos gráficos (paintComponent) e rodar uma thread que atualiza o estado e chama repaint()

ConfigManager → gerencia configurações (cor, velocidade, padrões da animação).
Responsabilidade: central de configurações globais. armazenar cor do fundo, velocidade da animação, padrões etc.

FileManager → utilitário para leitura de arquivos de texto.
Responsabilidade: retornar o conteúdo como String para exibir no JTextArea.

About → diálogo simples com informações da aplicação.
Responsabilidade: mostrar informações sobre a aplicação.

Help → diálogo personalizado com imagens, texto rolável e botões.
Responsabilidade: mostrar texto rolável, imagens e botões.


Adriano 
Implementar Help:
JDialog modal com JScrollPane, imagens e texto explicativo.
Botões "Fechar" e talvez "Mais info".
Implementar About simples com versão, autores e informações do projeto.

Ana
Implementar "Abrir Arquivo" usando JFileChooser + FileManager.
Implementar "Fechar Arquivo" (limpa JTextArea).
Implementar "Sair" (encerra a aplicação).
Conectar ouvintes de JMenuItem ao JTextArea.

João
Implementar submenus:
Padrões: ex. resetar cor/velocidade.
Cores: escolher cor do fundo animado (JColorChooser ou opções predefinidas).
Velocidade: usar JSlider ou subopções (lento, médio, rápido).
Garantir que as opções alteram comportamento da BackgroundPanel.

Maria
Criar a classe MainFrame (extends JFrame).
Adicionar título, ícone, área de status.
Preparar o JTextArea central para exibir conteúdo de arquivos.
Integrar JMenuBar com os menus (Arquivo, Configuração, Ajuda).
Criar esqueleto dos JMenuItem com ouvintes vazios (stub).

Lorenzo
Implementar BackgroundPanel que herda de JPanel.
Criar Thread para animação (ex: formas geométricas mudando cor/posição).
Integrar controle de velocidade e cor via ConfigManager.
Garantir execução suave (usar SwingUtilities.invokeLater quando necessário).

Yuu
Criar FileManager (leitura de arquivo de texto e retorno como String).
Criar ConfigManager (armazenar cor, velocidade, padrão).
Fazer a integração final: juntar MainFrame + FileManager + BackgroundPanel + Dialogs.
Garantir consistência visual da interface.