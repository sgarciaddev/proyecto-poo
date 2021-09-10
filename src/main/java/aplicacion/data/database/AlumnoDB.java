package aplicacion.data.database;

import aplicacion.data.AlumnoData;
import aplicacion.models.Alumno;

import java.util.Map;

public class AlumnoDB implements AlumnoData {
    @Override
    public Map<String, Alumno> getAlumnos() {
        return null;
    }

    @Override
    public Map<String, Alumno> getAlumnos(int nivel) {
        return null;
    }

    @Override
    public Map<String, Alumno> getAlumnos(int nivel, char letra) {
        return null;
    }

    @Override
    public Alumno getAlumno(String rut) {
        return null;
    }

    @Override
    public boolean insertAlumno(Alumno alumno, int nivel, char letra) {
        return false;
    }

    @Override
    public boolean updateAlumno(Alumno alumno, int nivel, char letra) {
        return false;
    }

    @Override
    public boolean deleteAlumno(Alumno alumno, int nivel, char letra) {
        return false;
    }
}