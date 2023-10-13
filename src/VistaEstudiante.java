import java.util.Scanner;

public class VistaEstudiante {

    private static final String INGRESAR_NOMBRES = "Ingresar nombres: ";
    private static final String INGRESAR_APELLIDOS = "Ingresar apellidos: ";
    private static final String INGRESAR_FECHA_NAC = "Ingresar fecha de nacimiento (YYYY-MM-DD): ";
    private static final String INGRESAR_C_INSTITUCIONAL = "Ingresar correo institucional: ";
    private static final String INGRESAR_C_PERSONAL = "Ingresar correo personal: ";
    private static final String INGRESAR_N_CELULAR = "Ingresar número de celular: ";
    private static final String INGRESAR_N_FIJO = "Ingresar número fijo: ";
    private static final String INGRESAR_PROGRAMA = "Ingresar programa: ";

    private final Scanner sc;

    public VistaEstudiante() {
        this.sc = new Scanner(System.in);
    }

    public int menuPrincipal(){
        System.out.println("INSTITUTO LA FLORESTA\n"
                + "Seleccione tarea a realizar:\n"
                + " 1. Ingresar estudiante\n"
                + " 2. Buscar estudiante\n"
                + " 3. Modificar estudiante\n"
                + " 4. Eliminar Estudiante\n"
                + " 5. Ver directorio de estudiantes\n"
                + " 6. Salir\n"
                + "Opción: ");
        return pasarInt();
    }

    public int menuConsultas() {
        System.out.println("Consultas \n"
                + "Seleccione consulta a realizar: \n"
                + "1. Buscar por correo electrónico \n"
                + "2. Buscar por apellidos \n"
                + "3. Bucar por programa \n"
                + "4. Buscar cantidad de estudiantes por programa \n"
                + "5. Buscar por fecha de nacimiento \n"
                + "6. Buscar por número de celular \n"
                + "Opción:");
        return pasarInt();
    }

    public void ingresarEstudiante(EstudianteDTO estudianteDTO) {
        Estudiante estudiante = new Estudiante();
        System.out.println("Ingresar estudiante");
        estudiante.setNombres(pasarString(INGRESAR_NOMBRES));
        estudiante.setApellidos(pasarString(INGRESAR_APELLIDOS));
        estudiante.setFecha_nacimiento(pasarString(INGRESAR_FECHA_NAC));
        estudiante.setCorreo_institucional(pasarString(INGRESAR_C_INSTITUCIONAL));
        estudiante.setCorreo_personal(pasarString(INGRESAR_C_PERSONAL));
        estudiante.setNum_celular(pasarLong(INGRESAR_N_CELULAR));
        estudiante.setNum_fijo(pasarLong(INGRESAR_N_FIJO));
        estudiante.setPrograma_academico(pasarString(INGRESAR_PROGRAMA));
        System.out.println(estudianteDTO.agregarEstudiante(estudiante));
    }

    public void buscarEstudianteCorreo(EstudianteDTO estudianteDTO) {
        System.out.println("1. Buscar estudiante por correo electrónico");
        System.out.println(estudianteDTO.obtenerPorCorreo(pasarString(INGRESAR_C_INSTITUCIONAL)));
    }

    public void buscarEstudianteApellidos(EstudianteDTO estudianteDTO) {
        System.out.println("2. Buscar estudiante por apellidos");
        System.out.println(estudianteDTO.obtenerPorApellidos(pasarString(INGRESAR_APELLIDOS)));
    }

    public void buscarEstudiantesPrograma(EstudianteDTO estudianteDTO) {
        System.out.println("3. Bucar por programa");
        System.out.println(estudianteDTO.obtenerPorPrograma(pasarString(INGRESAR_PROGRAMA)));
    }

    public void contarEstudiantesPorPrograma(EstudianteDTO estudianteDTO) {
        System.out.println("4. Buscar cantidad de estudiantes por programa");
        String programa = pasarString(INGRESAR_PROGRAMA);
        int cantidad = estudianteDTO.contarEstudiantesPorPrograma(programa);
        System.out.printf("Cantidad estudiantes %s: %d\n", programa, cantidad);
    }

    public void buscarEstudianteFechaNacimiento(EstudianteDTO estudianteDTO) {
        System.out.println("5. Buscar por fecha de nacimiento");
        System.out.println(estudianteDTO.obtenerPorFechaNacimiento(pasarString("Ingresar fecha de nacimiento:")));
    }

    public void buscarEstudiantesPorNumCelular(EstudianteDTO estudianteDTO) {
        System.out.println("6. Buscar por número de celular ");
        String numeroCelular = String.valueOf(pasarLong(INGRESAR_N_CELULAR));
        System.out.println(estudianteDTO.obtenerPorNumeroCelular(numeroCelular));
    }

    public void eliminar_Estudiante(EstudianteDTO estudianteDTO) {
        System.out.println("Eliminar estudiante");
        System.out.println(estudianteDTO.eliminarEstudiante(pasarString(INGRESAR_C_INSTITUCIONAL)));
    }

    public void modificar_Estudiante(EstudianteDTO estudianteDTO) {
        System.out.println("Modificar estudiante");
        Estudiante estudiante = estudianteDTO.obtenerEstudiante(pasarString(INGRESAR_C_INSTITUCIONAL));
        if (estudiante != null) {
            estudiante.setCorreo_personal(pasarString(INGRESAR_C_PERSONAL));
            estudiante.setNum_celular(pasarLong(INGRESAR_N_CELULAR));
            estudiante.setNum_fijo(pasarLong(INGRESAR_N_FIJO));
            estudiante.setPrograma_academico(pasarString(INGRESAR_PROGRAMA));
            System.out.println(estudianteDTO.actualizarEstudiante(estudiante));
        } else {
            System.out.println("El estudiante no se encuentra registrado en el instituto");
        }
    }

    public void mostrar_Directorio(EstudianteDTO estudianteDTO) {
        System.out.println("El directorio de los estudiantes");
        System.out.println(estudianteDTO.directorioEstudiantes());
    }

    public void mensaje_salida() {
        System.out.println("Hasta pronto");
    }

    private String pasarString(String msj) {
        String dato = "";
        do {
            System.out.println(msj);
            dato = this.sc.nextLine();
        } while (dato.equals(""));
        return dato;
    }

    private long pasarLong(String msj) {
        long dato = 0;
        do {
            try {
                System.out.println(msj);
                dato = Long.parseLong(this.sc.nextLine());
                return dato;
            } catch (NumberFormatException nfe) {
                System.err.println("El dato ingresado no es un número\n");
            }
        } while (dato == 0);
        return dato;
    }

    private int pasarInt() {
        int dato = 0;
        do {
            try {
                dato = Integer.parseInt(this.sc.nextLine());
                return dato;
            } catch (NumberFormatException nfe) {
                System.err.println("El dato ingresado no es un número\n");
            }
        } while (dato != 0);
        return dato;
    }
}
