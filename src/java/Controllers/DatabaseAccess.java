package Controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseAccess {

    //Default constructor
    public DatabaseAccess() {
    }

    //Steps 1-3, creating the database connection.
    public static Connection getConnection() throws ServletException {

        try {
            //Using try{...} and catch{...}for error control
            //Step 1: load JDBC driver, with classname of of database driver
            Class.forName("org.postgresql.Driver"); // This makes sure the required sql driver exists
        } catch (ClassNotFoundException e) {
            // This throws an error if it doesn't
            throw new ServletException(String.format("Error: Cannot find JDBC driver... "), e);
        }
        try {
            /* Steps 2 and 3: Create a variable of type Connection, 
             * named connection, and establish a connection to the url, 
             * with database address, name, username and password. 
             */
            //Tries to make the connection
            Connection connection = DriverManager.getConnection(
                    /*Database URL and name*/"jdbc:postgresql://127.0.0.1/studentdb",
                    /*Database username*/ "student",
                    /*Database password*/ "dbpassword");
            //Returns connection if sucesful

            return connection;

        } catch (SQLException e) {
            // Throw an error message if any connection problems occur
            throw new ServletException(String.format(
                    "Error: Database connection failed... "), e);
        }
    }
    // Steps 4, 5, 6, 7. Runs a given Select query through a given connection, and returns the result.
    public static ResultSet runSelectQuery(String sql, Connection connection) throws ServletException {
        try {
            /*Step 4: Create a Statement object, named statement, used to
             * send commands and queries to the database.
             */
            Statement statement = connection.createStatement();
            /* Step 5: execute a query or update. SQL Statements can be sent
             * to the database with the use of execute, executeQuery,
             * executeUpdate or executeBatch methods.
             */

            // Run the query and retrieve the results
            ResultSet resultSet = statement.executeQuery(sql);
            
            //Step 7: Close the connection. Must always be done.
            connection.close();
            
            return resultSet;

            /* The result set represents a set of rows and columns that can
             * be proccessed by calls to next and various get<something>
             * methods.
             * One of the rows within a ResultSet is the current row which
             * will start and end its life in an undefined state.
             * The current row can be advnaced by using the next () method
             * Each column within a row has a label. Labels are usually the
             * column names as defined within a database, but can also be
             * defined by using the AS command within an SQL query.
             * The getstring("") method retrieves the entry from the current
             * row and the column specified within the ""
             */
            
        } catch (SQLException e) {
            // Problem encountered
            return null;

        }
    }
    
        // Steps 4, 5, 7. Runs a given query that does not return anything.
    public static void runUpdateQuery(String sql, Connection connection) throws ServletException {
        try {
            /*Step 4: Create a Statement object, named statement, used to
             * send commands and queries to the database.
             */
            Statement statement = connection.createStatement();
            /* Step 5: execute a query or update. SQL Statements can be sent
             * to the database with the use of execute, executeQuery,
             * executeUpdate or executeBatch methods.
             */

            // Run the query 
            statement.executeQuery(sql);
                        
            //Step 7: Close the connection. Must always be done.
            connection.close();
        } catch (SQLException e) {
            // Problem encountered

        }
    }
}
