package aplicacion.views.cli;

import aplicacion.controllers.cli.AlumnoControllerCLI;
import aplicacion.controllers.cli.CursoControllerCLI;
import aplicacion.models.Curso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Aplicación principal de la interfaz de consola de comandos (CLI).
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 1.0
 */
public class CLI {

    private final BufferedReader lector;
    private final CursoControllerCLI cursoController;
    private final AlumnoControllerCLI alumnoController;

    /**
     * Genera la instancia de la aplicación CLI
     */
    public CLI() {
        this.lector = new BufferedReader(new InputStreamReader(System.in));
        this.cursoController = new CursoControllerCLI(this.lector);
        this.alumnoController = new AlumnoControllerCLI(this.lector);
    }

    /**
     * Muestra el menú principal por pantalla
     *
     * @throws IOException Posible errores de entrada/salida de datos
     */
    private void mostrarMenuPrincipal() throws IOException {
        short opt = -1;
        while (opt != 0) {
            UtilsCLI.imprimirMenu(UtilsCLI.opcMenuPrincipal, "Menú principal");
            UtilsCLI.imprimirIngresarOpcion("numérica");
            opt = Short.parseShort(this.lector.readLine());
            opcMenuPrincipal(opt);
        }
    }

    /**
     * Muestra el menú de generación de reportes por pantalla
     *
     * @throws IOException Posible errores de entrada/salida de datos
     */
    private void mostrarMenuReportes() throws IOException {
        short opt = -1;
        while (opt != 0) {
            UtilsCLI.imprimirMenu(UtilsCLI.opcMenuReportes, "Generar reportes");
            UtilsCLI.imprimirIngresarOpcion("numérica");
            opt = Short.parseShort(this.lector.readLine());
            opcMenuReportes(opt);
        }
    }

    /**
     * Muestra el menú de administración del colegio por pantalla
     *
     * @throws IOException Posible errores de entrada/salida de datos
     */
    private void mostrarMenuAdmin() throws IOException {
        short opt = -1;
        while (opt != 0) {
            UtilsCLI.imprimirMenu(UtilsCLI.opcMenuAdmin, "Administración del colegio");
            UtilsCLI.imprimirIngresarOpcion("numérica");
            opt = Short.parseShort(this.lector.readLine());
            opcMenuAdmin(opt);
        }
    }

    /**
     * Controla el flujo de trabajo de la opción marcada por el usuario, en el menú principal.
     *
     * @param opt Entero que contiene la opción marcada por el usuario
     * @throws IOException Posibles errores de entrada/salida de datos
     */
    private void opcMenuPrincipal(short opt) throws IOException {
        switch (opt) {
            case 0:
                break;
            case 1:
                mostrarMenuAdmin();
                break;
            case 2:
                mostrarMenuReportes();
                break;
            case 3:
                System.out.println("\nEsta funcionalidad aún no ha sido implementada\n");
                break;
            default:
                UtilsCLI.mensajeErrIngresado();
                break;
        }
    }

    /**
     * Controla el flujo de trabajo de la opción marcada por el usuario, en el menú de administración de colegio.
     *
     * @param opt Entero que contiene la opción marcada por el usuario
     * @throws IOException Posibles errores de entrada/salida de datos
     */
    private void opcMenuAdmin(short opt) throws IOException {
        switch (opt) {
            case 0:
                break;
            case 9:
                UtilsCLI.mensajeDespedida();
                System.exit(0);
            case 1:
                this.cursoController.mostrarMenuCursos();
                break;
            case 2:
                this.alumnoController.mostrarMenuAlumnos();
                break;
            case 3:
                System.out.println("\nEsta funcionalidad aún no ha sido implementada\n");
                break;
            default:
                UtilsCLI.mensajeErrIngresado();
                break;
        }
    }

    /**
     * Controla el flujo de trabajo de la opción marcada por el usuario, en el menú de generación de reportes del
     * colegio.
     *
     * @param opt Entero que contiene la opción marcada por el usuario
     * @throws IOException Posibles errores de entrada/salida de datos
     */
    private void opcMenuReportes(short opt) throws IOException {
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
     * Método main. Contiene el programa principal, y la instancia del objeto CLI
     *
     * @param args Argumentos del main
     * @throws IOException Posibles errores de entrada/salida de datos
     */
    public static void main(String[] args) throws IOException {
        Dotenv dotenv = Dotenv.load();
        System.out.println(dotenv.get("TESTING"));
        UtilsCLI.mensajeBienvenida();
        CLI cli = new CLI();
        cli.mostrarMenuPrincipal();
        UtilsCLI.mensajeDespedida();
    }

}
