package aplicacion.data;

import aplicacion.models.Curso;
import aplicacion.models.Profesor;

import java.util.List;

/**
 * Interfaz que permite interactuar con los datos de Profesor.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 1.0
 */
public interface ProfesorData {
    /**
     * Obtiene todos los profesores almacenados
     *
     * @return ArrayList de profesores
     */
    List<Profesor> getProfesores();

    /**
     * Obtiene un profesor según su RUT
     *
     * @param rut RUT del profesor
     * @return Profesor
     */
    Profesor getProfesor(String rut);

    /**
     * Obtiene el profesor jefe de un curso en especifico
     *
     * @param curso Curso del que se desea obtener su profesor jefe
     * @return Profesor jefe del curso
     */
    Profesor getProfesorJefe(Curso curso);

    /**
     * Agrega un profesor
     *
     * @param profesor Profesor a agregar
     * @return Valor de verdad sobre el éxito o fracaso de la operación
     */
    boolean insertProfesor(Profesor profesor);

    /**
     * Actualiza un profesor
     *
     * @param profesor Profesor a actualizar
     * @return Valor de verdad sobre el éxito o fracaso de la operación
     */
    boolean updateProfesor(Profesor profesor);

    /**
     * Elimina un profesor
     *
     * @param profesor Profesor a eliminar
     * @return Valor de verdad sobre el éxito o fracaso de la operación
     */
    boolean deleteProfesor(Profesor profesor);
}