package com.quintrix.jfs.JCDBdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDemo {
  public static void main(String[] argv) {
    Statement insertStmt = null;
    Statement deleteStmt = null;
    Statement selectStmt = null;
    Statement updateStmt = null;


    // establish connection
    System.out.println("-------- MySQL JDBC Connection Demo ------------");
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      System.out.println("MySQL JDBC Driver not found !!");
      return;
    }
    System.out.println("MySQL JDBC Driver Registered!");
    Connection connection = null;
    try {
      connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root",
          "admin.sl0w");
      System.out.println("SQL Connection to database established!");

      // query
      selectStmt = connection.createStatement();
      ResultSet rs = selectStmt.executeQuery("SELECT city,territory,country FROM offices");
      while (rs.next()) {
        System.out.println(rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getString(3));

      }

      // insert block below, adds trenton office to office table

      insertStmt = connection.createStatement();
      insertStmt.execute(
          "INSERT INTO offices (officeCode,city,phone,addressLine1,addressLine2,state,country,postalCode,territory) VALUES(8,'Trenton','9999999999', '123 street', null, 'NJ', 'USA', '08618' , 'NA' )");

      System.out.println("After insert:");
      rs = selectStmt.executeQuery("SELECT city,territory,country FROM offices");
      while (rs.next()) {
        System.out.println(rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getString(3));
      }

      // update block below, changes trenton country to EGY

      updateStmt = connection.createStatement();
      updateStmt.execute("UPDATE offices SET country='EGY' WHERE officeCode >= 8");

      System.out.println("After update:");
      rs = selectStmt.executeQuery("SELECT city,territory,country FROM offices");
      while (rs.next()) {
        System.out.println(rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getString(3));

      }


      // delete
      deleteStmt = connection.createStatement();
      deleteStmt.execute("DELETE FROM offices WHERE officeCode >= 8");


    } catch (SQLException e) {
      System.out.println("Connection Failed! Check output console");
      return;
    }


    // query


    // insert

    // update



    // close connection
    try {
      if (connection != null)
        selectStmt.close();
      insertStmt.close();
      connection.close();
      System.out.println("Connection closed !!");
    } catch (SQLException e) {
      e.printStackTrace();
    }


  }
}
