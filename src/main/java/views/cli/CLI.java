package views.cli;

import data.CursoData;
import data.datafile.CursoDatafile;
import models.Curso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class CLI {
    private CursoData cursoData;
    private List<Curso> cursos;
    private BufferedReader lector;

    public CLI() {
        this.cursoData = new CursoDatafile();
        this.cursos = cursoData.getCursos();
        this.lector = new BufferedReader(new InputStreamReader(System.in));
    }

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

    public void opcionErronea() {
        System.out.println("\nOPCION ERRONEA. Por favor, intente nuevamente");
    }

    public void verCursos() {
        CursoView.mostrarCursos(this.cursos);
    }

    public void verAlumnos() throws IOException {
        short nivel;
        char letra;
        System.out.print("\nIngrese el nivel (numero de 1 a 12): ");
        nivel = Short.parseShort(lector.readLine());
        System.out.print("Ingrese la letra (identifica paralelo): ");
        letra = lector.readLine().toUpperCase().charAt(0);
        AlumnoView.mostrarAlumnos(this.cursoData.getCurso(nivel, letra).getAlumnos());
    }

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

    public static void main(String[] args) throws IOException {
        System.out.println("Cargando datos...");
        CLI cli = new CLI();
        System.out.println("Datos cargados con exito\n");
        cli.menuPrincipal();
        System.out.println("Saliendo de la aplicacion...");
    }
}
