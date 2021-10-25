package aplicacion.data.datafile;

import aplicacion.data.RegistroAsistenciaData;
import aplicacion.models.IDAsistencia;
import aplicacion.models.RegistroAsistencia;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RegistroAsistenciaDF implements RegistroAsistenciaData {

    private final Datafile datafile;

    public RegistroAsistenciaDF() {
        this.datafile = new Datafile("registroasistencia");
    }

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

    public RegistroAsistencia regitroAsistenciaFromCSV(String csv) {
        String[] parts = csv.split(",");
        Date fecha = Date.valueOf(parts[1]);
        if (fecha != null)
            return new RegistroAsistencia(new IDAsistencia(parts[0], fecha),
                    Double.parseDouble(parts[2]), Boolean.parseBoolean(parts[3]),
                Boolean.parseBoolean(parts[4]), Boolean.parseBoolean(parts[5]));
        return null;
    }

    @Override
    public RegistroAsistencia get(IDAsistencia id) {
        return null;
    }

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
                System.out.println(csv);
                System.out.println(String.format("presente %s - justificado %s - retiro %s", Boolean.parseBoolean(parts[2]),
                        Boolean.parseBoolean(parts[3]), Boolean.parseBoolean(parts[4])));
                registro.put(id, regitroAsistenciaFromCSV(csv));
            }
        }
        return registro;
    }

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

    @Override
    public void insertarRegistroAsistencia(RegistroAsistencia registroAsistencia) {
        this.datafile.insertLine(registroAsistenciaToCSV(registroAsistencia));
    }

    @Override
    public void actualizarRegistroAsistencia(RegistroAsistencia registroAsistencia) {
        String oldLine = registroAsistenciaToCSV(get(registroAsistencia.getId())), newLine = registroAsistenciaToCSV(registroAsistencia);
        this.datafile.updateLine(oldLine, newLine);
    }

    @Override
    public void eliminarRegistroAsistencia(RegistroAsistencia registroAsistencia) {
        this.datafile.deleteLine(registroAsistenciaToCSV(registroAsistencia));
    }
}
