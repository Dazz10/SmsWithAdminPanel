package org.example;

import java.util.Objects;
import java.util.Scanner;

public class AdminLoginUI {
    public static void main(String[] args) {
        String username = "admin";
        String pass = "admin123";

        adminLogin(username, pass);


    }
    public static void adminLogin(String username, String pass) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter username: ");
        String usr = sc.nextLine();
        System.out.println("Enter password: ");
        String pas = sc.nextLine();

        if(Objects.equals(usr, username) && Objects.equals(pass, pas)) {
            System.out.println("Welcome to SMS");
            StudentUI studentUI = new StudentUI();
            studentUI.StudentUI();
        } else  {
            System.out.println("Invalid credentials, please try again");
        }
    }
}
