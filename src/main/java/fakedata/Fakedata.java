package fakedata;

import com.github.javafaker.Faker;
import data.datafile.*;
import models.*;

/**
 * Clase que permite generar datos falsos y almacenarlos en los archivos de
 * texto plano para trabajar con ellos en el programa principal. Utiliza la
 * librería externa Faker.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 1.0
 */
public class Fakedata {

    private final AlumnoDatafile alumnoDatafile;
    private final ApoderadoDatafile apoderadoDatafile;
    private final CursoDatafile cursoDatafile;
    private final ProfesorDatafile profesorDatafile;
    private final Faker faker;

    public Fakedata() {
        this.alumnoDatafile = new AlumnoDatafile();
        this.apoderadoDatafile = new ApoderadoDatafile();
        this.cursoDatafile = new CursoDatafile();
        this.profesorDatafile = new ProfesorDatafile();
        this.faker = new Faker();
    }

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

    public Alumno generateAlumno(Apoderado ap) {
        String rut, nombres, apMat;
        rut = Integer.toString(this.faker.number().numberBetween(40000000, 50000000))
                + "-" + Integer.toString(this.faker.number().numberBetween(0, 9));
        nombres = this.faker.name().firstName();
        apMat = this.faker.name().lastName();
        return new Alumno(rut, nombres, ap.getApPaterno(), apMat, ap);
    }

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
                    apoderadoDatafile.insertApoderado(apoderado);
                    alumno = generateAlumno(apoderado);
                    alumnoDatafile.insertAlumno(alumno, i, j);
                }
            }
        }
    }

    public static void main(String[] args) {
        Fakedata fakedata = new Fakedata();
        fakedata.generateFakedata();
    }

}