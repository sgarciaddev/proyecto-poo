package aplicacion.models;


import java.sql.Date;

public class IDAsistencia {
    public String rutAlumno;
    public Date fecha;

    public IDAsistencia(String rutAlumno, Date fecha) {
        this.rutAlumno = rutAlumno;
        this.fecha = fecha;
    }



}