package views.cli;

import models.Curso;
import models.Profesor;

import java.util.List;

public class CursoView {
    public static void mostrarCursos(List<Curso> cursos) {
        Profesor profesor;
        System.out.println(String.format("\n%55s\n", "Listado de cursos"));
        System.out.println(String.format("%s %7s %2s %6s %2s %24s %13s %30s %3s", "|", "Curso", "|", "Nivel", "|",
                "Profesor jefe", "|", "Correo electronico profesor", "|"));
        System.out.println(
                "|----------------------------------------------------------------------------------------------|");
        for (Curso curso: cursos) {
            profesor = curso.getProfesorJefe();
            System.out.println(String.format("%s %6s %3s %4d %4s %-35s %2s %-31s %2s", "|", curso.toShortStr(), "|",
                    curso.getNivel(), "|", profesor.getNombreCompleto(), "|", profesor.getEmail(), "|"));
        }
        System.out.println(
                "|----------------------------------------------------------------------------------------------|\n");
    }
}
