package aplicacion.controllers.cli;

import aplicacion.models.Alumno;
import aplicacion.models.Apoderado;
import aplicacion.views.cli.MenuCLI;
import aplicacion.views.cli.UtilsCLI;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Clase controladora del menú de gestión de Alumnos de la interfaz de linea de comandos.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 2.0
 */
public class AlumnoControllerCLI {

    private final BufferedReader lector;
    private final MenuCLI menuCLI;

    /**
     * Objeto controlador de Alumno en la interfaz de línea de comandos
     *
     * @param lector  BufferedReader instancia de lector.
     * @param menuCLI Instancia de menú con los origenes de datos.
     */
    public AlumnoControllerCLI(BufferedReader lector, MenuCLI menuCLI) {
        this.lector = lector;
        this.menuCLI = menuCLI;
    }

    /**
     * Solicita los datos necesarios al usuario para crear un objeto de tipo Alumno.
     *
     * @param nivel    Nivel del alumno a generar
     * @param paralelo Paralelo del curso al que pertenece el alumno
     * @return Alumno
     * @throws IOException Posibles errores de entrada/salida de datos
     */
    public Alumno obtenerDatosAlumno(int nivel, char paralelo) throws IOException {
        int telefonoAp;
        String rut, nombres, apPat, apMat, rutAp, nombresAp, apPatAp, apMatAp, emailAp;
        UtilsCLI.imprimirSolicitar("el RUT del alumno", "RUT");
        rut = this.lector.readLine();
        UtilsCLI.imprimirSolicitar("los nombres del alumno", "texto");
        nombres = this.lector.readLine();
        UtilsCLI.imprimirSolicitar("el apellido paterno del alumno", "texto");
        apPat = this.lector.readLine();
        UtilsCLI.imprimirSolicitar("el apellido materno del alumno", "texto");
        apMat = this.lector.readLine();
        UtilsCLI.imprimirSolicitar("el RUT del apoderado", "RUT");
        rutAp = this.lector.readLine();
        UtilsCLI.imprimirSolicitar("los nombres del apoderado", "texto");
        nombresAp = this.lector.readLine();
        UtilsCLI.imprimirSolicitar("el apellido paterno del apoderado", "texto");
        apPatAp = this.lector.readLine();
        UtilsCLI.imprimirSolicitar("el apellido materno del apoderado", "texto");
        apMatAp = this.lector.readLine();
        UtilsCLI.imprimirSolicitar("el email del apoderado", "texto");
        emailAp = this.lector.readLine();
        UtilsCLI.imprimirSolicitar("el teléfono del apoderado", "texto");
        telefonoAp = Integer.parseInt(this.lector.readLine());
        return new Alumno(rut, nombres, apPat, apMat, nivel, paralelo, new Apoderado(rutAp, nombresAp, apPatAp, apMatAp,
                telefonoAp, emailAp));
    }

    /**
     * Solicita los datos necesarios al usuario para crear un objeto de tipo Alumno, generado desde la edición de un
     * alumno inicial.
     *
     * @param alumnoOriginal Alumno original (para actualizar datos)
     * @param nivel          Nivel del alumno a generar
     * @param paralelo       Paralelo del curso al que pertenece el alumno
     * @return Alumno
     * @throws IOException Posibles errores de entrada/salida de datos
     */
    public Alumno obtenerDatosAlumno(Alumno alumnoOriginal, int nivel, char paralelo) throws IOException {
        String nombres, apPat, apMat;
        UtilsCLI.imprimirSolicitar("los nombres del alumno", "texto");
        nombres = this.lector.readLine();
        UtilsCLI.imprimirSolicitar("el apellido paterno del alumno", "texto");
        apPat = this.lector.readLine();
        UtilsCLI.imprimirSolicitar("el apellido materno del alumno", "texto");
        apMat = this.lector.readLine();
        return new Alumno(alumnoOriginal.getRut(), nombres, apPat, apMat, nivel, paralelo,
                alumnoOriginal.getApoderado());
    }



}
