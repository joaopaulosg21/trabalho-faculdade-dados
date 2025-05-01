package projeto.faculdade;

import java.sql.Connection;

import projeto.faculdade.util.DbConnection;

public class App {
    public static void main(String[] args) throws Exception{
        Connection con = DbConnection.getConnection();
        System.out.println(con);
    }
}