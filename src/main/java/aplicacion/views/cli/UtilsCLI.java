package aplicacion.views.cli;

import aplicacion.models.Curso;
import aplicacion.models.Persona;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Clase con utilidades para la interfaz de consola de comandos (CLI).
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 4.0
 */
public class UtilsCLI {

    /**
     * Mapa con los headers de las tablas que se pueden imprimir por pantalla.
     */
    public final static Map<String, String[]> headers = new HashMap<String, String[]>() {{
        put("cursos", new String[]{
                "Curso",
                "Nivel",
                "Paralelo",
                "Nombre profesor jefe",
                "Email profesor jefe",
                "Telefono profesor jefe"
        });

        put("alumnos", new String[]{
                "RUT",
                "Ap. Paterno",
                "Ap. Materno",
                "Nombres",
                "Nombre completo apoderado",
                "Teléfono apoderado"
        });

        put("asistencia", new String[]{
                "RUT",
                "Ap. Paterno",
                "Ap. Materno",
                "Nombres",
                "Nombre completo apoderado",
                "Teléfono apoderado",
                "% Asistencia"
        });

        put("retiros", new String[]{
                "RUT",
                "Ap. Paterno",
                "Ap. Materno",
                "Nombres",
                "Nombre completo apoderado",
                "Teléfono apoderado",
                "% Retiros"
        });
    }};

    /**
     * Permite obtener el ancho de las columnas (en cant. de caracteres) para ser mostrados por pantalla en formato
     * de tabla.
     *
     * @param data   Datos a ser mostrados
     * @param header Cabecera de la tabla
     * @return Arreglo de enteros correspondiente al ancho de cada una de las columnas de la tabla.
     */
    private static int[] anchoColumnas(Object[][] data, String[] header) {
        int[] anchos = new int[header.length];
        for (int j = 0; j < header.length; j++) {
            anchos[j] = header[j].length();
            for (int i = 0; i < data.length; i++)
                if (data[i][j].toString().length() > anchos[j])
                    anchos[j] = data[i][j].toString().length();
        }
        return anchos;
    }

    /**
     * Permite obtener el ancho total de la tabla (en cantidad de caracteres)
     *
     * @param anchos Arreglo de enteros con los anchos de las columnas
     * @return Entero correspondiente al ancho total de la tabla
     */
    private static int anchoTotalTabla(int[] anchos) {
        int anchoTotal = 4;
        for (int ancho : anchos)
            anchoTotal += ancho;
        anchoTotal += (anchos.length - 1) * 3;
        return anchoTotal;
    }

    /**
     * Imprime una fila de datos de la tabla por pantalla
     *
     * @param fila   Columnas con los datos a ser mostrados
     * @param anchos Anchos de las columnas
     */
    private static void imprimirFilaTabla(Object[] fila, int[] anchos) {
        System.out.print("| ");
        for (int i = 0; i < anchos.length; i++) {
            System.out.print(String.format("%-" + anchos[i] + "s", fila[i]));
            System.out.print(" | ");
        }
        System.out.println();
    }

    /**
     * Imprime un mensaje de bienvenida al usuario
     */
    public static void mensajeBienvenida() {
        System.out.println("Bienvenidos a la aplicación");
        System.out.println("Gestión de asistencia\n");
    }

    /**
     * Imprime un mensaje de despedida al usuario
     */
    public static void mensajeDespedida() {
        System.out.print("Finalizando la aplicación... ");
    }

    /**
     * Imprime un mensaje de error de opción al usuario
     */
    public static void mensajeErrIngresado() {
        System.out.println("\nLo ingresado no es válido. Intente nuevamente");
    }

    /**
     * Imprime por pantalla un mensaje para solicitar datos al usuario
     *
     * @param datoSolicitado Texto con el dato solicitado
     * @param tipo           Tipo de dato solicitado
     */
    public static void imprimirSolicitar(String datoSolicitado, String tipo) {
        System.out.print("Ingrese " + datoSolicitado + "(" + tipo + "): ");
    }

    /**
     * Imprime por pantalla la opción para ingresar datos al usuario
     *
     * @param tipo Tipo de dato solicitado (para ser mostrado)
     */
    public static void imprimirIngresarOpcion(String tipo) {
        System.out.print("Ingrese su opción (" + tipo + "): ");
    }

    /**
     * Imprime por pantalla la opción para ingresar datos al usuario
     *
     * @param tipo    Tipo de dato solicitado (para ser mostrado)
     * @param ejemplo Ejemplo para ser mostrado
     */
    public static void imprimirIngresarOpcion(String tipo, String ejemplo) {
        System.out.print("Ingrese su opción (" + tipo + ", ejemplo: " + ejemplo + "): ");
    }

