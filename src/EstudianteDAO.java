import java.sql.ResultSet;
import java.sql.SQLException;


//antes era el estandar del crud
// ahora es el estandar tambien de consultas

//ya no tiene ni array list, ni archivo, ahota tiene ResultSet, 
// Implementa la interfaz para especializar los métodos
// de acceso a la base de datos que contiene información de  Estudiante

public class EstudianteDAO implements IEstudianteDAO {

    //antes era Archivo, archivo
    private final Conexion conexion;
    
    // Inicializa un objeto de tipo Conexion} pasando como parametro una sentencia sql
    
    public EstudianteDAO(String crearDB) {
        this.conexion = new Conexion(crearDB);
    }

    //Transfiere una sentencia para agregar una fila a la tabla estudiantes
     
    @Override
    public String agregarEstudiante(String sentenciaAgregarEstudiante) {
        String msj = "";
        try {
            this.conexion.conectar();
            if (this.conexion.ejecutarSentenciaSQL(sentenciaAgregarEstudiante) != 0) {
                msj = "Se agregó el estudiante\n";
            }
        } catch (SQLException sqlException) {
            if (sqlException.getMessage().contains("A UNIQUE constraint failed")) {
                msj = "Ya existe otro estudiante inscrito con el mismo correo institucional\n";
            } else {
                System.err.println(sqlException);
            }
        } finally {
            this.conexion.desconectar();
        }
        return msj;
    }

    //Transfiere una sentencia para obtener una fila de la tabla estudiantes 
    
    @Override
    public Estudiante obtenerEstudiante(String sentenciaObtenerEstudiante) {
        Estudiante estudiante = new Estudiante();
        try {
            this.conexion.conectar();
            ResultSet respuesta = this.conexion.consultarRegistros(sentenciaObtenerEstudiante);
            if (respuesta.next()) {
                estudiante = retornaEstudiante(respuesta);
            } else {
                estudiante = null;
            }
        } catch (SQLException sqlException) {
            System.err.println(sqlException);
        } finally {
            this.conexion.desconectar();
        }
        return estudiante;
    }

    // Transfiere una sentencia para actualizar una fila en la tabla estudiantes
     
    @Override
    public String actualizarEstudiante(String sentenciaActualizarEstudiante) {
        String msj = "";
        try {
            this.conexion.conectar();
            if (this.conexion.ejecutarSentenciaSQL(sentenciaActualizarEstudiante) != 0) {
                msj = "Se modificó el estudiante\n";
            } else {
                msj = "El estudiante no se encuentra registrado en el instituto\n";
            }
        } catch (SQLException sqlException) {
            System.err.println(sqlException);
        } finally {
            this.conexion.desconectar();
        }
        return msj;
    }

    // Transfiere una sentencia para eliminar una fila en la tabla estudiantes
     
    @Override
    public String eliminarEstudiante(String sentenciaEliminarEstudiante) {
        String msj = "";
        try {
            this.conexion.conectar();
            if (this.conexion.ejecutarSentenciaSQL(sentenciaEliminarEstudiante) != 0) {
                msj = "Se eliminó el estudiante\n";
            } else {
                msj = "El estudiante no se encuentra registrado en el instituto\n";
            }
        } catch (SQLException sqlException) {
            System.err.println(sqlException);
        } finally {
            this.conexion.desconectar();
        }
        return msj;
    }

    // Transfiere una sentencia para obtener las filas de la tabla estudiantes 
    
    @Override
    public String mostrarEstudiantes(String sentenciaMostrarEstudiantes) {
        StringBuilder stb = new StringBuilder();
        try {
            this.conexion.conectar();
            ResultSet respuesta = this.conexion.consultarRegistros(sentenciaMostrarEstudiantes);
            while (respuesta.next()) {
                stb.append(retornaEstudiante(respuesta).toString());
            }
        } catch (SQLException sqlException) {
            System.err.println(sqlException);
        } finally {
            this.conexion.desconectar();
        }
        return stb.toString();
    }

    // Transfiere una sentencia para obtener la cantidad de filas en la tabla estudiantes
     
