package aplicacion.data.database;

import aplicacion.data.ApoderadoData;
import aplicacion.models.Apoderado;

import java.sql.ResultSet;

/**
 * Clase que permite la interacción con la base de datos MySQL que almacena
 * los datos de Apoderado. Implementa la interfaz ApoderadoData.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 2.0
 */
public class ApoderadoDB implements ApoderadoData {

    /**
     * Obtener apoderado desde la base de datos MySQL, según su RUT
     *
     * @param rut RUT del apoderado
     * @return Apoderado correspondiente al RUT ingresado, o `null` si no se encontró.
     */
    @Override
    public Apoderado getApoderado(String rut) {
        try {
            ResultSet resultados = DBConnection.getQuery(String.format(SQLSentences.GET_APODERADO.toString(), rut));
            if (resultados == null) return null;
            if (resultados.next()) {
                return new Apoderado(
                        resultados.getString("rut"),
                        resultados.getString("nombres"),
                        resultados.getString("apellido_paterno"),
                        resultados.getString("apellido_materno"),
                        resultados.getInt("telefono"),
                        resultados.getString("email"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Actualiza los datos de un Apoderado en la base de datos MySQL
     *
     * @param apoderado Apoderado a actualizar
     * @return Valor de verdad (boolean) sobre el exito o fracaso de la operacion de actualizacion
     */
    @Override
    public boolean updateApoderado(Apoderado apoderado) {
        return DBConnection.updateQuery(String.format(SQLSentences.UPDATE_APODERADO.toString(),
                apoderado.getRut(),
                apoderado.getNombres(),
                apoderado.getApPaterno(),
                apoderado.getApMaterno(),
                apoderado.getTelefono(),
                apoderado.getEmail(),
                apoderado.getRut())) != 0;
    }

    /**
     * Agrega un Apoderado a la base de datos MySQL
     *
     * @param apoderado Apoderado que se desea agregar a la base de datos.
     * @return Valor de verdad (boolean) sobre el exito o fracaso de la operacion de inserción
     */
    @Override
    public boolean insertApoderado(Apoderado apoderado) {
        return DBConnection.updateQuery(String.format(SQLSentences.INSERT_APODERADO.toString(),
                apoderado.getRut(),
                apoderado.getNombres(),
                apoderado.getApPaterno(),
                apoderado.getApMaterno(),
                apoderado.getTelefono(),
                apoderado.getEmail())) != 0;
    }

    /**
     * Elimina un apoderado de la base de datos
     *
     * @param apoderado Apoderado a eliminar
     * @return Valor de verdad (boolean) sobre el exito o fracaso de la operacion de borrado
     */
    @Override
    public boolean deleteApoderado(Apoderado apoderado) {
        return DBConnection.updateQuery(String.format(SQLSentences.DELETE_APODERADO.toString(), apoderado.getRut())) == 1;
    }
}