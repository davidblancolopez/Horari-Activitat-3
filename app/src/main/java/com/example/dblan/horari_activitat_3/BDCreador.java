package com.example.dblan.horari_activitat_3;

/**
 * Created by dblan on 08/01/2017.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import java.util.HashMap;

/**
 * Created by dblan on 29/12/2016.
 */

public class BDCreador extends SQLiteOpenHelper {
    String [] sqlCreate = new String [3];

    //String[] sqlInserts = new String[30];

    public BDCreador(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        sqlCreate[0] = "CREATE TABLE horari (id_horari INTEGER, grup VARCHAR2(2), id_assignatura VARCHAR2(2), hora_inici VARCHAR2(8), hora_fi VARCHAR2(8), dia_setmana VARCHAR2(9))";
        sqlCreate[1] = "CREATE TABLE asignatura (id_asignatura VARCHAR2(2), nom_asignatura VARCHAR2(8), id_professor INTEGER)";
        sqlCreate[2] = "CREATE TABLE professors (id_professor INTEGER PRIMARY KEY, nom_professor VARCHAR2(30))";


        String sqlInsertAsignaturas[] = {"INSERT INTO asignatura VALUES(1, 'M2', 1)",
                "INSERT INTO asignatura VALUES(2, 'M3', 2) ",
                "INSERT INTO asignatura VALUES(3, 'M7', 3) " ,
                "INSERT INTO asignatura VALUES(8, 'M8', 4) " ,
                "INSERT INTO asignatura VALUES(4, 'M9', 1) " ,
                "INSERT INTO asignatura VALUES(5, 'M10', 5) " ,
                "INSERT INTO asignatura VALUES(6, 'Tutoria', 2) " ,
                "INSERT INTO asignatura VALUES(7, 'PATI', NULL)"};


        String sqlInsertProfessors[] = {"INSERT INTO professors  VALUES(1, 'Jorge Rubio')" ,
                "INSERT INTO professors  VALUES(2, 'Josefa Gonzalez') " ,
                "INSERT INTO professors  VALUES(3, 'Jose Antonio Leo') " ,
                "INSERT INTO professors  VALUES(4, 'Lluis Maria Perpinya') " ,
                "INSERT INTO professors  VALUES(5, 'Marta Planas')"};


        String sqlInsertsHoraris[] = {"INSERT INTO horari VALUES(1, 'A1', 3, '15:00:00', '17:59:59', 'Lunes')",
                "INSERT INTO horari VALUES(1, 'A2', 3, '15:00:00', '17:59:59', 'Lunes') " ,
                "INSERT INTO horari VALUES(1, 'A2', 7, '18:00:00', '18:19:59', 'Lunes') " ,
                "INSERT INTO horari VALUES(1, 'A2', 2, '19:20:00', '21:20:00', 'Lunes')" ,
                "INSERT INTO horari VALUES(1, 'A1', 2, '15:00:00', '16:59:59', 'Martes') ",
                "INSERT INTO horari VALUES(1, 'A2', 8, '15:00:00', '16:59:59', 'Martes') " ,
                "INSERT INTO horari VALUES(1, 'A1', 5, '17:00:00', '17:59:59', 'Martes') " ,
                "INSERT INTO horari VALUES(1, 'A2', 5, '17:00:00', '17:59:59', 'Martes') " ,
                "INSERT INTO horari VALUES(1, 'A1', 7, '18:00:00', '18:19:59', 'Martes') " ,
                "INSERT INTO horari VALUES(1, 'A2', 7, '18:00:00', '18:19:59', 'Martes') " ,
                "INSERT INTO horari VALUES(1, 'A1', 3, '18:20:00', '19:19:59', 'Martes') " ,
                "INSERT INTO horari VALUES(1, 'A2', 3, '18:20:00', '19:19:59', 'Martes') " ,
                "INSERT INTO horari VALUES(1, 'A1', 1, '19:20:00', '21:20:20', 'Martes') " ,
                "INSERT INTO horari VALUES(1, 'A2', 1, '19:20:00', '21:20:20', 'Martes') " ,
                "INSERT INTO horari VALUES(1, 'A1', 1, '16:00:00', '16:59:59', 'Miercoles') " ,
                "INSERT INTO horari VALUES(1, 'A2', 1, '16:00:00', '16:59:59', 'Miercoles') " ,
                "INSERT INTO horari VALUES(1, 'A1', 4, '17:00:00', '17:59:59', 'Miercoles') " ,
                "INSERT INTO horari VALUES(1, 'A2', 8, '17:00:00', '17:59:59', 'Miercoles') " ,
                "INSERT INTO horari VALUES(1, 'A1', 4, '18:20:00', '19:19:59', 'Miercoles') " ,
                "INSERT INTO horari VALUES(1, 'A2', 8, '18:20:00', '19:19:59', 'Miercoles') " ,
                "INSERT INTO horari VALUES(1, 'A1', 2, '19:20:00', '20:19:59', 'Miercoles') " ,
                "INSERT INTO horari VALUES(1, 'A2', 8, '19:20:00', '20:20:00', 'Miercoles') " ,
                "INSERT INTO horari VALUES(1, 'A1', 2, '20:20:00', '21:20:00', 'Miercoles') " ,
                "INSERT INTO horari VALUES(1, 'A1', 4, '15:00:00', '15:59:59', 'Jueves') " ,
                "INSERT INTO horari VALUES(1, 'A1', 8, '16:00:00', '16:59:59', 'Jueves') " ,
                "INSERT INTO horari VALUES(1, 'A2', 2, '16:00:00', '16:59:59', 'Jueves') " ,
                "INSERT INTO horari VALUES(1, 'A1', 8, '17:00:00', '17:59:59', 'Jueves') " ,
                "INSERT INTO horari VALUES(1, 'A2', 2, '17:00:00', '17:59:59', 'Jueves') " ,
                "INSERT INTO horari VALUES(1, 'A1', 1, '18:20:00', '21:20:00', 'Jueves') " ,
                "INSERT INTO horari VALUES(1, 'A2', 1, '18:20:00', '21:20:00', 'Jueves') " ,
                "INSERT INTO horari VALUES(1, 'A1', 5, '15:00:00', '16:59:59', 'Viernes')" ,
                "INSERT INTO horari VALUES(1, 'A2', 5, '15:00:00', '16:59:59', 'Viernes')" ,
                "INSERT INTO horari VALUES(1, 'A1', 8, '17:00:00', '17:59:59', 'Viernes')" ,
                "INSERT INTO horari VALUES(1, 'A2', 4, '17:00:00', '17:59:59', 'Viernes')" ,
                "INSERT INTO horari VALUES(1, 'A1', 8, '18:20:00', '19:19:00', 'Viernes')" ,
                "INSERT INTO horari VALUES(1, 'A2', 4, '18:20:00', '19:19:00', 'Viernes')" ,
                "INSERT INTO horari VALUES(1, 'A1', 1, '19:20:00', '21:20:00', 'Viernes')" ,
                "INSERT INTO horari VALUES(1, 'A2', 1, '19:20:00', '21:20:00', 'Viernes')"};




        //SENTENCIAS PARA CREAR LAS TABLAS
        db.execSQL(sqlCreate[0]);
        db.execSQL(sqlCreate[1]);
        db.execSQL(sqlCreate[2]);

        //SENTENCIAS PARA INSERTAR DATOS EN LAS TABLAS

        //Bucle de assignaturas
        for (int i= 0; i < sqlInsertAsignaturas.length; i++){
            db.execSQL(sqlInsertAsignaturas[i]);
        }

        //Bucle de profesores
        for (int i= 0; i < sqlInsertProfessors.length; i++){
            db.execSQL(sqlInsertProfessors[i]);
        }

        //Bucle de horari
        for (int i= 0; i < sqlInsertsHoraris.length; i++){
            db.execSQL(sqlInsertsHoraris[i]);
        }


    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }




        /*LUNES = 2, MARTES = 3, DOMINGO = 0*/

    /* SELECT id_assignatura
    *  FROM horaris
    *  WHERE hora_del_sistema BETWEEN hora_inici AND hora_fi*/
}
