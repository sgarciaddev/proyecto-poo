package aplicacion.views.cli;

import aplicacion.controllers.exceptions.DatabaseException;
import aplicacion.data.AlumnoData;
import aplicacion.data.ApoderadoData;
import aplicacion.data.CursoData;
import aplicacion.data.ProfesorData;
import aplicacion.data.database.*;
import aplicacion.data.datafile.AlumnoDF;
import aplicacion.data.datafile.ApoderadoDF;
import aplicacion.data.datafile.CursoDF;
import aplicacion.data.datafile.ProfesorDF;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Aplicación principal de la interfaz de consola de comandos (CLI).
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 4.0
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

        try {
            if (DBConnection.connect() != null) {
                UtilsCLI.mensajeExitoConexionMySQL();
                alumnoData = new AlumnoDB();
                apoderadoData = new ApoderadoDB();
                profesorData = new ProfesorDB();
                cursoData = new CursoDB();
            } else throw new DatabaseException();
        } catch (DatabaseException e) {
            e.imprimirMensajeError();
            alumnoData = new AlumnoDF();
            apoderadoData = new ApoderadoDF();
            profesorData = new ProfesorDF();
            cursoData = new CursoDF();
        }
        this.menuCLI = new MenuCLI(lector, alumnoData, apoderadoData, cursoData, profesorData);

    }

    /**
     * Método que inicia la aplicación de consola
     *
     * @throws IOException Errores de entrada/salida de datos
     */
    public void iniciarCLI() throws IOException{
        menuCLI.mostrarMenu("principal");
    }

    /**
     * Método que permite finalizar el programa. Imprime un mensaje de despedida.
     */
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
