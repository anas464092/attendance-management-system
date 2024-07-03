import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DatabaseViewer extends JFrame implements ActionListener {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/attendance_management_system";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Anas@464092";
    private static Connection connection;
    private static Statement statement;
    private JTextField searchField;
    private JButton searchButton;
    private JTable resultTable;
    private DefaultTableModel tableModel;
    private JComboBox<String> searchOptions;

    public DatabaseViewer() {
        setTitle("Search Student");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Search by:");
        searchOptions = new JComboBox<>(new String[]{"Name", "Username", "Email", "Semester", "Qalam_ID"});
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        panel.add(label);
        panel.add(searchOptions);
        panel.add(searchField);
        panel.add(searchButton);

        tableModel = new DefaultTableModel();
        resultTable = new JTable(tableModel);
        resultTable.setRowHeight(30);
        resultTable.setFont(new Font("Arial", Font.PLAIN, 14));
        resultTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        resultTable.setGridColor(Color.LIGHT_GRAY);
        resultTable.setSelectionBackground(new Color(240, 240, 240));
        resultTable.setSelectionForeground(Color.BLACK);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        resultTable.setDefaultRenderer(Object.class, renderer);
        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Add return_button to the SOUTH position
        JButton return_button = new JButton("Back");
        return_button.setBackground(Color.DARK_GRAY);
        return_button.setForeground(Color.WHITE);
        return_button.setFont(new Font("Arial", Font.BOLD, 12));
        return_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Dashboard();
            }
        });
        add(return_button, BorderLayout.SOUTH);

        establishConnection();
        displayAllStudents();
    }

    private void establishConnection() {
        try {
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to connect to the database.");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            DatabaseViewer viewer = new DatabaseViewer();
            viewer.setVisible(true);
        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            String searchTerm = searchField.getText();
            String selectedOption = (String) searchOptions.getSelectedItem();
            searchRecords(searchTerm, selectedOption);
        }
    }

    private void displayAllStudents() {
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT name, username, email, semester, qalam_id FROM " +LoginPage.course_table);
            tableModel.setRowCount(0);
            tableModel.setColumnIdentifiers(new Object[]{"Name", "Username", "Email", "Semester", "Qalam ID"});

            while (resultSet.next()) {
                Object[] rowData = {resultSet.getString("name"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("semester"),
                        resultSet.getInt("qalam_id")};
                tableModel.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to fetch data from the database.");
        }
    }

    private void searchRecords(String searchTerm, String searchOption) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT name, username, email, semester, qalam_id FROM "+LoginPage.course_table+" WHERE " + searchOption.toLowerCase() + " LIKE ?");
            preparedStatement.setString(1, "%" + searchTerm + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            tableModel.setRowCount(0);
            tableModel.setColumnIdentifiers(new Object[]{"Name", "Username", "Email", "Semester", "Qalam ID"});

            while (resultSet.next()) {
                Object[] rowData = {resultSet.getString("name"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("semester"),
                        resultSet.getInt("qalam_id")};
                tableModel.addRow(rowData);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to search records in the database.");
        }
    }
}
