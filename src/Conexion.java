import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//conexion de sql con java
public class Conexion {

    private static final String URL = "jdbc:sqlite:bd_estudiantes.db";
    private Connection con;

    //crear tabla  espera una sentencia SQL para crear 
    public Conexion(String crearDB) {
        try {
            this.con = DriverManager.getConnection(URL);
            PreparedStatement pstm = this.con.prepareStatement(crearDB);
            pstm.execute();
            this.con.close();
        } catch (SQLException exception) {
            System.err.println(exception);
        }
    }

    //establece la conexion
    
    public void conectar() throws SQLException {
        this.con = DriverManager.getConnection(URL);
    }

    
    //Desconectar
    public void desconectar() {
        try {
            this.con.close();
        } catch (SQLException sqlException) {
            System.err.println(sqlException);
        }
    }

    //Ejecucion de sql
    
    public int ejecutarSentenciaSQL(String sentencia) throws SQLException {
        PreparedStatement pst = this.con.prepareStatement(sentencia);
        return pst.executeUpdate();
    }

    // Retorna respuestas de consultas
    public ResultSet consultarRegistros(String sentencia) throws SQLException {
        PreparedStatement pst = this.con.prepareStatement(sentencia);
        ResultSet respuesta = pst.executeQuery();
        return respuesta;
    }
}
