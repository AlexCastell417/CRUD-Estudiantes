
// Interfaz para estandarización de acceso a datos

public interface IEstudianteDAO {

    //Gestión individual de estudiantes
    public String agregarEstudiante(String sentenciaAgregarEstudiante);
    public Estudiante obtenerEstudiante(String sentenciaObtenerEstudiante);
    public String actualizarEstudiante(String sentenciaActualizarEstudiante);
    public String eliminarEstudiante(String sentenciaEliminarEstudiante);

    //Gestión de varios estudiantes
    public String mostrarEstudiantes(String sentenciaMostrarEstudiantes);
    public String consultasEstudiantes(String sentenciaObtenerEstudiante, int tipoConsulta);
    public int contarEstudiantes(String sentenciaContarEstudiantes);
}
