package aplicacion.models;

/**
 * Clase que define un profesor.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 2.0
 */
public class Profesor extends Persona {
    private String asignatura;
    private String email;
    private int telefono;

    /**
     * Genera un objeto de tipo Profesor
     *
     * @param rut        RUT del profesor
     * @param nombres    Nombres del profesor
     * @param apPaterno  Apellido paterno del profesor
     * @param apMaterno  Apellido materno del profesor
     * @param asignatura Asignatura que dicta el profesor
     * @param email      Correo electrónico del profesor
     * @param telefono   Teléfono de contacto del profesor
     */
    public Profesor(String rut, String nombres, String apPaterno, String apMaterno, String asignatura, String email,
                    int telefono) {
        super(rut, nombres, apPaterno, apMaterno);
        this.asignatura = asignatura;
        this.telefono = telefono;
        this.email = email;
    }

    /**
     * Obtiene la asignatura que dicta el profesor
     *
     * @return Asignatura que dicta el profesor
     */
    public String getAsignatura() {
        return asignatura;
    }

    /**
     * Obtiene el correo electrónico de contacto del profesor
     *
     * @return Correo electrónico del profesor
     */
    public String getEmail() {
        return email;
    }

    /**
     * Obtiene el teléfono de contacto del profesor
     *
     * @return Teléfono de contacto del profesor
     */
    public int getTelefono() {
        return telefono;
    }

    /**
     * Actualiza la asignatura que dicta el profesor
     *
     * @param asignatura Asignatura que dicta el profesor
     */
    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    /**
     * Actualiza el correo electrónico de contacto del profesor
     *
     * @param email Correo electrónico del profesor
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Actualiza el teléfono de contacto del profesor
     *
     * @param telefono Teléfono de contacto del profesor
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return super.toString("Profesor") +
                "    -> Asignatura       : " + asignatura + "\n" +
                "    Datos de contacto\n" +
                "      -> Teléfono       : " + telefono + "\n" +
                "      -> Email          : " + email + "\n";
    }

    @Override
    public String toString(String titulo) {
        return super.toString(titulo) +
                "    -> Asignatura       : " + asignatura + "\n" +
                "    Datos de contacto\n" +
                "      -> Teléfono       : " + telefono + "\n" +
                "      -> Email          : " + email + "\n";
    }
}
