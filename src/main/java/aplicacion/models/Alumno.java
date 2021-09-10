package aplicacion.models;

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
     * Genera un objeto de tipo Alumno
     *
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
     * Obtener apoderado asociado al alumno
     *
     * @return Apoderado
     */
    public Apoderado getApoderado() {
        return apoderado;
    }

    /**
     * Obtener el registro de asistencia asociado al alumno
     *
     * @return Registro de asistencia del alumno
     */
    public RegistroAsistencia getAsistencia() {
        return asistencia;
    }

    /**
     * Asociar registro de asistencia al alumno
     *
     * @param asistencia Registro de asistencia a asociar
     */
    public void setAsistencia(RegistroAsistencia asistencia) {
        this.asistencia = asistencia;
    }
}