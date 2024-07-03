import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SearchStudent {

    JLabel dashboardlabel = dashboard_label();

    JFrame searchstudent_window = new JFrame();

    JLabel enterqalamlabel = enterqalam_label();
    static textfield qalam_txt;
    JTextArea detailsArea;

    static Connection c;
    static String url = "jdbc:mysql://localhost:3306/attendance_management_system";
    static String username_mysql = "root";
    static String password = "Anas@464092";

    SearchStudent(){

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
                searchstudent_window.setVisible(false);
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
        searchstudent_button.setBackground(Color.WHITE);
        searchstudent_button.setForeground(Color.DARK_GRAY);
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

        // ========================== DETAILS AREA =====================================================

        detailsArea = new JTextArea();
        detailsArea.setBounds(250, 120, 400, 200);
        detailsArea.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        detailsArea.setForeground(Color.BLACK);
        detailsArea.setEditable(false);

        // ========================== SEARCH BUTTON =====================================================

        RoundButton search_button = new RoundButton("Search");
        search_button.setBackground(Color.DARK_GRAY);
        search_button.setForeground(Color.WHITE);
        search_button.setFont(new Font("Arial", Font.BOLD, 12));
        search_button.setBounds(650, 45, 75, 26);
        search_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchstudent_window.setVisible(false);
                new DatabaseViewer();
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

        searchstudent_window.setVisible(true);
        searchstudent_window.getContentPane().setBackground(Color.WHITE);
        searchstudent_window.setSize(800, 500);
        searchstudent_window.setTitle("Login Window");
        searchstudent_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        searchstudent_window.setResizable(false);
        searchstudent_window.setLocationRelativeTo(null);
        searchstudent_window.add(qalam_txt);
        searchstudent_window.add(enterqalamlabel);
        searchstudent_window.add(search_button);
        searchstudent_window.add(detailsArea);
        searchstudent_window.setLayout(null);
        searchstudent_window.add(leftpanel);
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


}
