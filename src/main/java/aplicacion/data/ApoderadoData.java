package aplicacion.data;

import aplicacion.models.Apoderado;

/**
 * Interfaz que permite interactuar con los datos de Apoderados.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 4.0
 */
public interface ApoderadoData {
    /**
     * Obtiene un apoderado según su RUT
     *
     * @param rut RUT del apoderado
     * @return Apoderado
     */
    Apoderado getApoderado(String rut);

    /**
     * Actualiza un apoderado
     *
     * @param apoderado Apoderado a actualizar
     * @return Valor de verdad sobre el éxito o fracaso de la operación
     */
    boolean updateApoderado(Apoderado apoderado);

    /**
     * Agrega un apoderado
     *
     * @param apoderado Apoderado a agregar
     * @return Valor de verdad sobre el éxito o fracaso de la operación
     */
    boolean insertApoderado(Apoderado apoderado);

    /**
     * Elimina un apoderado
     *
     * @param apoderado Apoderado a eliminar
     * @return Valor de verdad sobre el éxito o fracaso de la operación
     */
    boolean deleteApoderado(Apoderado apoderado);
}