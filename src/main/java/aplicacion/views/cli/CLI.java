package aplicacion.views.cli;

import aplicacion.data.AlumnoData;
import aplicacion.data.ApoderadoData;
import aplicacion.data.CursoData;
import aplicacion.data.ProfesorData;
import aplicacion.data.database.*;
import aplicacion.data.datafile.AlumnoDatafile;
import aplicacion.data.datafile.ApoderadoDatafile;
import aplicacion.data.datafile.CursoDatafile;
import aplicacion.data.datafile.ProfesorDatafile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Aplicación principal de la interfaz de consola de comandos (CLI).
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 2.0
 */
public class CLI {

    private final BufferedReader lector;

    private final MenuCLI menuCLI;

    private final AlumnoData alumnoData;
    private final ApoderadoData apoderadoData;
    private final ProfesorData profesorData;
    private final CursoData cursoData;

    /**
     * Genera la instancia de la aplicación CLI
     */
    public CLI() {
        this.lector = new BufferedReader(new InputStreamReader(System.in));

        // Se intenta conexión con la base da datos y se verifica
        UtilsCLI.mensajeIntentandoConexionMySQL();
        if (DBConnection.connect() != null) {
            UtilsCLI.mensajeExitoConexionMySQL();
            this.alumnoData = new AlumnoDB();
            this.apoderadoData = new ApoderadoDB();
            this.profesorData = new ProfesorDB();
            this.cursoData = new CursoDB();
        } else {
            UtilsCLI.mensajeUtilizandoDatafile();
            this.alumnoData = new AlumnoDatafile();
            this.apoderadoData = new ApoderadoDatafile();
            this.profesorData = new ProfesorDatafile();
            this.cursoData = new CursoDatafile();
        }

        this.menuCLI = new MenuCLI(lector, this.alumnoData, this.apoderadoData, this.cursoData, this.profesorData);

    }

    /**
     * Método main. Contiene el programa principal, y la instancia del objeto CLI
     *
     * @param args Argumentos del main
     * @throws IOException Posibles errores de entrada/salida de datos
     */
    public static void main(String[] args) throws IOException {
        UtilsCLI.mensajeBienvenida();
        CLI cli = new CLI();
        cli.menuCLI.mostrarMenu("principal");
        UtilsCLI.mensajeDespedida();
    }

}
