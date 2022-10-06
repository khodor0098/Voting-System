package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLDatabaseConnection {
    private Connection connection;
    private Connection connect(){
        String dbURL = "jdbc:sqlserver://DESKTOP-PLVQU31;database=VotingProject;Integratedsecurity=true";
        try {
            connection = DriverManager.getConnection(dbURL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public Connection getConnection(){
        if(connection != null)
            return connection;
        else
            return connect();
    }
}


