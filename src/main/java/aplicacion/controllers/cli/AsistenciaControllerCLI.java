package aplicacion.controllers.cli;

import aplicacion.models.Alumno;
import aplicacion.models.Curso;
import aplicacion.views.cli.MenuCLI;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.DecimalFormat;
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
     * Selecciona el alumno con mejor asistencia o más retiros dependiendo del caso
     *
     * @param value Valor por defecto que designa la opción del dato a retornar; 1 si es asistencia, 2 si es retiros
     * @return Alumno
     */
    private Alumno getAlumnoRequerido(int value){
        Alumno alumnoReq = null;
        List<Curso> cursos = this.cursoData.getCursos();

        for (Curso curso: cursos) {
            for (Alumno alumno: curso.getAlumnos().values()) {
                if (value == 1 && (alumnoReq == null || alumno.getAsistencia().obtenerAsistencia() > alumnoReq.getAsistencia().obtenerAsistencia())) {
                    alumnoReq = alumno;
                }
                if (value == 0 && (alumnoReq == null || alumno.getAsistencia().obtenerRetiros() > alumnoReq.getAsistencia().obtenerRetiros()))
                    alumnoReq = alumno;
            }
        }
        return alumnoReq;
    }

    /**
     * Selecciona los alumnos con asistencia o retiros dentro de los rangos dados por usuario
     *
     * @param percent1 Primer porcentaje del rango
     * @param percent2 Segundo porcentaje del rango
     * @param value Valor por defecto que designa la opción del dato a retornar; 1 si es asistencia, 2 si es retiros
     * @return HashMap con los alumnos seleccionados
     */
    private Map<String, Alumno> getEntrePorcentajes(int percent1, int percent2, int value){
        List<Curso> cursos = this.cursoData.getCursos();
        Map<String, Alumno> alumnos = new HashMap<>();
        int alumnoReq;

        for (Curso curso: cursos) {
            for (Alumno alumno: curso.getAlumnos().values()) {
                if (value == 1){
                    alumnoReq = (int) Math.round((alumno.getAsistencia().obtenerAsistencia() * 100.0));
                    if (alumnoReq >= percent1 && alumnoReq <= percent2)
                        alumnos.put(alumno.getRut(), alumno);
                }
                if (value == 0){
                    alumnoReq = (int) Math.round((alumno.getAsistencia().obtenerRetiros() * 100.0));
                    if (alumnoReq >= percent1 && alumnoReq <= percent2)
                        alumnos.put(alumno.getRut(), alumno);
                }
            }
        }
        return alumnos;
    }

}
