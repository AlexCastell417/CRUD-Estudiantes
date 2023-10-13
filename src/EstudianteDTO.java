
//DTO, ANTES SOLO TRANSFERIA INFORMACION, AHORA TRANSFIERE INFORMACION TAMBIEN ENTRE EL LENGUAJE Y SQL
// POR LO TANTO SE VUELVE MAS COMPLEJO E IMPLEMENTA TODO LO QUE SE NECESITA DE SQL

public class EstudianteDTO {

    private final EstudianteDAO estudianteDAO;

    public EstudianteDTO() {
        //Envia la sentencia SQL para creación de tabla estudiantes
        this.estudianteDAO = new EstudianteDAO(Sentencias.CREAR_DB);
    }

    //Formatea una sentencia SQL con la información de n objeto de tipo estudainte
    
    public String agregarEstudiante(Estudiante estudiante) {
        String sentenciaAgregarEstudiante = String.format(Sentencias.AGREGAR_ESTUDIANTE,
                estudiante.getNombres(),
                estudiante.getApellidos(),
                estudiante.getFecha_nacimiento(),
                estudiante.getCorreo_institucional(),
                estudiante.getCorreo_personal(),
                estudiante.getNum_celular(),
                estudiante.getNum_fijo(),
                estudiante.getPrograma_academico());
        return this.estudianteDAO.agregarEstudiante(sentenciaAgregarEstudiante);
    }

    //Formatea una sentencia SQL con la información de n objeto de tipo estudainte para actualizar la tabla 
    
    public String actualizarEstudiante(Estudiante estudiante) {
        String sentenciaActualizarEstudiante = String.format(Sentencias.ACTUALIZAR_ESTUDIANTE,
                estudiante.getCorreo_personal(),
                String.valueOf(estudiante.getNum_celular()),
                String.valueOf(estudiante.getNum_fijo()),
                estudiante.getPrograma_academico(),
                estudiante.getId_estudiante());
        return this.estudianteDAO.actualizarEstudiante(sentenciaActualizarEstudiante);
    }

    // Sentencia SQL con el correo instiucional para eliminar una fila de la tabla estudiantes
    
    public String eliminarEstudiante(String correo_institucional) {
        String sentenciaEliminarEstudiante = String.format(Sentencias.ELIMINAR_ESTUDIANTE, correo_institucional);
        return this.estudianteDAO.eliminarEstudiante(sentenciaEliminarEstudiante);
    }

    //  SQL para obtener la información de todas las filas de la tabla estudiantes
    
    public String directorioEstudiantes() {
        return this.estudianteDAO.mostrarEstudiantes(Sentencias.LISTAR_ESTUDIANTES);
    }

    //sentencia SQL con el correo institucional para obtener una fila de la tabla estudiantes
     
    public Estudiante obtenerEstudiante(String correo_institucional) {
        String sentenciaObtenerEstudiante = String.format(Sentencias.BUSCAR_ESTUDIANTE_CORREO, correo_institucional);
        return this.estudianteDAO.obtenerEstudiante(sentenciaObtenerEstudiante);
    }

    // SQL con el correo institucional para obtener  información de una fila de la tabla estudiantes
    
    public String obtenerPorCorreo(String correo_institucional) {
        String sentenciaObtenerEstudiante = String.format(Sentencias.BUSCAR_ESTUDIANTE_CORREO, correo_institucional);
        return this.estudianteDAO.consultasEstudiantes(sentenciaObtenerEstudiante, 1);
    }

    // SQL con los apelidos para obtener informacion de un conjunto de filas de la tabla estudiantes que posean el mismo valor
    public String obtenerPorApellidos(String apellidos) {
        String sentenciaObtenerEstudiantes = String.format(Sentencias.BUSCAR_ESTUDIANTE_APELLIDOS, apellidos);
        return this.estudianteDAO.consultasEstudiantes(sentenciaObtenerEstudiantes, 2);
    }

    // SQL con el programa para obtener información de un conjunto de filas de la tabla estudiantes 
    // que posean el mismo valor para programa_academico 
    
    public String obtenerPorPrograma(String programa) {
        String sentenciaObtenerEstudiantes = String.format(Sentencias.BUSCAR_ESTUDIANTE_PROGRAMA, programa);
        return this.estudianteDAO.consultasEstudiantes(sentenciaObtenerEstudiantes, 3);
    }

