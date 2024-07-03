import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DeleteStudent {

    JLabel dashboardlabel = dashboard_label();
    JLabel enterqalamlabel = enterqalam_label();
    static textfield qalam_txt;
    JTextArea detailsArea;

    static Connection c;
    static String url = "jdbc:mysql://localhost:3306/attendance_management_system";
    static String username_mysql = "root";
    static String password = "Anas@464092";

    JFrame DeleteStudent_window = new JFrame();

    DeleteStudent() {

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
                DeleteStudent_window.setVisible(false);
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
        deletestudent_button.setBackground(Color.WHITE);
        deletestudent_button.setForeground(Color.DARK_GRAY);
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

        // ========================== SEARCH BUTTON =====================================================

        RoundButton search_button = new RoundButton("Search");
        search_button.setBackground(Color.DARK_GRAY);
        search_button.setForeground(Color.WHITE);
        search_button.setFont(new Font("Arial", Font.BOLD, 12));
        search_button.setBounds(650, 45, 75, 26);
        search_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int qalamid;
                connectingdatabase();
                try {
                    qalamid = Integer.parseInt(qalam_txt.getText());
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null, "Qalam id must be integer !!!", "Warning", JOptionPane.INFORMATION_MESSAGE);
                    qalam_txt.grabFocus();
                    return;
                }
                String studentDetails = getStudentDetails(qalamid);
                detailsArea.setText(studentDetails);
                addDeleteButton(qalamid);
            }
        });

        // ========================== DETAILS AREA =====================================================

        detailsArea = new JTextArea();
        detailsArea.setBounds(250, 120, 400, 200);
        detailsArea.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        detailsArea.setForeground(Color.BLACK);
        detailsArea.setEditable(false);

        //  ===================== LEFT PANEL ===================================================================

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

        DeleteStudent_window.setVisible(true);
        DeleteStudent_window.getContentPane().setBackground(Color.WHITE);
        DeleteStudent_window.setSize(800, 500);
        DeleteStudent_window.setTitle("Delete Student");
        DeleteStudent_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DeleteStudent_window.setResizable(false);
        DeleteStudent_window.setLocationRelativeTo(null);
        DeleteStudent_window.setLayout(null);
        DeleteStudent_window.add(enterqalamlabel);
        DeleteStudent_window.add(qalam_txt);
        DeleteStudent_window.add(search_button);
        DeleteStudent_window.add(detailsArea);
        DeleteStudent_window.add(leftpanel);
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

    public String getStudentDetails(int searchField) {
        String details = "Student Detail : \n\n";
        try {
            String query = "SELECT * FROM "+LoginPage.course_table+" WHERE qalam_id = ?";

            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setInt(1, searchField);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                details += "Name:           " + rs.getString("name") + "\n";
                details += "Qalam ID:     " + rs.getInt("qalam_id") + "\n";
                details += "Username:    " + rs.getString("username") + "\n";
                details += "Email:            " + rs.getString("email") + "\n";
                details += "Semester:     " + rs.getString("semester") + "\n";

            } else {
                details = "Student not found.";
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to fetch student details. Please try again.");
            e.printStackTrace();
            details = "Error occurred while fetching student details.";
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Invalid search criteria. Please provide a valid criteria.");
            e.printStackTrace();
            details = e.getMessage();
        }
        return details;
    }

    public void addDeleteButton(int qalam_id) {
        RoundButton deleteButton = new RoundButton("Delete");
        deleteButton.setBackground(Color.RED);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Arial", Font.BOLD, 12));
        deleteButton.setBounds(450, 360, 75, 30);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteStudent(qalam_id);
                detailsArea.setText("");
            }
        });
        DeleteStudent_window.add(deleteButton);
        DeleteStudent_window.revalidate();
        DeleteStudent_window.repaint();
    }

    public void deleteStudent(int qalam_id) {
        PreparedStatement pstmt = null;
        try {
            String query = "DELETE FROM "+LoginPage.course_table+" WHERE qalam_id = ?";
            System.out.println("Query: " + query); // Debug print
            pstmt = c.prepareStatement(query);
            pstmt.setInt(1, qalam_id);
            int result = pstmt.executeUpdate();

            if (result > 0) {
                JOptionPane.showMessageDialog(null, "Student deleted successfully.");
                qalam_txt.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to delete student.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Sorry. Please try again.\n" + e.getMessage()); // Add exception message to dialog
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



}
