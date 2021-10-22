package aplicacion.views.cli;

import aplicacion.models.Alumno;
import aplicacion.models.Curso;

import java.util.logging.XMLFormatter;

/**
 * Clase con utilidades para la interfaz de consola de comandos (CLI).
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 1.0
 */
public class UtilsCLI {

    /**
     * Cabeceras de tablas de cursos
     */
    public static String[] headersCursos = {
            "Curso",
            "Nivel",
            "Paralelo",
            "Nombre profesor jefe",
            "Email profesor jefe",
            "Telefono profesor jefe"
    };

    /**
     * Cabeceras de tablas de alumnos
     */
    public static String[] headersAlumnos = {
            "RUT",
            "Ap. Paterno",
            "Ap. Materno",
            "Nombres",
            "Nombre completo apoderado",
            "Teléfono apoderado"
    };

    /**
     * Ítems del menú principal
     */
    public static final Object[][] opcMenuPrincipal = {
            {
                    1,
                    "Administrar colegio",
                    "Permite gestionar los cursos, alumnos, profesores y asistencia"
            },
            {
                    2,
                    "Reportes",
                    "Permite generar reportes con los datos del sistema"
            },
            {
                    3,
                    "Gráficos (próximamente)",
                    "Permite obtener gráficos sobre los datos del sistema"
            },
            {
                    0,
                    "Salir",
                    "Salir de la aplicación"
            }
    };

    /**
     * Ítems del menú de administración del colegio
     */
    public static final Object[][] opcMenuAdmin = {
            {
                    1,
                    "Gestionar cursos",
                    "Permite gestionar los datos de los cursos"
            },
            {
                    2,
                    "Gestionar alumnos",
                    "Permite gestionar los datos de los alumnos"
            },
            {
                    3,
                    "Gestionar asistencia",
                    "Permite gestionar la asistencia de los alumnos"
            },
            {
                    9,
                    "Salir",
                    "Salir de la aplicación"
            },
            {
                    0,
                    "Volver al menú principal",
                    "Salir de la administración del colegio"
            }
    };

    /**
     * Ítems del menú de reportes
     */
    public static final Object[][] opcMenuReportes = {
            {
                    1,
                    "Generar lista de curso",
                    "Permite generar un archivo CSV con la lista de un curso (alumnos enumerados)"
            },
            {
                    2,
                    "Generar tabla de cursos",
                    "Permite generar una tabla con todos los cursos del colegio"
            },
            {
                    9,
                    "Salir",
                    "Salir de la aplicación"
            },
            {
                    0,
                    "Volver al menú principal",
                    "Salir de la generación de reportes"
            }
    };

    /**
     * Ítems del menú de cursos
     */
    public static final Object[][] opcMenuCursos = {
            {
                    1,
                    "Agregar curso",
                    "Permite agregar un curso al sistema"
            },
            {
                    2,
                    "Ver cursos",
                    "Permite ver la lista de cursos disponibles"
            },
            {
                    3,
                    "Asignar nuevo profesor jefe a curso",
                    "Permite asignar un nuevo profesor jefe al curso"
            },
            {
                    4,
                    "Eliminar curso",
                    "Permite eliminar un curso en específico"
            },
            {
                    9,
                    "Salir",
                    "Salir de la aplicación"
            },
            {
                    0,
                    "Volver al menú de administración",
                    "Salir de la gestión de cursos"
            }
    };

    /**
     * Ítems del menú de alumnos
     */
    public static final Object[][] opcMenuAlumnos = {
            {
                    1,
                    "Agregar alumno",
                    "Permite agregar un alumno al curso"
            },
            {
                    2,
                    "Ver alumnos",
                    "Permite ver la lista de alumnos del curso"
            },
            {
                    3,
                    "Editar alumno",
                    "Permite editar un alumno en específico"
            },
            {
                    4,
                    "Eliminar alumno",
                    "Permite eliminar un alumno en específico"
            },
            {
                    9,
                    "Salir",
                    "Salir de la aplicación"
            },
            {
                    0,
                    "Volver al menú de administración",
                    "Salir de la gestión de alumnos"
            }
    };

    public static final Object[][] opcMenuAsistencia = {
            {
                    1,
                    "Alumno con mejor asistencia",
                    "Permite ver el alumno con mejor asistencia del año"
            },
            {
                    2,
                    "Alumnos entre % y % de asistencia",
                    "Permite ver los alumnos con los porcentajes de asistencia especificados"
            },
            {
                    3,
                    "Alumno con mas retiros",
                    "Permite ver el alumno con mas retiros en el año"
            },
            {
                    4,
                    "Alumnos entre % y % de retiros",
                    "Permite ver los alumnos con los porcentajes de retiros especificados"
            },
            {
                    9,
                    "Salir",
                    "Salir de la aplicación"
            },
            {
                    0,
                    "Volver al menú de administración",
                    "Salir de la gestión de cursos"
            }
    };

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

    public static void imprimirAlumno(Alumno alumno) {
        /*

        RUT             : XXXXXXXX-X
        NOMBRE COMPLETO : OAIJSGIOUJFOASDOIASUDOISAUDOIUSADASDLIJASJLASDJ
        CURSO           : asdfasdfasdf
        APODERADO       : XXXXXXXX-X | AWSFDPASODIPSAODIPOASDIPASODIAPSOD
        */
        imprimirTitulo("Datos del alumno", 50);
        System.out.println("\nRUT             : " + alumno.getRut());
        System.out.println("NOMBRE COMPLETO : " + alumno.getNombreCompleto());
        System.out.println("CURSO           : " + Curso.cursoToString(alumno.getNivel(), alumno.getParalelo()));
        System.out.println("APODERADO       : " + alumno.getApoderado().getRut() +
                                                   " | " +
                                                   alumno.getApoderado().getNombreCompleto());
        System.out.println();
    }

    public static void mensajeIntentandoConexionMySQL() {
        System.out.println("Intentando conexión a la base de datos...");
    }

    public static void mensajeExitoConexionMySQL() {
        System.out.println("Conexión con base de datos MySQL exitosa!\n");
    }

    public static void mensajeUtilizandoDatafile() {
        System.out.println("La conexión con la base de datos MySQL no ha sido posible. Se utilizará base de datos " +
                "local en reemplazo.\n");
    }

}