   /// SQL con el programa para obtener el conteo de las filas de la tabla estudiantes que posean el mismo 
    // valor para programa_academico
     
    public int contarEstudiantesPorPrograma(String programa) {
        String sentenciaContarEstudiantes = String.format(Sentencias.CONTAR_ESTUDIANTES_PROGRAMA, programa);
        return this.estudianteDAO.contarEstudiantes(sentenciaContarEstudiantes);
    }

    // SQL con la fecha de nacimiento para obtener  información de un conjunto de filas de la tabla estudiantes 
    // que posean el mismo valor para fecha_nacimiento
     
    public String obtenerPorFechaNacimiento(String fechaNacimiento) {
        String sentenciaObtenerEstudiantes = String.format(Sentencias.BUSCAR_ESTUDIANTE_FECHANACIMIENTO, fechaNacimiento);
        return this.estudianteDAO.consultasEstudiantes(sentenciaObtenerEstudiantes, 4);
    }

    // SQL con la fecha de nacimiento para obtener información de un conjunto de filas 
    
    public String obtenerPorNumeroCelular(String numeroCelular) {
        String sentenciaObtenerEstudiantes = String.format(Sentencias.BUSCAR_ESTUDIANTE_NUMCELULAR, numeroCelular);
        return this.estudianteDAO.consultasEstudiantes(sentenciaObtenerEstudiantes, 5);
    }

    // SQL para obtener el conteo de las filas de la tabla estudiantes
    
    public boolean hayEstudiantes() {
        return this.estudianteDAO.contarEstudiantes(Sentencias.CONTAR_ESTUDIANTES) != 0;
    }
}

 //Clase con la definición de sentencias SQL en cadenas de tipo  String

class Sentencias {

      protected final static String CREAR_DB = "CREATE TABLE IF NOT EXISTS \"estudiantes\" (\n"
            + "	\"id_estudiante\"	INTEGER NOT NULL UNIQUE,\n"
            + "	\"nombres\"	TEXT NOT NULL,\n"
            + "	\"apellidos\"	TEXT NOT NULL,\n"
            + "	\"fecha_nacimiento\"	TEXT NOT NULL,\n"
            + "	\"correo_institucional\"	TEXT NOT NULL UNIQUE,\n"
            + "	\"correo_personal\"	TEXT NOT NULL,\n"
            + "	\"num_celular\"	TEXT NOT NULL,\n"
            + "	\"num_fijo\"	TEXT NOT NULL,\n"
            + "	\"programa_academico\"	TEXT NOT NULL,\n"
            + "	PRIMARY KEY(\"id_estudiante\")\n"
            + ");";

   
    protected final static String BUSCAR_ESTUDIANTE_CORREO = "SELECT * FROM estudiantes WHERE correo_institucional = '%s'";
    protected final static String BUSCAR_ESTUDIANTE_APELLIDOS = "SELECT * FROM estudiantes WHERE apellidos = '%s'";
    protected final static String BUSCAR_ESTUDIANTE_PROGRAMA = "SELECT * FROM estudiantes WHERE programa_academico = '%s'";
    protected final static String BUSCAR_ESTUDIANTE_FECHANACIMIENTO = "SELECT * FROM estudiantes WHERE fecha_nacimiento = '%s'";
    protected final static String BUSCAR_ESTUDIANTE_NUMCELULAR = "SELECT * FROM estudiantes WHERE num_celular = '%s'";

    
    protected final static String AGREGAR_ESTUDIANTE = "INSERT INTO estudiantes "
            + "(id_estudiante, nombres, apellidos, fecha_nacimiento, "
            + "correo_institucional, correo_personal, num_celular, num_fijo, programa_academico) "
            + "VALUES (NULL, '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')";

    
    protected final static String ACTUALIZAR_ESTUDIANTE = "UPDATE estudiantes SET "
            + "correo_personal = '%s', num_celular = '%s', num_fijo = '%s', programa_academico = '%s' "
            + "WHERE id_estudiante = '%d'";

   
    protected final static String ELIMINAR_ESTUDIANTE = "DELETE FROM estudiantes WHERE correo_institucional = '%s'";

    protected final static String LISTAR_ESTUDIANTES = "SELECT * FROM estudiantes";

    protected final static String CONTAR_ESTUDIANTES = "SELECT count(*) FROM estudiantes";

    protected final static String CONTAR_ESTUDIANTES_PROGRAMA = "SELECT count(*) FROM estudiantes WHERE programa_academico = '%s'";

}
