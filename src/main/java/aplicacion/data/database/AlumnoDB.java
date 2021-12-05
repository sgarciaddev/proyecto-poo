package aplicacion.data.database;

import aplicacion.data.AlumnoData;
import aplicacion.models.Alumno;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase que permite la interacción con la base de datos MySQL que almancena
 * los datos de los alumnos. Implementa la interfaz AlumnoData.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 4.0
 */
public class AlumnoDB implements AlumnoData {


    private final ApoderadoDB apoderadoData;
    private final RegistroAsistenciaDB registroAsistenciaData;

    /**
     * Constructor de AlumnoDatafile. Trabaja con el origen de datos de apoderados.
     */
    public AlumnoDB() {
        this.apoderadoData = new ApoderadoDB();
        this.registroAsistenciaData = new RegistroAsistenciaDB();
    }

    /**
     * Obtiene todos los alumnos de la base de datos.
     *
     * @return HashMap de alumnos
     */
    @Override
    public Map<String, Alumno> getAlumnos() {
        Map<String, Alumno> alumnos = new HashMap<>();
        try {
            ResultSet resultados = DBConnection.getQuery(SQLSentences.GET_TODOS_ALUMNOS.toString());
            if (resultados == null) return null;
            while (resultados.next()) {
                alumnos.put(resultados.getString("rut"), new Alumno(
                        resultados.getString("rut"),
                        resultados.getString("nombres"),
                        resultados.getString("apellido_paterno"),
                        resultados.getString("apellido_materno"),
                        resultados.getInt("nivel"),
                        resultados.getString("paralelo").charAt(0),
                        this.apoderadoData.getApoderado(resultados.getString("rut_apoderado")),
                        this.registroAsistenciaData.getRegistroAsistencia(resultados.getString("rut"))
                ));
            }
            return alumnos;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    /**
     * Obtiene todos los alumnos almacenados en la base de datos, y que estén en el
     * nivel requerido.
     *
     * @param nivel Nivel por el que se busca filtrar los datos
     * @return HashMap de alumnos del nivel entregado
     */
    @Override
    public Map<String, Alumno> getAlumnos(int nivel) {
        Map<String, Alumno> alumnos = new HashMap<>();
        try {
            ResultSet resultados = DBConnection.getQuery(String.format(SQLSentences.GET_ALUMNOS_NIVEL.toString(), nivel));
            if (resultados == null) return null;
            while (resultados.next()) {
                alumnos.put(resultados.getString("rut"), new Alumno(
                        resultados.getString("rut"),
                        resultados.getString("nombres"),
                        resultados.getString("apellido_paterno"),
                        resultados.getString("apellido_materno"),
                        resultados.getInt("nivel"),
                        resultados.getString("paralelo").charAt(0),
                        this.apoderadoData.getApoderado(resultados.getString("rut_apoderado")),
                        this.registroAsistenciaData.getRegistroAsistencia(resultados.getString("rut"))
                ));
            }
            return alumnos;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Obtiene todos los alumnos almacenados en la base de datos, y que estén en el
     * curso requerido.
     *
     * @param nivel    Nivel por el que se busca filtrar los datos
     * @param paralelo Caracter que identifica el paralelo por el que se busca
     *                 filtrar los datos
     * @return HashMap de alumnos del nivel y paralelo entregado
     */
    @Override
    public Map<String, Alumno> getAlumnos(int nivel, char paralelo) {
        Map<String, Alumno> alumnos = new HashMap<>();
        try {
            ResultSet resultados = DBConnection.getQuery(String.format(SQLSentences.GET_ALUMNOS_NIVEL_PAR.toString(), nivel,
                    paralelo));
            if (resultados == null) return null;
            while (resultados.next()) {
                alumnos.put(resultados.getString("rut"), new Alumno(
                        resultados.getString("rut"),
                        resultados.getString("nombres"),
                        resultados.getString("apellido_paterno"),
                        resultados.getString("apellido_materno"),
                        resultados.getInt("nivel"),
                        resultados.getString("paralelo").charAt(0),
                        this.apoderadoData.getApoderado(resultados.getString("rut_apoderado")),
                        this.registroAsistenciaData.getRegistroAsistencia(resultados.getString("rut"))
                ));
            }
            return alumnos;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Permite obtener un alumno especifico
     *
     * @param rut RUT del alumno a obtener
     * @return El alumno, de no encontrarse retorna null
     */
    @Override
    public Alumno getAlumno(String rut) {
        try {
            ResultSet resultados = DBConnection.getQuery(String.format(SQLSentences.GET_ALUMNO_RUT.toString(), rut));
            if (resultados == null) return null;
            if (resultados.next()) {
                return new Alumno(
                        resultados.getString("rut"),
                        resultados.getString("nombres"),
                        resultados.getString("apellido_paterno"),
                        resultados.getString("apellido_materno"),
                        resultados.getInt("nivel"),
                        resultados.getString("paralelo").charAt(0),
                        this.apoderadoData.getApoderado(resultados.getString("rut_apoderado")),
                        this.registroAsistenciaData.getRegistroAsistencia(resultados.getString("rut"))
                );
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Inserta un nuevo alumno a la base de datos MySQL
     *
     * @param alumno Alumno a agregar
     */
    @Override
    public void insertAlumno(Alumno alumno) {
        DBConnection.updateQuery(String.format(SQLSentences.INSERT_ALUMNO.toString(),
                alumno.getNivel(),
                alumno.getParalelo(),
                alumno.getRut(),
                alumno.getNombres(),
                alumno.getApPaterno(),
                alumno.getApMaterno(),
                alumno.getApoderado().getRut()));
    }

    /**
     * Actualiza un alumno en la base de datos MySQL
     *
     * @param alumno Alumno a actualizar
     * @return Valor de verdad de la operación de actualización
     */
    @Override
    public boolean updateAlumno(Alumno alumno) {
        return DBConnection.updateQuery(String.format(SQLSentences.UPDATE_ALUMNO.toString(),
                alumno.getNivel(),
                alumno.getParalelo(),
                alumno.getRut(),
                alumno.getNombres(),
                alumno.getApPaterno(),
                alumno.getApMaterno(),
                alumno.getApoderado().getRut(),
                alumno.getRut())) != 0;
    }

    /**
     * Elimina un alumno de la base de datos MySQL
     *
     * @param alumno Alumno a eliminar
     * @return Valor de verdad de la operación de eliminación
     */
    @Override
    public boolean deleteAlumno(Alumno alumno) {
        return DBConnection.updateQuery(String.format(SQLSentences.DELETE_ALUMNO.toString(), alumno.getRut())) == 1;
    }
}