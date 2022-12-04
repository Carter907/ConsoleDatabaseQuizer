package org.example;

import java.io.*;
import java.sql.*;
import java.util.*;
public class Main {

    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:"+args[0]);
                Statement statement = conn.createStatement()
        ) {

          statement.execute("SELECT * FROM quiz");

          ResultSet questions = statement.getResultSet();
          String answer = "";
          int correct = 0;
          int total = 0;
          while (questions.next()) {
              total++;
              System.out.println(questions.getString("question"));
              answer = scan.nextLine();
              if (answer.equalsIgnoreCase(questions.getString("answer"))) {
                  System.out.println("correct!\n");
                  correct++;
              }
              else
                  System.out.println("false.\n");

          }

          System.out.println("final score is " + correct + " / " + total);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}