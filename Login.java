import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Login {
    public static void main(String[] args) {
        JFrame f = new JFrame("Login Form");
        f.setSize(300, 300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);

        ImageIcon bgImage = new ImageIcon("car.jpg");
        Image img = bgImage.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
        bgImage = new ImageIcon(img);

        JLabel background = new JLabel(bgImage);
        background.setBounds(0, 0, 400, 300);
        f.setContentPane(background);
        background.setLayout(null);

        JLabel jl = new JLabel("Username:");
        jl.setBounds(30, 30, 80, 25);
        background.add(jl);

        JTextField jt = new JTextField();
        jt.setBounds(120, 30, 130, 25);
        background.add(jt);

        JLabel jl1 = new JLabel("Password:");
        jl1.setBounds(30, 70, 80, 25);
        background.add(jl1);

        JPasswordField jp = new JPasswordField();
        jp.setBounds(120, 70, 130, 25);
        background.add(jp);

        JButton jb1 = new JButton("Login");
        jb1.setBounds(30, 150, 80, 25);
        background.add(jb1);

        JButton jb2 = new JButton("Register");
        jb2.setBounds(150, 150, 90, 25);
        background.add(jb2);

        JLabel jl2 = new JLabel("If you are a new user, please register first");
        jl2.setBounds(15, 190, 270, 25);
        jl2.setForeground(Color.BLUE);
        background.add(jl2);

        jb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = jt.getText();
                String password = new String(jp.getPassword());

                if (validateUser(username, password)) {
                    JOptionPane.showMessageDialog(f, "Login successful!");
                    CarService.form();
                    f.dispose();
                } else {
                    JOptionPane.showMessageDialog(f, "Invalid username or password.");
                }
            }
        });

        jb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                registration.main(null);
            }
        });

        f.setLayout(null);
        f.setVisible(true);
    }

    public static boolean userExists(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length > 0 && parts[0].equals(username))
                    return true;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return false;
    }

    public static boolean validateUser(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length > 1 && parts[0].equals(username) && parts[1].equals(password))
                    return true;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return false;
    }
}
