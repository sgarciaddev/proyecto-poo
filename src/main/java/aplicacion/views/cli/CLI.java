package aplicacion.views.cli;

import aplicacion.controllers.cli.AlumnoControllerCLI;
import aplicacion.controllers.cli.CursoControllerCLI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
            case 3:
                System.out.println("\nEsta funcionalidad aún no ha sido implementada\n");
                break;
            default:
                UtilsCLI.mensajeErrOpc();
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
                UtilsCLI.mensajeErrOpc();
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
        UtilsCLI.mensajeBienvenida();
        CLI cli = new CLI();
        cli.mostrarMenuPrincipal();
        UtilsCLI.mensajeDespedida();
    }

}
