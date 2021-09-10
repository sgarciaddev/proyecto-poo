package aplicacion.data.datafile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que permite la interacción con los archivos de texto plano CSV. Permite
 * las operaciones de listar, insertar, modificar y eliminar líneas de los
 * archivos.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 1.0
 */
public class Datafile {
    private final File file;
    private final String type;
    private boolean fileExists;

    /**
     * @param type String con el tipo de archivo con el que se desea trabajar.
     */
    public Datafile(String type) {
        this.type = type;
        this.file = new File("datafiles/" + type + ".csv");
        if (!this.file.isFile()) {
            try {
                this.fileExists = this.file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
        } else
            this.fileExists = true;
    }

    /**
     * Método que permite transformar una lista de String a valores separados por
     * punto y coma (CSV).
     * @param list Lista de String a transformar
     * @return String con la línea en formato CSV correspondiente
     */
    public static String listToCSV(List<String> list) {
        String csv = "";
        for (String param: list) {
            csv = csv.concat(param).concat(",");
        }
        csv = csv.substring(0, csv.length() - 1);
        return csv;
    }

    /**
     * Obtiene todos los datos almacenados en el archivo. Los datos vienen como
     * lista de String, y cada uno de ellos tiene la línea CSV correspondiente.
     *
     * @return Listado de String con los datos
     */
    public List<String> getData() {
        List<String> data = new ArrayList<>();
        String line;
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(this.file));
            do {
                line = fileReader.readLine();
                if (line != null) {
                    data.add(line);
                }
            } while (line != null);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return data;
    }

    /**
     * Inserta una nueva línea al archivo de texto.
     *
     * @param line String con los valores en formato CSV a ser incorporados al archivo
     * @return Valor de verdad de la operación de inserción.
     */
    public boolean insertLine(String line) {
        try {
            PrintWriter salida = new PrintWriter(new FileWriter(this.file, true));
            salida.println(line);
            salida.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Actualiza una línea de datos por una nueva.
     * @param oldLine Línea en formato CSV a modificar
     * @param newLine Línea en formato CSV con los datos actualizados
     * @return Valor de verdad de la operación de actualización
     */
    public boolean updateLine(String oldLine, String newLine) {
        boolean updated = false;
        String currLine, trimmedLine;
        File tempFile = new File("_" + this.type + ".csv");
        try {
            if (!tempFile.isFile())
                tempFile.createNewFile();
            BufferedReader reader = new BufferedReader(new FileReader(this.file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile, true));

            while ((currLine = reader.readLine()) != null) {
                trimmedLine = currLine.trim();
                if (trimmedLine.equals(oldLine)) {
                    writer.write(newLine + System.getProperty("line.separator"));
                    updated = true;
                } else
                    writer.write(currLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final boolean d = this.file.delete();
        final boolean b = tempFile.renameTo(this.file);
        return updated && d && b;
    }

    /**
     * Elimina una línea del archivo
     *
     * @param line Línea en formato CSV a ser eliminada
     * @return Valor de verdad de la operación de borrado
     */
    public boolean deleteLine(String line) {
        boolean deleted = false;
        File tempFile = new File("_" + this.type + ".csv");
        String currLine, trimmedLine;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile, true));

            while ((currLine = reader.readLine()) != null) {
                trimmedLine = currLine.trim();
                if (!trimmedLine.equals(line))
                    writer.write(line + System.getProperty("line.separator"));
                else
                    deleted = true;
            }
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final boolean d = this.file.delete();
        final boolean b = tempFile.renameTo(this.file);
        return b && d && deleted;
    }

    /**
     * @return Valor de verdad sobre la existencia del archivo.
     */
    public boolean isFileExists() {
        return fileExists;
    }
}