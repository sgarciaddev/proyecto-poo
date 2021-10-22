package aplicacion.data.database;

import aplicacion.data.CursoData;
import aplicacion.models.Curso;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CursoDB implements CursoData {

    private final AlumnoDB alumnoData;
    private final ProfesorDB profesorData;

    public CursoDB() {
        this.alumnoData = new AlumnoDB();
        this.profesorData = new ProfesorDB();
    }

    public static final String GET_TODOS_CURSOS = "SELECT * FROM Cursos";
    public static final String GET_CURSOS_NIVEL = "SELECT * FROM Cursos WHERE nivel = %h";
    public static final String GET_CURSO = "SELECT * FROM Cursos WHERE nivel = %d AND paralelo = '%c'";
    public static final String INSERT_CURSO = "INSERT INTO Cursos (nivel, paralelo, rut_profesor) VALUES (%d, '%c', '%s')";
    public static final String UPDATE_CURSO = "UPDATE Alumnos SET nivel = %d, paralelo = '%c' , rut_profesor = '%s' " +
                    "WHERE nivel = %d AND paralelo = '%c'";
    public static final String DELETE_CURSO = "DELETE FROM Cursos WHERE nivel = %d AND paralelo = '%c'";

    @Override
    public List<Curso> getCursos() {
        List<Curso> cursos = new ArrayList<>();
        try {
            ResultSet resultados = DBConnection.getQuery(GET_TODOS_CURSOS);
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

    @Override
    public List<Curso> getCursos(short nivel) {
        List<Curso> cursos = new ArrayList<>();
        try {
            ResultSet resultados = DBConnection.getQuery(String.format(GET_CURSOS_NIVEL, nivel));
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

    @Override
    public Curso getCurso(short nivel, char paralelo) {
        Curso curso;
        try {
            ResultSet resultados = DBConnection.getQuery(String.format(GET_CURSO, nivel, paralelo));
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

    @Override
    public boolean insertCurso(Curso curso) {
        if (DBConnection.updateQuery(String.format(INSERT_CURSO,
                curso.getNivel(),
                curso.getParalelo(),
                curso.getProfesorJefe().getRut())) == 0)
            return false;
        return true;
    }

    @Override
    public boolean updateCurso(Curso curso) {
        if (DBConnection.updateQuery(String.format(UPDATE_CURSO,
                curso.getNivel(),
                curso.getParalelo(),
                curso.getProfesorJefe().getRut(),
                curso.getNivel(),
                curso.getParalelo())) == 1)
            return true;
        return false;
    }

    @Override
    public boolean deleteCurso(Curso curso) {
        if (DBConnection.updateQuery(String.format(DELETE_CURSO,
                curso.getNivel(),
                curso.getParalelo())) == 1)
            return true;
        return false;
    }
}
