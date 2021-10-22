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
        switch (opt) {
            case 0:
                break;
            case 9:
                UtilsCLI.mensajeDespedida();
                System.exit(0);
            case 1:
                System.out.println("Alumno con mejor Asistencia: ");
                System.out.println(getAlumnoMejorAsistencia().getNombreCompleto());
                break;
            case 2:
                UtilsCLI.imprimirSolicitar("porcentaje 1 ", "número de 1 a 100");
                porcentaje1 = Integer.parseInt(lector.readLine());
                UtilsCLI.imprimirSolicitar("porcentaje 2 ", "número de 1 a 100");
                porcentaje2 = Integer.parseInt(lector.readLine());
                alumnosEnPorcentaje = getAsistenciaEntrePorcentajes(porcentaje1, porcentaje2);
                alumnoViewCLI.mostrarTablaAlumnos(alumnosEnPorcentaje, "porcentaje dado");
                break;
            case 3:

                break;
            case 4:
                break;
            default:
                UtilsCLI.mensajeErrIngresado();
                break;
        }
    }

    /**
     * Filtra los datos seleccionando el alumno con mejor aistencia
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

    private Alumno getAlumnoMasRetiros(){
        Alumno masRetiros = null;
        List<Curso> cursos = this.cursoData.getCursos();

        for (Curso curso: cursos) {
            for (Alumno alumno: curso.getAlumnos().values()) {
                if (masRetiros == null || alumno.getAsistencia().obtenerAsistencia() > masRetiros.getAsistencia().obtenerAsistencia()) {
                    masRetiros = alumno;
                }
            }
        }

        return masRetiros;
    }

    private Map<String, Alumno> getRetirosEntrePorcentajes(int percent1, int percent2){
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
