package data.database;

import data.ProfesorData;
import models.Curso;
import models.Profesor;

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