    @Override
    public int contarEstudiantes(String sentenciaContarEstudiantes) {
        int contadorEstudiantes = 0;
        try {
            this.conexion.conectar();
            ResultSet respuesta = this.conexion.consultarRegistros(sentenciaContarEstudiantes);
            if (respuesta.next()) {
                contadorEstudiantes = respuesta.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            this.conexion.desconectar();
        }
        return contadorEstudiantes;
    }

    //Una función con diferentes consultas requeridas para la solución del reto
     
    @Override
    public String consultasEstudiantes(String sentenciaObtenerEstudiante, int tipoConsulta) {
        try {
            this.conexion.conectar();
            switch (tipoConsulta) {
                case 1:
                    return this.consultaCorreo(sentenciaObtenerEstudiante);
                case 2:
                    return this.consultaApellidos(sentenciaObtenerEstudiante);
                case 3:
                    return this.consultaPrograma(sentenciaObtenerEstudiante);
                case 4:
                    return this.consultaFechaNacimiento(sentenciaObtenerEstudiante);
                case 5:
                    return this.consultaNumeroCelular(sentenciaObtenerEstudiante);
                default:
                    return "";
            }
        } catch (SQLException sQLException) {
            System.err.println(sQLException.getMessage());
        } finally {
            this.conexion.desconectar();
        }
        return "";
    }

    // Devuelve un objeto de tipo estudiante en formato de cadena
     
    private String consultaCorreo(String sentenciaCorreoInstitucional) throws NullPointerException {
        try {
            return obtenerEstudiante(sentenciaCorreoInstitucional).toString();
        } catch (NullPointerException npe) {
            return "El estudiante no se encuentra registrado en el instituto\n";
        }
    }

    // Devuelve una cadena con estudiantes que compartan el mismo apellido
    
    private String consultaApellidos(String sentenciaApellidos) throws SQLException {
        StringBuilder stb = new StringBuilder();
        ResultSet respuesta = this.conexion.consultarRegistros(sentenciaApellidos);
        while (respuesta.next()) {
            stb.append(retornaEstudiante(respuesta).toString());
        }
        if (stb.toString().equals("")) {
            stb.append("No hay resultados para esa consulta");
        }
        return stb.toString();
    }

    // Devuelve una cadena con estudiantes que compartan el mismo programa
     
    private String consultaPrograma(String sentenciaProgramas) throws SQLException {
        StringBuilder stb = new StringBuilder();
        Estudiante estudiante;
        ResultSet respuesta = this.conexion.consultarRegistros(sentenciaProgramas);
        while (respuesta.next()) {
            stb.append(retornaEstudiante(respuesta).toString());
            //estudiante = retornaEstudiante(respuesta);
            //stb.append(estudiante.getApellidos()).append(" ").append(estudiante.getNombres());
        }
        if (stb.toString().equals("")) {
            stb.append("No hay resultados para esa consulta");
        }
        return stb.toString();
    }

    //Devuelve una cadena con estudiantes que compartan la fecha de nacimiento
    
    private String consultaFechaNacimiento(String sentenciaFechaNacimiento) throws SQLException {
        StringBuilder stb = new StringBuilder();
        ResultSet respuesta = this.conexion.consultarRegistros(sentenciaFechaNacimiento);
        while (respuesta.next()) {
            stb.append(retornaEstudiante(respuesta).toString());
        }
        if (stb.toString().equals("")) {
            stb.append("No hay resultados para esa consulta");
        }
        return stb.toString();
    }

    //Devuelve un objeto de tipo estudiante en formato de cadena
     
    private String consultaNumeroCelular(String sentenciaNumeroCelular) throws SQLException {
        StringBuilder stb = new StringBuilder();
        Estudiante estudiante;
        ResultSet respuesta = this.conexion.consultarRegistros(sentenciaNumeroCelular);
        while (respuesta.next()) {
            stb.append(retornaEstudiante(respuesta).toString());
            //estudiante = retornaEstudiante(respuesta);
            //stb.append(estudiante.getApellidos()).append(" ").
            //        append(estudiante.getNombres()).append(" ").
            //        append(estudiante.getPrograma_academico());
        }
        if (stb.toString().equals("")) {
            stb.append("No hay resultados para esa consulta");
        }
        return stb.toString();
    }

    //Retorna un objeto de tipo estudiante 
    
    private Estudiante retornaEstudiante(ResultSet respuesta) throws SQLException {
        Estudiante estudiante = new Estudiante();
        estudiante.setId_estudiante(respuesta.getInt("id_estudiante"));
        estudiante.setNombres(respuesta.getString("nombres"));
        estudiante.setApellidos(respuesta.getString("apellidos"));
        estudiante.setFecha_nacimiento(respuesta.getString("fecha_nacimiento"));
        estudiante.setCorreo_institucional(respuesta.getString("correo_institucional"));
        estudiante.setCorreo_personal(respuesta.getString("correo_personal"));
        estudiante.setNum_celular(Long.parseLong(respuesta.getString("num_celular")));
        estudiante.setNum_fijo(Long.parseLong(respuesta.getString("num_fijo")));
        estudiante.setPrograma_academico(respuesta.getString("programa_academico"));
        return estudiante;
    }
}
