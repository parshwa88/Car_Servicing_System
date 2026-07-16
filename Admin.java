import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class Admin {
    public static void main(String[] args) {
        JFrame f = new JFrame("Admin Login");
        f.setSize(300, 200);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);

        JLabel jl = new JLabel("Username:");
        jl.setBounds(30, 30, 80, 25);
        f.add(jl);

        JTextField tf = new JTextField();
        tf.setBounds(120, 30, 130, 25);
        f.add(tf);

        JLabel jl1 = new JLabel("Password:");
        jl1.setBounds(30, 70, 80, 25);
        f.add(jl1);

        JPasswordField pf = new JPasswordField();
        pf.setBounds(120, 70, 130, 25);
        f.add(pf);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(90, 110, 100, 25);
        loginBtn.setBackground(Color.BLUE);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);
        f.add(loginBtn);

        f.setVisible(true);

        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = tf.getText();
                String password = new String(pf.getPassword());

                if (username.equals("admin") && password.equals("admin123")) {
                    f.dispose();
                    showServiceHistory();
                } else {
                    JOptionPane.showMessageDialog(f, "Invalid Admin Credentials!");
                }
            }
        });
    }

    public static void showServiceHistory() {
        JFrame frame = new JFrame("Service History");
        frame.setSize(900, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        String[] columnNames = { "First Name", "Last Name", "Email", "Phone", "Vehicle ID", "Brand", "Model", "Year",
                "KM", "Issue", "Date", "Time Slot" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);

        sp.setBounds(20, 20, 850, 300);
        frame.add(sp);

        Connection con;
        Statement st;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "Pr@872005");

            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM car_db");

            while (rs.next()) {
                String[] row = {
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("vehicle_id"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getString("year"),
                        rs.getString("kilometers"),
                        rs.getString("issue"),
                        rs.getString("service_date"),
                        rs.getString("time_slot")
                };
                model.addRow(row);
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        frame.setVisible(true);
    }
}
