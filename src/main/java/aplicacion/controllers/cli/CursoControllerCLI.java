package aplicacion.controllers.cli;

import aplicacion.data.CursoData;
import aplicacion.data.datafile.CursoDatafile;;
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
    private final CursoViewCLI cursoView;
    private final BufferedReader lector;

    /**
     * Objeto controlador de Curso en la interfaz de línea de comandos
     *
     * @param lector BufferedReader lector utilizado en el main
     */
    public CursoControllerCLI(BufferedReader lector) {
        this.cursoData = new CursoDatafile();
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
     * Controla el flujo de trabajo de la opción marcada por el usuario, en el menú de gestión de cursos.
     *
     * @param opt Entero que contiene la opción marcada por el usuario
     * @throws IOException Posibles errores de entrada/salida de datos
     */
    private void opcMenuCursos(short opt) throws IOException {
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
