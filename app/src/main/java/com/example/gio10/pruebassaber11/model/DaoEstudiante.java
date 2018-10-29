package com.example.gio10.pruebassaber11.model;


import android.provider.BaseColumns;

public final class DaoEstudiante {

    private DaoEstudiante(){

    }

    public static class EstudianteEntry implements BaseColumns {
        public static final String TABLE_NAME = "Estudiante";
        public static final String COLUMN_NAME_IDENTIFICACION = "identificacion";
        public static final String COLUMN_NAME_NOMBRE = "nombre";
        public static final String COLUMN_NAME_APELLIDO = "apellido";
        public static final String COLUMN_NAME_COLEGIO = "colegio";
        public static final String COLUMN_NAME_TCOLEGIO = "tcolegio";
        public static final String COLUMN_NAME_DEPARTAMENTO = "departamento";
        public static final String COLUMN_NAME_CIUDAD = "ciudad";
        public static final String COLUMN_NAME_PUNTAJE = "puntaje";
    }

    public static final String SQL_CREATE_ENTRIES =
            /*"CREATE TABLE " + EstudianteEntry.TABLE_NAME + " (" +
                    EstudianteEntry._ID + " INTEGER PRIMARY KEY," +
                    EstudianteEntry.COLUMN_NAME_IDENTIFICACION + " INTEGER," +
                    EstudianteEntry.COLUMN_NAME_NOMBRE + " TEXT," +
                    EstudianteEntry.COLUMN_NAME_APELLIDO + " TEXT," +
                    EstudianteEntry.COLUMN_NAME_COLEGIO + " TEXT," +
                    EstudianteEntry.COLUMN_NAME_TCOLEGIO + " TEXT," +
                    EstudianteEntry.COLUMN_NAME_DEPARTAMENTO + " TEXT," +
                    EstudianteEntry.COLUMN_NAME_CIUDAD + " TEXT," +
                    EstudianteEntry.COLUMN_NAME_PUNTAJE + " INTEGER)";*/
            "CREATE TABLE " + EstudianteEntry.TABLE_NAME + " (" +
                    EstudianteEntry._ID + " INTEGER PRIMARY KEY," +
                    EstudianteEntry.COLUMN_NAME_IDENTIFICACION + " INTEGER NOT NULL," +
                    EstudianteEntry.COLUMN_NAME_NOMBRE + " TEXT," +
                    EstudianteEntry.COLUMN_NAME_APELLIDO + " TEXT," +
                    EstudianteEntry.COLUMN_NAME_COLEGIO + " TEXT," +
                    EstudianteEntry.COLUMN_NAME_TCOLEGIO + " TEXT," +
                    EstudianteEntry.COLUMN_NAME_DEPARTAMENTO + " TEXT," +
                    EstudianteEntry.COLUMN_NAME_CIUDAD + " TEXT," +
                    EstudianteEntry.COLUMN_NAME_PUNTAJE + " INTEGER," +
                    "CONSTRAINT name_unique UNIQUE (" + EstudianteEntry.COLUMN_NAME_IDENTIFICACION + "))";


    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + EstudianteEntry.TABLE_NAME;
}
