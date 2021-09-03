package models;

/**
 * Clase que define un apoderado. Clase hija de Persona.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 1.0
 */
public class Apoderado extends Persona {
    private int telefono;
    private String email;

    /**
     * @param rut RUT del apoderado
     * @param nombres Nombres del apoderado
     * @param apPaterno Apellido paterno del apoderado
     * @param apMaterno Apellido materno del apoderado
     * @param telefono Teléfono del apoderado
     * @param email Correo electrónico del apoderado
     */
    public Apoderado(String rut, String nombres, String apPaterno, String apMaterno, int telefono, String email) {
        super(rut, nombres, apPaterno, apMaterno);
        this.telefono = telefono;
        this.email = email;
    }

    /**
     * @return Teléfono de contacto del apoderado
     */
    public int getTelefono() {
        return telefono;
    }

    /**
     * @return Correo electrónico del apoderado
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param telefono Teléfono de contacto del apoderado
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    /**
     * @param email Correo electrónico del apoderado
     */
    public void setEmail(String email) {
        this.email = email;
    }
}