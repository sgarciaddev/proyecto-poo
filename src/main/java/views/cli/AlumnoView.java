package views.cli;

import models.Alumno;

import java.util.Map;

/**
 * Clase que contiene los aspectos visuales de los objetos Alumno, de la aplicación de consola.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 1.0
 */
public class AlumnoView {
    /**
     * Muestra un alumno por pantalla
     *
     * @param alumno Alumno a mostrar
     */
    public static void mostrarAlumno(Alumno alumno) {
        System.out.println("\nVer alumno:\n");
        System.out.println("   - Nombre: " + alumno.getNombreCompleto());
        System.out.println("   - RUT: " + alumno.getRut());
        System.out.println();
    }

    /**
     * Muestra una tabla con alumnos
     *
     * @param alumnos HashMap con los alumnos a ser mostrados
     */
    public static void mostrarAlumnos(Map<String, Alumno> alumnos) {
        short i = 1;
        System.out.println(String.format("\n%40s\n", "Listado de alumnos"));
        System.out.println(String.format("%s %3s %1s %6s %5s %30s %14s", "|", "No.", "|", "RUT", "|", "Nombre completo", "|"));
        System.out.println("|----------------------------------------------------------------|");
        for (Alumno alumno : alumnos.values()) {
            System.out.println(String.format("%s %2d %2s %-10s %1s %-43s %1s", "|", i, "|", alumno.getRut(), "|",
                    alumno.getNombreCompleto(), "|"));
            i++;
        }
        System.out.println("|----------------------------------------------------------------|\n");
    }

    /**
     * Muestra un apoderado del alumno correspondiente.
     *
     * @param alumno Alumno del que se desea mostrar los datos de su apoderado
     */
    public static void mostrarApoderado(Alumno alumno) {
        System.out.println("\nALUMNO: " + alumno.getNombreCompleto() + " (" + alumno.getRut() + ")\n");
        System.out.println("Ver apoderado");
        System.out.println("  - Nombre: " + alumno.getApoderado().getNombreCompleto());
        System.out.println("  - RUT: " + alumno.getApoderado().getRut());
        System.out.println("  - Telefono: " + alumno.getApoderado().getTelefono());
        System.out.println("  - Email: " + alumno.getApoderado().getEmail());
        System.out.println();
    }

}
