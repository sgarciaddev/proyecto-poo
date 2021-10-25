package aplicacion.data;

import aplicacion.models.IDAsistencia;
import aplicacion.models.RegistroAsistencia;

import java.sql.Date;
import java.util.HashMap;

public interface RegistroAsistenciaData {

    RegistroAsistencia get(IDAsistencia id);
    HashMap<IDAsistencia, RegistroAsistencia> getRegistroAsistencia();
    HashMap<IDAsistencia, RegistroAsistencia> getRegistroAsistencia(Date fecha);
    HashMap<IDAsistencia, RegistroAsistencia> getRegistroAsistencia(String rutAlumno);
    HashMap<IDAsistencia, RegistroAsistencia> getRegistroAsistencia(IDAsistencia idAsistencia);
    void insertarRegistroAsistencia(RegistroAsistencia registroAsistencia);
    void actualizarRegistroAsistencia(RegistroAsistencia registroAsistencia);
    void eliminarRegistroAsistencia(RegistroAsistencia registroAsistencia);

}
