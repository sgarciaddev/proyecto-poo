package aplicacion.models;

import java.text.SimpleDateFormat;

/**
 * Clase que registra la asistencia de un alumno, para un dia especifico.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 3.0
 */
public class RegistroAsistencia {

    private final IDAsistencia id;
    private double valor;
    private boolean estaPresente, estaJustificado, hizoRetiro;

    public static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Constructor que permite crear un objeto para registrar la asistencia. No recibe parámetros.
     */
    public RegistroAsistencia(IDAsistencia id, double valor, boolean p, boolean j, boolean r) {
        // Considera 31 días máximo por mes, desde Marzo (índice 0) a Diciembre (índice 9).
        this.id = id;
        this.valor = valor;
        this.estaPresente = p;
        this.estaJustificado = j;
        this.hizoRetiro = r;
    }

    public IDAsistencia getId() {
        return id;
    }

    /**
     * Define el valor de asistencia. Acepta 2 opciones:
     * - 1: El alumno queda como presente
     * - 2: El alumno queda como ausente (sin justificar)
     * - 3: El alumno queda como ausente (justificado)
     *
     * @param opt Opcion numerica con el valor a colocar
     */
    public void setValor(int opt) {
        switch (opt) {
            case 1:
                this.valor = 1.0;
                this.estaPresente = true;
                break;
            case 2:
                this.valor = 0.0;
                this.estaPresente = false;
                break;
            case 3:
                this.valor = 0.0;
                this.estaPresente = false;
                this.estaJustificado = true;
                break;
        }
    }

    /**
     * Permite obtener el valor numérico de la asistencia
     *
     * @return Valor numérico entre 0 y 1 que representa la asistencia del alumno.
     */
    public double getValor() {
        return valor;
    }

    /**
     * Permite obtener el valor de verdad sobre si el alumno está como presente
     *
     * @return Si el alumno está presente.
     */
    public boolean presente() {
        return this.estaPresente;
    }

    /**
     * Permite obtener el valor de verdad sobre si el alumno está ausente, pero justificado.
     *
     * @return Si la ausencia del alumno está justificada.
     */
    public boolean justificado() {
        return this.estaJustificado;
    }

    /**
     * Permite obtener el valor de verdad sobre si el alumno realizó retiro.
     *
     * @return Si el alumno realizó retiro.
     */
    public boolean retirado() {
        return this.hizoRetiro;
    }

    /**
     * Permite justificar la inasistencia del alumno
     */
    public void justificar() {
        this.estaJustificado = true;
    }

    /**
     * Permite generar un retiro en el registro de asistencia específico.
     *
     * @param valor Valor numérico con el porcentaje del día que el alumno estuvo presente.
     */
    public void retirar(double valor) {
        this.valor = valor;
        this.hizoRetiro = true;
    }

}