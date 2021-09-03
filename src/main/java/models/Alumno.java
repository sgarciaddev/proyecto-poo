package models;

/**
 * Clase que define el objeto de típo Alumno.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 1.0
 */
public class Alumno extends Persona {
    private Apoderado apoderado;
    private RegistroAsistencia asistencia;

    /**
     * @param rut       RUT del alumno
     * @param nombres   Primer y segundo nombre del alumno
     * @param apPaterno Apellido paterno del alumno
     * @param apMaterno Apellido materno del alumno
     * @param apoderado Apoderado del alumno
     */
    public Alumno(String rut, String nombres, String apPaterno, String apMaterno, Apoderado apoderado) {
        super(rut, nombres, apPaterno, apMaterno);
        this.apoderado = apoderado;
        this.asistencia = new RegistroAsistencia();
    }

    /**
     * @return Apoderado
     */
    public Apoderado getApoderado() {
        return apoderado;
    }

    /**
     * @return Registro de asistencia del alumno
     */
    public RegistroAsistencia getAsistencia() {
        return asistencia;
    }

    /**
     * @param asistencia Registro de asistencia a asociar
     */
    public void setAsistencia(RegistroAsistencia asistencia) {
        this.asistencia = asistencia;
    }
}