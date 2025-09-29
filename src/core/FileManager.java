package core;

import java.awt.Component;
import java.io.*;
import java.nio.file.*;
import javax.swing.*;

public class FileManager {
    // opens a JFileChooser, lets the user select a text file and returns the content as String 
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

    public static String readFile(String path) throws IOException {
        return Files.readString(Path.of(path));
    }

    public static void closeFile(JTextArea textArea, JLabel statusBar) {
        textArea.setText(""); // clears content
        statusBar.setText("File closed.");
    }
}
