import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UpdateData {

    JLabel dashboardlabel = dashboard_label();

    JFrame updatedata_window = new JFrame();

    static textfield qalam_txt;
    JLabel enterqalamlabel = enterqalam_label();

    JLabel name = name_label();
    JLabel username = username_label();
    JLabel email = email_label();
    JLabel semester = semester_label();

    static int qalamid;

    static RoundButton updateButton;


    static textfield name_txt;
    static textfield username_txt;
    static textfield email_txt;
    static textfield semester_txt;

    static Connection c;
    static String url = "jdbc:mysql://localhost:3306/attendance_management_system";
    static String username_mysql = "root";
    static String password = "Anas@464092";

    UpdateData(){

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

        // ============================== Qalam_ID TEXT FIELD ========================================

        qalam_txt = new textfield(20,20,20);
        qalam_txt.setPreferredSize(new Dimension(200, 25));
        qalam_txt.setBackground(Color.WHITE);
        qalam_txt.setBounds(350, 45, 280, 25);


        // ========================== RETURN BUTTON =====================================================

        RoundButton return_button = new RoundButton("Back");
        return_button.setBackground(Color.DARK_GRAY);
        return_button.setForeground(Color.WHITE);
        return_button.setFont(new Font("Arial", Font.BOLD, 12));
        return_button.setBounds(100, 430, 100, 30);
        return_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatedata_window.setVisible(false);
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
        addstudent_button.setBackground(Color.DARK_GRAY);
        addstudent_button.setForeground(Color.lightGray);
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
        updatestudent_button.setBackground(Color.WHITE);
        updatestudent_button.setForeground(Color.DARK_GRAY);
        updatestudent_button.setFont(new Font("Arial", Font.BOLD, 16));
        updatestudent_button.setBounds(0, 229, 200, 30);

        // ========================== MARK ATTENDANCE BUTTON =====================================================

        RoundButton markattendance_button = new RoundButton("Mark Attendance");
        markattendance_button.setBackground(Color.DARK_GRAY);
        markattendance_button.setForeground(Color.lightGray);
        markattendance_button.setFont(new Font("Arial", Font.BOLD, 16));
        markattendance_button.setBounds(0, 277, 200, 30);

        // ========================== SEARCH BUTTON =====================================================

        RoundButton search_button = new RoundButton("Search");
        search_button.setBackground(Color.DARK_GRAY);
        search_button.setForeground(Color.WHITE);
        search_button.setFont(new Font("Arial", Font.BOLD, 12));
        search_button.setBounds(650, 45, 75, 26);
        search_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            connectingdatabase();
            try {
                qalamid = Integer.parseInt(qalam_txt.getText());
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(null, "Qalam id must be integer !!!", "Warning", JOptionPane.INFORMATION_MESSAGE);
                qalam_txt.grabFocus();
                return;
            }
            getStudentDetails(qalamid);
            }
        });

        // ======================= UPDATE BUTTON ===================================

        updateButton = new RoundButton("Update");
        updateButton.setBackground(Color.RED);
        updateButton.setForeground(Color.WHITE);
        updateButton.setFont(new Font("Arial", Font.BOLD, 12));
        updateButton.setBounds(450, 360, 75, 30);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newName = name_txt.getText();
                String newUsername = username_txt.getText();
                String newEmail = email_txt.getText();
                String newSemester = semester_txt.getText();

                if (name_txt.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(updatedata_window, "Enter Name!!!", "Warning", JOptionPane.INFORMATION_MESSAGE);
                    name_txt.setText("");
                    name_txt.grabFocus();
                    return;
                } else if (username_txt.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(updatedata_window, "Enter Username!!!", "Warning", JOptionPane.INFORMATION_MESSAGE);
                    username_txt.setText("");
                    username_txt.grabFocus();
                    return;
                } else if (email_txt.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(updatedata_window, "Enter Email!!!", "Warning", JOptionPane.INFORMATION_MESSAGE);
                    email_txt.setText("");
                    email_txt.grabFocus();
                    return;
                } else if (semester_txt.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(updatedata_window, "Enter Semester!!!", "Warning", JOptionPane.INFORMATION_MESSAGE);
                    semester_txt.setText("");
                    semester_txt.grabFocus();
                    return;
                }

                String updateSql = "UPDATE "+LoginPage.course_table+ " SET name = ?, username = ?, email = ?, semester = ? WHERE qalam_id = ?";
                try (PreparedStatement pstmt = c.prepareStatement(updateSql)) {
                    pstmt.setString(1, newName);
                    pstmt.setString(2, newUsername);
                    pstmt.setString(3, newEmail);
                    pstmt.setString(4, newSemester);
                    pstmt.setInt(5, qalamid);
                    int rowsAffected = pstmt.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Student record updated successfully.");
                        qalam_txt.setText("");
                        name_txt.setText("");
                        username_txt.setText("");
                        email_txt.setText("");
                        semester_txt.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to update student record.");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Failed to update student record. Please try again.");
                    ex.printStackTrace();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Qalam ID.");
                    ex.printStackTrace();
                }
            }
        });


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

        updatedata_window.setVisible(true);
        updatedata_window.getContentPane().setBackground(Color.WHITE);
        updatedata_window.setSize(800, 500);
        updatedata_window.setTitle("Update Data");
        updatedata_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        updatedata_window.setResizable(false);
        updatedata_window.setLocationRelativeTo(null);
        updatedata_window.add(enterqalamlabel);
        updatedata_window.add(qalam_txt);
        updatedata_window.add(search_button);
        updatedata_window.setLayout(null);
        updatedata_window.add(leftpanel);
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

    private static JLabel enterqalam_label() {
        JLabel label = new JLabel();
        label.setText("Qalam ID");
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setForeground(Color.darkGray);
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(240, 45, 100, 50);
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
            String sql = "CREATE TABLE IF NOT EXISTS "+ LoginPage.course_table +" (\n"
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

    public void getStudentDetails(int searchField) {
        try {
            String query = "SELECT * FROM "+LoginPage.course_table+" WHERE qalam_id = ?";

            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setInt(1, searchField);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                updatedata_window.add(name);
                updatedata_window.add(username);
                updatedata_window.add(email);
                updatedata_window.add(semester);
                updatedata_window.add(updateButton);
                updatedata_window.add(name_txt);
                updatedata_window.add(username_txt);
                updatedata_window.add(email_txt);
                updatedata_window.add(semester_txt);
                updatedata_window.revalidate();
                updatedata_window.repaint();

                System.out.println("Student found successfully....");
            } else {
                JOptionPane.showMessageDialog(null, "Student not found...");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to fetch student details. Please try again.");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Invalid search criteria. Please provide a valid criteria.");
            e.printStackTrace();
        }
    }



}