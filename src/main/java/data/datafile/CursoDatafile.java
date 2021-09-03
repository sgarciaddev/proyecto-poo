package data.datafile;

import data.CursoData;
import models.Curso;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que permite la interacción con el archivo de texto plano que almancena
 * los datos del Curso. Implementa la interfaz CursoData.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 1.0
 */
public class CursoDatafile implements CursoData {

    private final Datafile datafile;
    private final ProfesorDatafile prDatafile;
    private final AlumnoDatafile alDatafile;

    public CursoDatafile() {
        this.datafile = new Datafile("curso");
        this.prDatafile = new ProfesorDatafile();
        this.alDatafile = new AlumnoDatafile();
    }

    public String cursoToCSV(Curso curso) {
        List<String> dataList = new ArrayList<>();
        dataList.add(Short.toString(curso.getNivel()));
        dataList.add(Character.toString(curso.getLetra()));
        dataList.add(curso.getProfesorJefe().getRut());
        return Datafile.listToCSV(dataList);
    }

    public Curso cursoFromCSV(String csv) {
        String[] parts = csv.split(",");
        return new Curso(Short.parseShort(parts[0]), parts[1].charAt(0), prDatafile.getProfesor(parts[2]),
                alDatafile.getAlumnos(Short.parseShort(parts[0]), parts[1].charAt(0)));
    }

    @Override
    public List<Curso> getCursos() {
        List<Curso> cursos = new ArrayList<>();
        List<String> data = this.datafile.getData();
        for (String csv: data)
            cursos.add(cursoFromCSV(csv));
        return cursos;
    }

    @Override
    public List<Curso> getCursos(short nivel) {
        List<Curso> cursos = new ArrayList<>();
        List<String> data = this.datafile.getData();
        for (String csv: data)
            if (Short.parseShort(csv.split(",")[0]) == nivel)
                cursos.add(cursoFromCSV(csv));
        return cursos;
    }

    @Override
    public Curso getCurso(short nivel, char letra) {
        List<String> data = this.datafile.getData();
        for (String csv: data)
            if ((Integer.parseInt(csv.split(",")[0]) == nivel) && (csv.split(",")[1].charAt(0) == letra))
                return cursoFromCSV(csv);
        return null;
    }

    @Override
    public boolean insertCurso(Curso curso) {
        return this.datafile.insertLine(cursoToCSV(curso));
    }

    @Override
    public boolean updateCurso(Curso curso) {
        String oldLine = cursoToCSV(getCurso(curso.getNivel(), curso.getLetra())), newLine = cursoToCSV(curso);
        return this.datafile.updateLine(oldLine, newLine);
    }

    @Override
    public boolean deleteCurso(Curso curso) {
        return this.datafile.deleteLine(cursoToCSV(curso));
    }
}