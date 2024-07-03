import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Addstudent {

    JLabel dashboardlabel = dashboard_label();
    static JFrame addstudent_window = new JFrame();
    JLabel qalam_id = qalam_label();
    JLabel name = name_label();
    JLabel username = username_label();
    JLabel email = email_label();
    JLabel semester = semester_label();

    textfield qalam_txt;
    textfield name_txt;
    textfield username_txt;
    textfield email_txt;
    textfield semester_txt;

    static java.sql.Date dateCreated = new java.sql.Date(System.currentTimeMillis());

    static Connection c;
    static String url = "jdbc:mysql://localhost:3306/attendance_management_system";
    static String username_mysql = "root";
    static String password = "Anas@464092";

    Addstudent() {

        // ============================== Qalam_ID TEXT FIELD ========================================

        qalam_txt = new textfield(20, 20, 20);
        qalam_txt.setPreferredSize(new Dimension(200, 25));
        qalam_txt.setBackground(Color.WHITE);
        qalam_txt.setBounds(430, 75, 280, 25);

        // ============================== NAME TEXT FIELD ========================================

        name_txt = new textfield(20, 20, 20);
        name_txt.setPreferredSize(new Dimension(200, 25));
        name_txt.setBackground(Color.WHITE);
        name_txt.setBounds(430, 115, 280, 25);

        // ============================== USERNAME TEXT FIELD ========================================

        username_txt = new textfield(20, 20, 20);
        username_txt.setPreferredSize(new Dimension(200, 25));
        username_txt.setBackground(Color.WHITE);
        username_txt.setBounds(430, 155, 280, 25);

        // ============================== EMAIL TEXT FIELD ========================================

        email_txt = new textfield(20, 20, 20);
        email_txt.setPreferredSize(new Dimension(200, 25));
        email_txt.setBackground(Color.WHITE);
        email_txt.setBounds(430, 195, 280, 25);

        // ============================== SEMESTER TEXT FIELD ========================================

        semester_txt = new textfield(20, 20, 20);
        semester_txt.setPreferredSize(new Dimension(200, 25));
        semester_txt.setBackground(Color.WHITE);
        semester_txt.setBounds(430, 235, 280, 25);

        // ========================== ENROll BUTTON ================================================

        RoundButton enrole_button = new RoundButton("Enroll");
        enrole_button.setBackground(Color.DARK_GRAY);
        enrole_button.setForeground(Color.WHITE);
        enrole_button.setFont(new Font("Arial", Font.BOLD, 18));
        enrole_button.setBounds(440, 300, 100, 30);
        enrole_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectingdatabase();
                addingStudent();
                System.out.println("Function working properly....");
            }
        });

        // ========================== RETURN BUTTON ================================================

        RoundButton return_button = new RoundButton("Back");
        return_button.setBackground(Color.DARK_GRAY);
        return_button.setForeground(Color.WHITE);
        return_button.setFont(new Font("Arial", Font.BOLD, 12));
        return_button.setBounds(100, 430, 100, 30);
        return_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                qalam_txt.setText("");
                name_txt.setText("");
                username_txt.setText("");
                email_txt.setText("");
                semester_txt.setText("");
                addstudent_window.setVisible(false);
                new Dashboard();
            }
        });

        // ========================== EXIT BUTTON =====================================================

        RoundButton exit_button = new RoundButton("EXIT");
        exit_button.setBackground(Color.DARK_GRAY);
        exit_button.setForeground(Color.WHITE);
        exit_button.setFont(new Font("Arial", Font.BOLD, 13));
        exit_button.setBounds(0, 430, 63, 30);
        exit_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // ========================== ADD STUDENT BUTTON =====================================================

        RoundButton addstudent_button = new RoundButton("Add Student");
        addstudent_button.setBackground(Color.WHITE);
        addstudent_button.setForeground(Color.DARK_GRAY);
        addstudent_button.setFont(new Font("Arial", Font.BOLD, 16));
        addstudent_button.setBounds(0, 83, 200, 30);

        // ========================== DELETE STUDENT BUTTON =====================================================

        RoundButton deletestudent_button = new RoundButton("Delete Student");
        deletestudent_button.setBackground(Color.DARK_GRAY);
        deletestudent_button.setForeground(Color.lightGray);
        deletestudent_button.setFont(new Font("Arial", Font.BOLD, 16));
        deletestudent_button.setBounds(0, 133, 200, 30);

        // ========================== SEARCH STUDENT BUTTON =====================================================

        RoundButton searchstudent_button = new RoundButton("Search Student");
        searchstudent_button.setBackground(Color.DARK_GRAY);
        searchstudent_button.setForeground(Color.lightGray);
        searchstudent_button.setFont(new Font("Arial", Font.BOLD, 16));
        searchstudent_button.setBounds(0, 179, 200, 30);

        // ========================== UPDATE STUDENT BUTTON =====================================================

        RoundButton updatestudent_button = new RoundButton("Update Data");
        updatestudent_button.setBackground(Color.DARK_GRAY);
        updatestudent_button.setForeground(Color.lightGray);
        updatestudent_button.setFont(new Font("Arial", Font.BOLD, 16));
        updatestudent_button.setBounds(0, 229, 200, 30);

        // ========================== MARK ATTENDANCE BUTTON =====================================================

        RoundButton markattendance_button = new RoundButton("Mark Attendance");
        markattendance_button.setBackground(Color.DARK_GRAY);
        markattendance_button.setForeground(Color.lightGray);
        markattendance_button.setFont(new Font("Arial", Font.BOLD, 16));
        markattendance_button.setBounds(0, 277, 200, 30);

        //  ===================== LEFT PANEL ======================

        JPanel leftpanel = new JPanel();
        leftpanel.setBounds(0, 0, 200, 500);
        leftpanel.setBackground(Color.DARK_GRAY);
        leftpanel.add(dashboardlabel);
        leftpanel.add(addstudent_button);
        leftpanel.add(deletestudent_button);
        leftpanel.add(searchstudent_button);
        leftpanel.add(updatestudent_button);
        leftpanel.add(markattendance_button);
        leftpanel.add(exit_button);
        leftpanel.add(return_button);
        leftpanel.setLayout(null);

        // =======================================

        addstudent_window.setVisible(true);
        addstudent_window.getContentPane().setBackground(Color.WHITE);
        addstudent_window.setSize(800, 500);
        addstudent_window.setTitle("Add Student");
        addstudent_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addstudent_window.setResizable(false);
        addstudent_window.setLocationRelativeTo(null);
        addstudent_window.add(qalam_id);
        addstudent_window.add(name);
        addstudent_window.add(username);
        addstudent_window.add(email);
        addstudent_window.add(semester);
        addstudent_window.add(qalam_txt);
        addstudent_window.add(name_txt);
        addstudent_window.add(username_txt);
        addstudent_window.add(email_txt);
        addstudent_window.add(semester_txt);
        addstudent_window.add(enrole_button);
        addstudent_window.setLayout(null);
        addstudent_window.add(leftpanel);
    }

    //  ========================= DASHBOARD LABEL ==================================

    private static JLabel dashboard_label() {
        JLabel label = new JLabel();
        label.setText("DashBoard");
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Georgia", Font.BOLD, 18));
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(0, 20, 200, 100);
        return label;
    }

    private static JLabel qalam_label() {
        JLabel label = new JLabel();
        label.setText("Qalam ID");
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setForeground(Color.darkGray);
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(290, 75, 100, 50);
        return label;
    }

    private static JLabel name_label() {
        JLabel label = new JLabel();
        label.setText("Name     ");
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setForeground(Color.darkGray);
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(290, 115, 100, 50);
        return label;
    }

    private static JLabel username_label() {
        JLabel label = new JLabel();
        label.setText("Username");
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setForeground(Color.darkGray);
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(290, 155, 108, 50);
        return label;
    }

    private static JLabel email_label() {
        JLabel label = new JLabel();
        label.setText("Email     ");
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setForeground(Color.darkGray);
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(290, 195, 100, 50);
        return label;
    }

    private static JLabel semester_label() {
        JLabel label = new JLabel();
        label.setText("Semester");
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setForeground(Color.darkGray);
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(290, 235, 105, 50);
        return label;
    }

    public static void connectingdatabase() {
        try {
            c = DriverManager.getConnection(url, username_mysql, password);
            String sql = "CREATE TABLE IF NOT EXISTS " + LoginPage.course_table + " (\n"
                    + "    qalam_id INT UNIQUE,\n"
                    + "    name VARCHAR(255),\n"
                    + "    username VARCHAR(255),\n"
                    + "    email VARCHAR(255),\n"
                    + "    semester VARCHAR(255),\n"
                    + "    attendance_status VARCHAR(255),\n"
                    + "    date_attendance DATE\n"
                    + ");";
            try (Statement stmt = c.createStatement()) {
                stmt.execute(sql);
                System.out.println("Table created successfully.");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Failed to create table. Please check your SQL query.");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to connect to the database. Please check your connection settings.");
            e.printStackTrace();
        }
    }

    public void addingStudent() {
        // Get and trim input value
        String qalamIdStr = qalam_txt.getText();
        String studentName = name_txt.getText();
        String studentUsername = username_txt.getText();
        String studentEmail = email_txt.getText();
        String studentSemester = semester_txt.getText();

        // Debugging: Print the trimmed values to the console
        System.out.println("Qalam ID: '" + qalamIdStr + "'");
        System.out.println("Name: '" + studentName + "'");
        System.out.println("Username: '" + studentUsername + "'");
        System.out.println("Email: '" + studentEmail + "'");
        System.out.println("Semester: '" + studentSemester + "'");

        // Check for empty Qalam ID
        if (qalamIdStr.isEmpty()) {
            JOptionPane.showMessageDialog(addstudent_window, "Enter Qalam ID!!!", "Warning", JOptionPane.INFORMATION_MESSAGE);
            qalam_txt.grabFocus();
            return;
        } else if (studentName.isEmpty()) {
            JOptionPane.showMessageDialog(addstudent_window, "Enter Name!!!", "Warning", JOptionPane.INFORMATION_MESSAGE);
            name_txt.grabFocus();
            return;
        } else if (studentUsername.isEmpty()) {
            JOptionPane.showMessageDialog(addstudent_window, "Enter Username!!!", "Warning", JOptionPane.INFORMATION_MESSAGE);
            username_txt.grabFocus();
            return;
        } else if (studentEmail.isEmpty()) {
            JOptionPane.showMessageDialog(addstudent_window, "Enter Email!!!", "Warning", JOptionPane.INFORMATION_MESSAGE);
            email_txt.grabFocus();
            return;
        } else if (studentSemester.isEmpty()) {
            JOptionPane.showMessageDialog(addstudent_window, "Enter Semester!!!", "Warning", JOptionPane.INFORMATION_MESSAGE);
            semester_txt.grabFocus();
            return;
        }

        // Attempt to parse Qalam ID to integer
        int qalamid;
        try {
            qalamid = Integer.parseInt(qalamIdStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(addstudent_window, "Qalam ID must be an integer!!!", "Warning", JOptionPane.INFORMATION_MESSAGE);
            qalam_txt.grabFocus();
            return;
        }

        // SQL insertion
        String insertSql = "INSERT INTO " + LoginPage.course_table + " (name, qalam_id, username, email, semester, attendance_status, date_attendance) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection c = DriverManager.getConnection(url, username_mysql, password);
             PreparedStatement pstmt = c.prepareStatement(insertSql)) {
            pstmt.setString(1, studentName);
            pstmt.setInt(2, qalamid);
            pstmt.setString(3, studentUsername);
            pstmt.setString(4, studentEmail);
            pstmt.setString(5, studentSemester);
            pstmt.setString(6, "Absent");
            pstmt.setDate(7, dateCreated);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Student Added Successfully");
            qalam_txt.setText("");
            name_txt.setText("");
            username_txt.setText("");
            email_txt.setText("");
            semester_txt.setText("");
            qalam_txt.grabFocus(); // Set focus to the first field
        } catch (SQLException e) {
            e.printStackTrace(); // Print the exception stack trace
            if (e.getMessage().contains("Duplicate entry")) {
                JOptionPane.showMessageDialog(null, "Qalam ID already exists...");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to enroll Student");
            }
        }
    }

}
