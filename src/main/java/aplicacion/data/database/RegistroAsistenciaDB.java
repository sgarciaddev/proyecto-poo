package aplicacion.data.database;

import aplicacion.data.RegistroAsistenciaData;
import aplicacion.models.IDAsistencia;
import aplicacion.models.RegistroAsistencia;

import java.sql.Date;
import java.util.HashMap;

/**
 * Clase que permite la interacción con la base de datos MySQL que almancena
 * los datos con los registros de asistencia de los alumnos.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 3.0
 */
public class RegistroAsistenciaDB implements RegistroAsistenciaData {

    /**
     * Permite obtener un registro de asistencia determinado, según el ID especificado como parámetro.
     *
     * @param id ID de la asistencia
     * @return El registro de assitencia solicitado.
     */
    @Override
    public RegistroAsistencia get(IDAsistencia id) {
        return null;
    }

    /**
     * Permite obtener un HashMap con todos los registros de asistencia registrados en el sistema.
     *
     * @return HashMap con los registros de asistencia.
     */
    @Override
    public HashMap<IDAsistencia, RegistroAsistencia> getRegistroAsistencia() {
        return null;
    }

    /**
     * Permite obtener un HashMap con los registros de asistencia de una fecha determinada.
     *
     * @param fecha Fecha de lq que se desea obtener los registros de asistencia.
     * @return HashMap con los registros de asistencia de la fecha determinada.
     */
    @Override
    public HashMap<IDAsistencia, RegistroAsistencia> getRegistroAsistencia(Date fecha) {
        return null;
    }

    /**
     * Permite obtener un HashMap con los registros de asistencia de una alumno determinado.
     *
     * @param rutAlumno RUT del alumno del que se desea obtener los registros de asistencia.
     * @return HashMap con los registros de asistencia de la fecha determinada.
     */
    @Override
    public HashMap<IDAsistencia, RegistroAsistencia> getRegistroAsistencia(String rutAlumno) {
        return null;
    }

    /**
     * Permite obtener un HashMap con los registros de asistencia según un ID determinado.
     *
     * @param idAsistencia ID de asistencia que se desea obtener.
     * @return HashMap con los registros de asistencia de la fecha determinada.
     */
    @Override
    public HashMap<IDAsistencia, RegistroAsistencia> getRegistroAsistencia(IDAsistencia idAsistencia) {
        return null;
    }

    /**
     * Permite insertar un registro de asistencia a la base de datos.
     *
     * @param registroAsistencia Registro de asistencia que se desea agregar al sistema.
     */
    @Override
    public void insertarRegistroAsistencia(RegistroAsistencia registroAsistencia) {

    }

    /**
     * Permite actualizar un registro de asistencia determinado.
     *
     * @param registroAsistencia Registro de asistencia que se desea actualizar.
     */
    @Override
    public void actualizarRegistroAsistencia(RegistroAsistencia registroAsistencia) {

    }

    /**
     * Permite eliminar un registro de asistencia determinado.
     *
     * @param registroAsistencia Registro de asistencia a eliminar.
     */
    @Override
    public void eliminarRegistroAsistencia(RegistroAsistencia registroAsistencia) {

    }
}
