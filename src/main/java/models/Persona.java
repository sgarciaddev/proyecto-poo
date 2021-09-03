package models;

/**
 * Clase que define una persona.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 1.0
 */
public class Persona {
    private String rut, nombres, apPaterno, apMaterno;

    /**
     * Genera un objeto de tipo Persona
     *
     * @param rut       RUT de la persona
     * @param nombres   Nombres de la persona
     * @param apPaterno Apellido paterno de la persona
     * @param apMaterno Apellido materno de la persona
     */
    public Persona(String rut, String nombres, String apPaterno, String apMaterno) {
        this.rut = rut;
        this.nombres = nombres;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
    }

    /**
     * Obtiene el RUT de la persona
     *
     * @return RUT de la persona
     */
    public String getRut() {
        return rut;
    }

    /**
     * Obtiene los nombres de la persona
     *
     * @return Nombres de la persona
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Obtiene el apellido paterno de la persona
     *
     * @return Apellido paterno de la persona
     */
    public String getApPaterno() {
        return apPaterno;
    }

    /**
     * Obtiene el apellido materno de la persona
     *
     * @return Apellido materno de la persona
     */
    public String getApMaterno() {
        return apMaterno;
    }

    /**
     * Actualiza los nombres de la persona
     *
     * @param nombres Nombres de la persona
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Actualiza el apellido paterno de la persona
     *
     * @param apPaterno Apellido paterno de la persona
     */
    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    /**
     * Actualiza el apellido materno de la persona
     * @param apMaterno Apellido materno de la persona
     */
    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    /**
     * Método que permite obtener el nombre completo de la persona.
     *
     * @return String con el nombre completo (nombres y dos apellidos).
     */
    public String getNombreCompleto() {
        return this.nombres + " " + this.apPaterno + " " + this.apMaterno;
    }
}