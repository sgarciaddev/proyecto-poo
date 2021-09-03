package data;

import models.Curso;

import java.util.List;

/**
 * Interfaz que permite interactuar con los datos de los cursos.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 1.0
 */
public interface CursoData {
    public List<Curso> getCursos();
    public List<Curso> getCursos(short nivel);
    public Curso getCurso(short nivel, char letra);
    public boolean insertCurso(Curso curso);
    public boolean updateCurso(Curso curso);
    public boolean deleteCurso(Curso curso);
}