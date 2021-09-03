package data;

import models.Alumno;

import java.util.Map;

/**
 * Interfaz que permite interactuar con los datos de Alumnos.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 1.0
 */
public interface AlumnoData {
    public Map<String, Alumno> getAlumnos();
    public Map<String, Alumno> getAlumnos(int nivel);
    public Map<String, Alumno> getAlumnos(int nivel, char letra);
    public Alumno getAlumno(String rut);
    public boolean insertAlumno(Alumno alumno, int nivel, char letra);
    public boolean updateAlumno(Alumno alumno, int nivel, char letra);
    public boolean deleteAlumno(Alumno alumno, int nivel, char letra);
}