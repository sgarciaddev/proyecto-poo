package aplicacion.data.database;

import aplicacion.data.RegistroAsistenciaData;
import aplicacion.models.IDAsistencia;
import aplicacion.models.RegistroAsistencia;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.HashMap;

/**
 * Clase que permite la interacción con la base de datos MySQL que almancena
 * los datos con los registros de asistencia de los alumnos.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 4.0
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
        try {
            ResultSet resultados = DBConnection.getQuery(String.format(SQLSentences.GET_REG_AS_ID.toString(),
                    id.rutAlumno, id.fecha.toString()));
            if (resultados == null) return null;
            if (resultados.next()) {
                return new RegistroAsistencia(
                        id,
                        resultados.getDouble("valor"),
                        resultados.getBoolean("presente"),
                        resultados.getBoolean("justificado"),
                        resultados.getBoolean("retiro"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Permite obtener un HashMap con todos los registros de asistencia registrados en el sistema.
     *
     * @return HashMap con los registros de asistencia.
     */
    @Override
    public HashMap<IDAsistencia, RegistroAsistencia> getRegistroAsistencia() {
        HashMap<IDAsistencia, RegistroAsistencia> asistencia = new HashMap<>();
        IDAsistencia id;
        RegistroAsistencia as;
        try {
            ResultSet resultados = DBConnection.getQuery(SQLSentences.GET_TODOS_REG_AS.toString());
            if (resultados == null) return null;
            while (resultados.next()) {
                id = new IDAsistencia(resultados.getString("rut_alumno"), Date.valueOf(resultados.getString("fecha")));
                as = new RegistroAsistencia(id, resultados.getDouble("valor"), resultados.getBoolean("presente"),
                        resultados.getBoolean("justificado"), resultados.getBoolean("retiro"));
                asistencia.put(id, as);
            }
            return asistencia;
        } catch (Exception e) {
            System.out.println(e);
        }
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
        HashMap<IDAsistencia, RegistroAsistencia> asistencia = new HashMap<>();
        IDAsistencia id;
        RegistroAsistencia as;
        try {
            ResultSet resultados = DBConnection.getQuery(String.format(SQLSentences.GET_REG_AS_FECHA.toString(),
                    fecha.toString()));
            if (resultados == null) return null;
            while (resultados.next()) {
                id = new IDAsistencia(resultados.getString("rut_alumno"), Date.valueOf(resultados.getString("fecha")));
                as = new RegistroAsistencia(id, resultados.getDouble("valor"), resultados.getBoolean("presente"),
                        resultados.getBoolean("justificado"), resultados.getBoolean("retiro"));
                asistencia.put(id, as);
            }
            return asistencia;
        } catch (Exception e) {
            System.out.println(e);
        }
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
        HashMap<IDAsistencia, RegistroAsistencia> asistencia = new HashMap<>();
        IDAsistencia id;
        RegistroAsistencia as;
        try {
            ResultSet resultados = DBConnection.getQuery(String.format(SQLSentences.GET_REG_AS_RUT.toString(),
                    rutAlumno));
            if (resultados == null) return null;
            while (resultados.next()) {
                id = new IDAsistencia(resultados.getString("rut_alumno"), Date.valueOf(resultados.getString("fecha")));
                as = new RegistroAsistencia(id, resultados.getDouble("valor"), resultados.getBoolean("presente"),
                        resultados.getBoolean("justificado"), resultados.getBoolean("retiro"));
                asistencia.put(id, as);
            }
            return asistencia;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Permite obtener un HashMap con los registros de asistencia según un ID determinado.
     *
     * @param id ID de asistencia que se desea obtener.
     * @return HashMap con los registros de asistencia de la fecha determinada.
     */
    @Override
    public HashMap<IDAsistencia, RegistroAsistencia> getRegistroAsistencia(IDAsistencia id) {
        HashMap<IDAsistencia, RegistroAsistencia> asistencia = new HashMap<>();
        RegistroAsistencia as;
        try {
            ResultSet resultados = DBConnection.getQuery(String.format(SQLSentences.GET_REG_AS_ID.toString(),
                    id.rutAlumno, id.fecha.toString()));
            if (resultados == null) return null;
            while (resultados.next()) {
                id = new IDAsistencia(resultados.getString("rut_alumno"), Date.valueOf(resultados.getString("fecha")));
                as = new RegistroAsistencia(id, resultados.getDouble("valor"), resultados.getBoolean("presente"),
                        resultados.getBoolean("justificado"), resultados.getBoolean("retiro"));
                asistencia.put(id, as);
            }
            return asistencia;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Permite insertar un registro de asistencia a la base de datos.
     *
     * @param registroAsistencia Registro de asistencia que se desea agregar al sistema.
     */
    @Override
    public void insertarRegistroAsistencia(RegistroAsistencia registroAsistencia) {
        DBConnection.updateQuery(String.format(SQLSentences.INSERT_REG_AS.toString(),
                registroAsistencia.getId().rutAlumno,
                registroAsistencia.getId().fecha,
                registroAsistencia.getValor(),
                registroAsistencia.presente(),
                registroAsistencia.justificado(),
                registroAsistencia.retirado()));
    }

    /**
     * Permite actualizar un registro de asistencia determinado.
     *
     * @param registroAsistencia Registro de asistencia que se desea actualizar.
     */
    @Override
    public void actualizarRegistroAsistencia(RegistroAsistencia registroAsistencia) {
        DBConnection.updateQuery(String.format(SQLSentences.UPDATE_REG_AS.toString(),
                registroAsistencia.getId().rutAlumno,
                registroAsistencia.getId().fecha,
                registroAsistencia.getValor(),
                registroAsistencia.presente(),
                registroAsistencia.justificado(),
                registroAsistencia.retirado(),
                registroAsistencia.getId().rutAlumno,
                registroAsistencia.getId().fecha));
    }

    /**
     * Permite eliminar un registro de asistencia determinado.
     *
     * @param registroAsistencia Registro de asistencia a eliminar.
     */
    @Override
    public void eliminarRegistroAsistencia(RegistroAsistencia registroAsistencia) {
        DBConnection.updateQuery(String.format(SQLSentences.DELETE_REG_AS.toString(),
                registroAsistencia.getId().rutAlumno,
                registroAsistencia.getId().fecha));
    }
}
