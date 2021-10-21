package aplicacion.data.database;

import aplicacion.data.ApoderadoData;
import aplicacion.models.Apoderado;

import java.sql.ResultSet;

public class ApoderadoDB implements ApoderadoData {

    public static final String GET_APODERADO = "SELECT * FROM Apoderados WHERE rut = '%s'";
    public static final String INSERT_APODERADO = "INSERT INTO Apoderados (rut, nombres, apellido_paterno," +
            "apellido_materno, telefono, email) VALUES ('%s', '%s', '%s', '%s', %d, '%s')";
    public static final String UPDATE_APODERADO =
            "UPDATE Apoderados SET rut = '%s' , nombres = '%s' , apellido_paterno = '%s', apellido_materno = '%s', " +
                    "telefono = %d, email = '%s' WHERE rut = '%s'";
    public static final String DELETE_APODERADO = "DELETE FROM Apoderados WHERE rut = '%s'";

    @Override
    public Apoderado getApoderado(String rut) {
        try {
            ResultSet resultados = DBConnection.getQuery(String.format(GET_APODERADO, rut));
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

    @Override
    public boolean updateApoderado(Apoderado apoderado) {
        if (DBConnection.updateQuery(String.format(UPDATE_APODERADO,
                apoderado.getRut(),
                apoderado.getNombres(),
                apoderado.getApPaterno(),
                apoderado.getApMaterno(),
                apoderado.getTelefono(),
                apoderado.getEmail(),
                apoderado.getRut())) == 0)
            return false;
        return true;
    }

    @Override
    public boolean insertApoderado(Apoderado apoderado) {
        if (DBConnection.updateQuery(String.format(INSERT_APODERADO,
                apoderado.getRut(),
                apoderado.getNombres(),
                apoderado.getApPaterno(),
                apoderado.getApMaterno(),
                apoderado.getTelefono(),
                apoderado.getEmail())) == 0)
            return false;
        return true;
    }

    @Override
    public boolean deleteApoderado(Apoderado apoderado) {
        if (DBConnection.updateQuery(String.format(DELETE_APODERADO, apoderado.getRut())) == 1)
            return true;
        return false;
    }
}