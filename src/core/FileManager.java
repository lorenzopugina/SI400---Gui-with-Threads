package core;

import java.awt.Component;
import java.io.*;
import java.nio.file.*;
import javax.swing.*;

public class FileManager {
    // Opens a JFileChooser, lets the user select a text file and returns the content as String 
    public static String openFile(Component parent) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(parent);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                return Files.readString(file.toPath());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(parent,
                        "Error opening file: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }

    // Reads the content of a file from the given path
    public static String readFile(String path) throws IOException {
        return Files.readString(Path.of(path));
    }

    // "Closes" the file: clears the JTextArea and updates the status bar
    public static void closeFile(JTextArea textArea, JLabel statusBar) {
        textArea.setText(""); // clears content
        statusBar.setText("File closed.");
    }
}
