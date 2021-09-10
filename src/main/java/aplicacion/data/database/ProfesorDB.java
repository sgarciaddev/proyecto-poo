package aplicacion.data.database;

import aplicacion.data.ProfesorData;
import aplicacion.models.Curso;
import aplicacion.models.Profesor;

import java.util.List;

public class ProfesorDB implements ProfesorData {
    @Override
    public List<Profesor> getProfesores() {
        return null;
    }

    @Override
    public Profesor getProfesor(String rut) {
        return null;
    }

    @Override
    public Profesor getProfesorJefe(Curso curso) {
        return null;
    }

    @Override
    public boolean insertProfesor(Profesor profesor) {
        return false;
    }

    @Override
    public boolean updateProfesor(Profesor profesor) {
        return false;
    }

    @Override
    public boolean deleteProfesor(Profesor profesor) {
        return false;
    }
}