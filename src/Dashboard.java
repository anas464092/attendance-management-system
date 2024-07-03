import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard {

    JLabel welcome_label = welcomeLabel();
    JLabel welcome_label2 = welcomeLabel2();

    JLabel dashboardlabel = dashboard_label();

    JFrame Dashboard_window = new JFrame("Dashboard");

    Dashboard(){

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
        addstudent_button.setForeground(Color.WHITE);
        addstudent_button.setFont(new Font("Arial", Font.BOLD, 16));
        addstudent_button.setBounds(0, 83, 200, 30);
        addstudent_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dashboard_window.setVisible(false);
                new Addstudent();
            }
        });

        // ========================== DELETE STUDENT BUTTON =====================================================

        RoundButton deletestudent_button = new RoundButton("Delete Student");
        deletestudent_button.setBackground(Color.DARK_GRAY);
        deletestudent_button.setForeground(Color.WHITE);
        deletestudent_button.setFont(new Font("Arial", Font.BOLD, 16));
        deletestudent_button.setBounds(0, 133, 200, 30);
        deletestudent_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dashboard_window.setVisible(false);
                new DeleteStudent();
            }
        });

        // ========================== SEARCH STUDENT BUTTON =====================================================

        RoundButton searchstudent_button = new RoundButton("Search Student");
        searchstudent_button.setBackground(Color.DARK_GRAY);
        searchstudent_button.setForeground(Color.WHITE);
        searchstudent_button.setFont(new Font("Arial", Font.BOLD, 16));
        searchstudent_button.setBounds(0, 179, 200, 30);
        searchstudent_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dashboard_window.setVisible(false);
                DatabaseViewer viewer = new DatabaseViewer();
                viewer.setVisible(true);
            }
        });

        // ========================== UPDATE STUDENT BUTTON =====================================================

        RoundButton updatestudent_button = new RoundButton("Update Data");
        updatestudent_button.setBackground(Color.DARK_GRAY);
        updatestudent_button.setForeground(Color.WHITE);
        updatestudent_button.setFont(new Font("Arial", Font.BOLD, 16));
        updatestudent_button.setBounds(0, 229, 200, 30);
        updatestudent_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dashboard_window.setVisible(false);
                new UpdateData();
            }
        });

        // ========================== MARK ATTENDANCE BUTTON =====================================================

        RoundButton markattendance_button = new RoundButton("Mark Attendance");
        markattendance_button.setBackground(Color.DARK_GRAY);
        markattendance_button.setForeground(Color.WHITE);
        markattendance_button.setFont(new Font("Arial", Font.BOLD, 16));
        markattendance_button.setBounds(0, 277, 200, 30);
        markattendance_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dashboard_window.setVisible(false);
                new MarkAttendance();
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
        leftpanel.setLayout(null);

        // =======================================

        Dashboard_window.setVisible(true);
        Dashboard_window.getContentPane().setBackground(Color.WHITE);
        Dashboard_window.setSize(800, 500);
        Dashboard_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dashboard_window.setResizable(false);
        Dashboard_window.setLocationRelativeTo(null);
        Dashboard_window.setLayout(null);
        Dashboard_window.add(leftpanel);
        Dashboard_window.add(welcome_label);
        Dashboard_window.add(welcome_label2);
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

    private static JLabel welcomeLabel() {
        JLabel label = new JLabel();
        label.setText("Welcome to Attendance");
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setForeground(Color.DARK_GRAY);
        label.setFont(new Font("Book Antiqua", Font.BOLD, 38));
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(200, 150, 550, 150);
        return label;
    }

    private static JLabel welcomeLabel2() {
        JLabel label = new JLabel();
        label.setText("Management System");
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setForeground(Color.DARK_GRAY);
        label.setFont(new Font("Book Antiqua", Font.BOLD, 38));
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(200, 200, 550, 150);
        return label;
    }
}