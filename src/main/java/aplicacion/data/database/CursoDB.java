package aplicacion.data.database;

import aplicacion.data.CursoData;
import aplicacion.models.Curso;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que permite la interacción con la base de datos MySQL que almacena
 * los datos del Curso. Implementa la interfaz CursoData.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 2.0
 */
public class CursoDB implements CursoData {

    private final AlumnoDB alumnoData;
    private final ProfesorDB profesorData;

    /**
     * Constructor de CursoDB. Trabaja con una base de datos MySQL.
     */
    public CursoDB() {
        this.alumnoData = new AlumnoDB();
        this.profesorData = new ProfesorDB();
    }

    /**
     * Permite obtener todos los cursos almacenados en la base de datos MySQL.
     *
     * @return ArrayList de cursos
     */
    @Override
    public List<Curso> getCursos() {
        List<Curso> cursos = new ArrayList<>();
        try {
            ResultSet resultados = DBConnection.getQuery(SQLSentences.GET_TODOS_CURSOS.toString());
            if (resultados == null) return null;
            while (resultados.next()) {
                cursos.add(new Curso(
                        resultados.getShort("nivel"),
                        resultados.getString("paralelo").charAt(0),
                        this.profesorData.getProfesor(resultados.getString("rut_profesor"))));
                cursos.get(cursos.size() - 1).setAlumnos(alumnoData.getAlumnos(cursos.get(cursos.size() - 1).getNivel(), cursos.get(cursos.size() - 1).getParalelo()));
            }
            return cursos;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Permite obtener los cursos de cierto nivel, almacenados en la base de datos MySQL
     *
     * @param nivel Nivel que se desea filtrar
     * @return ArrayList de cursos del nivel especificado
     */
    @Override
    public List<Curso> getCursos(short nivel) {
        List<Curso> cursos = new ArrayList<>();
        try {
            ResultSet resultados = DBConnection.getQuery(String.format(SQLSentences.GET_CURSOS_NIVEL.toString(), nivel));
            if (resultados == null) return null;
            while (resultados.next()) {
                cursos.add(new Curso(
                        resultados.getShort("nivel"),
                        resultados.getString("paralelo").charAt(0),
                        this.profesorData.getProfesor(resultados.getString("rut_profesor"))));
                cursos.get(cursos.size() - 1).setAlumnos(alumnoData.getAlumnos(cursos.get(cursos.size() - 1).getNivel(), cursos.get(cursos.size() - 1).getParalelo()));
            }
            return cursos;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Permite obtener un curso en específico, almacenado en la base de datos MySQL.
     *
     * @param nivel    Nivel del curso
     * @param paralelo Letra identificadora de paralelo
     * @return Curso solicitado
     */
    @Override
    public Curso getCurso(short nivel, char paralelo) {
        Curso curso;
        try {
            ResultSet resultados = DBConnection.getQuery(String.format(SQLSentences.GET_CURSO.toString(), nivel, paralelo));
            if (resultados == null) return null;
            if (resultados.next()) {
                curso = new Curso(resultados.getShort("nivel"), resultados.getString("paralelo").charAt(0),
                        this.profesorData.getProfesor(resultados.getString("rut_profesor")));
                curso.setAlumnos(alumnoData.getAlumnos(curso.getNivel(), curso.getParalelo()));
                return curso;
            }
        } catch (Exception e) {
            System.out.println(e);

        }
        return null;
    }

    /**
     * Agrega un Curso a la base de datos MySQL
     *
     * @param curso Curso que se desea agregar a la base de datos
     */
    @Override
    public void insertCurso(Curso curso) {
        DBConnection.updateQuery(String.format(SQLSentences.INSERT_CURSO.toString(),
                curso.getNivel(),
                curso.getParalelo(),
                curso.getProfesorJefe().getRut()));
    }

    /**
     * Actualiza un Curso en la base de datos MySQL
     *
     * @param curso Curso que se desea actualizar en la base de datos
     * @return Valor de verdad (boolean) sobre el éxito o fracaso de la operación de actualización
     */
    @Override
    public boolean updateCurso(Curso curso) {
        return DBConnection.updateQuery(String.format(SQLSentences.UPDATE_CURSO.toString(),
                curso.getNivel(),
                curso.getParalelo(),
                curso.getProfesorJefe().getRut(),
                curso.getNivel(),
                curso.getParalelo())) == 1;
    }

    /**
     * Elimina un Curso de la base de datos MySQL
     *
     * @param curso Curso que se desea eliminar de la base de datos.
     * @return Valor de verdad (boolean) sobre el éxito o fracaso de la operación de eliminación.
     */
    @Override
    public boolean deleteCurso(Curso curso) {
        return DBConnection.updateQuery(String.format(SQLSentences.DELETE_CURSO.toString(),
                curso.getNivel(),
                curso.getParalelo())) == 1;
    }
}
