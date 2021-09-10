package aplicacion.data;

import aplicacion.models.Alumno;

import java.util.Map;

/**
 * Interfaz que permite interactuar con los datos de Alumnos.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 1.0
 */
public interface AlumnoData {
    /**
     * Obtiene todos los alumnos.
     *
     * @return HashMap con todos los alumnos
     */
    Map<String, Alumno> getAlumnos();

    /**
     * Obtiene todos los alumnos de un nivel específico.
     *
     * @param nivel Nivel del que se desean obtener los alumnos
     * @return HashMap con todos los alumnos del nivel especificado
     */
    Map<String, Alumno> getAlumnos(int nivel);

    /**
     * Obtiene todos los alumnos.
     *
     * @param nivel Nivel del que se desean obtener los alumnos
     * @param letra Paralelo del que se desean obtener los alumnos
     * @return HashMap con todos los alumnos
     */
    Map<String, Alumno> getAlumnos(int nivel, char letra);

    /**
     * Obtiene un alumno según su RUT
     *
     * @param rut RUT del alumno
     * @return Alumno buscado, o `null` en caso que no se encuentre
     */
    Alumno getAlumno(String rut);

    /**
     * Agrega un alumno a los datos.
     *
     * @param alumno Alumno a agregar
     * @param nivel  Nivel del alumno
     * @param letra  Paralelo que identifica el curso del alumno
     * @return Valor de verdad sobre el éxito o fracaso de la operación
     */
    boolean insertAlumno(Alumno alumno, int nivel, char letra);

    /**
     * Actualiza un alumno.
     *
     * @param alumno Alumno a actualizar
     * @param nivel  Nivel del alumno
     * @param letra  Paralelo que identifica el curso del alumno
     * @return Valor de verdad sobre el éxito o fracaso de la operación
     */
    boolean updateAlumno(Alumno alumno, int nivel, char letra);

    /**
     * Elimina un alumno
     *
     * @param alumno Alumno a eliminar
     * @param nivel  Nivel del alumno
     * @param letra  Paralelo que identifica el curso del alumno
     * @return Valor de verdad sobre el éxito o fracaso de la operación
     */
    boolean deleteAlumno(Alumno alumno, int nivel, char letra);
}