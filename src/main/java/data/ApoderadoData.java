package data;

import models.Apoderado;

/**
 * Interfaz que permite interactuar con los datos de Apoderados.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 1.0
 */
public interface ApoderadoData {
    public Apoderado getApoderado(String rut);
    public boolean updateApoderado(Apoderado apoderado);
    public boolean insertApoderado(Apoderado apoderado);
    public boolean deleteApoderado(Apoderado apoderado);
}