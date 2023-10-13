
public class Controlador {

    private final EstudianteDTO estudianteDTO;
    private final VistaEstudiante vistaEstudiante;

    public Controlador() {
        this.estudianteDTO = new EstudianteDTO();
        this.vistaEstudiante = new VistaEstudiante();
    }

    //nuevos  verificares de fines de  tablas 
    public boolean hayEstudiantes() {
        return this.estudianteDTO.hayEstudiantes();
    }

   
    public void caseModificarEstudiante() {
        if (hayEstudiantes()) {
            this.vistaEstudiante.modificar_Estudiante(this.estudianteDTO);
        } else {
            System.out.println("No existen estudiantes\n");
        }
    }

    public void caseEliminarEstudiante() {
        if (hayEstudiantes()) {
            this.vistaEstudiante.eliminar_Estudiante(this.estudianteDTO);
        } else {
            System.out.println("No existen estudiantes\n");
        }
    }

    public void caseDirectorioEstudiantes() {
        if (hayEstudiantes()) {
            this.vistaEstudiante.mostrar_Directorio(this.estudianteDTO);
        } else {
            System.out.println("No existen estudiantes\n");
        }
    }

    // Menu Principal 
    public void manejoEstudiantes() {
        int opc;
        do {
            opc = this.vistaEstudiante.menuPrincipal();
            switch (opc) {
                case 1:
                    this.vistaEstudiante.ingresarEstudiante(this.estudianteDTO);
                    break;
                case 2:
                    consultas(this.vistaEstudiante.menuConsultas());
                    break;
                case 3:
                    caseModificarEstudiante();
                    break;
                case 4:
                    caseEliminarEstudiante();
                    break;
                case 5:
                    caseDirectorioEstudiantes();
                    break;
                case 6:
                    this.vistaEstudiante.mensaje_salida();
                    break;
                default:
                    System.out.println("Error: Opción no válida");
                    break;
            }
        } while (opc != 6);
    }

    
    //menu consultas
    public void consultas(int opc) {
        switch (opc) {
            case 1:
                consultaEstudiantePorCorreo();
                break;
            case 2:
                consultaEstudiantePorApellidos();
                break;
            case 3:
                consultaEstudiantesPorPrograma();
                break;
            case 4:
                contarEstudiantesPorPrograma();
                break;
            case 5:
                consultaEstudiantesPorFechaNacimiento();
                break;
            case 6:
                consultaEstudiantesPorNumCelular();
                break;
            default:
                System.out.println("No ingreso una opción valida");
                break;
        }
    }

    public void consultaEstudiantePorCorreo() {
        if (hayEstudiantes()) {
            this.vistaEstudiante.buscarEstudianteCorreo(this.estudianteDTO);
        } else {
            System.out.println("No existen estudiantes\n");
        }
    }

    public void consultaEstudiantePorApellidos() {
        if (hayEstudiantes()) {
            this.vistaEstudiante.buscarEstudianteApellidos(this.estudianteDTO);
        } else {
            System.out.println("No existen estudiantes\n");
        }
    }

    public void consultaEstudiantesPorPrograma() {
        if (hayEstudiantes()) {
            this.vistaEstudiante.buscarEstudiantesPrograma(this.estudianteDTO);
        } else {
            System.out.println("No existen estudiantes\n");
        }
    }

    public void consultaEstudiantesPorFechaNacimiento() {
        if (hayEstudiantes()) {
            this.vistaEstudiante.buscarEstudianteFechaNacimiento(this.estudianteDTO);
        } else {
            System.out.println("No existen estudiantes\n");
        }
    }

    public void contarEstudiantesPorPrograma() {
        if (hayEstudiantes()) {
            this.vistaEstudiante.contarEstudiantesPorPrograma(this.estudianteDTO);
        } else {
            System.out.println("No existen estudiantes\n");
        }
    }

    public void consultaEstudiantesPorNumCelular() {
        if (hayEstudiantes()) {
            this.vistaEstudiante.buscarEstudiantesPorNumCelular(this.estudianteDTO);
        } else {
            System.out.println("No existen estudiantes\n");
        }
    }
}
