package aplicacion.controllers.cli;

import aplicacion.models.Alumno;
import aplicacion.models.Curso;
import aplicacion.views.cli.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Map;

/**
 * Clase controladora de los menús de la interfaz de línea de comandos.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 2.0
 */
public class MenuControllerCLI {

    private final CursoControllerCLI cursoController;
    private final AsistenciaControllerCLI asistenciaController;
    private final AlumnoControllerCLI alumnoController;

    private final BufferedReader lector;
    private final MenuCLI menuCLI;

    /**
     * Objeto controlador de menús en la interfaz de línea de comandos
     *
     * @param lector  BufferedReader lector utilizado en el main
     * @param menuCLI Instancia de menú con los origenes de datos.
     */
    public MenuControllerCLI(BufferedReader lector, MenuCLI menuCLI) {
        this.cursoController = new CursoControllerCLI(lector, menuCLI);
        this.asistenciaController = new AsistenciaControllerCLI(lector, menuCLI);
        this.alumnoController = new AlumnoControllerCLI(lector, menuCLI);
        this.menuCLI = menuCLI;
        this.lector = lector;
    }

    /**
     * Método privado que controla el flujo de trabajo en el menú principal.
     *
     * @param opt Opción leída ingresada por el usuario
     * @throws IOException Posibles errores en I/O.
     */
    private void menuPrincipal(short opt) throws IOException {
        switch (opt) {
            case 0:
                break;
            case 1:
                menu("administracion");
                break;
            case 2:
                menu("reportes");
                break;
            case 3:
                // Todo: Gráficos
                UtilsCLI.imprimirFuncionalidadNoImplementada();
                break;
            default:
                UtilsCLI.mensajeErrIngresado();
                break;
        }
    }

    /**
     * Método privado que controla el flujo de trabajo en el menú de administración.
     *
     * @param opt Opción leída ingresada por el usuario
     * @throws IOException Posibles errores en I/O.
     */
    private void menuAdministracion(short opt) throws IOException {
        switch (opt) {
            case 0:
                break;
            case 9:
                UtilsCLI.mensajeDespedida();
                System.exit(0);
            case 1:
                try {
                    menuCLI.mostrarMenu("cursos");
                } catch (IOException e) {
                    System.out.println(e);
                }
                break;
            case 2:
                try {
                    menuCLI.mostrarMenu("alumnos");
                } catch (IOException e) {
                    System.out.println(e);
                }
                break;
            case 3:
                try {
                    menuCLI.mostrarMenu("asistencia");
                } catch (IOException e) {
                    System.out.println(e);
                }
                break;
            default:
                UtilsCLI.mensajeErrIngresado();
                break;
        }
    }

    /**
     * Método privado que controla el flujo de trabajo en el menú de reportes.
     *
     * @param opt Opción leída ingresada por el usuario
     * @throws IOException Posibles errores en I/O.
     */
    private void menuReportes(short opt) throws IOException {
        short nivel;
        char paralelo;
        Curso curso;
        String rutaArchivo;
        switch (opt) {
            case 0:
                break;
            case 9:
                UtilsCLI.mensajeDespedida();
                System.exit(0);
            case 1:
                UtilsCLI.imprimirSolicitar("el nivel del alumno", "número de 1 a 12");
                nivel = Short.parseShort(this.lector.readLine());
                UtilsCLI.imprimirSolicitar("el paralelo al que pertenece el alumno", "caracter");
                paralelo = this.lector.readLine().charAt(0);
                // Todo: Agregar funcion
                System.out.println("Generar reporte del curso " + nivel + "/" + paralelo);
                rutaArchivo = this.cursoController.generarReporteListaCurso(nivel, paralelo);
                if (!rutaArchivo.equals(""))
                    System.out.println("Archivo generado: " + rutaArchivo);
                else
                    System.out.println("Ha ocurrido un error, por favor intente nuevamente.");
                break;
            case 2:
                System.out.println("Generar reporte de todos los cursos");
                rutaArchivo = this.cursoController.generarReporteTablaCursos();
                if (!rutaArchivo.equals(""))
                    System.out.println("Archivo generado: " + rutaArchivo);
                else
                    System.out.println("Ha ocurrido un error, por favor intente nuevamente.");
                break;
            default:
                UtilsCLI.mensajeErrIngresado();
                break;
        }
    }

