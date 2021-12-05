package aplicacion.controllers.cli;

import aplicacion.views.cli.MenuCLI;
import aplicacion.views.cli.UtilsCLI;

import java.io.IOException;

/**
 * Clase controladora de los menús de la interfaz de línea de comandos.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 4.0
 */
public class MenuControllerCLI {

    private final CursoControllerCLI cursoController;
    private final AsistenciaControllerCLI asistenciaController;
    private final AlumnoControllerCLI alumnoController;
    private final ReportesControllerCLI reportesController;

    private final MenuCLI menuCLI;

    /**
     * Objeto controlador de menús en la interfaz de línea de comandos
     *
     * @param menuCLI Instancia de menú con los origenes de datos.
     */
    public MenuControllerCLI(MenuCLI menuCLI) {
        this.cursoController = new CursoControllerCLI(menuCLI);
        this.asistenciaController = new AsistenciaControllerCLI(menuCLI);
        this.alumnoController = new AlumnoControllerCLI(menuCLI);
        this.reportesController = new ReportesControllerCLI(menuCLI);
        this.menuCLI = menuCLI;
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
                UtilsCLI.imprimirEsperaEnter();
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
        switch (opt) {
            case 0:
                break;
            case 9:
                UtilsCLI.mensajeDespedida();
                System.exit(0);
            case 1:
                this.reportesController.generarListaCurso(this.cursoController);
                UtilsCLI.imprimirEsperaEnter();
                break;
            case 2:
                this.reportesController.generarTablaCursos(this.cursoController);
                UtilsCLI.imprimirEsperaEnter();
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
        switch (opt) {
            case 0:
                break;
            case 9:
                UtilsCLI.mensajeDespedida();
                System.exit(0);
            case 1:
                this.asistenciaController.alumnoMejorAsistencia();
                UtilsCLI.imprimirEsperaEnter();
                break;
            case 2:
                // Todo: Registrar asistencia
                UtilsCLI.imprimirFuncionalidadNoImplementada();
                UtilsCLI.imprimirEsperaEnter();
                break;
            case 3:
                this.asistenciaController.alumnosEntrePorcentajesAsistencia();
                UtilsCLI.imprimirEsperaEnter();
                break;
            case 4:
                this.asistenciaController.alumnoConMasRetiros();
                UtilsCLI.imprimirEsperaEnter();
                break;
            case 5:
                this.asistenciaController.alumnoEntrePorcentajesRetiros();
                UtilsCLI.imprimirEsperaEnter();
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
        switch (opt) {
            case 0:
                break;
            case 9:
                UtilsCLI.mensajeDespedida();
                System.exit(0);
            case 1:
                this.alumnoController.agregarAlumno(this.cursoController);
                UtilsCLI.imprimirEsperaEnter();
                break;
            case 2:
                this.alumnoController.verAlumno();
                UtilsCLI.imprimirEsperaEnter();
                break;
            case 3:
                this.alumnoController.verAlumnos(this.cursoController);
                UtilsCLI.imprimirEsperaEnter();
                break;
            case 4:
                this.alumnoController.editarAlumno();
                UtilsCLI.imprimirEsperaEnter();
                break;
            case 5:
                this.alumnoController.eliminarAlumno();
                UtilsCLI.imprimirEsperaEnter();
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
        switch (opt) {
            case 0:
                break;
            case 9:
                UtilsCLI.mensajeDespedida();
                System.exit(0);
            case 1:
                this.cursoController.agregarCurso();
                UtilsCLI.imprimirEsperaEnter();
                break;
            case 2:
                this.cursoController.verDatosCurso();
                UtilsCLI.imprimirEsperaEnter();
                break;
            case 3:
                this.cursoController.mostrarCursos();
                UtilsCLI.imprimirEsperaEnter();
                break;
            case 4:
                this.cursoController.asignarNuevoProfesorJefeACurso();
                UtilsCLI.imprimirEsperaEnter();
                break;
            case 5:
                this.cursoController.eliminarCurso();
                UtilsCLI.imprimirEsperaEnter();
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
