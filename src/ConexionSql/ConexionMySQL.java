package ConexionSql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {

    public static Connection ConectarBD(){

        Connection conexion;
        String host = "jdbc:mysql://localhost/";
        String user = "root";
        String pass = "";
        String bd = "registro de usuarios";


        try {
            conexion = DriverManager.getConnection(host+bd,user,pass);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);

        }
        return conexion;
    }



}