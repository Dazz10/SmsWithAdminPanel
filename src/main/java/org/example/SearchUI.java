package org.example;

import java.sql.*;
import java.util.Scanner;

public class SearchUI {


    public void SearchUI() {
        String url = "jdbc:mysql://localhost:3306/student_db?serverTimezone=UTC";
        String user = "root";
        String pass = "123456";

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("n--- Student Management System ---");
            System.out.println("1. Search student by department");
            System.out.println("2. Search student by name");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter department: ");
                    String dept = scanner.nextLine();
                    try (Connection connection = DriverManager.getConnection(url, user, pass)) {
                        System.out.println("Connected to database.");

                        searchStudent(connection, dept);

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    //enter code for name of student
                    System.out.println("Enter name: ");
                    String nam = scanner.nextLine();
                    try (Connection connection = DriverManager.getConnection(url, user, pass)) {
                        System.out.println("Connected to database.");

                        searchStudentByName(connection, nam);

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.println("System exiting...");
                    running = false;
                    break;
            }
        }
    }

    public static void searchStudent(Connection connection, String dept) {
        String sql = "";
        if (dept.equals("science")) {
            sql = "SELECT id, name, roll_no, department, email, phone,marks FROM students WHERE department ='science'";
        } else if (dept.equals("arts")) {
            sql = "SELECT id, name, roll_no, department, email, phone,marks FROM students WHERE department ='arts'";
        } else if (dept.equals("commerce")) {
            sql = "SELECT id, name, roll_no, department, email, phone,marks FROM students WHERE department ='commerce'";
        } else {
            System.out.println("Invalid department please try again.");
        }


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

                System.out.printf("ID: %d, Name: %s,roll_no: %d, Department: %s,  Email: %s, Phone: %s, Marks: %d \n", id, name, roll_no, department, email, phone, marks);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void searchStudentByName(Connection connection, String nam) {
        String sql = "";

        sql = "SELECT id, name, roll_no, department, email, phone,marks FROM students WHERE name='ajit'";


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

                System.out.printf("ID: %d, Name: %s,roll_no: %d, Department: %s,  Email: %s, Phone: %s, Marks: %d \n", id, name, roll_no, department, email, phone, marks);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}