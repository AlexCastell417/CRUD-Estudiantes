//ya no es serializable 
public class Estudiante {

    private int id_estudiante;  //Para guardar la llave primaria de la base de datos
    private String nombres;
    private String apellidos;
    private String fecha_nacimiento;
    private String correo_institucional;
    private String correo_personal;
    private long num_celular;
    private long num_fijo;
    private String programa_academico;

      public Estudiante() {

    }


    public Estudiante(int id_estudiante, String nombres, String apellidos, String fecha_nacimiento, String correo_institucional, String correo_personal, long num_celular, long num_fijo, String programa_academico) {
        this.id_estudiante = id_estudiante;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fecha_nacimiento = fecha_nacimiento;
        this.correo_institucional = correo_institucional;
        this.correo_personal = correo_personal;
        this.num_celular = num_celular;
        this.num_fijo = num_fijo;
        this.programa_academico = programa_academico;
    }

    public int getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(int id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getCorreo_institucional() {
        return correo_institucional;
    }

    public void setCorreo_institucional(String correo_institucional) {
        this.correo_institucional = correo_institucional;
    }

    public String getCorreo_personal() {
        return correo_personal;
    }

    public void setCorreo_personal(String correo_personal) {
        this.correo_personal = correo_personal;
    }

    public long getNum_celular() {
        return num_celular;
    }

    public void setNum_celular(long num_celular) {
        this.num_celular = num_celular;
    }

    public long getNum_fijo() {
        return num_fijo;
    }

    public void setNum_fijo(long num_fijo) {
        this.num_fijo = num_fijo;
    }

    public String getPrograma_academico() {
        return programa_academico;
    }

    public void setPrograma_academico(String programa_academico) {
        this.programa_academico = programa_academico;
    }

  
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("\nNombres: ").append(this.getNombres());
        str.append("\nApellidos: ").append(this.getApellidos());
        str.append("\nFecha nacimiento: ").append(this.getFecha_nacimiento());
        str.append("\nCorreo institucional: ").append(this.getCorreo_institucional());
        str.append("\nCorreo personal: ").append(this.getCorreo_personal());
        str.append("\nNúmero de teléfono celular: ").append(this.getNum_celular());
        str.append("\nNúmero de teléfono fijo: ").append(this.getNum_fijo());
        str.append("\nPrograma académico: ").append(this.getPrograma_academico());
        str.append("\n");
        return str.toString();
    }
}
