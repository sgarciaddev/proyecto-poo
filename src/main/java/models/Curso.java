package models;

import java.util.Map;

/**
 * Clase que define un curso. Clase hija de Nivel.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 1.0
 */
public class Curso {
    private short nivel;
    private char letra;
    private Profesor profesorJefe;
    private Map<String, Alumno> alumnos;

    /**
     * @param nivel Nivel (de 1 a 12) del curso
     * @param letra Caracter identificador del paralelo del curso
     * @param profesorJefe Profesor jefe a cargo del curso
     * @param alumnos Lista de alumnos del curso
     */
    public Curso(short nivel, char letra, Profesor profesorJefe, Map<String, Alumno> alumnos) {
        this.nivel = nivel;
        this.letra = letra;
        this.profesorJefe = profesorJefe;
        this.alumnos = alumnos;
    }

    /**
     * @return Valor numérico del nivel (de 1 a 12)
     */
    public short getNivel() {
        return nivel;
    }

    /**
     * @return Caracter identificador del paralelo del curso
     */
    public char getLetra() {
        return letra;
    }

    /**
     * @return Profesor jefe a cargo del curso
     */
    public Profesor getProfesorJefe() {
        return profesorJefe;
    }

    /**
     * @return Lista de alumnos del curso
     */
    public Map<String, Alumno> getAlumnos() {
        return alumnos;
    }

    /**
     * @param profesorJefe Profesor jefe a cargo del curso
     */
    public void setProfesorJefe(Profesor profesorJefe) {
        this.profesorJefe = profesorJefe;
    }

    /**
     * @param alumnos Lista de alumnos del curso
     */
    public void setAlumnos(Map<String, Alumno> alumnos) {
        this.alumnos = alumnos;
    }

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
        return this.nivelToString() + " " + Character.toUpperCase(this.letra);
    }

    public String toShortStr() {
        if (this.nivel >= 9) {
            return Integer.toString(this.nivel - 8) + "M" + Character.toUpperCase(this.letra);
        } else {
            return Integer.toString(this.nivel) + "B" + Character.toUpperCase(this.letra);
        }
    }
}