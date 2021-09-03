package data;

import models.Curso;
import models.Profesor;

import java.util.List;

/**
 * Interfaz que permite interactuar con los datos de Profesor.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 1.0
 */
public interface ProfesorData {
    public List<Profesor> getProfesores();
    public Profesor getProfesor(String rut);
    public Profesor getProfesorJefe(Curso curso);
    public boolean insertProfesor(Profesor profesor);
    public boolean updateProfesor(Profesor profesor);
    public boolean deleteProfesor(Profesor profesor);
}