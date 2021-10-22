package aplicacion.data.datafile;

import aplicacion.data.ProfesorData;
import aplicacion.models.Curso;
import aplicacion.models.Profesor;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que permite la interacción con el archivo de texto plano que almancena
 * los datos de Profesor. Implementa la interfaz ProfesorData.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 2.0
 */
public class ProfesorDatafile implements ProfesorData {

    private final Datafile datafile;

    /**
     * Constructor de ProfesorDatafile. Trabaja con un objeto Datafile.
     */
    public ProfesorDatafile() {
        this.datafile = new Datafile("profesor");
    }

    /**
     * Permite transformar un objeto de tipo Profesor, en una línea con formato CSV
     *
     * @param profesor Profesor
     * @return String con la línea en formato CSV correspondiente al profesor
     */
    public String profesorToCSV(Profesor profesor) {
        List<String> dataList = new ArrayList<>();
        dataList.add(profesor.getRut());
        dataList.add(profesor.getNombres());
        dataList.add(profesor.getApPaterno());
        dataList.add(profesor.getApMaterno());
        dataList.add(profesor.getAsignatura());
        dataList.add(profesor.getEmail());
        dataList.add(Integer.toString(profesor.getTelefono()));
        return Datafile.listToCSV(dataList);
    }

    /**
     * Permite obtener un objeto de tipo Profesor, desde un CSV
     *
     * @param csv String con la línea CSV con los datos del profesor
     * @return Objeto Profesor con los datos del profesor.
     */
    public Profesor profesorFromCSV(String csv) {
        String[] parts = csv.split(",");
        return new Profesor(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], Integer.parseInt(parts[6]));
    }

    /**
     * Permite obtener todos los profesores almacenados en el archivo CSV
     *
     * @return ArrayList de profesores
     */
    @Override
    public List<Profesor> getProfesores() {
        List<Profesor> profesores = new ArrayList<>();
        List<String> data = this.datafile.getData();
        for (String csv : data)
            profesores.add(profesorFromCSV(csv));
        return profesores;
    }

    /**
     * Permite obtener un profesor en especifico, almacenado en el archivo CSV.
     *
     * @param rut RUT del profesor
     * @return Profesor solicitado
     */
    @Override
    public Profesor getProfesor(String rut) {
        List<String> data = this.datafile.getData();
        for (String csv : data)
            if (csv.split(",")[0].equals(rut))
                return profesorFromCSV(csv);
        return null;
    }

    /**
     * Permite obtener un profesor en especifico, almacenado en el archivo CSV.
     *
     * @param curso Curso
     * @return Profesor solicitado
     */
    @Override
    public Profesor getProfesorJefe(Curso curso) {
        List<String> data = this.datafile.getData();
        for (String csv : data)
            if (csv.split(",")[0].equals(curso.getProfesorJefe().getRut()))
                return profesorFromCSV(csv);
        return null;
    }

    /**
     * Agrega un Profesor al archivo CSV
     *
     * @param profesor Profesor que se desea agregar al archivo CSV
     * @return Valor de verdad (boolean) sobre el exito o fracaso de la operacion de inserción
     */
    @Override
    public boolean insertProfesor(Profesor profesor) {
        return this.datafile.insertLine(profesorToCSV(profesor));
    }

    /**
     * Actualiza los datos de un Profesor en el archivo CSV
     *
     * @param profesor Profesor a actualizar
     * @return Valor de verdad (boolean) sobre el exito o fracaso de la operacion de actualizacion
     */
    @Override
    public boolean updateProfesor(Profesor profesor) {
        String oldLine = profesorToCSV(getProfesor(profesor.getRut())), newLine = profesorToCSV(profesor);
        return this.datafile.updateLine(oldLine, newLine);
    }

    /**
     * Elimina un profesor del archivo CSV
     *
     * @param profesor Profesor a eliminar
     */
    @Override
    public void deleteProfesor(Profesor profesor) {
        this.datafile.deleteLine(profesorToCSV(profesor));
    }
}