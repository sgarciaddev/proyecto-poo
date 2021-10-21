package aplicacion.data.database;

import aplicacion.data.AlumnoData;
import aplicacion.models.Alumno;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class AlumnoDB implements AlumnoData {

    public static final String GET_TODOS_ALUMNOS = "SELECT * FROM Alumnos";
    public static final String GET_ALUMNOS_NIVEL = "SELECT * FROM Alumnos WHERE nivel = %d";
    public static final String GET_ALUMNOS_NIVEL_PAR = "SELECT * FROM Alumnos WHERE nivel = %d AND paralelo = '%c'";
    public static final String GET_ALUMNO_RUT = "SELECT * FROM Alumnos WHERE rut = '%s'";
    public static final String INSERT_ALUMNO = "INSERT INTO Alumnos (nivel, paralelo, rut, nombres, apellido_paterno," +
            "apellido_materno, rut_apoderado) VALUES (%d, '%c', '%s', '%s', '%s', '%s', '%s')";
    public static final String UPDATE_ALUMNO = "UPDATE Alumnos SET nivel = %d, paralelo = '%c' , rut = '%s' ," +
            "nombres = '%s' , apellido_paterno = '%s' , apellido_materno = '%s', " + "rut_apoderado = '%s' " +
            "WHERE rut = '%s'";
    public static final String DELETE_ALUMNO = "DELETE FROM Alumnos WHERE rut = '%s'";
    private final ApoderadoDB apoderadoData;

    public AlumnoDB() {
        this.apoderadoData = new ApoderadoDB();
    }

    @Override
    public Map<String, Alumno> getAlumnos() {
        Map<String, Alumno> alumnos = new HashMap<>();
        try {
            ResultSet resultados = DBConnection.getQuery(GET_TODOS_ALUMNOS);
            if (resultados == null) return null;
            while (resultados.next()) {
                alumnos.put(resultados.getString("rut"), new Alumno(
                        resultados.getString("rut"),
                        resultados.getString("nombres"),
                        resultados.getString("apellido_paterno"),
                        resultados.getString("apellido_materno"),
                        resultados.getInt("nivel"),
                        resultados.getString("paralelo").charAt(0),
                        this.apoderadoData.getApoderado(resultados.getString("rut_apoderado"))
                ));
            }
            return alumnos;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    @Override
    public Map<String, Alumno> getAlumnos(int nivel) {
        Map<String, Alumno> alumnos = new HashMap<>();
        try {
            ResultSet resultados = DBConnection.getQuery(String.format(GET_ALUMNOS_NIVEL, nivel));
            if (resultados == null) return null;
            while (resultados.next()) {
                alumnos.put(resultados.getString("rut"), new Alumno(
                        resultados.getString("rut"),
                        resultados.getString("nombres"),
                        resultados.getString("apellido_paterno"),
                        resultados.getString("apellido_materno"),
                        resultados.getInt("nivel"),
                        resultados.getString("paralelo").charAt(0),
                        this.apoderadoData.getApoderado(resultados.getString("rut_apoderado"))
                ));
            }
            return alumnos;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public Map<String, Alumno> getAlumnos(int nivel, char paralelo) {
        Map<String, Alumno> alumnos = new HashMap<>();
        try {
            ResultSet resultados = DBConnection.getQuery(String.format(GET_ALUMNOS_NIVEL_PAR, nivel, paralelo));
            if (resultados == null) return null;
            while (resultados.next()) {
                alumnos.put(resultados.getString("rut"), new Alumno(
                        resultados.getString("rut"),
                        resultados.getString("nombres"),
                        resultados.getString("apellido_paterno"),
                        resultados.getString("apellido_materno"),
                        resultados.getInt("nivel"),
                        resultados.getString("paralelo").charAt(0),
                        this.apoderadoData.getApoderado(resultados.getString("rut_apoderado"))
                ));
            }
            return alumnos;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public Alumno getAlumno(String rut) {
        try {
            ResultSet resultados = DBConnection.getQuery(String.format(GET_ALUMNO_RUT, rut));
            if (resultados == null) return null;
            if (resultados.next()) {
                return new Alumno(
                        resultados.getString("rut"),
                        resultados.getString("nombres"),
                        resultados.getString("apellido_paterno"),
                        resultados.getString("apellido_materno"),
                        resultados.getInt("nivel"),
                        resultados.getString("paralelo").charAt(0),
                        this.apoderadoData.getApoderado(resultados.getString("rut_apoderado"))
                );
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public boolean insertAlumno(Alumno alumno) {
        if (DBConnection.updateQuery(String.format(INSERT_ALUMNO,
                alumno.getNivel(),
                alumno.getParalelo(),
                alumno.getRut(),
                alumno.getNombres(),
                alumno.getApPaterno(),
                alumno.getApMaterno(),
                alumno.getApoderado().getRut())) == 0)
            return false;
        return true;
    }

    @Override
    public boolean updateAlumno(Alumno alumno) {
        if (DBConnection.updateQuery(String.format(UPDATE_ALUMNO,
                alumno.getNivel(),
                alumno.getParalelo(),
                alumno.getRut(),
                alumno.getNombres(),
                alumno.getApPaterno(),
                alumno.getApMaterno(),
                alumno.getApoderado().getRut(),
                alumno.getRut())) == 0)
            return false;
        return true;
    }

    @Override
    public boolean deleteAlumno(Alumno alumno) {
        if (DBConnection.updateQuery(String.format(DELETE_ALUMNO, alumno.getRut())) == 1)
            return true;
        return false;
    }
}