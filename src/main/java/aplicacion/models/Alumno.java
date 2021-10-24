package aplicacion.models;

/**
 * Clase que define el objeto de típo Alumno.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 2.0
 */
public class Alumno extends Persona {
    private final Apoderado apoderado;
    private RegistroAsistencia asistencia;
    private int nivel;
    private char paralelo;

    /**
     * Genera un objeto de tipo Alumno
     *
     * @param rut       RUT del alumno
     * @param nombres   Primer y segundo nombre del alumno
     * @param apPaterno Apellido paterno del alumno
     * @param apMaterno Apellido materno del alumno
     * @param apoderado Apoderado del alumno
     */
    public Alumno(String rut, String nombres, String apPaterno, String apMaterno, int nivel,
                  char paralelo, Apoderado apoderado) {
        super(rut, nombres, apPaterno, apMaterno);
        this.nivel = nivel;
        this.paralelo = paralelo;
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

    public int getNivel() {
        return this.nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public char getParalelo() {
        return this.paralelo;
    }

    public void setParalelo(char paralelo) {
        this.paralelo = paralelo;
    }

    @Override
    public String toString() {
        return super.toString("Alumno") +
                "    -> Curso            : " + Curso.cursoToString(nivel, paralelo) + "\n" +
                "    -> Apoderado        : " + apoderado.getRut() + " - " + apoderado.getNombreCompleto() + "\n" +
                "    -> Prom. Asistencia : " + String.format("%.1f", asistencia.obtenerAsistencia() * 100) + " %\n";
    }

    @Override
    public String toString(String titulo) {
        return super.toString(titulo) +
                "    -> Curso            : " + Curso.cursoToString(nivel, paralelo) + "\n" +
                "    -> Apoderado        : " + apoderado.getRut() + " - " + apoderado.getNombreCompleto() + "\n" +
                "    -> Prom. Asistencia : " + String.format("%.1f", asistencia.obtenerAsistencia() * 100) + " %\n";
    }
}