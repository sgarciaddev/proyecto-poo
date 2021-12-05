package aplicacion.models;


import java.sql.Date;

/**
 * Clase que define un identificador para los registros de asistencia.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 3.0
 */
public class IDAsistencia {
    public String rutAlumno;
    public Date fecha;

    /**
     * Genera un objeto de tipo IDAsistencia
     *
     * @param rutAlumno El rut del alumno
     * @param fecha La fecha del registro de asistencia.
     */
    public IDAsistencia(String rutAlumno, Date fecha) {
        this.rutAlumno = rutAlumno;
        this.fecha = fecha;
    }

}