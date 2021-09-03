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

- [Estructura de directorios](#estructura-de-directorios)
  - [Mirada general](#mirada-general)
  - [Estructura de clases](#estructura-de-clases)
- [IDE de desarrollo](#ide)
- [Descripción](#descripcion)
- [To-do](markdown/todo.md)

## Estructura de directorios

### Mirada general
```
.
├── .gitignore
├── README.md
├── pom.xml
└── src/
    └── main/
        └── java/
            ├── data/
            │   ├── database/
            │   └── datafile/
            ├── fakedata/
            ├── models/
            └── views/
                ├── cli/
                └── gui/
```

### Estructura de clases
```
.
└── src/main/java/
    ├── data/
    │   ├── AlumnoData.java
    │   ├── ApoderadoData.java
    │   ├── CursoData.java
    │   ├── ProfesorData.java
    │   ├── database/
    │   │   ├── AlumnoDB.java
    │   │   ├── ApoderadoDB.java
    │   │   ├── CursoDB.java
    │   │   ├── ProfesorDB.java
    │   │   └── DBConnection.java
    │   └── datafile/
    │       ├── AlumnoDatafile.java
    │       ├── ApoderadoDatafile.java
    │       ├── CursoDatafile.java
    │       ├── ProfesorDatafile.java
    │       └── Datafile.java
    ├── fakedata/
    │   └── Fakedata.java
    ├── models/
    │   ├── Alumno.java
    │   ├── Apoderado.java
    │   ├── Curso.java
    │   ├── Persona.java
    │   ├── Profesor.java
    │   └── RegistroAsistencia.java
    └── views/
        ├── cli/
        │   ├── AlumnoView.java
        │   ├── CursoView.java
        │   └── CLI.java
        └── gui/
            └── GUI.java
```

Las carpetas en las que se divide el proyecto corresponden a:
* `data`: Carpeta con las **interfaces** de acceso de datos para generar los objetos que correspondan. Incluye los dos directorios `database` y `datafile`, ambas con las clases que implementan las interfaces en `data` y que permiten trabajar con BBDD MySQL y archivos .CSV, respectivamente.
* `fakedata`: Carpeta con los archivos necesarios para generar datos aleatorios falsos, para utilizar en las pruebas al programa.
* `models`: Modelos de datos (objetos) correspondientes al problema.
* `views`: Vistas al usuario, ya sea por `cli` (linea de comandos) o `gui` (interfaz grafica).

## IDE

Para el desarrollo de este código, se utilizará **Netbeans** e **IntelliJ Idea**.

## Descripcion

Proyecto de software de gestión de asistencia de alumnos en un colegio, utilizando el lenguaje **Java** y el paradigma de **programación orientada a objetos**.

[java-badge]: https://camo.githubusercontent.com/f6c777e8c5c9ae4a6331664dab0a10c4cc3a1895ac3ababcc39b53058ba145d2/68747470733a2f2f696d672e736869656c64732e696f2f7374617469632f76313f7374796c653d666f722d7468652d6261646765266d6573736167653d4a61766126636f6c6f723d303037333936266c6f676f3d4a617661266c6f676f436f6c6f723d464646464646266c6162656c3d