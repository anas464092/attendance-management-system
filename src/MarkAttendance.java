import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;

public class MarkAttendance {

    JLabel dashboardlabel = dashboard_label();
    JTable table;
    DefaultTableModel model;

    static JFrame markattendance_window = new JFrame("Mark Attendance");

    static Connection c;
    static String url = "jdbc:mysql://localhost:3306/attendance_management_system";
    static String username_mysql = "root";
    static String password = "Anas@464092";

    MarkAttendance() {
        // ======================= Connecting to the Database ==============================
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

        // Fetch data from the database
        fetchStudents(); // <--- Fetch students before creating the table

        // Table creation
        table = new JTable(model);
        table.getColumnModel().getColumn(3).setCellRenderer(new RadioButtonRenderer());
        table.getColumnModel().getColumn(3).setCellEditor(new RadioButtonEditor());
        table.setRowHeight(40);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(Color.LIGHT_GRAY);
        table.getTableHeader().setForeground(Color.BLACK);
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            {
                setHorizontalAlignment(SwingConstants.CENTER);
            }
        });

        // Adjusting column widths
        TableColumn rollNoColumn = table.getColumnModel().getColumn(0);
        rollNoColumn.setPreferredWidth(20); // Adjust width of Roll No. column
        TableColumn attendanceColumn = table.getColumnModel().getColumn(3);
        attendanceColumn.setPreferredWidth(150); // Adjust width of Attendance Status column

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(220, 20, 500, 400);

        // ========================== RETURN BUTTON =====================================================

        RoundButton return_button = new RoundButton("Back");
        return_button.setBackground(Color.DARK_GRAY);
        return_button.setForeground(Color.WHITE);
        return_button.setFont(new Font("Arial", Font.BOLD, 12));
        return_button.setBounds(100, 430, 100, 30);
        return_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markattendance_window.setVisible(false);
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

        // ========================== SAVE BUTTON =====================================================

        RoundButton save_button = new RoundButton("Save");
        save_button.setBackground(Color.RED);
        save_button.setForeground(Color.white);
        save_button.setFont(new Font("Arial", Font.BOLD, 13));
        save_button.setBounds(700, 425, 70, 30);
        save_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    c = DriverManager.getConnection(url, username_mysql, password);

                    // Check if all students have attendance marked
                    boolean allAttendanceMarked = true;
                    for (int i = 0; i < model.getRowCount(); i++) {
                        if (model.getValueAt(i, 3) == null) {
                            allAttendanceMarked = false;
                            break;
                        }
                    }

                    if (!allAttendanceMarked) {
                        throw new Exception("Please mark attendance for all students before saving.");
                    }

                    String filename = "C:\\\\SQL Sheets\\\\" + LoginPage.course_table + "_" + java.time.LocalDate.now() + ".csv";
                    String query = "SELECT qalam_id, name, attendance_status " +
                            "INTO OUTFILE '" + filename + "' " +
                            "FIELDS TERMINATED BY ',' " +
                            "LINES TERMINATED BY '\\n' " +
                            "FROM " + LoginPage.course_table;

                    Statement stmt = c.createStatement();
                    stmt.executeQuery(query);
                    JOptionPane.showMessageDialog(null, "Data exported successfully to " + filename);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "File Already Exist...Delete that file first");
                    // ex.printStackTrace();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
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
        updatestudent_button.setBackground(Color.DARK_GRAY);
        updatestudent_button.setForeground(Color.lightGray);
        updatestudent_button.setFont(new Font("Arial", Font.BOLD, 16));
        updatestudent_button.setBounds(0, 229, 200, 30);

        // ========================== MARK ATTENDANCE BUTTON =====================================================

        RoundButton markattendance_button = new RoundButton("Mark Attendance");
        markattendance_button.setBackground(Color.WHITE);
        markattendance_button.setForeground(Color.DARK_GRAY);
        markattendance_button.setFont(new Font("Arial", Font.BOLD, 16));
        markattendance_button.setBounds(0, 277, 200, 30);

        //  ===================== LEFT PANEL ====================================================================

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
        markattendance_window = new JFrame();
        markattendance_window.setVisible(true);
        markattendance_window.getContentPane().setBackground(Color.WHITE);
        markattendance_window.setSize(800, 500);
        markattendance_window.setTitle("Mark Attendance");
        markattendance_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        markattendance_window.setResizable(false);
        markattendance_window.add(save_button);
        markattendance_window.setLocationRelativeTo(null);
        markattendance_window.setLayout(null);
        markattendance_window.add(sp);
        markattendance_window.add(leftpanel);

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

    // Fetch students from the database
    private void fetchStudents() {
        model = new DefaultTableModel(new String[]{"Roll No.", "Qalam ID", "Name", "Attendance Status"}, 0);
        try {
            String query = "SELECT ROW_NUMBER() OVER (ORDER BY name) AS roll_no, qalam_id, name, attendance_status FROM " + LoginPage.course_table;
            PreparedStatement pstmt = c.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int rollNo = rs.getInt("roll_no");
                int qalam_id = rs.getInt("qalam_id");
                String name = rs.getString("name");
                String attendance_status = rs.getString("attendance_status");
                if (attendance_status == null) {
                    attendance_status = "Absent";
                }
                model.addRow(new Object[]{rollNo, qalam_id, name, attendance_status.equals("Present")});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Mark attendance
    public void markAttendance(int qalamID, String status) {
        try {
            String updateSql = "UPDATE " + LoginPage.course_table + " SET attendance_status = ?, date_attendance = ? WHERE qalam_id = ?";
            PreparedStatement pstmt = c.prepareStatement(updateSql);
            pstmt.setString(1, status);
            pstmt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            pstmt.setInt(3, qalamID);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update attendance.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to update attendance. Please try again.");
            e.printStackTrace();
        }
    }

    class RadioButtonRenderer implements TableCellRenderer {
        JRadioButton presentButton = new JRadioButton("Present");
        JRadioButton absentButton = new JRadioButton("Absent");
        ButtonGroup group = new ButtonGroup();
        JPanel panel = new JPanel();

        public RadioButtonRenderer() {
            panel.setLayout(new GridLayout(1, 2));
            panel.add(presentButton);
            panel.add(absentButton);
            group.add(presentButton);
            group.add(absentButton);
            panel.setBackground(Color.WHITE);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            if (isSelected) {
                panel.setForeground(table.getSelectionForeground());
                panel.setBackground(table.getSelectionBackground());
            } else {
                panel.setForeground(table.getForeground());
                panel.setBackground(table.getBackground());
            }
            presentButton.setSelected(Boolean.TRUE.equals(value));
            absentButton.setSelected(Boolean.FALSE.equals(value));
            return panel;
        }
    }

    class RadioButtonEditor extends DefaultCellEditor implements ActionListener {
        private JPanel panel;
        private JRadioButton presentButton;
        private JRadioButton absentButton;
        private int qalamID;

        public RadioButtonEditor() {
            super(new JCheckBox());
            panel = new JPanel();
            presentButton = new JRadioButton("Present");
            absentButton = new JRadioButton("Absent");
            ButtonGroup group = new ButtonGroup();
            group.add(presentButton);
            group.add(absentButton);
            panel.setLayout(new GridLayout(1, 2));
            panel.add(presentButton);
            panel.add(absentButton);
            presentButton.addActionListener(this);
            absentButton.addActionListener(this);
            panel.setBackground(Color.WHITE);
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            if (value == null) {
                presentButton.setSelected(false);
                absentButton.setSelected(false);
            } else if ((Boolean) value) {
                presentButton.setSelected(true);
                absentButton.setSelected(false);
            } else {
                presentButton.setSelected(false);
                absentButton.setSelected(true);
            }
            qalamID = (int) table.getValueAt(row, 1); // Changed to index 1 for qalamID
            return panel;
        }

        public Object getCellEditorValue() {
            if (presentButton.isSelected()) {
                return true;
            } else if (absentButton.isSelected()) {
                return false;
            }
            return null;
        }

        public void actionPerformed(ActionEvent e) {
            fireEditingStopped();
            String status = (presentButton.isSelected()) ? "Present" : "Absent";
            markAttendance(qalamID, status);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MarkAttendance();
            }
        });
    }
}
