import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginPage {

    JLabel labellogo = attendance_managment_label();
    JLabel labellogo2 = systemlabel();
    JLabel loginlabel = Label_login();
    JLabel username = username_label();
    JLabel password = password_label();
    JLabel notusertxt = newuser_label();
    JLabel coursetxt = course_label();

    static String course_table = "";

    private static Connection connection;

    JFrame login_window = new JFrame();

    LoginPage(){

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

        PasstxtField passtxt = new PasstxtField(20, 20, 20);
        passtxt.setPreferredSize(new Dimension(200, 25));
        passtxt.setBackground(Color.WHITE);
        passtxt.setBounds(510, 180, 250, 25);

        // ========================== LOGIN BUTTON =====================================================

        RoundButton loginbutton = new RoundButton("Login");
        loginbutton.setBackground(Color.DARK_GRAY);
        loginbutton.setForeground(Color.WHITE);
        loginbutton.setFont(new Font("Arial", Font.BOLD, 14));
        loginbutton.setBounds(560, 280, 85, 27);
        loginbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usertxt.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(login_window, "Enter username!!!","Warning",JOptionPane.INFORMATION_MESSAGE);
                    usertxt.setText("");
                    usertxt.grabFocus();
                }
                else if (passtxt.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(login_window, "Enter Password!!!","Warning",JOptionPane.INFORMATION_MESSAGE);
                    passtxt.setText("");
                    passtxt.grabFocus();
                }
                else if (course_txtfield.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(login_window, "Enter Course!!!","Warning",JOptionPane.INFORMATION_MESSAGE);
                    course_txtfield.setText("");
                    course_txtfield.grabFocus();
                }
                else{  //  both creddentoial are entered ..........................

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

                    //   Checking login Credentials....
                    int login_checker = login(usertxt.getText(),passtxt.getText(),course_txtfield.getText());
                    if(login_checker == 1 ){
                        course_table = course_txtfield.getText();
                        login_window.setVisible(false);
                        JOptionPane.showMessageDialog(login_window, "Login Successfully ...","Success",JOptionPane.INFORMATION_MESSAGE);
                        new Dashboard();
                    }
                    else if(login_checker == 2 ){
                        JOptionPane.showMessageDialog(login_window, "Invalid Credentials ...","Warning",JOptionPane.INFORMATION_MESSAGE);
                    }

                }
            }
        });

        // ========================== SINGUP BUTTON =====================================================

        RoundButton signupbutton = new RoundButton("Signup");
        signupbutton.setBackground(Color.DARK_GRAY);
        signupbutton.setForeground(Color.WHITE);
        signupbutton.setFont(new Font("Arial", Font.PLAIN, 13));
        signupbutton.setBounds(575, 418, 75, 27);
        signupbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login_window.setVisible(false);
                new Signup_page();
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
        login_window.setVisible(true);
        login_window.getContentPane().setBackground(Color.WHITE);
        login_window.setSize(800, 500);
        login_window.setTitle("Log In");
        login_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login_window.setResizable(false);
        login_window.setLocationRelativeTo(null);
        login_window.setLayout(null);
        login_window.add(leftpanel);
        login_window.add(loginlabel);
        login_window.add(username);
        login_window.add(password);
        login_window.add(usertxt);
        login_window.add(passtxt);
        login_window.add(loginbutton);
        login_window.add(buttonreset);
        login_window.add(notusertxt);
        login_window.add(signupbutton);
        login_window.add(coursetxt);
        login_window.add(course_txtfield);
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
        label.setText("Login");
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
        label.setText("User ID");
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
        label.setText("Password");
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setForeground(Color.darkGray);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(400, 180, 100, 500);
        return label;
    }

    //    ====================== NEW USER TEXT LABEL ===============================
    private static JLabel newuser_label() {
        JLabel label = new JLabel();
        label.setText("I don't have an account ");
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
        String createUserTable = "CREATE TABLE IF NOT EXISTS users (teacher_id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100), username VARCHAR(50) UNIQUE, password VARCHAR(50), date_created DATE)";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(useDatabase);
            statement.executeUpdate(createUserTable);
        }
        System.out.println("Create succeessfull login system ....");
    }

    //   =====================  CHECKING LOGIN CREDENTIAL ========================
    private static int login(String Teacher_ID_Login,String password,String course) {

        int teacherId = Integer.parseInt(Teacher_ID_Login);

        // Encrypt teacher ID using Caesar Cipher
        String encryptedId = encryptId(teacherId);

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE teacher_id = ? AND password = ? AND course = ?");
            statement.setInt(1, teacherId);
            statement.setString(2, password);
            statement.setString(3, course);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return 1;
            } else {
                return 2;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
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

}
