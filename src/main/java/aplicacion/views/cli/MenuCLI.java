package aplicacion.views.cli;

import aplicacion.controllers.cli.MenuControllerCLI;
import aplicacion.data.AlumnoData;
import aplicacion.data.ApoderadoData;
import aplicacion.data.CursoData;
import aplicacion.data.ProfesorData;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase que controla los menús de la interfaz de consola de comandos.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 4.0
 */
public class MenuCLI {

    private final BufferedReader lector;
    private final MenuControllerCLI menuControllerCLI;

    private final AlumnoData alumnoData;
    private final ApoderadoData apoderadoData;
    private final CursoData cursoData;
    private final ProfesorData profesorData;

    /**
     * Objeto que controla los menús en la interfaz de linea de comandos.
     *
     * @param lector        BufferedReader lector de datos
     * @param alumnoData    Origen de datos de los alumnos
     * @param apoderadoData Origen de datos de los apoderados
     * @param cursoData     Origen de datos de los cursos
     * @param profesorData  Origen de datos de los profesores
     */
    public MenuCLI(BufferedReader lector, AlumnoData alumnoData, ApoderadoData apoderadoData, CursoData cursoData,
                   ProfesorData profesorData) {
        this.lector = lector;
        this.menuControllerCLI = new MenuControllerCLI(this);
        this.alumnoData = alumnoData;
        this.apoderadoData = apoderadoData;
        this.cursoData = cursoData;
        this.profesorData = profesorData;
    }

    /**
     * Método getter del origen de datos de los alumnos.
     *
     * @return Origen de datos requerido.
     */
    public AlumnoData getAlumnoData() {
        return alumnoData;
    }

    /**
     * Método getter del origen de datos de los apoderados.
     *
     * @return Origen de datos requerido.
     */
    public ApoderadoData getApoderadoData() {
        return apoderadoData;
    }

    /**
     * Método getter del origen de datos de los cursos.
     *
     * @return Origen de datos requerido.
     */
    public CursoData getCursoData() {
        return cursoData;
    }

    /**
     * Método getter del origen de datos de los profesores.
     *
     * @return Origen de datos requerido.
     */
    public ProfesorData getProfesorData() {
        return profesorData;
    }

    /**
     * Mapa con los menús a desplegar por pantalla. Cada menú contiene las opciones numéricas, un título y una breve
     * descripción del mismo.
     */
    private final static Map<String, Object[][]> opciones = new HashMap<String, Object[][]>() {{
        put("principal", new Object[][]{
                {
                        1,
                        "Administrar colegio",
                        "Permite gestionar los cursos, alumnos, profesores y asistencia"
                },
                {
                        2,
                        "Generar reportes",
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
        });
        put("administracion", new Object[][]{
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
        });
        put("reportes", new Object[][]{
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
        });
        put("cursos", new Object[][]{
                {
                        1,
                        "Agregar curso",
                        "Permite agregar un curso al sistema"
                },
                {
                        2,
                        "Ver datos de curso",
                        "Permite ver los datos de un curso en específico"
                },
                {
                        3,
                        "Ver cursos",
                        "Permite ver la lista de cursos disponibles"
                },
                {
                        4,
                        "Asignar nuevo profesor jefe a curso",
                        "Permite asignar un nuevo profesor jefe al curso"
                },
                {
                        5,
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
        });
        put("alumnos", new Object[][]{
                {
                        1,
                        "Agregar alumno",
                        "Permite agregar un alumno al curso"
                },
                {
                        2,
                        "Ver datos de alumno",
                        "Permite ver los datos de un alumno en específico"
                },
                {
                        3,
                        "Ver alumnos",
                        "Permite ver la lista de alumnos del curso"
                },
                {
                        4,
                        "Editar alumno",
                        "Permite editar un alumno en específico"
                },
                {
                        5,
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
        });
        put("asistencia", new Object[][]{
                {
                        1,
                        "Alumno con mejor asistencia",
                        "Permite ver el alumno con mejor asistencia del año"
                },
                {
                        2,
                        "Registrar asistencia (próximamente)",
                        "Permite registrar la asistencia de un curso un día determinado"
                },
                {
                        3,
                        "Alumnos entre % y % de asistencia",
                        "Permite ver los alumnos con los porcentajes de asistencia especificados"
                },
                {
                        4,
                        "Alumno con mas retiros",
                        "Permite ver el alumno con mas retiros en el año"
                },
                {
                        5,
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
        });
    }};

    /**
     * Método que se encarga de mostrar un menú por pantalla, leer la opción ingresada por el usuario y realizar las
     * operaciones correspondientes.
     *
     * @param menu Típo de menú a mostrar
     * @throws IOException Posibles errores en I/O de datos.
     */
    public void mostrarMenu(String menu) throws IOException {
        short opt = -1;
        while (opt != 0) {
            UtilsCLI.imprimirMenu(opciones.get(String.format("%s", menu)), String.format("Menú %s", menu));
            UtilsCLI.imprimirIngresarOpcion("numérica");
            opt = Short.parseShort(this.lector.readLine());
            this.menuControllerCLI.menuOpc(menu, opt);
        }
    }

    /**
     * Método que se encarga de mostrar un menú por pantalla con un título especificado, leer la opción ingresada por
     * el usuario y realizar las operaciones correspondientes.
     *
     * @param menu   Típo de menú a mostrar
     * @param titulo Título que se desea mostrar sobre el menú.
     * @throws IOException Posibles errores en I/O de datos.
     */
    public void mostrarMenu(String menu, String titulo) throws IOException {
        short opt = -1;
        while (opt != 0) {
            UtilsCLI.imprimirMenu(opciones.get(String.format("%s", menu)), titulo);
            UtilsCLI.imprimirIngresarOpcion("numérica");
            opt = Short.parseShort(this.lector.readLine());
            this.menuControllerCLI.menuOpc(menu, opt);
        }
    }

    public BufferedReader getLector() {
        return lector;
    }
}
