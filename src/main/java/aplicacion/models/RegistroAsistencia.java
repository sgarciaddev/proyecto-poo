package aplicacion.models;

/**
 * Clase que registra la asistencia de un alumno, para un año escolar completo.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 2.0
 */
public class RegistroAsistencia {

    private float[][] asistencia; // Matriz que registra asistencia (dia y mes).
    private int[] contadorDias; // Arreglo que contiene el total de días a considerar en la asistencia

    /**
     * Constructor que permite crear un objeto para registrar la asistencia. No recibe parámetros.
     */
    public RegistroAsistencia() {
        // Considera 31 días máximo por mes, desde Marzo (índice 0) a Diciembre (índice 9).
        this.asistencia = new float[10][31];
        this.contadorDias = new int[10];

    }

    public float[][] getAsistenciaMatriz() {
        return this.asistencia;
    }

    public void setAsistenciaMatriz(float[][] asistencia) {
        this.asistencia = asistencia;
    }

    /**
     * Permite registrar la asistencia del alumno en un día y mes determinado.
     *
     * @param valor El valor de la asistencia (entre 0 y 1)
     * @param dia El día para el que se quiere registrar la asistencia
     * @param mes El mes para el que se quiere registrar la asistencia
     */
    public void registrarAsistencia(float valor, int mes, int dia) {
        if ((mes >= 0 && mes <= 9) && (dia >= 0 && dia <= 30))
            this.asistencia[mes][dia] = valor;
        this.contadorDias[mes]++;
    }

    /**
     * Permite obtener la asistencia del alumno, calculada al día de realizada la consulta.
     *
     * @return Porcentaje de asistencia (entre 0 y 1).
     */
    public float obtenerAsistencia() {
        float totalDiasAsistidos = 0;

        for (int mes = 0; mes < 10; mes++){
            for (int dia = 0; dia < 31; dia++){
                if (Float.compare(this.asistencia[mes][dia], 1.0f) == 0 || Float.compare(this.asistencia[mes][dia], 0.5f) == 0){
                    totalDiasAsistidos++;
                }
            }
        }
        if (totalDiasAsistidos != 0)
            return totalDiasAsistidos / 310;
        return 0.0f;
    }

    /**
     * Permite obtener la asistencia del alumno en un mes determinado.
     *
     * @param mes Número de mes (1 = marzo, 10 = diciembre)
     * @return Porcentaje de asistencia (entre 0 y 1).
     */
    public float obtenerAsistencia(int mes) {
        return 0.0f;
    }

    /**
     * Permite obtener la asistencia del alumno, calculada al día de realizada la consulta.
     *
     * @param dia Dia a buscar
     * @param mes Número de mes (1 = marzo, 10 = diciembre)
     * @return Porcentaje de asistencia (entre 0 y 1).
     */
    public float obtenerAsistencia(int dia, int mes) {
        return 0.0f;
    }

    /**
     * Permite obtener los retiros del alumno, calculada al día de realizada la consulta.
     *
     * @return Porcentaje de retiros (entre 0 y 1).
     */
    public float obtenerRetiros() {
        float totalDiasRetirado = 0;

        for (int mes = 0; mes < 10; mes++){
            for (int dia = 0; dia < 31; dia++){
                if (Float.compare(this.asistencia[mes][dia], 0.5f) == 0){
                    totalDiasRetirado++;
                }
            }
        }
        if (totalDiasRetirado != 0)
            return totalDiasRetirado / 310;
        return 0.0f;
    }

}