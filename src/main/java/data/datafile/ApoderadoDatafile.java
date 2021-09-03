package data.datafile;

import data.ApoderadoData;
import models.Apoderado;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que permite la interacción con el archivo de texto plano que almancena
 * los datos de Apoderado. Implementa la interfaz AooderadoData.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 1.0
 */
public class ApoderadoDatafile implements ApoderadoData {

    private final Datafile datafile;

    public ApoderadoDatafile() {
        this.datafile = new Datafile("apoderado");
    }

    public String apoderadoToCSV(Apoderado apoderado) {
        List<String> dataList = new ArrayList<>();
        dataList.add(apoderado.getRut());
        dataList.add(apoderado.getNombres());
        dataList.add(apoderado.getApPaterno());
        dataList.add(apoderado.getApMaterno());
        dataList.add(Integer.toString(apoderado.getTelefono()));
        dataList.add(apoderado.getEmail());
        return Datafile.listToCSV(dataList);
    }

    public Apoderado apoderadoFromCSV(String csv) {
        String[] parts = csv.split(",");
        return new Apoderado(parts[0], parts[1], parts[2], parts[3], Integer.parseInt(parts[4]), parts[5]);
    }

    @Override
    public Apoderado getApoderado(String rut) {
        List<String> data = this.datafile.getData();
        for (String csv: data) {
            if (csv.split(",")[0].equals(rut))
                return apoderadoFromCSV(csv);
        }
        return null;
    }

    @Override
    public boolean insertApoderado(Apoderado apoderado) {
        return this.datafile.insertLine(apoderadoToCSV(apoderado));
    }

    @Override
    public boolean updateApoderado(Apoderado apoderado) {
        String oldLine = apoderadoToCSV(null), newLine = apoderadoToCSV(apoderado);
        return this.datafile.updateLine(oldLine, newLine);
    }

    @Override
    public boolean deleteApoderado(Apoderado apoderado) {
        return this.datafile.deleteLine(apoderadoToCSV(apoderado));
    }
}