package projeto.faculdade.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/biblioteca";
        String username = "root";
        String password = "admin123";
        return DriverManager.getConnection(url,username,password);
    }
}