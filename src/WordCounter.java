import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.formdev.flatlaf.FlatLightLaf;

public class WordCounter extends JFrame implements ActionListener {

    // Components
    JTextArea textArea;
    JButton countButton, saveButton, loadButton, darkModeButton;
    JLabel resultLabel;
    JScrollPane scrollPane;
    JMenuBar menuBar;
    JMenu fileMenu, helpMenu;
    JMenuItem exitMenuItem, aboutMenuItem;
    JLabel statusBar;

    boolean isDarkMode = false;  // for dark mode

    public WordCounter() {
        JFrame frame = new JFrame("Word Counter");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Set Light theme
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea.setForeground(Color.BLACK);
        textArea.setBackground(Color.WHITE);
        textArea.setToolTipText("Enter your text here to count words");

        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(50, 30, 380, 200);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.add(scrollPane);

        // word count button
        countButton = new JButton("Count Words");
        countButton.setBounds(50, 250, 150, 30);
        countButton.addActionListener(this);
        countButton.setBackground(Color.GREEN);
        countButton.setForeground(Color.WHITE);
        countButton.setFont(new Font("Arial", Font.BOLD, 14));
        countButton.setToolTipText("Click to count words");
        frame.add(countButton);

        resultLabel = new JLabel("Word count: 0 | Character count: 0");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resultLabel.setForeground(Color.BLUE);
        resultLabel.setBounds(50, 300, 300, 30);
        frame.add(resultLabel);

        // save button
        saveButton = new JButton("Save");
        saveButton.setBounds(50, 350, 80, 30);
        saveButton.setBackground(Color.BLUE);
        saveButton.setForeground(Color.WHITE);
        saveButton.addActionListener(e -> saveText(frame));
        frame.add(saveButton);

        // load button
        loadButton = new JButton("Upload");
        loadButton.setBounds(150, 350, 80, 30);
        loadButton.setBackground(Color.ORANGE);
        loadButton.setForeground(Color.WHITE);
        loadButton.addActionListener(e -> loadText(frame));
        frame.add(loadButton);

        // dark mode
        darkModeButton = new JButton("Dark Mode");
        darkModeButton.setBounds(250, 350, 120, 30);
        darkModeButton.setBackground(Color.DARK_GRAY);
        darkModeButton.setForeground(Color.WHITE);
        darkModeButton.addActionListener(e -> toggleDarkMode(frame));
        frame.add(darkModeButton);

        // status bar at the bottom of the window
        statusBar = new JLabel("Ready");
        statusBar.setBounds(0, 430, 500, 20);
        statusBar.setOpaque(true);
        statusBar.setBackground(Color.LIGHT_GRAY);
        frame.add(statusBar);

        // menu bar
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        // "File" menu
        fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        // "Exit" option in the "File" menu
        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitMenuItem);

        // "Help" menu
        helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);

        // "About" option in the "Help" menu
        aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Word Counter v1.0"));
        helpMenu.add(aboutMenuItem);

        // real-time word and character count
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                updateWordCount();
            }

            public void removeUpdate(DocumentEvent e) {
                updateWordCount();
            }

            public void insertUpdate(DocumentEvent e) {
                updateWordCount();
            }

            public void updateWordCount() {
                String text = textArea.getText();
                String[] words = text.trim().split("\\s+");
                int wordCount = words.length;
                int charCount = text.replaceAll("\\s+", "").length();
                resultLabel.setText("Word count: " + wordCount + " | Character count: " + charCount);
                statusBar.setText("Words: " + wordCount + " | Characters: " + charCount);
            }
        });

        frame.setVisible(true);
    }

    // Save the text to a file
    private void saveText(JFrame frame) {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(frame);
        if (option == JFileChooser.APPROVE_OPTION) {
            try {
                FileWriter writer = new FileWriter(fileChooser.getSelectedFile());
                writer.write(textArea.getText());
                writer.close();
                JOptionPane.showMessageDialog(frame, "File saved successfully!");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Upload the text from a file
    private void loadText(JFrame frame) {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(frame);
        if (option == JFileChooser.APPROVE_OPTION) {
            try {
                FileReader reader = new FileReader(fileChooser.getSelectedFile());
                textArea.read(reader, null);
                reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // light and dark mode
    private void toggleDarkMode(JFrame frame) {
        if (isDarkMode) {
            textArea.setBackground(Color.WHITE);
            textArea.setForeground(Color.BLACK);
            resultLabel.setForeground(Color.BLUE);
            statusBar.setBackground(Color.LIGHT_GRAY);
            frame.getContentPane().setBackground(Color.LIGHT_GRAY);
            darkModeButton.setText("Dark Mode");
        } else {
            textArea.setBackground(Color.DARK_GRAY);
            textArea.setForeground(Color.WHITE);
            resultLabel.setForeground(Color.WHITE);
            statusBar.setBackground(Color.DARK_GRAY);
            frame.getContentPane().setBackground(Color.BLACK);
            darkModeButton.setText("Light Mode");
        }
        isDarkMode = !isDarkMode;
    }

    // Action event for the "Count Words" button
    public void actionPerformed(ActionEvent e) {
        // Manual word count when button is pressed
        String text = textArea.getText();
        String[] words = text.trim().split("\\s+");
        int wordCount = words.length;
        int charCount = text.replaceAll("\\s+", "").length();
        resultLabel.setText("Word count: " + wordCount + " | Character count: " + charCount);
        statusBar.setText("Words: " + wordCount + " | Characters: " + charCount);
    }

    public static void main(String[] args) {
        new WordCounter();
    }
}
