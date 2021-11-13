package aplicacion.models;

/**
 * Clase que define un identificador para los cursos.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 3.0
 */
public class IDCurso {
    public short nivel;
    public char paralelo;

    /**
     * Permite generar un objeto de tipo IDCurso
     *
     * @param nivel Nivel del curso
     * @param paralelo Paralelo del curso
     */
    public IDCurso(short nivel, char paralelo) {
        this.nivel = nivel;
        this.paralelo = paralelo;
    }

}