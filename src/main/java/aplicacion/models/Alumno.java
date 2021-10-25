package aplicacion.models;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase que define el objeto de típo Alumno.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 2.0
 */
public class Alumno extends Persona {
    private final Apoderado apoderado;
    private HashMap<IDAsistencia, RegistroAsistencia> asistencia;
    private double promAsistencia;
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
                  char paralelo, Apoderado apoderado, HashMap<IDAsistencia, RegistroAsistencia> asistencia) {
        super(rut, nombres, apPaterno, apMaterno);
        this.nivel = nivel;
        this.paralelo = paralelo;
        this.apoderado = apoderado;
        this.asistencia = asistencia;
        this.promAsistencia = 0.0;
        actualizarPromedio();
    }

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
        this.asistencia = new HashMap<>();
        this.promAsistencia = 0.0;
    }

    private void actualizarPromedio() {
        int dias = 0;
        double suma = 0.0;
        for (Map.Entry<IDAsistencia, RegistroAsistencia> registro: this.asistencia.entrySet()) {
            if (!registro.getValue().justificado()) {
                dias++;
                suma += registro.getValue().getValor();
            }
        }
        if (dias > 0)
            this.promAsistencia = suma / dias;
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
     * @param fecha Fecha del que se desea obtener el registro de asistencia
     * @return Registro de asistencia del alumno. @null si no se encuentra.
     */
    public RegistroAsistencia getAsistencia(Date fecha) {
        for (Map.Entry<IDAsistencia, RegistroAsistencia> registro: this.asistencia.entrySet()) {
            if (registro.getKey().fecha.compareTo(fecha) == 0) {
                return registro.getValue();
            }
        }
        return null;
    }

    public void setAsistencia(HashMap<IDAsistencia, RegistroAsistencia> asistencia) {
        this.asistencia = asistencia;
        this.actualizarPromedio();
    }

    public double getPromAsistencia() {
        return promAsistencia;
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
                "    -> Prom. Asistencia : " + String.format("%.1f", getPromAsistencia() * 100) + " %\n";
    }

    @Override
    public String toString(String titulo) {
        return super.toString(titulo) +
                "    -> Curso            : " + Curso.cursoToString(nivel, paralelo) + "\n" +
                "    -> Apoderado        : " + apoderado.getRut() + " - " + apoderado.getNombreCompleto() + "\n" +
                "    -> Prom. Asistencia : " + String.format("%.1f", getPromAsistencia() * 100) + " %\n";
    }
}