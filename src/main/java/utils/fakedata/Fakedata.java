package utils.fakedata;

import aplicacion.data.RegistroAsistenciaData;
import aplicacion.data.datafile.*;
import aplicacion.models.*;
import com.github.javafaker.Faker;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Clase que permite generar datos falsos y almacenarlos en los archivos de
 * texto plano para trabajar con ellos en el programa principal. Utiliza la
 * librería externa Faker.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 2.0
 */
public class Fakedata {

    private final AlumnoDatafile alumnoDatafile;
    private final CursoDatafile cursoDatafile;
    private final RegistroAsistenciaData registrarAsistenciaData;
    private final Faker faker;

    /**
     * Constructor del objeto Fakedata. Interactúa con los Datafile.
     */
    public Fakedata() {
        this.alumnoDatafile = new AlumnoDatafile();
        this.cursoDatafile = new CursoDatafile();
        this.registrarAsistenciaData = new RegistroAsistenciaDF();
        this.faker = new Faker();
    }

    /**
     * Genera apoderado ficticio
     *
     * @return Apoderado ficticio
     */
    public Apoderado generateApoderado() {
        String rut, nombres, apPat, apMat, email;
        int telefono;
        rut = Integer.toString(this.faker.number().numberBetween(70000000, 90000000))
                + "-" + Integer.toString(this.faker.number().numberBetween(0, 9));
        nombres = this.faker.name().firstName();
        apPat = this.faker.name().lastName();
        apMat = this.faker.name().lastName();
        email = this.faker.internet().emailAddress();
        telefono = this.faker.number().numberBetween(940000000, 999999999);
        return new Apoderado(rut, nombres, apPat, apMat, telefono, email);
    }

    /**
     * Genera alumno ficticio
     *
     * @param ap Apoderado del alumno
     * @return Alumno ficticio
     */
    public Alumno generateAlumno(Apoderado ap, int nivel, char paralelo) {
        String rut, nombres, apMat;
        rut = Integer.toString(this.faker.number().numberBetween(30000000, 50000000))
                + "-" + Integer.toString(this.faker.number().numberBetween(0, 9));
        nombres = this.faker.name().firstName();
        apMat = this.faker.name().lastName();
        return new Alumno(rut, nombres, ap.getApPaterno(), apMat, nivel, paralelo, ap);
    }

    /**
     * Genera asistencia, ausencias y retiros aleatorios a un alumno a base de una distribución normal
     *
     * @return registroAsistencia Registro de asistencia con valores en dias inicializados
     */
    public RegistroAsistencia generateAsistencia(){
        return null;
    }

    /**
     * Genera profesor ficticio
     *
     * @return Profesor ficticio
     */
    public Profesor generateProfesor() {
        String rut, nombres, apPat, apMat, email, asignatura;
        int telefono;
        rut = Integer.toString(this.faker.number().numberBetween(70000000, 90000000))
                + "-" + Integer.toString(this.faker.number().numberBetween(0, 9));
        nombres = this.faker.name().firstName();
        apPat = this.faker.name().lastName();
        apMat = this.faker.name().lastName();
        email = this.faker.internet().emailAddress();
        telefono = this.faker.number().numberBetween(940000000, 999999999);
        asignatura = this.faker.educator().course();
        return new Profesor(rut, nombres, apPat, apMat, asignatura, email, telefono);
    }

    /**
     * Genera los datos ficticios y los almacena en archivos CSV
     */
    public void generateFakedata() {
        Alumno alumno;
        Apoderado apoderado;
        Profesor profesor;
        for (short i = 1; i < 13; i++) {
            for (char j = 65; j < 67; j++) {
                profesor = generateProfesor();
                cursoDatafile.insertCurso(new Curso(i, j, profesor, null));
                for (int k = 0; k < 15; k++) {
                    apoderado = generateApoderado();
                    alumno = generateAlumno(apoderado, i, j);
                    alumnoDatafile.insertAlumno(alumno);
                }
            }
        }
    }

    public RegistroAsistencia genRegAleatorio(IDAsistencia id) {
        double random = faker.number().randomDouble(2, 0, 1);
        final double PROB_RET = 0.05, PROB_AUS = 0.2;
        RegistroAsistencia registroAsistencia;

        if (random < PROB_RET) {
            registroAsistencia = new RegistroAsistencia(id, faker.number().randomDouble(1, 0, 1), true, false, true);
        } else if (random < PROB_AUS) {
            double random2 = faker.number().randomDouble(2, 0, 1);
            if (random2 < 0.75) {
                registroAsistencia = new RegistroAsistencia(id, 0.0, false, false, false);
            } else {
                registroAsistencia = new RegistroAsistencia(id, 0.0, false, true, false);
            }
        } else {
            registroAsistencia = new RegistroAsistencia(id, 1.0, true, false, false);
        }

        return registroAsistencia;

    }

    public void generateDatosAsistencia() {
        Map<String, Alumno> alumnos = this.alumnoDatafile.getAlumnos();
        IDAsistencia id;

        for (Map.Entry<String, Alumno> entry: alumnos.entrySet()) {
            for (int i = 2; i <= 31; i++) {
                if (((i - 2) % 5) == 0)
                    i += 2;
                id = new IDAsistencia(entry.getKey(), Date.valueOf(String.format("%s-%s-%02d", "2021", "08", i)));
                this.registrarAsistenciaData.insertarRegistroAsistencia(genRegAleatorio(id));
            }
        }

    }

    /**
     * Programa principal (método main) que genera los archivos con los datos iniciales ficticios.
     *
     * @param args Argumentos de ejecución (método main)
     */
    public static void main(String[] args) {
        Fakedata fakedata = new Fakedata();
        fakedata.generateFakedata();
        fakedata.generateDatosAsistencia();
    }

}