    /**
     * Método privado que controla el flujo de trabajo en el menú de asistencia.
     *
     * @param opt Opción leída ingresada por el usuario
     * @throws IOException Posibles errores en I/O.
     */
    private void menuAsistencia(short opt) throws IOException {
        Map<String, Alumno> alumnosEnPorcentaje;
        int porcentaje1, porcentaje2;
        Alumno alumno;
        DecimalFormat df = new DecimalFormat("##.##");

        switch (opt) {
            case 0:
                break;
            case 9:
                UtilsCLI.mensajeDespedida();
                System.exit(0);
            case 1:
                System.out.println("Alumno con mejor Asistencia: ");
                alumno = this.asistenciaController.getAlumnoRequerido(1);
                System.out.println(alumno.getNombreCompleto() + " con " + df.format(alumno.getAsistencia().obtenerAsistencia()) + "% de asistencia");
                break;
            case 2:
                UtilsCLI.imprimirSolicitar("porcentaje 1 ", "número de 0 a 100");
                porcentaje1 = Integer.parseInt(this.lector.readLine());
                UtilsCLI.imprimirSolicitar("porcentaje 2 ", "número de 0 a 100");
                porcentaje2 = Integer.parseInt(this.lector.readLine());
                alumnosEnPorcentaje = this.asistenciaController.getEntrePorcentajes(porcentaje1, porcentaje2, 1);
                AsistenciaViewCli.mostrarTablaAlumnosAsistencia(alumnosEnPorcentaje, "porcentaje dado ASISTENCIA");
                break;
            case 3:
                System.out.println("Alumno con mas retiros: ");
                alumno = this.asistenciaController.getAlumnoRequerido(0);
                System.out.println(alumno.getNombreCompleto() + " con " + df.format(alumno.getAsistencia().obtenerRetiros()) + "% de retiros");
                break;
            case 4:
                UtilsCLI.imprimirSolicitar("porcentaje 1 ", "número de 0 a 100");
                porcentaje1 = Integer.parseInt(this.lector.readLine());
                UtilsCLI.imprimirSolicitar("porcentaje 2 ", "número de 0 a 100");
                porcentaje2 = Integer.parseInt(this.lector.readLine());
                alumnosEnPorcentaje = this.asistenciaController.getEntrePorcentajes(porcentaje1, porcentaje2, 0);
                AsistenciaViewCli.mostrarTablaAlumnosAsistencia(alumnosEnPorcentaje, "porcentaje dado RETIROS");

                break;
            default:
                UtilsCLI.mensajeErrIngresado();
                break;
        }
    }

    /**
     * Método privado que controla el flujo de trabajo en el menú de alumnos.
     *
     * @param opt Opción leída ingresada por el usuario
     * @throws IOException Posibles errores en I/O.
     */
    private void menuAlumnos(short opt) throws IOException {
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
                this.menuCLI.getAlumnoData().insertAlumno(this.alumnoController.obtenerDatosAlumno(nivel, paralelo));
                AlumnoViewCLI.mostrarTablaAlumnos(this.menuCLI.getAlumnoData().getAlumnos(nivel, paralelo),
                        Curso.cursoToString(nivel, paralelo));
                break;
            case 2:
                UtilsCLI.imprimirSolicitar("el nivel de los alumnos", "número de 1 a 12");
                nivel = Integer.parseInt(this.lector.readLine());
                UtilsCLI.imprimirSolicitar("el paralelo al que pertenecen los alumnos", "caracter");
                paralelo = this.lector.readLine().charAt(0);
                AlumnoViewCLI.mostrarTablaAlumnos(this.menuCLI.getAlumnoData().getAlumnos(nivel, paralelo),
                        Curso.cursoToString(nivel, paralelo));
                break;
            case 3:
                UtilsCLI.imprimirSolicitar("el nivel de los alumnos", "número de 1 a 12");
                nivel = Integer.parseInt(this.lector.readLine());
                UtilsCLI.imprimirSolicitar("el paralelo al que pertenecen los alumnos", "caracter");
                paralelo = this.lector.readLine().charAt(0);
                UtilsCLI.imprimirSolicitar("el RUT del alumno", "sin puntos, con guión");
                rut = this.lector.readLine();
                alumno = this.menuCLI.getAlumnoData().getAlumno(rut);
                if (alumno == null) {
                    UtilsCLI.mensajeErrIngresado();
                    break;
                }
                System.out.println("Editando alumno:\n  Nombre: " + alumno.getNombreCompleto() + "\n  RUT: " + rut);
                alumnoEditado = this.alumnoController.obtenerDatosAlumno(alumno, nivel, paralelo);
                if (this.menuCLI.getAlumnoData().updateAlumno(alumnoEditado))
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
                alumno = this.menuCLI.getAlumnoData().getAlumno(rut);
                if (alumno == null) {
                    UtilsCLI.mensajeErrIngresado();
                    break;
                }
                System.out.println("Eliminando alumno:\n  Nombre: " + alumno.getNombreCompleto() + "\n  RUT: " + rut);
                if (this.menuCLI.getAlumnoData().deleteAlumno(alumno))
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
     * Método privado que controla el flujo de trabajo en el menú de cursos.
     *
     * @param opt Opción leída ingresada por el usuario
     * @throws IOException Posibles errores en I/O.
     */
    private void menuCursos(short opt) throws IOException {
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
                this.menuCLI.getCursoData().insertCurso(this.cursoController.obtenerDatosCurso());
                CursoViewCLI.mostrarTablaCursos(this.menuCLI.getCursoData().getCursos());
                break;
            case 2:
                CursoViewCLI.mostrarTablaCursos(this.menuCLI.getCursoData().getCursos());
                break;
            case 3:
                UtilsCLI.imprimirSolicitar("el nivel de los alumnos", "número de 1 a 12");
                nivel = Short.parseShort(this.lector.readLine());
                UtilsCLI.imprimirSolicitar("el paralelo al que pertenecen los alumnos", "caracter");
                paralelo = this.lector.readLine().charAt(0);
                curso = this.menuCLI.getCursoData().getCurso(nivel, paralelo);
                System.out.println("Cambiando profesor jefe del " + curso.cursoToString());
                cursoActualizado = this.cursoController.obtenerDatosCurso(curso);
                if (this.menuCLI.getCursoData().updateCurso(cursoActualizado)) {
                    this.menuCLI.getProfesorData().insertProfesor(cursoActualizado.getProfesorJefe());
                    this.menuCLI.getProfesorData().deleteProfesor(curso.getProfesorJefe());
                    System.out.println("El curso ha sido actualizado exitosamente");
                } else
                    System.out.println("Ha ocurrido un error, por favor intente nuevamente.");
                break;
            case 4:
                UtilsCLI.imprimirSolicitar("el nivel del curso", "número de 1 a 12");
                nivel = Short.parseShort(this.lector.readLine());
                UtilsCLI.imprimirSolicitar("el paralelo del curso", "caracter");
                paralelo = this.lector.readLine().charAt(0);
                curso = this.menuCLI.getCursoData().getCurso(nivel, paralelo);
                System.out.println("Eliminando curso " + curso.cursoToString());
                if (this.menuCLI.getCursoData().deleteCurso(curso))
                    System.out.println("El curso ha sido eliminado exitosamente");
                else
                    System.out.println("Ha ocurrido un error, por favor intente nuevamente.");
                break;
            default:
                UtilsCLI.mensajeErrIngresado();
                break;
        }
    }

