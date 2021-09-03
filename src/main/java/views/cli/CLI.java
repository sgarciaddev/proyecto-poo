package views.cli;

import data.CursoData;
import data.datafile.CursoDatafile;
import models.Curso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Clase que contiene los aspectos visuales de la aplicación de consola.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 1.0
 */
public class CLI {
    private CursoData cursoData;
    private List<Curso> cursos;
    private BufferedReader lector;

    /**
     * Genera un objeto de tipo CLI, que contiene la aplicación de consola.
     */
    public CLI() {
        this.cursoData = new CursoDatafile();
        this.cursos = cursoData.getCursos();
        this.lector = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Método que se encarga de obtener la opción numérica que ingrese el usuario.
     *
     * @return Opcion numérica
     * @throws IOException Posible error al leer datos del usuario
     */
    public short obtenerOpcionUsuario() throws IOException {
        short opt;
        System.out.print("\nIngrese opcion numerica: ");
        try {
            opt = Short.parseShort(lector.readLine());
        } catch (NumberFormatException ex) {
            opt = -1;
            ex.printStackTrace(System.out);
        }
        return opt;
    }

    /**
     * Muestra un error al ingresar una opción no soportada por el programa
     */
    public void opcionErronea() {
        System.out.println("\nOPCION ERRONEA. Por favor, intente nuevamente");
    }

    /**
     * Muestra los cursos almacenados en los datos
     */
    public void verCursos() {
        CursoView.mostrarCursos(this.cursos);
    }

    /**
     * Muestra los alumnos de un curso especifico
     *
     * @throws IOException Posible error al leer datos del usuario
     */
    public void verAlumnos() throws IOException {
        short nivel;
        char letra;
        System.out.print("\nIngrese el nivel (numero de 1 a 12): ");
        nivel = Short.parseShort(lector.readLine());
        System.out.print("Ingrese la letra (identifica paralelo): ");
        letra = lector.readLine().toUpperCase().charAt(0);
        AlumnoView.mostrarAlumnos(this.cursoData.getCurso(nivel, letra).getAlumnos());
    }

    /**
     * Muestra el menú principal de la aplicación
     *
     * @throws IOException Posible error al leer datos del usuario
     */
    public void menuPrincipal() throws IOException {
        short opt;
        do {
            System.out.println("Menu principal");
            System.out.println("  -> (1) Ver cursos");
            System.out.println("  -> (2) Ver alumnos");
            System.out.println("  -> (0) Salir de la aplicacion");
            opt = obtenerOpcionUsuario();
            switch (opt) {
                case 0:
                    break;
                case 1:
                    verCursos();
                    break;
                case 2:
                    verAlumnos();
                    break;
                default:
                    opcionErronea();
                    break;
            }
        } while (opt != 0);
    }

    /**
     * Programa principal. Inicia la aplicación de consola
     *
     * @param args Argumentos de ejecución (método main)
     * @throws IOException Posible error al leer datos del usuario
     */
    public static void main(String[] args) throws IOException {
        System.out.println("Cargando datos...");
        CLI cli = new CLI();
        System.out.println("Datos cargados con exito\n");
        cli.menuPrincipal();
        System.out.println("Saliendo de la aplicacion...");
    }
}
