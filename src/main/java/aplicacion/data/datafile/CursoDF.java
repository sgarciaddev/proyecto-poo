package aplicacion.data.datafile;

import aplicacion.data.CursoData;
import aplicacion.models.Alumno;
import aplicacion.models.Curso;
import aplicacion.models.IDCurso;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que permite la interacción con el archivo de texto plano que almancena
 * los datos del Curso. Implementa la interfaz CursoData.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 3.0
 */
public class CursoDF implements CursoData {

    private final Datafile datafile;
    private final ProfesorDF prDatafile;
    private final AlumnoDF alDatafile;

    /**
     * Constructor de CursoDatafile. Trabaja con un objeto Datafile.
     */
    public CursoDF() {
        this.datafile = new Datafile("curso");
        this.prDatafile = new ProfesorDF();
        this.alDatafile = new AlumnoDF();
    }

    /**
     * Permite transformar un objeto de tipo Curso, en una línea con formato CSV
     *
     * @param curso Curso
     * @return String con la línea en formato CSV correspondiente al curso
     */
    public String cursoToCSV(Curso curso) {
        List<String> dataList = new ArrayList<>();
        dataList.add(Short.toString(curso.getNivel()));
        dataList.add(Character.toString(curso.getParalelo()));
        dataList.add(curso.getProfesorJefe().getRut());
        return Datafile.listToCSV(dataList);
    }

    /**
     * Permite obtener un objeto de tipo Curso, desde un CSV
     *
     * @param csv String con la línea CSV con los datos del curso
     * @return Objeto Curso con los datos del curso.
     */
    public Curso cursoFromCSV(String csv) {
        String[] parts = csv.split(",");
        return new Curso(Short.parseShort(parts[0]), parts[1].charAt(0), prDatafile.getProfesor(parts[2]),
                alDatafile.getAlumnos(Short.parseShort(parts[0]), parts[1].charAt(0)));
    }

    /**
     * Permite obtener todos los cursos almacenados en el archivo CSV
     *
     * @return ArrayList de cursos
     */
    @Override
    public List<Curso> getCursos() {
        List<Curso> cursos = new ArrayList<>();
        List<String> data = this.datafile.getData();
        for (String csv : data)
            cursos.add(cursoFromCSV(csv));
        return cursos;
    }

    /**
     * Permite obtener los cursos de cierto nivel, almacenados en el archivo CSV
     *
     * @param nivel Nivel que se desea filtrar
     * @return ArrayList de cursos del nivel especificado
     */
    @Override
    public List<Curso> getCursos(short nivel) {
        List<Curso> cursos = new ArrayList<>();
        List<String> data = this.datafile.getData();
        for (String csv : data)
            if (Short.parseShort(csv.split(",")[0]) == nivel)
                cursos.add(cursoFromCSV(csv));
        return cursos;
    }

    /**
     * Permite obtener un curso en especifico, almacenado en el archivo CSV.
     *
     * @param nivel    Nivel del curso
     * @param paralelo Letra identificadora de paralelo
     * @return Curso solicitado
     */
    @Override
    public Curso getCurso(short nivel, char paralelo) {
        List<String> data = this.datafile.getData();
        for (String csv : data)
            if ((Integer.parseInt(csv.split(",")[0]) == nivel) && (csv.split(",")[1].charAt(0) == paralelo))
                return cursoFromCSV(csv);
        return null;
    }

    /**
     * Permite obtener un curso en especifico, almacenado en el archivo CSV.
     *
     * @param idCurso IDCurso con el nivel y paralelo del curso.
     * @return Curso solicitado
     */
    @Override
    public Curso getCurso(IDCurso idCurso) {
        List<String> data = this.datafile.getData();
        for (String csv : data)
            if ((Integer.parseInt(csv.split(",")[0]) == idCurso.nivel) && (csv.split(",")[1].charAt(0) == idCurso.paralelo))
                return cursoFromCSV(csv);
        return null;
    }

    /**
     * Agrega un Curso al archivo CSV
     *
     * @param curso Curso que se desea agregar al archivo CSV
     */
    @Override
    public void insertCurso(Curso curso) {
        if (this.prDatafile.insertProfesor(curso.getProfesorJefe())) {
            this.datafile.insertLine(cursoToCSV(curso));
        }
    }

    /**
     * Actualiza los datos de un Curso en el archivo CSV
     *
     * @param curso Curso a actualizar
     * @return Valor de verdad (boolean) sobre el exito o fracaso de la operacion de actualizacion
     */
    @Override
    public boolean updateCurso(Curso curso) {
        String oldLine = cursoToCSV(getCurso(curso.getNivel(), curso.getParalelo())), newLine = cursoToCSV(curso);
        return this.datafile.updateLine(oldLine, newLine);
    }

    /**
     * Elimina un curso del archivo CSV
     *
     * @param curso Curso a eliminar
     * @return Valor de verdad (boolean) sobre el exito o fracaso de la operacion de borrado
     */
    @Override
    public boolean deleteCurso(Curso curso) {
        for (Alumno alumno: curso.getAlumnos().values()) {
            this.alDatafile.deleteAlumno(alumno);
        }
        this.prDatafile.deleteProfesor(curso.getProfesorJefe());
        return this.datafile.deleteLine(cursoToCSV(curso));
    }
}