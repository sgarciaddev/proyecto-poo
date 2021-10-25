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

    private final MenuCLI menuCLI;

    /**
     * Genera la instancia de la aplicación CLI
     */
    public CLI() {
        AlumnoData alumnoData;
        ApoderadoData apoderadoData;
        ProfesorData profesorData;
        CursoData cursoData;

        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        UtilsCLI.mensajeBienvenida();
        // Se intenta conexión con la base da datos y se verifica
        UtilsCLI.mensajeIntentandoConexionMySQL();

        if (DBConnection.connect() != null) {
            UtilsCLI.mensajeExitoConexionMySQL();
            alumnoData = new AlumnoDB();
            apoderadoData = new ApoderadoDB();
            profesorData = new ProfesorDB();
            cursoData = new CursoDB();
        } else {
            UtilsCLI.mensajeUtilizandoDatafile();
            alumnoData = new AlumnoDatafile();
            apoderadoData = new ApoderadoDatafile();
            profesorData = new ProfesorDatafile();
            cursoData = new CursoDatafile();
        }

        this.menuCLI = new MenuCLI(lector, alumnoData, apoderadoData, cursoData, profesorData);

    }

    public void iniciarCLI() throws IOException{
        menuCLI.mostrarMenu("principal");
    }

    public void finalizarCLI() {
        UtilsCLI.mensajeDespedida();
    }

    /**
     * Método main. Contiene el programa principal, y la instancia del objeto CLI
     *
     * @param args Argumentos del main
     * @throws IOException Posibles errores de entrada/salida de datos
     */
    public static void main(String[] args) throws IOException {
        CLI cli = new CLI();
        cli.iniciarCLI();
        cli.finalizarCLI();
    }

}
