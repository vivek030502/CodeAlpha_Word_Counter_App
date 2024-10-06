# Word Counter Application

### A simple Java Swing-based application to count the number of words in a text. The application provides real-time word count updates as the user types, with automatic word wrapping and scrollbars for easy navigation. It uses the modern Nimbus Look and Feel to enhance the appearance of the application.

## Features
- **Real-time Word Count**: Dynamically updates the word count as the user types in the text area.
- **Text Wrapping**: Automatically moves words to the next line when they reach the edge of the visible area.
- **Scrollbars**: Horizontal and vertical scrollbars appear when the text exceeds the visible area.
- **Reset Button**: Clears the text area with a single click.
- **Modern Look and Feel**: The application uses Nimbus Look and Feel for a modern UI experience.
- **Customizable Appearance**: Text area background color can be easily modified.

## Prerequisites
To run this project, you need the following software installed on your system:
- **Java JDK 8+**
- **IDE**: (Optional) An IDE like IntelliJ IDEA, Eclipse, or NetBeans for easy editing and running of the project.

## How to Run

1. **Clone or Download the Project**:
    - Clone the repository:
      ```bash
      [https://github.com/vivek030502/CodeAlpha-Internship/CodeAlpha_Word_Counter.git](https://github.com/vivek030502/CodeAlpha_Word_Counter_App)
      ```
    - Or download the project as a ZIP file and extract it.

2. **Open the Project**:
    - Open the project folder in your favorite Java IDE or compile it using the terminal.

3. **Compile and Run**:
    - If you're using an IDE, locate the `WordCounter.java` file, right-click, and select "Run" or use the IDE's build/run options.
    - Alternatively, if using the terminal, navigate to the project folder and run the following commands:
      ```bash
      javac WordCounter.java
      java WordCounter
      ```

4. **Use the Application**:
    - Type text into the text area to see the real-time word count update.
    - Scroll through the text using the scrollbars when the text exceeds the visible area.
    - Click the "Clear Text" button to reset the text area.

## Project Structure
```
word-counter-app/
│
├── src/
│   └── WordCounter.java       # Main Java file containing the application code
├── README.md                     # Project documentation (this file)
└── LICENSE                       # License file (optional)
```

## Customization
- **Background Color**: The background color of the text area can be customized by changing the following line in the `WordCounterApp.java` file:
  ```java
  textArea.setBackground(Color.LIGHT_GRAY);
  ```
  Replace `Color.LIGHT_GRAY` with any other color (e.g., `Color.WHITE`, `Color.BLUE`).

- **Font and Word Count Label**: The word count label’s font size and style can be modified in this line:
  ```java
  wordCountLabel.setFont(new Font("Arial", Font.BOLD, 16));
  ```
  Adjust the `Font.BOLD` and `16` to your preferred font style and size.

## Technologies Used
- **Java Swing**: For the graphical user interface (GUI).
- **Nimbus Look and Feel**: For the modern appearance of the application.
- **JTextArea, JScrollPane, JLabel, JButton**: Swing components used in the project.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
