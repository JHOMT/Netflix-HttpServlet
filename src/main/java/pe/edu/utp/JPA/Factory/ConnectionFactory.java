package pe.edu.utp.JPA.Factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String url = "jdbc:mysql://localhost:3306/netflixDB";
    private static final String username = "root";
    private static final String password = "jhon2002";

    public static Connection getConnection() {
        Connection conexion = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: no se pudo cargar el controlador JDBC");
            e.printStackTrace();
        }

        return conexion;
    }
    public static void closeConnection(Connection conexion) {
        try {
            conexion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main (String[]args){
        Connection conexion = ConnectionFactory.getConnection();

        if (conexion != null) {
            System.out.println("Conneccion establecida");
            ConnectionFactory.closeConnection(conexion);
        }else {
            System.out.println("Conneccion no establecida");
        }
    }

}
