package test;

import aplicacion.data.CursoData;
import aplicacion.models.Curso;

import javax.swing.*;
import java.util.List;

public class CursosComboBoxModel extends DefaultComboBoxModel<String> {

    private final CursoData cursoData;
    private final List<Curso> cursoList;

    private static String[] getCursoData(CursoData cursoData) {
        String[] data = new String[cursoData.getCursos().size()];
        int i = 0;
        for (Curso curso : cursoData.getCursos()) {
            data[i] = curso.cursoToString();
            i++;
        }
        return data;
    }

    public CursosComboBoxModel(CursoData cursoData) {
        super(getCursoData(cursoData));
        this.cursoData = cursoData;
        this.cursoList = cursoData.getCursos();
    }

    @Override
    public Object getSelectedItem() {
        return ((Curso) super.getSelectedItem()).cursoToString();
    }
}
