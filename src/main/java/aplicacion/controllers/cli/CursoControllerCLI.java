package aplicacion.controllers.cli;

import aplicacion.data.CursoData;
import aplicacion.data.ProfesorData;
import aplicacion.data.datafile.CursoDatafile;;
import aplicacion.data.datafile.ProfesorDatafile;
import aplicacion.models.Curso;
import aplicacion.models.Profesor;
import aplicacion.views.cli.CursoViewCLI;
import aplicacion.views.cli.UtilsCLI;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Clase controladora del menú de gestión de Cursos de la interfaz de linea de comandos.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 1.0
 */
public class CursoControllerCLI {
    private final CursoData cursoData;
    private final ProfesorData profesorData;
    private final CursoViewCLI cursoView;
    private final BufferedReader lector;

    /**
     * Objeto controlador de Curso en la interfaz de línea de comandos
     *
     * @param lector BufferedReader lector utilizado en el main
     */
    public CursoControllerCLI(BufferedReader lector) {
        this.cursoData = new CursoDatafile();
        this.profesorData = new ProfesorDatafile();
        this.cursoView = new CursoViewCLI();
        this.lector = lector;
    }

    /**
     * Solicita los datos necesarios al usuario para crear un objeto de tipo Curso
     *
     * @return Curso
     * @throws IOException Posibles errores de entrada/salida de datos
     */
    private Curso obtenerDatosCurso() throws IOException {
        int telefono;
        short nivel;
        char paralelo;
        String rut, nombres, apPat, apMat, email, asignatura;
        UtilsCLI.imprimirSolicitar("el nivel del curso", "número de 1 a 12");
        nivel = Short.parseShort(this.lector.readLine());
        UtilsCLI.imprimirSolicitar("el paralelo del curso", "caracter");
        paralelo = this.lector.readLine().charAt(0);
        UtilsCLI.imprimirSolicitar("el RUT del profesor jefe", "RUT");
        rut = this.lector.readLine();
        UtilsCLI.imprimirSolicitar("los nombres del profesor jefe", "texto");
        nombres = this.lector.readLine();
        UtilsCLI.imprimirSolicitar("el apellido paterno del profesor jefe", "texto");
        apPat = this.lector.readLine();
        UtilsCLI.imprimirSolicitar("el apellido materno del profesor jefe", "texto");
        apMat = this.lector.readLine();
        UtilsCLI.imprimirSolicitar("la asignatura del profesor jefe", "texto");
        asignatura = this.lector.readLine();
        UtilsCLI.imprimirSolicitar("el email del profesor jefe", "texto");
        email = this.lector.readLine();
        UtilsCLI.imprimirSolicitar("el teléfono del profesor jefe", "texto");
        telefono = Integer.parseInt(this.lector.readLine());
        return new Curso(nivel, paralelo, new Profesor(rut, nombres, apPat, apMat, asignatura, email, telefono));
    }

    /**
     * Solicita los datos necesarios al usuario para editar el profesor jefe de un curso definido.
     *
     * @param cursoOriginal Curso original a basar la información
     * @return Curso
     * @throws IOException Posibles errores de entrada/salida de datos
     */
    private Curso obtenerDatosCurso(Curso cursoOriginal) throws IOException {
        int telefono;
        String rut, nombres, apPat, apMat, email, asignatura;
        UtilsCLI.imprimirSolicitar("el RUT del profesor jefe", "RUT");
        rut = this.lector.readLine();
        UtilsCLI.imprimirSolicitar("los nombres del profesor jefe", "texto");
        nombres = this.lector.readLine();
        UtilsCLI.imprimirSolicitar("el apellido paterno del profesor jefe", "texto");
        apPat = this.lector.readLine();
        UtilsCLI.imprimirSolicitar("el apellido materno del profesor jefe", "texto");
        apMat = this.lector.readLine();
        UtilsCLI.imprimirSolicitar("la asignatura del profesor jefe", "texto");
        asignatura = this.lector.readLine();
        UtilsCLI.imprimirSolicitar("el email del profesor jefe", "texto");
        email = this.lector.readLine();
        UtilsCLI.imprimirSolicitar("el teléfono del profesor jefe", "texto");
        telefono = Integer.parseInt(this.lector.readLine());
        return new Curso(cursoOriginal.getNivel(), cursoOriginal.getLetra(), new Profesor(rut, nombres, apPat, apMat,
                asignatura, email, telefono));
    }

    /**
     * Controla el flujo de trabajo de la opción marcada por el usuario, en el menú de gestión de cursos.
     *
     * @param opt Entero que contiene la opción marcada por el usuario
     * @throws IOException Posibles errores de entrada/salida de datos
     */
    private void opcMenuCursos(short opt) throws IOException {
        Curso curso, cursoActualizado;
        short nivel;
        char paralelo;
        switch (opt) {
            case 0:
                break;
            case 9:
                UtilsCLI.mensajeDespedida();
                System.exit(0);
            case 1:
                this.cursoData.insertCurso(obtenerDatosCurso());
                this.cursoView.mostrarTablaCursos(this.cursoData.getCursos());
                break;
            case 2:
                this.cursoView.mostrarTablaCursos(this.cursoData.getCursos());
                break;
            case 3:
                UtilsCLI.imprimirSolicitar("el nivel de los alumnos", "número de 1 a 12");
                nivel = Short.parseShort(this.lector.readLine());
                UtilsCLI.imprimirSolicitar("el paralelo al que pertenecen los alumnos", "caracter");
                paralelo = this.lector.readLine().charAt(0);
                curso = this.cursoData.getCurso(nivel, paralelo);
                System.out.println("Cambiando profesor jefe del " + curso.cursoToString());
                cursoActualizado = obtenerDatosCurso(curso);
                if (this.cursoData.updateCurso(cursoActualizado)) {
                    this.profesorData.insertProfesor(cursoActualizado.getProfesorJefe());
                    this.profesorData.deleteProfesor(curso.getProfesorJefe());
                    System.out.println("El curso ha sido actualizado exitosamente");
                } else
                    System.out.println("Ha ocurrido un error, por favor intente nuevamente.");
                break;
            case 4:
                UtilsCLI.imprimirSolicitar("el nivel del curso", "número de 1 a 12");
                nivel = Short.parseShort(this.lector.readLine());
                UtilsCLI.imprimirSolicitar("el paralelo del curso", "caracter");
                paralelo = this.lector.readLine().charAt(0);
                curso = this.cursoData.getCurso(nivel, paralelo);
                System.out.println("Eliminando curso " + curso.cursoToString());
                if (this.cursoData.deleteCurso(curso))
                    System.out.println("El curso ha sido eliminado exitosamente");
                else
                    System.out.println("Ha ocurrido un error, por favor intente nuevamente.");
                break;
            default:
                UtilsCLI.mensajeErrOpc();
                break;
        }
    }

    /**
     * Muestra el menú de gestión de Cursos por pantalla
     *
     * @throws IOException Posibles errores de entrada/salida de datos
     */
    public void mostrarMenuCursos() throws IOException {
        short opt = -1;
        while (opt != 0) {
            this.cursoView.mostrarMenuCursos();
            UtilsCLI.imprimirIngresarOpcion("numérica");
            opt = Short.parseShort(this.lector.readLine());
            opcMenuCursos(opt);
        }
    }

}
