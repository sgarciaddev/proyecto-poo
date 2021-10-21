package aplicacion.data.database;

import aplicacion.data.ProfesorData;
import aplicacion.models.Curso;
import aplicacion.models.Profesor;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProfesorDB implements ProfesorData {

    public static final String GET_PROFESORES = "SELECT * FROM Profesores";
    public static final String GET_PROFESOR = "SELECT * FROM Profesores WHERE rut = '%s'";
    public static final String INSERT_PROFESOR = "INSERT INTO Profesores (rut, nombres, apellido_paterno," +
            "apellido_materno, asignatura, telefono, email) VALUES ('%s', '%s', '%s', '%s', '%s', %d, '%s')";
    public static final String UPDATE_PROFESOR =
            "UPDATE Profesores SET rut = '%s' , nombres = '%s' , apellido_paterno = '%s', apellido_materno = '%s', " +
                    "asignatura = '%s', telefono = %d, email = '%s' WHERE rut = '%s'";
    public static final String DELETE_PROFESOR = "DELETE FROM Profesores WHERE rut = '%s'";

    @Override
    public List<Profesor> getProfesores() {
        List<Profesor> profesores = new ArrayList<>();
        try {
            ResultSet resultados = DBConnection.getQuery(GET_PROFESORES);
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

    @Override
    public Profesor getProfesor(String rut) {
        try {
            ResultSet resultados = DBConnection.getQuery(String.format(GET_PROFESOR, rut));
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

    @Override
    public Profesor getProfesorJefe(Curso curso) {
        return getProfesor(curso.getProfesorJefe().getRut());
    }

    @Override
    public boolean insertProfesor(Profesor profesor) {
        if (DBConnection.updateQuery(String.format(INSERT_PROFESOR,
                profesor.getRut(),
                profesor.getNombres(),
                profesor.getApPaterno(),
                profesor.getApMaterno(),
                profesor.getAsignatura(),
                profesor.getTelefono(),
                profesor.getEmail())) == 0)
            return false;
        return true;
    }

    @Override
    public boolean updateProfesor(Profesor profesor) {
        if (DBConnection.updateQuery(String.format(UPDATE_PROFESOR,
                profesor.getRut(),
                profesor.getNombres(),
                profesor.getApPaterno(),
                profesor.getApMaterno(),
                profesor.getAsignatura(),
                profesor.getTelefono(),
                profesor.getEmail(),
                profesor.getRut())) == 1)
            return true;
        return false;
    }

    @Override
    public boolean deleteProfesor(Profesor profesor) {
        if (DBConnection.updateQuery(String.format(DELETE_PROFESOR, profesor.getRut())) == 1)
            return true;
        return false;
    }
}