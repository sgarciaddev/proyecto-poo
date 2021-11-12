package aplicacion.controllers.gui;

import aplicacion.data.AlumnoData;
import aplicacion.data.ApoderadoData;
import aplicacion.data.CursoData;
import aplicacion.data.ProfesorData;

public class MenuGUI {

    private final AlumnoData alumnoData;
    private final ApoderadoData apoderadoData;
    private final CursoData cursoData;
    private final ProfesorData profesorData;

    public MenuGUI(AlumnoData alumnoData, ApoderadoData apoderadoData, CursoData cursoData, ProfesorData profesorData) {
        this.alumnoData = alumnoData;
        this.apoderadoData = apoderadoData;
        this.cursoData = cursoData;
        this.profesorData = profesorData;
    }

    public AlumnoData getAlumnoData() {
        return alumnoData;
    }

    public ApoderadoData getApoderadoData() {
        return apoderadoData;
    }

    public CursoData getCursoData() {
        return cursoData;
    }

    public ProfesorData getProfesorData() {
        return profesorData;
    }
}
