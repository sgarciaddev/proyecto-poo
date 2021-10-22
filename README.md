# Proyecto - INF 2241 Programación Orientada a Objetos (POO)

- **Escuela de Ingeniería Informática | PUCV Chile**
- **2do semestre - 2021**

**Integrantes**:

- Sebastián García
- Guillermo González
- Benjamín Navarrete

**Tema:** Gestión de asistencia de alumnos en un colegio. <br />
**Lenguaje utilizado:** Java (JDK 8).<br />
![Java][java-badge]

## Indice

- [Instalación de la BBDD y Docker compose](database/README.md)
- [Estructura de directorios](#estructura-de-directorios)
  - [Mirada general](#mirada-general)
  - [Estructura de clases](#estructura-de-clases)
- [IDE de desarrollo](#ide)
- [Descripción](#descripcion)
- [To-do](markdown/todo.md)

## Base de datos

Para utilizar la base de datos, se debe utilizar MySQL y agregar la base de datos que aparece en el
archivo `database/sql/dump.sql`. Para el desarrollo de la aplicación, se utilizó Docker para contener una instancia de
MySQL, cuyas instrucciones de instalación están [aquí](database/README.md).

## Estructura de directorios

### Mirada general

```
.
├── .gitignore
├── pom.xml
├── README.md
├── nb-configuration.xml
├── markdown/
│   └── todo.md
├── datafiles/
│   ├── alumno.csv
│   ├── apoderado.csv
│   ├── curso.csv
│   └── profesor.csv
└── src/
    └── main/
        └── java/
            ├── aplicacion.controllers/
            │   ├── cli/
            │   └── gui/
            ├── aplicacion.data/
            │   ├── datafile/
            │   └── database/
            ├── aplicacion.models
            └── aplicacion.views/
                ├── cli/
                └── gui/
```

### Estructura de clases
```
.
└── src/main/java/
    ├── aplicacion.controllers.cli/
    │   ├── AlumnoControllerCLI.java
    │   └── CursoControllerCLI.java
    ├── aplicacion.data/
    │   ├── database/
    │   │   ├── AlumnoDB.java
    │   │   ├── ApoderadoDB.java
    │   │   ├── CursoDB.java
    │   │   ├── ProfesorDB.java
    │   │   └── ConexionDB.java
    │   ├── datafile/
    │   │   ├── AlumnoDatafile.java
    │   │   ├── ApoderadoDatafile.java
    │   │   ├── CursoDatafile.java
    │   │   ├── ProfesorDatafile.java
    │   │   └── Datafile.java
    │   ├── AlumnoData.java
    │   ├── ApoderadoData.java
    │   ├── CursoData.java
    │   └── ProfesorData.java
    ├── aplicacion.models/
    │   ├── Alumno.java
    │   ├── Apoderado.java
    │   ├── Curso.java
    │   ├── Persona.java
    │   ├── Profesor.java
    │   └── RegistroAsistencia.java
    └── aplicacion.views.cli/
        ├── AlumnoViewCLI.java
        ├── CursoViewCLI.java
        ├── CLI.java
        └── UtilsCLI.java
```

Las carpetas en las que se divide el proyecto corresponden a:
* Modelo -- Vista -- Controlador
  * `aplicacion.controllers`: Controladores de las interfaces de interacción con el usuario. Se encargan de la comunicación entre el origen de datos y los aspectos visuales de las interfaces visuales y de consola de comandos.
  * `aplicacion.models`: Modelos de datos (objetos) correspondientes al problema.
  * `aplicacion.views`: Vistas al usuario, ya sea por `cli` (linea de comandos) o `gui` (interfaz grafica).
* `aplicacion.data`: Carpeta con las **interfaces** de acceso de datos para generar los objetos que correspondan. Incluye los dos directorios `database` y `datafile`, ambas con las clases que implementan las interfaces en `aplicacion.data` y que permiten trabajar con BBDD MySQL y archivos .CSV, respectivamente.
* `utils.fakedata`: Carpeta con los archivos necesarios para generar datos aleatorios falsos, para utilizar en las pruebas al programa.

## IDE

Para el desarrollo de este código, se utilizará **Netbeans** e **IntelliJ Idea**.

## Descripcion

Proyecto de software de gestión de asistencia de alumnos en un colegio, utilizando el lenguaje **Java** y el paradigma de **programación orientada a objetos**.

[java-badge]: https://camo.githubusercontent.com/f6c777e8c5c9ae4a6331664dab0a10c4cc3a1895ac3ababcc39b53058ba145d2/68747470733a2f2f696d672e736869656c64732e696f2f7374617469632f76313f7374796c653d666f722d7468652d6261646765266d6573736167653d4a61766126636f6c6f723d303037333936266c6f676f3d4a617661266c6f676f436f6c6f723d464646464646266c6162656c3d