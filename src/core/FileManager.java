package core;

import java.awt.*;
import java.io.*;
import java.nio.file.*;
import javax.swing.*;

public class FileManager {

    /**
     * Abre um JFileChooser, permite selecionar um arquivo de texto
     * e retorna o conteúdo como String.
     */
    public static String openFile(Component parent) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(parent);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                return Files.readString(file.toPath());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(parent,
                        "Erro ao abrir arquivo: " + e.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }

    /**
     * Apenas lê o conteúdo de um arquivo a partir do caminho informado.
     * Esse método é útil se, no futuro, você quiser chamar o leitor sem JFileChooser.
     */
    public static String readFile(String path) throws IOException {
        return Files.readString(Path.of(path));
    }
}
