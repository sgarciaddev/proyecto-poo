package aplicacion.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase que define un curso. Clase hija de Nivel.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 1.0
 */
public class Curso {
    private short nivel;
    private char paralelo;
    private Profesor profesorJefe;
    private Map<String, Alumno> alumnos;

    /**
     * Genera un objeto de tipo Curso
     *
     * @param nivel        Nivel (de 1 a 12) del curso
     * @param paralelo        Caracter identificador del paralelo del curso
     * @param profesorJefe Profesor jefe a cargo del curso
     * @param alumnos      Lista de alumnos del curso
     */
    public Curso(short nivel, char paralelo, Profesor profesorJefe, Map<String, Alumno> alumnos) {
        this.nivel = nivel;
        this.paralelo = paralelo;
        this.profesorJefe = profesorJefe;
        this.alumnos = alumnos;
    }

    /**
     * Genera un objeto de tipo Curso
     *
     * @param nivel        Nivel (de 1 a 12) del curso
     * @param paralelo        Caracter identificador del paralelo del curso
     * @param profesorJefe Profesor jefe a cargo del curso
     */
    public Curso(short nivel, char paralelo, Profesor profesorJefe) {
        this.nivel = nivel;
        this.paralelo = paralelo;
        this.profesorJefe = profesorJefe;
        this.alumnos = new HashMap<String, Alumno>();
    }

    /**
     * Método estático que permite obtener el curso como texto de un curso determinado
     *
     * @param nivel Nivel del curso
     * @param paralelo Paralelo del curso
     * @return Curso en formato texto. Ejemplo: <code>1 básico A</code>.
     */
    public static String cursoToString(int nivel, char paralelo) {
        String curso = "";
        if (nivel < 9) {
            curso = curso.concat(Integer.toString(nivel));
            curso = curso.concat(" básico ");
        } else {
            curso = curso.concat(Integer.toString(nivel - 8));
            curso = curso.concat(" medio ");
        }
        curso = curso.concat(Character.toString(paralelo));
        return curso;
    }

    /**
     * Obtiene el nivel del curso
     *
     * @return Valor numérico del nivel (de 1 a 12)
     */
    public short getNivel() {
        return nivel;
    }

    /**
     * Obtiene la paralelo identificadora de paralelo del curso
     *
     * @return Caracter identificador del paralelo del curso
     */
    public char getParalelo() {
        return paralelo;
    }

    /**
     * Obtiene el Profesor jefe del curso
     *
     * @return Profesor jefe a cargo del curso
     */
    public Profesor getProfesorJefe() {
        return profesorJefe;
    }

    /**
     * Obtiene el HashMap con los alumnos del curso
     *
     * @return HashMap de alumnos del curso
     */
    public Map<String, Alumno> getAlumnos() {
        return alumnos;
    }

    /**
     * Actualiza al profesor jefe del curso.
     *
     * @param profesorJefe Profesor jefe a cargo del curso
     */
    public void setProfesorJefe(Profesor profesorJefe) {
        this.profesorJefe = profesorJefe;
    }

    /**
     * Actualiza el HashMap de alumnos del curso
     *
     * @param alumnos Lista de alumnos del curso
     */
    public void setAlumnos(Map<String, Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    /**
     * Permite obtener el nivel del curso en palabras, para ser mostrado por pantalla.
     *
     * @return String con el nivel del curso
     */
    public String nivelToString() {
        if (this.nivel >= 9) {
            return Integer.toString(this.nivel - 8) + " medio";
        } else {
            return Integer.toString(this.nivel) + " básico";
        }
    }

    /**
     * Método que permite obtener el curso en palabras, para ser mostrado por pantalla.
     *
     * @return String con el curso en palabras
     */
    public String cursoToString() {
        return this.nivelToString() + " " + Character.toUpperCase(this.paralelo);
    }

    /**
     * Permite obtener el curso en formato nivel-paralelo. Ejemplo: `4MB`
     *
     * @return String con el curso en formato corto
     */
    public String toShortStr() {
        if (this.nivel >= 9) {
            return Integer.toString(this.nivel - 8) + "M" + Character.toUpperCase(this.paralelo);
        } else {
            return Integer.toString(this.nivel) + "B" + Character.toUpperCase(this.paralelo);
        }
    }
}