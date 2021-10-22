package aplicacion.controllers.cli;

import aplicacion.data.CursoData;
import aplicacion.data.datafile.CursoDatafile;
import aplicacion.models.Alumno;
import aplicacion.models.Curso;
import aplicacion.views.cli.AlumnoViewCLI;
import aplicacion.views.cli.AsistenciaViewCli;
import aplicacion.views.cli.UtilsCLI;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.DecimalFormat;

/**
 * Clase controladora del menú de gestión de Asistencia de la interfaz de linea de comandos.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 1.0
 */

public class AsistenciaControllerCLI {
    private final CursoData cursoData;
    private final AsistenciaViewCli asistenciaView;
    private final AlumnoViewCLI alumnoViewCLI;
    private final BufferedReader lector;

    public AsistenciaControllerCLI(BufferedReader lector) {
        this.cursoData = new CursoDatafile();
        this.asistenciaView = new AsistenciaViewCli();
        this.alumnoViewCLI = new AlumnoViewCLI();
        this.lector = lector;
    }

    /**
     * Controla el flujo de trabajo de la opción marcada por el usuario, en el menú de gestión de alumnos.
     *
     * @param opt Entero que contiene la opción marcada por el usuario
     * @throws IOException Posibles errores de entrada/salida de datos
     */

    private void opcMenuAsistencia(short opt) throws IOException {
        Map<String, Alumno> alumnosEnPorcentaje;
        int porcentaje1, porcentaje2;
        Alumno alumno;
        DecimalFormat df = new DecimalFormat("#.##");

        switch (opt) {
            case 0:
                break;
            case 9:
                UtilsCLI.mensajeDespedida();
                System.exit(0);
            case 1:
                System.out.println("Alumno con mejor Asistencia: ");
                alumno = getAlumnoMasRetiros();
                System.out.println(alumno.getNombreCompleto() + " con " + df.format(alumno.getAsistencia().obtenerAsistencia()) + "% de asistencia");
                break;
            case 2:
                UtilsCLI.imprimirSolicitar("porcentaje 1 ", "número de 1 a 100");
                porcentaje1 = Integer.parseInt(lector.readLine());
                UtilsCLI.imprimirSolicitar("porcentaje 2 ", "número de 1 a 100");
                porcentaje2 = Integer.parseInt(lector.readLine());
                alumnosEnPorcentaje = getAsistenciaEntrePorcentajes(porcentaje1, porcentaje2);
                alumnoViewCLI.mostrarTablaAlumnos(alumnosEnPorcentaje, "porcentaje dado ASISTENCIA");
                break;
            case 3:
                System.out.println("Alumno con mas retiros: ");
                alumno = getAlumnoMasRetiros();
                System.out.println(alumno.getNombreCompleto() + " con " + df.format(alumno.getAsistencia().obtenerRetiros()) + "% de retiros");
                break;
            case 4:
                UtilsCLI.imprimirSolicitar("porcentaje 1 ", "número de 1 a 100");
                porcentaje1 = Integer.parseInt(lector.readLine());
                UtilsCLI.imprimirSolicitar("porcentaje 2 ", "número de 1 a 100");
                porcentaje2 = Integer.parseInt(lector.readLine());
                alumnosEnPorcentaje = getRetirosEntrePorcentajes(porcentaje1, porcentaje2);
                alumnoViewCLI.mostrarTablaAlumnos(alumnosEnPorcentaje, "porcentaje dado RETIROS");
                break;
            default:
                UtilsCLI.mensajeErrIngresado();
                break;
        }
    }

    /**
     * Filtra los datos seleccionando el alumno con mejor asistencia
     *
     * @return Alumno
     */
    private Alumno getAlumnoMejorAsistencia(){
        Alumno mejorAsistencia = null;
        List<Curso> cursos = this.cursoData.getCursos();


        for (Curso curso: cursos) {
            for (Alumno alumno: curso.getAlumnos().values()) {
                if (mejorAsistencia == null || alumno.getAsistencia().obtenerAsistencia() > mejorAsistencia.getAsistencia().obtenerAsistencia()) {
                    mejorAsistencia = alumno;
                }
            }
        }

        return mejorAsistencia;
    }

    /**
     * Filtra los datos seleccionando los alumnos con asistencia dentro de los rangos dados por usuario
     *
     * @return HashMap con los alumnos seleccionados
     */
    private Map<String, Alumno> getAsistenciaEntrePorcentajes(int percent1, int percent2){
        List<Curso> cursos = this.cursoData.getCursos();
        Map<String, Alumno> alumnos = new HashMap<>();
        int asistenciaAlumno;

        for (Curso curso: cursos) {
            for (Alumno alumno: curso.getAlumnos().values()) {
                asistenciaAlumno = (int) Math.round((alumno.getAsistencia().obtenerAsistencia() * 100.0));
                if (asistenciaAlumno >= percent1 && asistenciaAlumno <= percent2){
                    alumnos.put(alumno.getRut(), alumno);
                }
            }
        }
        return alumnos;
    }

    /**
     * Filtra los datos seleccionando el alumno con más retiros
     *
     * @return Alumno
     */
    private Alumno getAlumnoMasRetiros(){
        Alumno masRetiros = null;
        List<Curso> cursos = this.cursoData.getCursos();

        for (Curso curso: cursos) {
            for (Alumno alumno: curso.getAlumnos().values()) {
                if (masRetiros == null || alumno.getAsistencia().obtenerRetiros() > masRetiros.getAsistencia().obtenerRetiros()) {
                    masRetiros = alumno;
                }
            }
        }

        return masRetiros;
    }

    /**
     * Filtra los datos seleccionando los alumnos con retiros dentro de los rangos dados por usuario
     *
     * @return HashMap con los alumnos seleccionados
     */
    private Map<String, Alumno> getRetirosEntrePorcentajes(int percent1, int percent2){
        List<Curso> cursos = this.cursoData.getCursos();
        Map<String, Alumno> alumnos = new HashMap<>();
        int retirosAlumno;

        for (Curso curso: cursos) {
            for (Alumno alumno: curso.getAlumnos().values()) {
                retirosAlumno = (int) Math.round((alumno.getAsistencia().obtenerRetiros() * 100.0));
                if (retirosAlumno >= percent1 && retirosAlumno <= percent2){
                    alumnos.put(alumno.getRut(), alumno);
                }
            }
        }
        return alumnos;
    }


    /**
     * Muestra el menú de gestión de Asistencia por pantalla
     *
     * @throws IOException Posibles errores de entrada/salida de datos
     */

    public void mostrarMenuAsistencia() throws IOException {
        short opt = -1;
        while (opt != 0) {
            this.asistenciaView.mostrarMenuAsistencia();
            UtilsCLI.imprimirIngresarOpcion("numérica");
            opt = Short.parseShort(this.lector.readLine());
            opcMenuAsistencia(opt);
        }
    }

}
