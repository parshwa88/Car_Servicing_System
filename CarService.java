import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class Person {

    public String first_Name, last_Name, email, phone;

    public Person(String firstName, String lastName,
                  String email, String phone) {

        this.first_Name = firstName;
        this.last_Name = lastName;
        this.email = email;
        this.phone = phone;
    }

    public String getFullName() {

        return first_Name + " " + last_Name;
    }

    public String getContactDetails() {

        return "Email: " + email + "\nPhone: " + phone;
    }
}

class Vehicle {

    public String identity_no, brand, model;
    public int year, kilometers;

    public Vehicle(String identity_no,
                   String brand,
                   String model,
                   int year,
                   int kilometers) {

        this.identity_no = identity_no;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.kilometers = kilometers;
    }

    public String getVehicleInfo() {

        return brand + " " + model + " (" + year + ")" +
                "\nVehicle ID: " + identity_no +
                "\nKilometers: " + kilometers;
    }
}

class ServiceRequest extends Person {

    public Vehicle vehicle;
    public String issues;

    public ServiceRequest(String firstName,
                          String lastName,
                          String email,
                          String phone,
                          Vehicle vehicle,
                          String issues) {

        super(firstName, lastName, email, phone);

        this.vehicle = vehicle;
        this.issues = issues;
    }

    public String getFullDetails() {

        return "Customer: " + getFullName() + "\n" +
                getContactDetails() + "\n\n" +
                "Vehicle: " + vehicle.getVehicleInfo() + "\n\n" +
                "Issue: " + issues;
    }
}

public class CarService {

