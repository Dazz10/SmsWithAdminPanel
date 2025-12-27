package org.example;

import java.sql.*;
import java.util.Scanner;

public class StudentUI {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/student_db?serverTimezone=UTC";
        String user = "root";
        String pass = "123456";

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("n--- Student Management System ---");
            System.out.println("1. Insert Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student Email");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter roll no: ");
                    int roll_no = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter department: ");
                    String department = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter phone: ");
                    int phone = scanner.nextInt();
                    System.out.print("Enter marks: ");
                    int marks = scanner.nextInt();
                    // Call insertStudent method here
                    try (Connection connection = DriverManager.getConnection(url, user, pass)) {
                        System.out.println("Connected to database.");

                        insertStudent(connection, name,roll_no, department, email, phone,marks);

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    // Call readAllStudents method here
                    try (Connection connection = DriverManager.getConnection(url, user, pass)) {
                        System.out.println("Connected to database.");

                       viewAllStudents(connection);

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.print("Enter student name update: ");
                    name = scanner.nextLine();
                    System.out.print("Enter new email: ");
                    String newEmail = scanner.nextLine();
                    // Call updateStudentEmail method here
                    try (Connection connection = DriverManager.getConnection(url, user, pass)) {
                        System.out.println("Connected to database.");

                        updateStudent(connection, newEmail, name);

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    System.out.print("Enter student ID to delete: ");
                    int idToDelete = scanner.nextInt();
                    scanner.nextLine();
                    // Call deleteStudent method here
                    try (Connection connection = DriverManager.getConnection(url, user, pass)) {
                        System.out.println("Connected to database.");

                        deleteStudent(connection, idToDelete);

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();

    }

    public static void insertStudent(Connection connection, String name,int roll_no, String department,  String email, int phone, int marks) {
        String sql = "INSERT INTO students (name,roll_no, department,  email, phone, marks) VALUES ( ?, ?,?,?,?,?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, roll_no);
            pstmt.setString(3, department);
            pstmt.setString(4, email);
            pstmt.setInt(5, phone);
            pstmt.setInt(6, marks);

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("A new student was inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewAllStudents(Connection connection) {
        String sql = "SELECT id, name, roll_no, department, email, phone,marks FROM students";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Students List:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int roll_no = rs.getInt("roll_no");
                String department = rs.getString("department");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                int marks = rs.getInt("marks");

                System.out.printf("ID: %d, Name: %s,roll_no: %d, Department: %s,  Email: %s, Phone: %s, Marks: %d \n", id, name,roll_no,department,email,phone,marks);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateStudent (Connection conn, String email, String name) {
        String updateSQL = "UPDATE students SET email = ? WHERE name = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            pstmt.setString(1, email);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
            System.out.println("Student detail updated successfully!");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteStudent (Connection conn,  int  id) {
        String deleteSQL = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



}
