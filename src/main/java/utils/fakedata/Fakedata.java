package utils.fakedata;

import aplicacion.data.datafile.AlumnoDatafile;
import aplicacion.data.datafile.ApoderadoDatafile;
import aplicacion.data.datafile.CursoDatafile;
import aplicacion.data.datafile.ProfesorDatafile;
import aplicacion.models.*;
import com.github.javafaker.Faker;
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
    private final ApoderadoDatafile apoderadoDatafile;
    private final CursoDatafile cursoDatafile;
    private final ProfesorDatafile profesorDatafile;
    private final Faker faker;

    /**
     * Constructor del objeto Fakedata. Interactúa con los Datafile.
     */
    public Fakedata() {
        this.alumnoDatafile = new AlumnoDatafile();
        this.apoderadoDatafile = new ApoderadoDatafile();
        this.cursoDatafile = new CursoDatafile();
        this.profesorDatafile = new ProfesorDatafile();
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
        rut = Integer.toString(this.faker.number().numberBetween(25000000, 35000000))
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
        rut = Integer.toString(this.faker.number().numberBetween(40000000, 50000000))
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
        RegistroAsistencia registroAsistencia = new RegistroAsistencia();
        float randomNormal, randomDistribucionAsistir, randomDistribucionRetirarse;
        Random random = new Random();
        randomDistribucionAsistir = (float) (random.nextGaussian() * 15 + 75);
        randomDistribucionRetirarse = (float) (random.nextGaussian() * 6 + 13);

        for (int mes = 0; mes <= 9; mes++) {
            for (int dia = 0; dia <= 30; dia++) {
                randomNormal = random.nextFloat() * 100.0f;
                if (randomNormal <= randomDistribucionAsistir) {
                    randomNormal = random.nextFloat() * 100.0f;
                    if (randomNormal <= randomDistribucionRetirarse)
                        registroAsistencia.registrarAsistencia(0.5f, mes, dia);
                    else
                        registroAsistencia.registrarAsistencia(1.0f, mes, dia);
                }else
                    registroAsistencia.registrarAsistencia(0.0f, mes, dia);
            }
        }
        return registroAsistencia;
    }

    /**
     * Genera profesor ficticio
     *
     * @return Profesor ficticio
     */
    public Profesor generateProfesor() {
        String rut, nombres, apPat, apMat, email, asignatura;
        int telefono;
        rut = Integer.toString(this.faker.number().numberBetween(25000000, 35000000))
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
                profesorDatafile.insertProfesor(profesor);
                cursoDatafile.insertCurso(new Curso(i, j, profesor, null));
                for (int k = 0; k < 15; k++) {
                    apoderado = generateApoderado();
                    alumno = generateAlumno(apoderado, i, j);
                    alumno.setAsistencia(generateAsistencia());
                    alumnoDatafile.insertAlumno(alumno);
                }
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
    }

}