    public static void form() {

        JFrame f = new JFrame("Car Service Booking");

        f.setSize(1400, 700);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.setLayout(new GridLayout(13, 2, 10, 10));

        JMenuBar mb = new JMenuBar();

        JLabel title =
                new JLabel("Welcome To Car Service Booking",
                        JLabel.CENTER);

        title.setFont(new Font("Arial", Font.BOLD, 18));

        mb.setLayout(new BorderLayout());

        mb.add(title, BorderLayout.CENTER);

        f.setJMenuBar(mb);

        f.getContentPane().setBackground(
                new Color(230, 240, 255)
        );

        JLabel jl = new JLabel("First Name:");
        JTextField jt = new JTextField();

        JLabel jl1 = new JLabel("Last Name:");
        JTextField jt1 = new JTextField();

        JLabel jl2 = new JLabel("Email:");
        JTextField jt2 = new JTextField();

        JLabel jl3 = new JLabel("Phone:");
        JTextField jt3 = new JTextField();

        JLabel jl4 = new JLabel("Vehicle ID:");
        JTextField jt4 = new JTextField();

        JLabel jl5 = new JLabel("Brand:");

        JComboBox<String> jc =
                new JComboBox<>(new String[]{
                        "TOYOTA",
                        "MAHINDRA",
                        "SUZUKI",
                        "TATA"
                });

        JLabel jl6 = new JLabel("Model:");

        JComboBox<String> jc1 = new JComboBox<>();

        showModels("TOYOTA", jc1);

        jc.addActionListener(e -> {

            String brand = (String) jc.getSelectedItem();

            showModels(brand, jc1);
        });

        JLabel jl7 = new JLabel("Year:");
        JTextField jt6 = new JTextField();

        JLabel jl8 = new JLabel("Kilometers:");
        JTextField jt7 = new JTextField();

        JLabel jl9 = new JLabel("Issue:");

        JComboBox<String> issueBox =
                new JComboBox<>(new String[]{
                        "Oil Change",
                        "Tyre Replacement",
                        "Engine Work",
                        "Battery Check",
                        "Brake Service",
                        "AC Repair"
                });

        // FIXED DATE FIELD (NO JCALENDAR)

        JLabel jl10 = new JLabel("Service Date (YYYY-MM-DD):");

        JTextField dateField = new JTextField();

        JLabel jl11 = new JLabel("Time Slot:");

        JComboBox<String> jc2 =
                new JComboBox<>(new String[]{
                        "09:00 AM - 12:00 PM",
                        "12:00 PM - 03:00 PM",
                        "05:00 PM - 07:30 PM"
                });

        JButton submitBtn =
                new JButton("Complete Form");

        JButton lgbtn =
                new JButton("Logout");

        f.add(jl);
        f.add(jt);

        f.add(jl1);
        f.add(jt1);

        f.add(jl2);
        f.add(jt2);

        f.add(jl3);
        f.add(jt3);

        f.add(jl4);
        f.add(jt4);

        f.add(jl5);
        f.add(jc);

        f.add(jl6);
        f.add(jc1);

        f.add(jl7);
        f.add(jt6);

        f.add(jl8);
        f.add(jt7);

        f.add(jl9);
        f.add(issueBox);

        f.add(jl10);
        f.add(dateField);

        f.add(jl11);
        f.add(jc2);

        f.add(submitBtn);
        f.add(lgbtn);

        submitBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e1) {

                try {

                    String fName = jt.getText();

                    String lName = jt1.getText();

                    String email = jt2.getText();

                    String phone = jt3.getText();

                    String identity_no = jt4.getText();

                    String brand =
                            (String) jc.getSelectedItem();

                    String model =
                            (String) jc1.getSelectedItem();

                    int year =
                            Integer.parseInt(jt6.getText());

                    int km =
                            Integer.parseInt(jt7.getText());

                    String issue =
                            (String) issueBox.getSelectedItem();

                    String date =
                            dateField.getText();

                    String timeSlot =
                            (String) jc2.getSelectedItem();

                    if (date.isEmpty()) {

                        JOptionPane.showMessageDialog(
                                f,
                                "Please Enter Date"
                        );

                        return;
                    }

                    Vehicle vehicle =
                            new Vehicle(
                                    identity_no,
                                    brand,
                                    model,
                                    year,
                                    km
                            );

                    ServiceRequest request =
                            new ServiceRequest(
                                    fName,
                                    lName,
                                    email,
                                    phone,
                                    vehicle,
                                    issue
                            );

                    try {

                        Class.forName(
                                "oracle.jdbc.driver.OracleDriver"
                        );

                        Connection con =
                                DriverManager.getConnection(
                                        "jdbc:oracle:thin:@localhost:1521:XE",
                                        "system",
                                        "Pr@872005"
                                );

                        // CHECK SLOT

                        String checkQuery =
                                "SELECT COUNT(*) FROM car_db " +
                                        "WHERE service_date = TO_DATE(?, 'YYYY-MM-DD') " +
                                        "AND time_slot = ?";

                        PreparedStatement ps1 =
                                con.prepareStatement(checkQuery);

                        ps1.setString(1, date);

                        ps1.setString(2, timeSlot);

                        ResultSet rs =
                                ps1.executeQuery();

                        rs.next();

                        int count = rs.getInt(1);

                        if (count > 0) {

                            JOptionPane.showMessageDialog(
                                    f,
                                    "Time Slot Already Booked"
                            );

                            return;
                        }

                        // INSERT DATA

                        String sql =
                                "INSERT INTO car_db " +
                                        "(first_name,last_name,email,phone," +
                                        "vehicle_id,brand,model,year,kilometers," +
                                        "issue,service_date,time_slot)" +
                                        "VALUES(?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'YYYY-MM-DD'),?)";

                        PreparedStatement ps =
                                con.prepareStatement(sql);

                        ps.setString(1, fName);

                        ps.setString(2, lName);

                        ps.setString(3, email);

                        ps.setString(4, phone);

                        ps.setString(5, identity_no);

                        ps.setString(6, brand);

                        ps.setString(7, model);

                        ps.setInt(8, year);

                        ps.setInt(9, km);

                        ps.setString(10, issue);

                        ps.setString(11, date);

                        ps.setString(12, timeSlot);

                        int rows =
                                ps.executeUpdate();

                        if (rows > 0) {

                            JOptionPane.showMessageDialog(
                                    f,
                                    request.getFullDetails()
                                            + "\n\nDate: " + date
                                            + "\nTime Slot: " + timeSlot,
                                    "Booking Confirmed",
                                    JOptionPane.INFORMATION_MESSAGE
                            );

                        } else {

                            JOptionPane.showMessageDialog(
                                    f,
                                    "Booking Failed"
                            );
                        }

                        ps.close();

                        ps1.close();

                        rs.close();

                        con.close();

                    } catch (Exception db) {

                        JOptionPane.showMessageDialog(
                                f,
                                "Database Error:\n" + db.getMessage()
                        );

                        System.out.println(db);
                    }

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(
                            f,
                            "Invalid Input"
                    );

                    System.out.println(ex);
                }
            }
        });

        lgbtn.addActionListener(e -> {

            f.dispose();

            Login.main(null);
        });

        f.setVisible(true);
    }

    public static void showModels(String brand,
                                  JComboBox<String> jc1) {

        jc1.removeAllItems();

        if (brand.equals("TOYOTA")) {

            jc1.addItem("Etios");
            jc1.addItem("Innova");
            jc1.addItem("Fortuner");
            jc1.addItem("Crysta");

        } else if (brand.equals("MAHINDRA")) {

            jc1.addItem("Scorpio");
            jc1.addItem("Thar");
            jc1.addItem("XUV700");
            jc1.addItem("Bolero");

        } else if (brand.equals("SUZUKI")) {

            jc1.addItem("Brezza");
            jc1.addItem("Grand Vitara");
            jc1.addItem("Ertiga");
            jc1.addItem("Swift");

        } else if (brand.equals("TATA")) {

            jc1.addItem("Harrier");
            jc1.addItem("Curvv");
            jc1.addItem("Safari");
            jc1.addItem("Punch");
        }
    }

    public static void main(String[] args) {

        form();
    }
}