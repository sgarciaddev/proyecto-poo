package aplicacion.data.database;

import aplicacion.data.ProfesorData;
import aplicacion.models.Curso;
import aplicacion.models.Profesor;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que permite la interacción con la base de datos MySQL que almancena
 * los datos de Profesor. Implementa la interfaz ProfesorData.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 4.0
 */
public class ProfesorDB implements ProfesorData {

    /**
     * Permite obtener todos los profesores almacenados en la base de datos MySQL.
     *
     * @return ArrayList de profesores
     */
    @Override
    public List<Profesor> getProfesores() {
        List<Profesor> profesores = new ArrayList<>();
        try {
            ResultSet resultados = DBConnection.getQuery(SQLSentences.GET_PROFESORES.toString());
            if (resultados == null) return null;
            while (resultados.next()) {
                profesores.add(new Profesor(
                        resultados.getString("rut"),
                        resultados.getString("nombres"),
                        resultados.getString("apellido_paterno"),
                        resultados.getString("apellido_materno"),
                        resultados.getString("asignatura"),
                        resultados.getString("email"),
                        resultados.getInt("telefono")));
            }
            return profesores;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Permite obtener un profesor en especifico, almacenado en la base de datos MySQL
     *
     * @param rut RUT del profesor
     * @return Profesor solicitado
     */
    @Override
    public Profesor getProfesor(String rut) {
        try {
            ResultSet resultados = DBConnection.getQuery(String.format(SQLSentences.GET_PROFESOR.toString(), rut));
            if (resultados == null) return null;
            if (resultados.next()) {
                return new Profesor(
                        resultados.getString("rut"),
                        resultados.getString("nombres"),
                        resultados.getString("apellido_paterno"),
                        resultados.getString("apellido_materno"),
                        resultados.getString("asignatura"),
                        resultados.getString("email"),
                        resultados.getInt("telefono"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Permite obtener un profesor en especifico, almacenado en la base de datos MySQL.
     *
     * @param curso Curso
     * @return Profesor solicitado
     */
    @Override
    public Profesor getProfesorJefe(Curso curso) {
        return getProfesor(curso.getProfesorJefe().getRut());
    }

    @Override
    public boolean insertProfesor(Profesor profesor) {
        return DBConnection.updateQuery(String.format(SQLSentences.INSERT_PROFESOR.toString(),
                profesor.getRut(),
                profesor.getNombres(),
                profesor.getApPaterno(),
                profesor.getApMaterno(),
                profesor.getAsignatura(),
                profesor.getTelefono(),
                profesor.getEmail())) != 0;
    }

    /**
     * Actualiza los datos de un Profesor en la base de datos MySQL.
     *
     * @param profesor Profesor a actualizar
     * @return Valor de verdad (boolean) sobre el exito o fracaso de la operacion de actualizacion
     */
    @Override
    public boolean updateProfesor(Profesor profesor) {
        return DBConnection.updateQuery(String.format(SQLSentences.UPDATE_PROFESOR.toString(),
                profesor.getRut(),
                profesor.getNombres(),
                profesor.getApPaterno(),
                profesor.getApMaterno(),
                profesor.getAsignatura(),
                profesor.getTelefono(),
                profesor.getEmail(),
                profesor.getRut())) == 1;
    }

    /**
     * Elimina un profesor de la base de datos MySQL.
     *
     * @param profesor Profesor a eliminar
     */
    @Override
    public void deleteProfesor(Profesor profesor) {
        DBConnection.updateQuery(String.format(SQLSentences.DELETE_PROFESOR.toString(), profesor.getRut()));
    }
}