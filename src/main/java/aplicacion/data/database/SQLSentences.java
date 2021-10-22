package aplicacion.data.database;

/**
 * Enum con las sentencias SQL utilizadas
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 2.0
 */
public enum SQLSentences {
    GET_TODOS_ALUMNOS {
        public String toString() {
            return "SELECT * FROM Alumnos";
        }
    },
    GET_ALUMNOS_NIVEL {
        public String toString() {
            return "SELECT * FROM Alumnos WHERE nivel = %d";
        }
    },
    GET_ALUMNOS_NIVEL_PAR {
        public String toString() {
            return "SELECT * FROM Alumnos WHERE nivel = %d AND paralelo = '%c'";
        }
    },
    GET_ALUMNO_RUT {
        public String toString() {
            return "SELECT * FROM Alumnos WHERE rut = '%s'";
        }
    },
    INSERT_ALUMNO {
        public String toString() {
            return "INSERT INTO Alumnos (nivel, paralelo, rut, nombres, apellido_paterno," +
                    "apellido_materno, rut_apoderado) VALUES (%d, '%c', '%s', '%s', '%s', '%s', '%s')";
        }
    },
    UPDATE_ALUMNO {
        public String toString() {
            return "UPDATE Alumnos SET nivel = %d, paralelo = '%c' , rut = '%s' ,\" +\n" +
                    "        \"nombres = '%s' , apellido_paterno = '%s' , apellido_materno = '%s', \" + \"rut_apoderado = '%s' \" +\n" +
                    "        \"WHERE rut = '%s'";
        }
    },
    DELETE_ALUMNO {
        public String toString() {
            return "DELETE FROM Alumnos WHERE rut = '%s'";
        }
    },
    GET_PROFESORES {
        public String toString() {
            return "SELECT * FROM Profesores";
        }
    },
    GET_PROFESOR {
        public String toString() {
            return "SELECT * FROM Profesores WHERE rut = '%s'";
        }
    },
    INSERT_PROFESOR {
        public String toString() {
            return "INSERT INTO Profesores (rut, nombres, apellido_paterno, apellido_materno, asignatura, telefono, email) VALUES ('%s', '%s', '%s', '%s', '%s', %d, '%s')";
        }
    },
    UPDATE_PROFESOR {
        public String toString() {
            return "UPDATE Profesores SET rut = '%s' , nombres = '%s' , apellido_paterno = '%s', apellido_materno = '%s', asignatura = '%s', telefono = %d, email = '%s' WHERE rut = '%s'";
        }
    },
    DELETE_PROFESOR {
        public String toString() {
            return "DELETE FROM Profesores WHERE rut = '%s'";
        }
    },
    GET_TODOS_CURSOS {
        public String toString() {
            return "SELECT * FROM Cursos";
        }
    },
    GET_CURSOS_NIVEL {
        public String toString() {
            return "SELECT * FROM Cursos WHERE nivel = %h";
        }
    },
    GET_CURSO {
        public String toString() {
            return "SELECT * FROM Cursos WHERE nivel = %d AND paralelo = '%c'";
        }
    },
    INSERT_CURSO {
        public String toString() {
            return "INSERT INTO Cursos (nivel, paralelo, rut_profesor) VALUES (%d, '%c', '%s')";
        }
    },
    UPDATE_CURSO {
        public String toString() {
            return "UPDATE Alumnos SET nivel = %d, paralelo = '%c' , rut_profesor = '%s' WHERE nivel = %d AND paralelo = '%c'";
        }
    },
    DELETE_CURSO {
        public String toString() {
            return "DELETE FROM Cursos WHERE nivel = %d AND paralelo = '%c'";
        }
    },
    GET_APODERADO {
        public String toString() {
            return "SELECT * FROM Apoderados WHERE rut = '%s'";
        }
    },
    INSERT_APODERADO {
        public String toString() {
            return "INSERT INTO Apoderados (rut, nombres, apellido_paterno, apellido_materno, telefono, email) VALUES ('%s', '%s', '%s', '%s', %d, '%s')";
        }
    },
    UPDATE_APODERADO {
        public String toString() {
            return "UPDATE Apoderados SET rut = '%s' , nombres = '%s' , apellido_paterno = '%s', apellido_materno = '%s', telefono = %d, email = '%s' WHERE rut = '%s'";
        }
    },
    DELETE_APODERADO {
        public String toString() {
            return "DELETE FROM Apoderados WHERE rut = '%s'";
        }
    }
}