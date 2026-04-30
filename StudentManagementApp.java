import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentManagementApp extends JFrame implements ActionListener {

    JButton addBtn, viewBtn, updateBtn, deleteBtn;
    Connection con;

    public StudentManagementApp() {

        setTitle("Student Management System");
        setSize(400, 300);
        setLayout(new GridLayout(5,1));

        addBtn = new JButton("Add Student");
        viewBtn = new JButton("View Students");
        updateBtn = new JButton("Update Student");
        deleteBtn = new JButton("Delete Student");

        add(addBtn);
        add(viewBtn);
        add(updateBtn);
        add(deleteBtn);

        addBtn.addActionListener(this);
        viewBtn.addActionListener(this);
        updateBtn.addActionListener(this);
        deleteBtn.addActionListener(this);

        connectDB();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Database Connection
    public void connectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/student_db",
                "root",
                "priy@s*22"   // 🔴 CHANGE THIS to your MySQL password
            );
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Database Connection Failed!");
            e.printStackTrace();
        }
    }

    // Button Actions
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == addBtn) addStudent();
        if(e.getSource() == viewBtn) viewStudents();
        if(e.getSource() == updateBtn) updateStudent();
        if(e.getSource() == deleteBtn) deleteStudent();
    }

    // Add Student
    public void addStudent() {
        try {
            String name = JOptionPane.showInputDialog("Enter Name:");
            String course = JOptionPane.showInputDialog("Enter Course:");
            int marks = Integer.parseInt(JOptionPane.showInputDialog("Enter Marks:"));

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO students(name, course, marks) VALUES(?,?,?)"
            );

            ps.setString(1, name);
            ps.setString(2, course);
            ps.setInt(3, marks);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Student Added Successfully!");

        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "Error adding student");
        }
    }

    // View Students
    public void viewStudents() {
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM students");

            String data = "ID\tName\tCourse\tMarks\n";

            while(rs.next()) {
                data += rs.getInt("id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getString("course") + "\t" +
                        rs.getInt("marks") + "\n";
            }

            JTextArea ta = new JTextArea(data);
            JOptionPane.showMessageDialog(this, new JScrollPane(ta));

        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "Error fetching data");
        }
    }

    // Update Student
    public void updateStudent() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID to update:"));
            String name = JOptionPane.showInputDialog("Enter New Name:");
            String course = JOptionPane.showInputDialog("Enter New Course:");
            int marks = Integer.parseInt(JOptionPane.showInputDialog("Enter New Marks:"));

            PreparedStatement ps = con.prepareStatement(
                "UPDATE students SET name=?, course=?, marks=? WHERE id=?"
            );

            ps.setString(1, name);
            ps.setString(2, course);
            ps.setInt(3, marks);
            ps.setInt(4, id);

            int rows = ps.executeUpdate();

            if(rows > 0)
                JOptionPane.showMessageDialog(this, "Updated Successfully!");
            else
                JOptionPane.showMessageDialog(this, "Student Not Found!");

        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "Error updating student");
        }
    }

    // Delete Student
    public void deleteStudent() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID to delete:"));

            PreparedStatement ps = con.prepareStatement(
                "DELETE FROM students WHERE id=?"
            );

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if(rows > 0)
                JOptionPane.showMessageDialog(this, "Deleted Successfully!");
            else
                JOptionPane.showMessageDialog(this, "Student Not Found!");

        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "Error deleting student");
        }
    }

    public static void main(String[] args) {
        new StudentManagementApp();
    }
}