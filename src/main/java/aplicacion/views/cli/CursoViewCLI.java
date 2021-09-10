package aplicacion.views.cli;

import aplicacion.models.Curso;

import java.util.List;

/**
 * Clase que controla los aspectos visuales de los Cursos, en la interfaz de consola de comandos.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 1.0
 */
public class CursoViewCLI {

    /**
     * Muestra la tabla de cursos por pantalla
     *
     * @param cursos ArrayList con los Cursos
     */
    public void mostrarTablaCursos(List<Curso> cursos) {
        int i = 0;
        Object[][] data = new Object[cursos.size()][UtilsCLI.headersCursos.length];
        for (Curso curso: cursos) {
            data[i][0] = curso.toShortStr();
            data[i][1] = curso.getNivel();
            data[i][2] = curso.getLetra();
            data[i][3] = curso.getProfesorJefe().getNombreCompleto();
            data[i][4] = curso.getProfesorJefe().getEmail();
            data[i][5] = curso.getProfesorJefe().getTelefono();
            i++;
        }
        UtilsCLI.imprimirTabla(data, UtilsCLI.headersCursos, "Cursos");
    }

    /**
     * Despliega el menú de gestión de cursos por pantalla.
     */
    public void mostrarMenuCursos() {
        UtilsCLI.imprimirMenu(UtilsCLI.opcMenuCursos, "Gestionar cursos");
    }
}
