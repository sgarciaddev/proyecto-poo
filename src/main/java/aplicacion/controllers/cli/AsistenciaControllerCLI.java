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
import java.text.DecimalFormat;
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
                alumno = getAlumnoRequerido(1);
                System.out.println(alumno.getNombreCompleto() + " con " + df.format(alumno.getAsistencia().obtenerAsistencia()) + "% de asistencia");
                break;
            case 2:
                UtilsCLI.imprimirSolicitar("porcentaje 1 ", "número de 1 a 100");
                porcentaje1 = Integer.parseInt(lector.readLine());
                UtilsCLI.imprimirSolicitar("porcentaje 2 ", "número de 1 a 100");
                porcentaje2 = Integer.parseInt(lector.readLine());
                alumnosEnPorcentaje = getEntrePorcentajes(porcentaje1, porcentaje2, 1);
                alumnoViewCLI.mostrarTablaAlumnos(alumnosEnPorcentaje, "porcentaje dado ASISTENCIA");
                break;
            case 3:
                System.out.println("Alumno con mas retiros: ");
                alumno = getAlumnoRequerido(0);
                System.out.println(alumno.getNombreCompleto() + " con " + df.format(alumno.getAsistencia().obtenerRetiros()) + "% de retiros");
                break;
            case 4:
                UtilsCLI.imprimirSolicitar("porcentaje 1 ", "número de 1 a 100");
                porcentaje1 = Integer.parseInt(lector.readLine());
                UtilsCLI.imprimirSolicitar("porcentaje 2 ", "número de 1 a 100");
                porcentaje2 = Integer.parseInt(lector.readLine());
                alumnosEnPorcentaje = getEntrePorcentajes(porcentaje1, porcentaje2, 0);
                alumnoViewCLI.mostrarTablaAlumnos(alumnosEnPorcentaje, "porcentaje dado RETIROS");
                break;
            default:
                UtilsCLI.mensajeErrIngresado();
                break;
        }
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
