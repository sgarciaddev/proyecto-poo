package aplicacion.controllers.cli;

import aplicacion.models.Alumno;
import aplicacion.models.Curso;
import aplicacion.views.cli.MenuCLI;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase controladora del menú de gestión de Asistencia de la interfaz de linea de comandos.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 2.0
 */

public class AsistenciaControllerCLI {

    private final BufferedReader lector;
    private final MenuCLI menuCLI;

    /**
     * Objeto controlador de Asistencia en la interfaz de línea de comandos
     *
     * @param lector  Instancia de lector para lectura de datos ingresados por teclado.
     * @param menuCLI Instancia del origen de datos de alumnos.
     */
    public AsistenciaControllerCLI(BufferedReader lector, MenuCLI menuCLI) {
        this.menuCLI = menuCLI;
        this.lector = lector;
    }

    /**
     * Filtra los datos seleccionando el alumno con mejor aistencia
     *
     * @return Alumno
     */

    public Alumno getAlumnoMejorAsistencia() {
        Alumno mejorAsistencia = null;
        List<Curso> cursos = this.menuCLI.getCursoData().getCursos();

        for (Curso curso : cursos) {
            for (Alumno alumno : curso.getAlumnos().values()) {
                if (mejorAsistencia == null || alumno.getAsistencia().obtenerAsistencia() > mejorAsistencia.getAsistencia().obtenerAsistencia()) {
                    mejorAsistencia = alumno;
                }
            }
        }

        return mejorAsistencia;
    }

    public Map<String, Alumno> getAsistenciaEntrePorcentajes(int percent1, int percent2) {
        List<Curso> cursos = this.menuCLI.getCursoData().getCursos();
        Map<String, Alumno> alumnos = new HashMap<>();
        int asistenciaAlumno;

        for (Curso curso : cursos) {
            for (Alumno alumno : curso.getAlumnos().values()) {
                asistenciaAlumno = (int) Math.round((alumno.getAsistencia().obtenerAsistencia() * 100.0));
                if (asistenciaAlumno >= percent1 && asistenciaAlumno <= percent2) {
                    alumnos.put(alumno.getRut(), alumno);
                }
            }
        }
        return alumnos;
    }

    public Alumno getAlumnoMasRetiros() {
        Alumno masRetiros = null;
        List<Curso> cursos = this.menuCLI.getCursoData().getCursos();

        for (Curso curso : cursos) {
            for (Alumno alumno : curso.getAlumnos().values()) {
                if (masRetiros == null || alumno.getAsistencia().obtenerAsistencia() > masRetiros.getAsistencia().obtenerAsistencia()) {
                    masRetiros = alumno;
                }
            }
        }

        return masRetiros;
    }

    private Map<String, Alumno> getRetirosEntrePorcentajes(int percent1, int percent2){
        List<Curso> cursos = this.menuCLI.getCursoData().getCursos();
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

}
