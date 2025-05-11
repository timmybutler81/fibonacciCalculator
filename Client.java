import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Client extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Client calculator = new Client();
                calculator.setVisible(true);
            }
        });
    }

    public Client() {
        setTitle("Fibonacci Calculator");
        setSize(400, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new GridLayout(3, 2, 10, 10)); // Grid layout with gaps

        JLabel enterLabel = new JLabel("Enter Fibonacci Number:");
        JTextField inputTextField = new JTextField();
        JButton calculateButton = new JButton("Calculate");
        JTextField resultTextField = new JTextField();
        resultTextField.setEditable(false);

        add(enterLabel);
        add(inputTextField);
        add(new JLabel());
        add(calculateButton);
        add(new JLabel());
        add(resultTextField);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = inputTextField.getText();
                if (!inputText.matches("\\d+")) {
                    resultTextField.setText("Please enter a valid integer."); // Display error message in resultTextField
                    return;
                }
                try {
                    Socket socket = new Socket("127.0.0.1", 8888);
                    PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                    String input = inputTextField.getText();
                    writer.println(input);
                    writer.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    resultTextField.setText(reader.readLine());
                    socket.close();

                } catch (UnknownHostException a) {
                    a.printStackTrace();
                } catch (IOException a) {
                    a.printStackTrace();
                }
            }
        });
    }
}