package aplicacion.data.datafile;

import aplicacion.data.AlumnoData;
import aplicacion.data.RegistroAsistenciaData;
import aplicacion.models.Alumno;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Clase que permite la interacción con el archivo de texto plano que almancena
 * los datos de los alumnos. Implementa la interfaz AlumnoData.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 2.0
 */
public class AlumnoDatafile implements AlumnoData {

    private final Datafile datafile;
    private final ApoderadoDatafile apDataFile;
    private final RegistroAsistenciaDF registroAsistenciaDataFile;

    /**
     * Constructor de AlumnoDatafile. Trabaja con un objeto Datafile.
     */
    public AlumnoDatafile() {
        this.datafile = new Datafile("alumno");
        this.apDataFile = new ApoderadoDatafile();
        this.registroAsistenciaDataFile = new RegistroAsistenciaDF();
    }

    /**
     * Método que permite transformar un objeto de típo Alumno, a un String que
     * contiene los valores en formato CSV.
     *
     * @param alumno Alumno a obtener sus valores
     * @return String con formato CSV que contiene los datos del alumno
     */
    public String alumnoToCSV(Alumno alumno) {
        List<String> dataList = new ArrayList<>();
        dataList.add(Integer.toString(alumno.getNivel()));
        dataList.add(Character.toString(alumno.getParalelo()));
        dataList.add(alumno.getRut());
        dataList.add(alumno.getNombres());
        dataList.add(alumno.getApPaterno());
        dataList.add(alumno.getApMaterno());
        dataList.add(alumno.getApoderado().getRut());
        return Datafile.listToCSV(dataList);
    }

    /**
     * Método que permite transformar un String con formato CSV con los datos de
     * un alumno, a un objeto de típo Alumno.
     *
     * @param csv String que contiene los datos del alumno en formato CSV.
     * @return Alumno
     */
    public Alumno alumnoFromCSV(String csv) {
        // Todo: Leer asistencia desde el csv
        String[] parts = csv.split(",");
        return new Alumno(parts[2],
                          parts[3],
                          parts[4],
                          parts[5],
                          Integer.parseInt(parts[0]),
                          parts[1].charAt(0),
                          this.apDataFile.getApoderado(parts[6]));
    }

    /**
     * Obtiene todos los alumnos almacenados en el archivo
     *
     * @return HashMap de alumnos
     */
    @Override
    public Map<String, Alumno> getAlumnos() {
        Alumno alumno;
        Map<String, Alumno> alumnos = new HashMap<String, Alumno>();
        List<String> dataList = this.datafile.getData();
        for (String csv : dataList) {
            alumno = alumnoFromCSV(csv);
            alumno.setAsistencia(this.registroAsistenciaDataFile.getRegistroAsistencia(alumno.getRut()));
            alumnos.put(alumno.getRut(), alumno);
        }
        return alumnos;
    }

    /**
     * Obtiene todos los alumnos almacenados en el archivo, y que estén en el
     * nivel requerido.
     *
     * @param nivel Nivel por el que se busca filtrar los datos
     * @return HashMap de alumnos del nivel entregado
     */
    @Override
    public Map<String, Alumno> getAlumnos(int nivel) {
        Alumno alumno;
        Map<String, Alumno> alumnos = new HashMap<String, Alumno>();
        List<String> dataList = this.datafile.getData();
        for (String csv : dataList) {
            if (Integer.parseInt(csv.split(",")[0]) == nivel) {
                alumno = alumnoFromCSV(csv);
                alumno.setAsistencia(this.registroAsistenciaDataFile.getRegistroAsistencia(alumno.getRut()));
                alumnos.put(alumno.getRut(), alumno);
            }
        }
        return alumnos;
    }

    /**
     * Obtiene todos los alumnos almacenados en el archivo, y que estén en el
     * curso requerido.
     *
     * @param nivel    Nivel por el que se busca filtrar los datos
     * @param paralelo Caracter que identifica el paralelo por el que se busca
     *                 filtrar los datos
     * @return HashMap de alumnos del nivel y paralelo entregado
     */
    @Override
    public Map<String, Alumno> getAlumnos(int nivel, char paralelo) {
        Alumno alumno;
        Map<String, Alumno> alumnos = new HashMap<String, Alumno>();
        List<String> dataList = this.datafile.getData();
        for (String csv : dataList) {
            if (Integer.parseInt(csv.split(",")[0]) == nivel && csv.split(",")[1].charAt(0) == paralelo) {
                alumno = alumnoFromCSV(csv);
                alumno.setAsistencia(this.registroAsistenciaDataFile.getRegistroAsistencia(alumno.getRut()));
                alumnos.put(alumno.getRut(), alumno);
            }
        }
        return alumnos;
    }

    /**
     * Permite obtener un alumno especifico
     *
     * @param rut RUT del alumno a obtener
     * @return El alumno, de no encontrarse retorna null
     */
    @Override
    public Alumno getAlumno(String rut) {
        List<String> dataList = this.datafile.getData();
        Alumno alumno;
        for (String csv : dataList) {
            if (csv.split(",")[2].equals(rut)) {
                alumno = alumnoFromCSV(csv);
                alumno.setAsistencia(this.registroAsistenciaDataFile.getRegistroAsistencia(alumno.getRut()));
                return alumno;
            }

        }
        return null;
    }

    /**
     * Inserta un nuevo alumno al archivo plano que los contiene
     *
     * @param alumno Alumno a agregar al archivo
     */
    @Override
    public void insertAlumno(Alumno alumno) {
        if (this.datafile.insertLine(alumnoToCSV(alumno))) {
            this.apDataFile.insertApoderado(alumno.getApoderado());
        }
    }

    /**
     * Actualiza un alumno en el archivo plano que los contiene
     *
     * @param alumno Alumno a actualizar en el archivo
     * @return Valor de verdad de la operación de actualización
     */
    @Override
    public boolean updateAlumno(Alumno alumno) {
        Alumno oldData = getAlumno(alumno.getRut());
        return this.datafile.updateLine(alumnoToCSV(oldData), alumnoToCSV(alumno));
    }

    /**
     * Elimina un alumno del archivo plano
     *
     * @param alumno Alumno a agregar al archivo
     * @return Valor de verdad de la operación de borrado
     */
    @Override
    public boolean deleteAlumno(Alumno alumno) {
        this.apDataFile.deleteApoderado(alumno.getApoderado());
        return this.datafile.deleteLine(alumnoToCSV(alumno));
    }
}