    /**
     * Método que controla el output de los menús.
     *
     * @param menu Tipo de menú que se desea mostrar por pantalla
     */
    public void menu(String menu) {
        switch (menu) {
            case "principal":
                try {
                    this.menuCLI.mostrarMenu("principal");
                } catch (IOException e) {
                    System.out.println(e);
                }
                break;
            case "administracion":
                try {
                    this.menuCLI.mostrarMenu("administracion");
                } catch (IOException e) {
                    System.out.println(e);
                }
                break;
            case "reportes":
                try {
                    this.menuCLI.mostrarMenu("reportes");
                } catch (IOException e) {
                    System.out.println(e);
                }
                break;
            case "asistencia":
                try {
                    this.menuCLI.mostrarMenu("asistencia");
                } catch (IOException e) {
                    System.out.println(e);
                }
                break;
            case "alumnos":
                try {
                    this.menuCLI.mostrarMenu("alumnos");
                } catch (IOException e) {
                    System.out.println(e);
                }
                break;
            case "cursos":
                try {
                    this.menuCLI.mostrarMenu("cursos");
                } catch (IOException e) {
                    System.out.println(e);
                }
                break;
        }
    }

    /**
     * Método que controla el flujo de trabajo de los menús.
     *
     * @param menu Tipo de menú del que se capturó la opción
     * @param opt  Opción leída ingresada por el usuario
     */
    public void menuOpc(String menu, short opt) {
        switch (menu) {
            case "principal":
                try {
                    menuPrincipal(opt);
                } catch (IOException e) {
                    System.out.println(e);
                }
                break;
            case "administracion":
                try {
                    menuAdministracion(opt);
                } catch (IOException e) {
                    System.out.println(e);
                }
                break;
            case "reportes":
                try {
                    menuReportes(opt);
                } catch (IOException e) {
                    System.out.println(e);
                }
                break;
            case "asistencia":
                try {
                    menuAsistencia(opt);
                } catch (IOException e) {
                    System.out.println(e);
                }
                break;
            case "alumnos":
                try {
                    menuAlumnos(opt);
                } catch (IOException e) {
                    System.out.println(e);
                }
                break;
            case "cursos":
                try {
                    menuCursos(opt);
                } catch (IOException e) {
                    System.out.println(e);
                }
                break;
        }
    }

}
