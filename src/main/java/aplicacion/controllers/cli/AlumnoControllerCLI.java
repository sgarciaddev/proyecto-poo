package aplicacion.controllers.cli;

import aplicacion.data.AlumnoData;
import aplicacion.data.datafile.AlumnoDatafile;
import aplicacion.models.Alumno;
import aplicacion.models.Apoderado;
import aplicacion.models.Curso;
import aplicacion.views.cli.AlumnoViewCLI;
import aplicacion.views.cli.UtilsCLI;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Clase controladora del menú de gestión de Alumnos de la interfaz de linea de comandos.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 1.0
 */
public class AlumnoControllerCLI {

    private final AlumnoData alumnoData;
    private final AlumnoViewCLI alumnoViewCLI;
    private final BufferedReader lector;

    /**
     * Objeto controlador de Alumno en la interfaz de línea de comandos
     *
     * @param lector BufferedReader lector utilizado en el main
     */
    public AlumnoControllerCLI(BufferedReader lector) {
        this.alumnoData = new AlumnoDatafile();
        this.alumnoViewCLI = new AlumnoViewCLI();
        this.lector = lector;
    }

    /**
     * Solicita los datos necesarios al usuario para crear un objeto de tipo Alumno
     *
     * @return Alumno
     * @throws IOException Posibles errores de entrada/salida de datos
     */
    private Alumno obtenerDatosAlumno() throws IOException {
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
        return new Alumno(rut, nombres, apPat, apMat, new Apoderado(rutAp, nombresAp, apPatAp, apMatAp, telefonoAp,
                emailAp));
    }

    /**
     * Solicita los datos necesarios al usuario para crear un objeto de tipo Alumno, generado desde la edición de un
     * alumno inicial.
     *
     * @param alumnoOriginal Alumno original (para actualizar datos)
     * @return Alumno
     * @throws IOException Posibles errores de entrada/salida de datos
     */
    private Alumno obtenerDatosAlumno(Alumno alumnoOriginal) throws IOException {
        String nombres, apPat, apMat;
        UtilsCLI.imprimirSolicitar("los nombres del alumno", "texto");
        nombres = this.lector.readLine();
        UtilsCLI.imprimirSolicitar("el apellido paterno del alumno", "texto");
        apPat = this.lector.readLine();
        UtilsCLI.imprimirSolicitar("el apellido materno del alumno", "texto");
        apMat = this.lector.readLine();
        return new Alumno(alumnoOriginal.getRut(), nombres, apPat, apMat, alumnoOriginal.getApoderado());
    }

    /**
     * Controla el flujo de trabajo de la opción marcada por el usuario, en el menú de gestión de alumnos.
     *
     * @param opt Entero que contiene la opción marcada por el usuario
     * @throws IOException Posibles errores de entrada/salida de datos
     */
    private void opcMenuAlumnos(short opt) throws IOException {
        Alumno alumno, alumnoEditado;
        int nivel;
        char paralelo;
        String rut;
        switch (opt) {
            case 0:
                break;
            case 9:
                UtilsCLI.mensajeDespedida();
                System.exit(0);
            case 1:
                UtilsCLI.imprimirSolicitar("el nivel del alumno", "número de 1 a 12");
                nivel = Integer.parseInt(this.lector.readLine());
                UtilsCLI.imprimirSolicitar("el paralelo al que pertenece el alumno", "caracter");
                paralelo = this.lector.readLine().charAt(0);
                this.alumnoData.insertAlumno(obtenerDatosAlumno(), nivel, paralelo);
                this.alumnoViewCLI.mostrarTablaAlumnos(this.alumnoData.getAlumnos(nivel, paralelo),
                        Curso.cursoToString(nivel, paralelo));
                break;
            case 2:
                UtilsCLI.imprimirSolicitar("el nivel de los alumnos", "número de 1 a 12");
                nivel = Integer.parseInt(this.lector.readLine());
                UtilsCLI.imprimirSolicitar("el paralelo al que pertenecen los alumnos", "caracter");
                paralelo = this.lector.readLine().charAt(0);
                this.alumnoViewCLI.mostrarTablaAlumnos(this.alumnoData.getAlumnos(nivel, paralelo),
                        Curso.cursoToString(nivel, paralelo));
                break;
            case 3:
                UtilsCLI.imprimirSolicitar("el nivel de los alumnos", "número de 1 a 12");
                nivel = Integer.parseInt(this.lector.readLine());
                UtilsCLI.imprimirSolicitar("el paralelo al que pertenecen los alumnos", "caracter");
                paralelo = this.lector.readLine().charAt(0);
                UtilsCLI.imprimirSolicitar("el RUT del alumno", "sin puntos, con guión");
                rut = this.lector.readLine();
                alumno = this.alumnoData.getAlumno(rut);
                if (alumno == null) {
                    UtilsCLI.mensajeErrIngresado();
                    break;
                }
                System.out.println("Editando alumno:\n  Nombre: " + alumno.getNombreCompleto() + "\n  RUT: " + rut);
                alumnoEditado = obtenerDatosAlumno(alumno);
                if (this.alumnoData.updateAlumno(alumnoEditado, nivel, paralelo))
                    System.out.println("Alumno actualizado exitosamente.");
                else
                    System.out.println("Ha ocurrido un error, por favor intente nuevamente.");
                break;
            case 4:
                UtilsCLI.imprimirSolicitar("el nivel de los alumnos", "número de 1 a 12");
                nivel = Integer.parseInt(this.lector.readLine());
                UtilsCLI.imprimirSolicitar("el paralelo al que pertenecen los alumnos", "caracter");
                paralelo = this.lector.readLine().charAt(0);
                UtilsCLI.imprimirSolicitar("el RUT del alumno", "sin puntos, con guión");
                rut = this.lector.readLine();
                alumno = this.alumnoData.getAlumno(rut);
                if (alumno == null) {
                    UtilsCLI.mensajeErrIngresado();
                    break;
                }
                System.out.println("Eliminando alumno:\n  Nombre: " + alumno.getNombreCompleto() + "\n  RUT: " + rut);
                if (this.alumnoData.deleteAlumno(alumno, nivel, paralelo))
                    System.out.println("Alumno eliminado exitosamente.");
                else
                    System.out.println("Ha ocurrido un error, por favor intente nuevamente.");
                break;
            default:
                UtilsCLI.mensajeErrIngresado();
                break;
        }
    }

    /**
     * Muestra el menú de gestión de Alumnos por pantalla
     *
     * @throws IOException Posibles errores de entrada/salida de datos
     */
    public void mostrarMenuAlumnos() throws IOException {
        short opt = -1;
        while (opt != 0) {
            this.alumnoViewCLI.mostrarMenuAlumnos();
            UtilsCLI.imprimirIngresarOpcion("numérica");
            opt = Short.parseShort(this.lector.readLine());
            opcMenuAlumnos(opt);
        }
    }

}
