// This code is for establishing connection with MySQL
// database and retrieving data
// from db Java Database connectivity

/*
 *1. import --->java.sql
 *2. load and register the driver ---> com.jdbc.
 *3. create connection
 *4. create a statement
 *5. execute the query
 *6. process the results
 *7. close
 */

 import java.sql.*;

public class Main {
     public static void main(String[] args) throws Exception {
         String url = "jdbc:mysql://localhost:3306/StorFront"; // Database details
         String username = "root"; // MySQL credentials
         String password = "root";
         String query = "select * from Product"; // Query to be run
 
         // Load and register the driver
         Class.forName("com.mysql.cj.jdbc.Driver");
 
         // Establish connection
         Connection con = DriverManager.getConnection(url, username, password);
         System.out.println("Connection Established successfully");
 
         // Create a statement
         Statement st = con.createStatement();
 
         // Execute the query
         ResultSet rs = st.executeQuery(query);
 
         // Process the results
         while (rs.next()) {
             String name = rs.getString("name"); // Retrieve name from db
             System.out.println(name); // Print result on console
         }
 
         // Close the statement and connection
         st.close();
         con.close();
         System.out.println("Connection Closed....");
     }
 }
 