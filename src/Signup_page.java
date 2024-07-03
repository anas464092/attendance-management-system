import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Signup_page {

    JLabel labellogo = attendance_managment_label();
    JLabel labellogo2 = systemlabel();
    JLabel loginlabel = Label_login();
    JLabel username = username_label();
    JLabel password = password_label();
    JLabel notusertxt = newuser_label();
    JLabel coursetxt = course_label();

    private static Connection connection; // Instance for the connection...

    static JFrame signup_window = new JFrame("Sign Up");

    Signup_page(){

        // ============================== COURSE TEXT FIELD ========================================

        textfield course_txtfield = new textfield(20, 20, 20);
        course_txtfield.setPreferredSize(new Dimension(200, 25));
        course_txtfield.setBackground(Color.WHITE);
        course_txtfield.setBounds(510, 230, 250, 25);

        // ============================== USERNAME TEXT FIELD ========================================

        textfield usertxt = new textfield(20, 20, 20);
        usertxt.setPreferredSize(new Dimension(200, 25));
        usertxt.setBackground(Color.WHITE);
        usertxt.setBounds(510, 130, 250, 25);

        // ============================== PASSWORD TEXT FIELD ========================================

        textfield passtxt = new textfield(20, 20, 20);
        passtxt.setPreferredSize(new Dimension(200, 25));
        passtxt.setBackground(Color.WHITE);
        passtxt.setBounds(510, 180, 250, 25);

        // ========================== Signup BUTTON =====================================================

        RoundButton signupbutton = new RoundButton("Signup");
        signupbutton.setBackground(Color.DARK_GRAY);
        signupbutton.setForeground(Color.WHITE);
        signupbutton.setFont(new Font("Arial", Font.BOLD, 14));
        signupbutton.setBounds(560, 280, 85, 27);
        signupbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usertxt.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(signup_window, "Enter Name!!!","Warning",JOptionPane.INFORMATION_MESSAGE);
                    usertxt.setText("");
                    usertxt.grabFocus();
                }
                else if (passtxt.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(signup_window, "Enter Username!!!","Warning",JOptionPane.INFORMATION_MESSAGE);
                    passtxt.setText("");
                    passtxt.grabFocus();
                }
                else{  //  both creddentoia lare entered ..........................

                    signup_window.setVisible(false);

                    //   Conneccting Database ....
                    try {
                        connectToDatabase();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                    //  Creating login database....if not already exist .......
                    try {
                        createTableIfNotExists();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                    //  Signup Credentials .................
                    signUp(usertxt.getText(),passtxt.getText(), course_txtfield.getText());
                }
            }
        });

        // ========================== LOGIN BUTTON =====================================================

        RoundButton loginbutton = new RoundButton("Login");
        loginbutton.setBackground(Color.DARK_GRAY);
        loginbutton.setForeground(Color.WHITE);
        loginbutton.setFont(new Font("Arial", Font.PLAIN, 13));
        loginbutton.setBounds(575, 418, 75, 27);
        loginbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signup_window.setVisible(false);
                new LoginPage();
            }
        });

        // ============================ RESET BUTTON ===============================================

        RoundButton buttonreset = new RoundButton("Reset"); // For reset button
        buttonreset.setBackground(Color.WHITE);
        buttonreset.setForeground(Color.DARK_GRAY);
        buttonreset.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        buttonreset.setBounds(700,283, 80, 25);
        buttonreset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usertxt.setText("");
                passtxt.setText("");
                course_txtfield.setText("");
            }
        });

        //  ===================== LEFT PANEL ======================

        JPanel leftpanel = new JPanel();
        leftpanel.setBounds(0, 0, 400, 500);
        leftpanel.setBackground(Color.DARK_GRAY);
        leftpanel.setLayout(null);
        leftpanel.add(labellogo);
        leftpanel.add(labellogo2);

        // =======================================

        signup_window.setVisible(true);
        signup_window.getContentPane().setBackground(Color.WHITE);
        signup_window.setSize(800, 500);
        signup_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signup_window.setResizable(false);
        signup_window.setLocationRelativeTo(null);
        signup_window.setLayout(null);
        signup_window.add(leftpanel);
        signup_window.add(loginlabel);
        signup_window.add(username);
        signup_window.add(password);
        signup_window.add(usertxt);
        signup_window.add(passtxt);
        signup_window.add(loginbutton);
        signup_window.add(buttonreset);
        signup_window.add(notusertxt);
        signup_window.add(coursetxt);
        signup_window.add(course_txtfield);
        signup_window.add(signupbutton);
    }

    private static JLabel attendance_managment_label() { // Function for the label... Attendance Management System
        JLabel label = new JLabel();
        label.setText("Attendance Management");
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Book Antiqua", Font.BOLD, 30));
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(0, 150, 400, 500);
        return label;
    }

    private static JLabel systemlabel() { // Function for the label... Attendance Management System
        JLabel label = new JLabel();
        label.setText("System");
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Book Antiqua", Font.BOLD, 30));
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(0, 200, 400, 500);
        return label;
    }

    //  ========================  LOGIN TEXT LABEL =============================

    private static JLabel Label_login() {
        JLabel label = new JLabel();
        label.setText("SignUp");
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setForeground(Color.darkGray);
        label.setFont(new Font("Segoe UI", Font.BOLD, 33));
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(400, 50, 400, 500);
        return label;
    }

    // ===================== USERNAME TEXT LABEL =============================

    private static JLabel username_label() {
        JLabel label = new JLabel();
        label.setText("Your Name");
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setForeground(Color.darkGray);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(400, 130, 100, 500);
        return label;
    }

    //    ====================== PASSWORD TEXT LABEL ===============================
    private static JLabel password_label() {
        JLabel label = new JLabel();
        label.setText("Username");
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setForeground(Color.darkGray);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(400, 180, 100, 500);
        return label;
    }
    private static JLabel course_label() {
        JLabel label = new JLabel();
        label.setText("Course");
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setForeground(Color.darkGray);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(400, 230, 100, 500);
        return label;
    }

    //    ====================== NEW USER TEXT LABEL ===============================
    private static JLabel newuser_label() {
        JLabel label = new JLabel();
        label.setText("I already have an account ");
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setForeground(Color.darkGray);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(400, 420, 180, 25);
        return label;
    }

    //   ======= CONNECTION TO THE DATABASE ======================

    private static void connectToDatabase() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/";
        String username = "root";
        String password = "Anas@464092"; // Replace with your MySQL password
        connection = DriverManager.getConnection(url, username, password);
        System.out.println("Connected Successfully....");
    }

    //  ======= Creating login database ===============================

    private static void createTableIfNotExists() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = "CREATE DATABASE IF NOT EXISTS attendance_management_system";
            statement.executeUpdate(sql);
        }

        String useDatabase = "USE attendance_management_system";
        String createUserTable = "CREATE TABLE IF NOT EXISTS users (teacher_id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100), username VARCHAR(50) UNIQUE, password VARCHAR(50),course VARCHAR(100), date_created DATE)";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(useDatabase);
            statement.executeUpdate(createUserTable);
        }
        System.out.println("Create succeessfull login system ....");
    }

    //  ====================== Siginup Credential ======================================

    private static void signUp(String name,String username,String course) {

        // Generate teacher ID starting from 100001
        int teacherId = generateTeacherId();

        // Encrypt teacher ID using Caesar Cipher
        String encryptedId = encryptId(teacherId);

        // Generate password
        String password = generatePassword();

        // Get current date
        java.sql.Date dateCreated = new java.sql.Date(System.currentTimeMillis());

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (teacher_id, name, username, password, course, date_created) VALUES (?, ?, ?,?, ?, ?)");
            statement.setInt(1, teacherId);
            statement.setString(2, name);
            statement.setString(3, username);
            statement.setString(4, password);
            statement.setString(5, course); // Use the provided course parameter instead of course_txtfield
            statement.setDate(6, dateCreated); // Set date_created parameter
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                String message = "Signed up successfully.\n";
                message += "Your teacher ID: " + teacherId + "\n";
                message += "Your password: " + password;
                //  OptionPane showing password and user id for the new user...
                JOptionPane.showMessageDialog(null, message, "Signup Success", JOptionPane.INFORMATION_MESSAGE);
                new LoginPage();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to sign up.", "Signup Error", JOptionPane.ERROR_MESSAGE);
                signup_window.setVisible(false);
                new Signup_page();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Username already exists.", "Signup Error", JOptionPane.ERROR_MESSAGE);
            signup_window.setVisible(false);
            new Signup_page();
        }
    }

    //  =====================  Generating User ID  ========================

    private static int generateTeacherId() {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT MAX(teacher_id) FROM users");
            if (resultSet.next()) {
                int maxId = resultSet.getInt(1);
                // If there are no existing users, return starting ID 100001
                return Math.max(maxId + 1, 100001);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Default ID if there are no existing users
        return 100001;
    }

    // =============== Generating Password ================================

    private static String generatePassword() {
        // Generate a random 6-character password
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            char randomChar = (char) ((int) (Math.random() * 26) + 'a');
            password.append(randomChar);
        }
        return password.toString();
    }

    private static String encryptId(int teacherId) {
        // Caesar Cipher with a fixed shift value
        int shift = 3;
        StringBuilder encryptedId = new StringBuilder();
        for (char c : String.valueOf(teacherId).toCharArray()) {
            if (Character.isDigit(c)) {
                int digit = Character.getNumericValue(c);
                int encryptedDigit = (digit + shift) % 10;
                encryptedId.append(encryptedDigit);
            } else {
                encryptedId.append(c);
            }
        }
        return encryptedId.toString();
    }

}
