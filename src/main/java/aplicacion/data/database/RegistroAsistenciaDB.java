package aplicacion.data.database;

import aplicacion.data.RegistroAsistenciaData;
import aplicacion.models.IDAsistencia;
import aplicacion.models.RegistroAsistencia;

import java.sql.Date;
import java.util.HashMap;

public class RegistroAsistenciaDB implements RegistroAsistenciaData {

    @Override
    public RegistroAsistencia get(IDAsistencia id) {
        return null;
    }

    @Override
    public HashMap<IDAsistencia, RegistroAsistencia> getRegistroAsistencia() {
        return null;
    }

    @Override
    public HashMap<IDAsistencia, RegistroAsistencia> getRegistroAsistencia(Date fecha) {
        return null;
    }

    @Override
    public HashMap<IDAsistencia, RegistroAsistencia> getRegistroAsistencia(String rutAlumno) {
        return null;
    }

    @Override
    public HashMap<IDAsistencia, RegistroAsistencia> getRegistroAsistencia(IDAsistencia idAsistencia) {
        return null;
    }

    @Override
    public void insertarRegistroAsistencia(RegistroAsistencia registroAsistencia) {

    }

    @Override
    public void actualizarRegistroAsistencia(RegistroAsistencia registroAsistencia) {

    }

    @Override
    public void eliminarRegistroAsistencia(RegistroAsistencia registroAsistencia) {

    }
}
