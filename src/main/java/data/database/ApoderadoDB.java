package data.database;

import data.ApoderadoData;
import models.Apoderado;

public class ApoderadoDB implements ApoderadoData {
    @Override
    public Apoderado getApoderado(String rut) {
        return null;
    }

    @Override
    public boolean updateApoderado(Apoderado apoderado) {
        return false;
    }

    @Override
    public boolean insertApoderado(Apoderado apoderado) {
        return false;
    }

    @Override
    public boolean deleteApoderado(Apoderado apoderado) {
        return false;
    }
}