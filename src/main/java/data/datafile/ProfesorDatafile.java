package data.datafile;

import data.ProfesorData;
import models.Curso;
import models.Profesor;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que permite la interacción con el archivo de texto plano que almancena
 * los datos de Profesor. Implementa la interfaz ProfesorData.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 1.0
 */
public class ProfesorDatafile implements ProfesorData {

    private final Datafile datafile;

    public ProfesorDatafile() {
        this.datafile = new Datafile("profesor");
    }

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

    public Profesor profesorFromCSV(String csv) {
        String[] parts = csv.split(",");
        return new Profesor(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], Integer.parseInt(parts[6]));
    }

    @Override
    public List<Profesor> getProfesores() {
        List<Profesor> profesores = new ArrayList<>();
        List<String> data = this.datafile.getData();
        for (String csv: data)
            profesores.add(profesorFromCSV(csv));
        return profesores;
    }

    @Override
    public Profesor getProfesor(String rut) {
        List<String> data = this.datafile.getData();
        for (String csv: data)
            if (csv.split(",")[0].equals(rut))
                return profesorFromCSV(csv);
        return null;
    }

    @Override
    public Profesor getProfesorJefe(Curso curso) {
        List<String> data = this.datafile.getData();
        for (String csv: data)
            if (csv.split(",")[0].equals(curso.getProfesorJefe().getRut()))
                return profesorFromCSV(csv);
        return null;
    }

    @Override
    public boolean insertProfesor(Profesor profesor) {
        return this.datafile.insertLine(profesorToCSV(profesor));
    }

    @Override
    public boolean updateProfesor(Profesor profesor) {
        String oldLine = profesorToCSV(getProfesor(profesor.getRut())), newLine = profesorToCSV(profesor);
        return this.datafile.updateLine(oldLine, newLine);
    }

    @Override
    public boolean deleteProfesor(Profesor profesor) {
        return this.datafile.deleteLine(profesorToCSV(profesor));
    }
}