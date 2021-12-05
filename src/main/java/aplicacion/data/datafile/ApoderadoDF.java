package aplicacion.data.datafile;

import aplicacion.data.ApoderadoData;
import aplicacion.models.Apoderado;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que permite la interacción con el archivo de texto plano que almacena
 * los datos de Apoderado. Implementa la interfaz ApoderadoData.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 3.0
 */
public class ApoderadoDF implements ApoderadoData {

    private final Datafile datafile;

    /**
     * Constructor de ApoderadoDatafile. Trabaja con un objeto Datafile.
     */
    public ApoderadoDF() {
        this.datafile = new Datafile("apoderado");
    }

    /**
     * Permite transformar un objeto de tipo Apoderado, en una línea con formato CSV
     *
     * @param apoderado Apoderado
     * @return String con la línea en formato CSV correspondiente al apoderado
     */
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

    /**
     * Permite obtener un objeto de tipo Apoderado, desde un CSV
     *
     * @param csv String con la línea CSV con los datos del apoderado
     * @return Objeto Apoderado con los datos del apoderado.
     */
    public Apoderado apoderadoFromCSV(String csv) {
        String[] parts = csv.split(",");
        return new Apoderado(parts[0], parts[1], parts[2], parts[3], Integer.parseInt(parts[4]), parts[5]);
    }

    /**
     * Obtener apoderado desde archivo, según su RUT
     *
     * @param rut RUT del apoderado
     * @return Apoderado correspondiente al RUT ingresado, o `null` si no se encontró.
     */
    @Override
    public Apoderado getApoderado(String rut) {
        List<String> data = this.datafile.getData();
        for (String csv : data) {
            if (csv.split(",")[0].equals(rut))
                return apoderadoFromCSV(csv);
        }
        return null;
    }

    /**
     * Agrega un Apoderado al archivo CSV
     *
     * @param apoderado Apoderado que se desea agregar al archivo CSV
     * @return Valor de verdad (boolean) sobre el exito o fracaso de la operacion de inserción
     */
    @Override
    public boolean insertApoderado(Apoderado apoderado) {
        return this.datafile.insertLine(apoderadoToCSV(apoderado));
    }

    /**
     * Actualiza los datos de un Apoderado en el archivo CSV
     *
     * @param apoderado Apoderado a actualizar
     * @return Valor de verdad (boolean) sobre el exito o fracaso de la operacion de actualizacion
     */
    @Override
    public boolean updateApoderado(Apoderado apoderado) {
        String oldLine = apoderadoToCSV(null), newLine = apoderadoToCSV(apoderado);
        return this.datafile.updateLine(oldLine, newLine);
    }

    /**
     * Elimina un apoderado del archivo CSV
     *
     * @param apoderado Apoderado a eliminar
     * @return Valor de verdad (boolean) sobre el exito o fracaso de la operacion de borrado
     */
    @Override
    public boolean deleteApoderado(Apoderado apoderado) {
        return this.datafile.deleteLine(apoderadoToCSV(apoderado));
    }
}