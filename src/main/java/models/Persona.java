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
     * @param rut RUT de la persona
     * @param nombres Nombres de la persona
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
     * @return RUT de la persona
     */
    public String getRut() {
        return rut;
    }

    /**
     * @return Nombres de la persona
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * @return Apellido paterno de la persona
     */
    public String getApPaterno() {
        return apPaterno;
    }

    /**
     * @return Apellido materno de la persona
     */
    public String getApMaterno() {
        return apMaterno;
    }

    /**
     * @param nombres Nombres de la persona
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * @param apPaterno Apellido paterno de la persona
     */
    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    /**
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