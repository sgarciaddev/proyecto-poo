package aplicacion.data.datafile;

import aplicacion.data.RegistroAsistenciaData;
import aplicacion.models.IDAsistencia;
import aplicacion.models.RegistroAsistencia;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Clase que permite la interacción con el archivo de texto plano que almacena
 * los datos de los registros de asistencia. Implementa la interfaz RegistroAsistenciaData.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 3.0
 */
public class RegistroAsistenciaDF implements RegistroAsistenciaData {

    private final Datafile datafile;

    /**
     * Permite generar el objeto que maneja los datos del registro de asistencia en el archivo de texto plano.
     */
    public RegistroAsistenciaDF() {
        this.datafile = new Datafile("registroasistencia");
    }

    /**
     * Permite transformar un registro de asistencia, a una linea con formato CSV.
     *
     * @param registroAsistencia Registro de asistencia a convertir
     * @return String con el registro de asistencia en CSV.
     */
    public String registroAsistenciaToCSV(RegistroAsistencia registroAsistencia) {
        List<String> dataList = new ArrayList<>();
        dataList.add(registroAsistencia.getId().rutAlumno);
        dataList.add(RegistroAsistencia.formatter.format(registroAsistencia.getId().fecha));
        dataList.add(Double.toString(registroAsistencia.getValor()));
        dataList.add(Boolean.toString(registroAsistencia.presente()));
        dataList.add(Boolean.toString(registroAsistencia.justificado()));
        dataList.add(Boolean.toString(registroAsistencia.retirado()));
        return Datafile.listToCSV(dataList);
    }

    /**
     * Permite obtener un registro de asistencia a partir de un texto CSV.
     *
     * @param csv String con la linea CSV con los datos del registro de asistencia.
     * @return Registro de asistencia obtenido desde el CSV.
     */
    public RegistroAsistencia regitroAsistenciaFromCSV(String csv) {
        String[] parts = csv.split(",");
        Date fecha = Date.valueOf(parts[1]);
        if (fecha != null)
            return new RegistroAsistencia(new IDAsistencia(parts[0], fecha),
                    Double.parseDouble(parts[2]), Boolean.parseBoolean(parts[3]),
                Boolean.parseBoolean(parts[4]), Boolean.parseBoolean(parts[5]));
        return null;
    }

    /**
     * Permite obtener un registro de asistencia, según el ID entregado como parámetro, desde el archivo de texto.
     *
     * @param id ID de asistencia a obtener
     * @return Registro de asistencia buscado.
     */
    @Override
    public RegistroAsistencia get(IDAsistencia id) {
        return null;
    }

    /**
     * Permite obtener un HashMap con todos los registros de asistencia registrados en el sistema.
     *
     * @return HashMap con los registros de asistencia.
     */
    @Override
    public HashMap<IDAsistencia, RegistroAsistencia> getRegistroAsistencia() {
        HashMap<IDAsistencia, RegistroAsistencia> registro = new HashMap<>();
        List<String> data = this.datafile.getData();
        IDAsistencia id;
        String[] parts;
        for (String csv : data) {
            parts = csv.split(",");
            id = new IDAsistencia(parts[0], Date.valueOf(parts[1]));
            registro.put(id, regitroAsistenciaFromCSV(csv));
        }
        return registro;
    }

    /**
     * Permite obtener un HashMap con los registros de asistencia de una fecha determinada.
     *
     * @param fecha Fecha de lq que se desea obtener los registros de asistencia.
     * @return HashMap con los registros de asistencia de la fecha determinada.
     */
    @Override
    public HashMap<IDAsistencia, RegistroAsistencia> getRegistroAsistencia(Date fecha) {
        HashMap<IDAsistencia, RegistroAsistencia> registro = new HashMap<>();
        List<String> data = this.datafile.getData();
        IDAsistencia id;
        String[] parts;
        for (String csv : data) {
            parts = csv.split(",");
            if (Date.valueOf(parts[1]).compareTo(fecha) == 0) {
                id = new IDAsistencia(parts[0], Date.valueOf(parts[1]));
                registro.put(id, regitroAsistenciaFromCSV(csv));
            }
        }
        return registro;
    }

    /**
     * Permite obtener un HashMap con los registros de asistencia de una alumno determinado.
     *
     * @param rutAlumno RUT del alumno del que se desea obtener los registros de asistencia.
     * @return HashMap con los registros de asistencia de la fecha determinada.
     */
    @Override
    public HashMap<IDAsistencia, RegistroAsistencia> getRegistroAsistencia(String rutAlumno) {
        HashMap<IDAsistencia, RegistroAsistencia> registro = new HashMap<>();
        List<String> data = this.datafile.getData();
        IDAsistencia id;
        String[] parts;
        for (String csv : data) {
            parts = csv.split(",");
            if (parts[0].equals(rutAlumno)) {
                id = new IDAsistencia(parts[0], Date.valueOf(parts[1]));
                //System.out.println(csv);
                //System.out.println(String.format("presente %s - justificado %s - retiro %s", Boolean.parseBoolean(parts[2]),
                        //Boolean.parseBoolean(parts[3]), Boolean.parseBoolean(parts[4])));
                registro.put(id, regitroAsistenciaFromCSV(csv));
            }
        }
        return registro;
    }

    /**
     * Permite obtener un HashMap con los registros de asistencia según un ID determinado.
     *
     * @param idAsistencia ID de asistencia que se desea obtener.
     * @return HashMap con los registros de asistencia de la fecha determinada.
     */
    @Override
    public HashMap<IDAsistencia, RegistroAsistencia> getRegistroAsistencia(IDAsistencia idAsistencia) {
        HashMap<IDAsistencia, RegistroAsistencia> registro = new HashMap<>();
        List<String> data = this.datafile.getData();
        IDAsistencia id;
        String[] parts;
        for (String csv : data) {
            parts = csv.split(",");
            if ((Date.valueOf(parts[1]).compareTo(idAsistencia.fecha) == 0) && parts[0].equals(idAsistencia.rutAlumno)) {
                id = new IDAsistencia(parts[0], Date.valueOf(parts[1]));
                registro.put(id, regitroAsistenciaFromCSV(csv));
            }
        }
        return registro;
    }

    /**
     * Permite insertar un registro de asistencia a la base de datos.
     *
     * @param registroAsistencia Registro de asistencia que se desea agregar al sistema.
     */
    @Override
    public void insertarRegistroAsistencia(RegistroAsistencia registroAsistencia) {
        this.datafile.insertLine(registroAsistenciaToCSV(registroAsistencia));
    }

    /**
     * Permite actualizar un registro de asistencia determinado.
     *
     * @param registroAsistencia Registro de asistencia que se desea actualizar.
     */
    @Override
    public void actualizarRegistroAsistencia(RegistroAsistencia registroAsistencia) {
        String oldLine = registroAsistenciaToCSV(get(registroAsistencia.getId())), newLine = registroAsistenciaToCSV(registroAsistencia);
        this.datafile.updateLine(oldLine, newLine);
    }

    /**
     * Permite eliminar un registro de asistencia determinado.
     *
     * @param registroAsistencia Registro de asistencia a eliminar.
     */
    @Override
    public void eliminarRegistroAsistencia(RegistroAsistencia registroAsistencia) {
        this.datafile.deleteLine(registroAsistenciaToCSV(registroAsistencia));
    }
}
