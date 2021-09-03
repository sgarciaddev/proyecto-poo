package data.database;

import data.CursoData;
import models.Curso;

import java.util.List;

public class CursoDB implements CursoData {
    @Override
    public List<Curso> getCursos() {
        return null;
    }

    @Override
    public List<Curso> getCursos(short nivel) {
        return null;
    }

    @Override
    public Curso getCurso(short nivel, char letra) {
        return null;
    }

    @Override
    public boolean insertCurso(Curso curso) {
        return false;
    }

    @Override
    public boolean updateCurso(Curso curso) {
        return false;
    }

    @Override
    public boolean deleteCurso(Curso curso) {
        return false;
    }
}
