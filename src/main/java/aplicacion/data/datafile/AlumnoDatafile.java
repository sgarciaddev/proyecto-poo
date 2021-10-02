package aplicacion.data.datafile;

import aplicacion.data.AlumnoData;
import aplicacion.models.Alumno;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase que permite la interacción con el archivo de texto plano que almancena
 * los datos de los alumnos. Implementa la interfaz AlumnoData.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 1.0
 */
public class AlumnoDatafile implements AlumnoData {

    private final Datafile datafile;
    private final ApoderadoDatafile apDataFile;

    /**
     * Constructor de AlumnoDatafile. Trabaja con un objeto Datafile.
     */
    public AlumnoDatafile() {
        this.datafile = new Datafile("alumno");
        this.apDataFile = new ApoderadoDatafile();
    }

    /**
     * Método que permite transformar un objeto de típo Alumno, a un String que
     * contiene los valores en formato CSV.
     *
     * @param alumno Alumno a obtener sus valores
     * @param nivel  Valor numérico con el nivel del alumno
     * @param letra  Caracter que identifica el paralelo al que pertenece el alumno
     * @return String con formato CSV que contiene los datos del alumno
     */
    public String alumnoToCSV(Alumno alumno, int nivel, char letra) {
        List<String> dataList = new ArrayList<>();
        dataList.add(Integer.toString(nivel));
        dataList.add(Character.toString(letra));
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
        String[] parts = csv.split(",");
        return new Alumno(parts[2], parts[3], parts[4], parts[5], this.apDataFile.getApoderado(parts[6]));
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
                alumnos.put(alumno.getRut(), alumno);
            }
        }
        return alumnos;
    }

    /**
     * Obtiene todos los alumnos almacenados en el archivo, y que estén en el
     * curso requerido.
     *
     * @param nivel Nivel por el que se busca filtrar los datos
     * @param letra Caracter que identifica el paralelo por el que se busca
     *              filtrar los datos
     * @return HashMap de alumnos del nivel y paralelo entregado
     */
    @Override
    public Map<String, Alumno> getAlumnos(int nivel, char letra) {
        Alumno alumno;
        Map<String, Alumno> alumnos = new HashMap<String, Alumno>();
        List<String> dataList = this.datafile.getData();
        for (String csv : dataList) {
            if (Integer.parseInt(csv.split(",")[0]) == nivel && csv.split(",")[1].charAt(0) == letra) {
                alumno = alumnoFromCSV(csv);
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
        for (String csv : dataList) {
            if (csv.split(",")[2].equals(rut))
                return alumnoFromCSV(csv);
        }
        return null;
    }

    /**
     * Inserta un nuevo alumno al archivo plano que los contiene
     *
     * @param alumno Alumno a agregar al archivo
     * @param nivel  Nivel del alumno a agregar al archivo
     * @param letra  Paralelo del curso del alumno a agregar al archivo
     * @return Valor de verdad de la operación de inserción
     */
    @Override
    public boolean insertAlumno(Alumno alumno, int nivel, char letra) {
        return this.datafile.insertLine(alumnoToCSV(alumno, nivel, letra)) && this.apDataFile.insertApoderado(alumno.getApoderado());
    }

    /**
     * Actualiza un alumno en el archivo plano que los contiene
     *
     * @param alumno Alumno a actualizar en el archivo
     * @param nivel  Nivel del alumno a actualizar en el archivo
     * @param letra  Paralelo del curso del alumno a actualizar en el archivo
     * @return Valor de verdad de la operación de actualización
     */
    @Override
    public boolean updateAlumno(Alumno alumno, int nivel, char letra) {
        Alumno oldData = getAlumno(alumno.getRut());
        return this.datafile.updateLine(alumnoToCSV(oldData, nivel, letra), alumnoToCSV(alumno, nivel, letra));
    }

    /**
     * Elimina un alumno del archivo plano
     *
     * @param alumno Alumno a agregar al archivo
     * @param nivel  Nivel del alumno a agregar al archivo
     * @param letra  Paralelo del curso del alumno a agregar al archivo
     * @return Valor de verdad de la operación de borrado
     */
    @Override
    public boolean deleteAlumno(Alumno alumno, int nivel, char letra) {
        this.apDataFile.deleteApoderado(alumno.getApoderado());
        return this.datafile.deleteLine(alumnoToCSV(alumno, nivel, letra));
    }
}