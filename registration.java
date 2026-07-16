import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class registration {
    public static void main(String[] args) {
        JFrame f = new JFrame("Registration Form");
        f.setSize(400, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setLayout(null);

        f.getContentPane().setBackground(new Color(230, 240, 255));

        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setBounds(30, 30, 100, 25);
        f.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(140, 30, 200, 25);
        f.add(nameField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(30, 70, 100, 25);
        f.add(ageLabel);

        JTextField ageField = new JTextField();
        ageField.setBounds(140, 70, 200, 25);
        f.add(ageField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(30, 110, 100, 25);
        f.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(140, 110, 200, 25);
        f.add(emailField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(30, 150, 100, 25);
        f.add(phoneLabel);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(140, 150, 200, 25);
        f.add(phoneField);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(30, 190, 100, 25);
        f.add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(140, 190, 200, 25);
        f.add(userField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(30, 230, 100, 25);
        f.add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(140, 230, 200, 25);
        f.add(passField);

        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(140, 280, 100, 30);
        f.add(registerBtn);

        registerBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String age = ageField.getText().trim();
                String email = emailField.getText().trim();
                String phone = phoneField.getText().trim();
                String username = userField.getText().trim();
                String password = new String(passField.getPassword());

                if (name.isEmpty() || age.isEmpty() || email.isEmpty() || phone.isEmpty() ||
                        username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(f, "All fields are required.");
                    return;
                }

                try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
                    writer.write(username + ":" + password + ":" + name + ":" + age + ":" + email + ":" + phone);
                    writer.newLine();
                    JOptionPane.showMessageDialog(f, "Registration successful.");
                    f.dispose();
                    Login.main(null);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(f, "Error saving user data.");
                }
            }
        });

        f.setVisible(true);
    }
}
