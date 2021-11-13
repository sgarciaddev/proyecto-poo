package aplicacion.data;

import aplicacion.models.IDAsistencia;
import aplicacion.models.RegistroAsistencia;

import java.sql.Date;
import java.util.HashMap;

/**
 * Clase que permite la interacción con el archivo de texto plano que almacena
 * los datos de los registros de asistencia. Implementa la interfaz RegistroAsistenciaData.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 3.0
 */
public interface RegistroAsistenciaData {

    /**
     * Permite obtener un registro de asistencia, según el ID entregado como parámetro, desde el archivo de texto.
     *
     * @param id ID de asistencia a obtener
     * @return Registro de asistencia buscado.
     */
    RegistroAsistencia get(IDAsistencia id);

    /**
     * Permite obtener un HashMap con todos los registros de asistencia registrados en el sistema.
     *
     * @return HashMap con los registros de asistencia.
     */
    HashMap<IDAsistencia, RegistroAsistencia> getRegistroAsistencia();

    /**
     * Permite obtener un HashMap con los registros de asistencia de una fecha determinada.
     *
     * @param fecha Fecha de lq que se desea obtener los registros de asistencia.
     * @return HashMap con los registros de asistencia de la fecha determinada.
     */
    HashMap<IDAsistencia, RegistroAsistencia> getRegistroAsistencia(Date fecha);

    /**
     * Permite obtener un HashMap con los registros de asistencia de una alumno determinado.
     *
     * @param rutAlumno RUT del alumno del que se desea obtener los registros de asistencia.
     * @return HashMap con los registros de asistencia de la fecha determinada.
     */
    HashMap<IDAsistencia, RegistroAsistencia> getRegistroAsistencia(String rutAlumno);

    /**
     * Permite obtener un HashMap con los registros de asistencia según un ID determinado.
     *
     * @param idAsistencia ID de asistencia que se desea obtener.
     * @return HashMap con los registros de asistencia de la fecha determinada.
     */
    HashMap<IDAsistencia, RegistroAsistencia> getRegistroAsistencia(IDAsistencia idAsistencia);

    /**
     * Permite insertar un registro de asistencia a la base de datos.
     *
     * @param registroAsistencia Registro de asistencia que se desea agregar al sistema.
     */
    void insertarRegistroAsistencia(RegistroAsistencia registroAsistencia);

    /**
     * Permite actualizar un registro de asistencia determinado.
     *
     * @param registroAsistencia Registro de asistencia que se desea actualizar.
     */
    void actualizarRegistroAsistencia(RegistroAsistencia registroAsistencia);

    /**
     * Permite eliminar un registro de asistencia determinado.
     *
     * @param registroAsistencia Registro de asistencia a eliminar.
     */
    void eliminarRegistroAsistencia(RegistroAsistencia registroAsistencia);

}
