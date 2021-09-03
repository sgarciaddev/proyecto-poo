package models;

/**
 * Clase que define un profesor.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 1.0
 */
public class Profesor extends Persona {
    private String asignatura;
    private String email;
    private int telefono;

    /**
     * @param rut RUT del profesor
     * @param nombres Nombres del profesor
     * @param apPaterno Apellido paterno del profesor
     * @param apMaterno Apellido materno del profesor
     * @param asignatura Asignatura que dicta el profesor
     * @param email Correo electrónico del profesor
     * @param telefono Teléfono de contacto del profesor
     */
    public Profesor(String rut, String nombres, String apPaterno, String apMaterno, String asignatura, String email,
                    int telefono) {
        super(rut, nombres, apPaterno, apMaterno);
        this.asignatura = asignatura;
        this.telefono = telefono;
        this.email = email;
    }

    /**
     * @return Asignatura que dicta el profesor
     */
    public String getAsignatura() {
        return asignatura;
    }

    /**
     * @return Correo electrónico del profesor
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return Teléfono de contacto del profesor
     */
    public int getTelefono() {
        return telefono;
    }

    /**
     * @param asignatura Asignatura que dicta el profesor
     */
    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    /**
     * @param email Correo electrónico del profesor
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param telefono Teléfono de contacto del profesor
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
}