    /**
     * Imprime un título centrado
     *
     * @param titulo Titulo a ser impreso
     * @param ancho  Ancho de caracteres para centrar el título
     */
    public static void imprimirTitulo(String titulo, int ancho) {
        System.out.println(String.format("%" + ((ancho - titulo.length()) / (2) - 1) + "s %s", " ", titulo));
    }

    /**
     * Imprime un título alineado a la izquierda
     *
     * @param titulo Titulo a ser impreso
     */
    public static void imprimirTitulo(String titulo) {
        System.out.println(titulo + "\n");
    }

    /**
     * Imprime un caracter una cantidad de veces
     *
     * @param caracter Caracter a ser impreso
     * @param veces    Cantidad de veces que se desea se imprima el caracter
     */
    public static void imprimirCaracter(char caracter, int veces) {
        System.out.println(new String(new char[veces]).replace('\0', caracter));
    }

    /**
     * Imprime por pantalla una tabla con datos
     *
     * @param data   Datos a ser impresos
     * @param header Cabecera de la tabla
     * @param titulo Título de la tabla
     */
    public static void imprimirTabla(Object[][] data, String[] header, String titulo) {
        int[] anchos = anchoColumnas(data, header);
        int anchoTotal = anchoTotalTabla(anchos);

        imprimirTitulo(titulo, anchoTotal);
        imprimirCaracter('-', anchoTotal);
        imprimirFilaTabla(header, anchos);
        imprimirCaracter('-', anchoTotal);
        for (Object[] fila : data) {
            imprimirFilaTabla(fila, anchos);
        }
        imprimirCaracter('-', anchoTotal);
        System.out.println();
    }

    /**
     * Imprime por pantalla un menú con sus ítems y opciones
     *
     * @param menuItems Ítems del menú
     * @param titulo    Título del menú
     */
    public static void imprimirMenu(Object[][] menuItems, String titulo) {
        System.out.println("\n" + titulo);
        for (Object[] item : menuItems) {
            System.out.print("  -> ");
            System.out.println(String.format("%s %2s %2s - %s", "(", item[0], ")", item[1]));
            System.out.println(String.format("%14s %s", " ", item[2]));
        }
    }

    /**
     * Método que se encarga de imprimir una persona. Llama el método toString para que muestre cada persona en el
     * formato correcto.
     * @param persona Persona que se desea mostrar por pantalla
     */
    public static void imprimirPersona(Persona persona) {
        System.out.println(persona.toString());
    }

    /**
     * Método que se encarga de imprimir una curso. Llama el método toString para que muestre el curso en el
     * formato correcto.
     * @param curso Curso que se desea mostrar por pantalla
     */
    public static void imprimirCurso(Curso curso) {
        System.out.println(curso.toString());
    }

    /**
     * Imprime por pantalla un mensaje sobre la funcionalidad que aún no ha sido implementada en el software.
     */
    public static void imprimirFuncionalidadNoImplementada() {
        System.out.println("Esta funcionalidad aún no ha sido implementada.\n");
    }

    /**
     * Imprime por pantalla un mensaje cuando se intenta la conexión a la base de datos MySQL.
     */
    public static void mensajeIntentandoConexionMySQL() {
        System.out.println("Intentando conexión a la base de datos...");
    }

    /**
     * Muestra un mensaje de éxito en la conexión con la base de datos MySQL.
     */
    public static void mensajeExitoConexionMySQL() {
        System.out.println("Conexión con base de datos MySQL exitosa!\n");
    }

    /**
     * Muestra un mensaje de fracaso en la conexión con la base de datos MySQL.
     * Se muestra que se utilizará base de datos local en vez.
     */
    public static void mensajeUtilizandoDatafile() {
        System.out.println("La conexión con la base de datos MySQL no ha sido posible. Se utilizará base de datos " +
                "local en reemplazo.\n");
    }

    /**
     * Permite mostrar un mensaje de Error: no encontrado por pantalla
     *
     * @param tipo Tipo de dato que no se encuentra
     * @param g Género (0: La, 1: El)
     */
    public static void mensajeErrorNoEncontrado(String tipo, int g) {
        if (g == 0)
            System.out.println("El " + tipo + " solicitado no ha sido encontrado. Revisa lo ingresado e intenta " +
                    "nuevamente.");
        else
            System.out.println("La " + tipo + " solicitada no ha sido encontrada. Revisa lo ingresado e intenta " +
                    "nuevamente.");
    }

    /**
     * Permite generar por pantalla una pausa, esperando un enter para continuar.
     */
    public static void imprimirEsperaEnter() {
        System.out.println("-> Presiona enter para continuar...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

}
