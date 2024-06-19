import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LockerApp extends JFrame {
    private JPasswordField passwordField;
    private JLabel statusLabel;
    private String storedPassword;

    public LockerApp() {
        // Set up the frame
        setTitle("Locker App");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create components
        passwordField = new JPasswordField(10);
        JButton enterButton = new JButton("Enter");
        JButton clearButton = new JButton("Clear");
        statusLabel = new JLabel("Enter Password", SwingConstants.CENTER);

        // Panel for the number buttons
        JPanel numberPanel = new JPanel();
        numberPanel.setLayout(new GridLayout(3, 3));

        // Add number buttons
        for (int i = 1; i <= 9; i++) {
            JButton numberButton = new JButton(String.valueOf(i));
            numberButton.addActionListener(new NumberButtonListener());
            numberPanel.add(numberButton);
        }

        // Panel for the control buttons and password field
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());
        JPanel controlButtonsPanel = new JPanel();
        controlButtonsPanel.setLayout(new GridLayout(1, 2));
        controlButtonsPanel.add(clearButton);
        controlButtonsPanel.add(enterButton);

        JPanel passwordPanel = new JPanel();
        passwordPanel.add(passwordField);

        controlPanel.add(controlButtonsPanel, BorderLayout.WEST);
        controlPanel.add(passwordPanel, BorderLayout.CENTER);
        controlPanel.add(statusLabel, BorderLayout.EAST);

        // Add components to the frame
        add(numberPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        // Add action listeners
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleEnterButton();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordField.setText("");
                statusLabel.setText("Enter Password");
            }
        });

        // Initialize stored password
        storedPassword = ""; // initialize as empty, you can change this to null if needed
    }

    private void handleEnterButton() {
        String enteredPassword = new String(passwordField.getPassword());
        if (storedPassword.isEmpty()) {
            storedPassword = enteredPassword;
            statusLabel.setText("Password Set");
        } else {
            if (storedPassword.equals(enteredPassword)) {
                statusLabel.setText("Correct Password");
            } else {
                statusLabel.setText("Incorrect Password");
            }
        }
        passwordField.setText("");
    }

    private class NumberButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String number = e.getActionCommand();
            passwordField.setText(passwordField.getText() + number);
        }
    }


}
