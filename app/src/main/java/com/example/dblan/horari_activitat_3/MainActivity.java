package com.example.dblan.horari_activitat_3;


import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.database.Cursor;
        import android.graphics.Color;
        import android.icu.text.SimpleDateFormat;
        import android.icu.util.Calendar;
        import android.preference.PreferenceManager;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteDatabase.CursorFactory;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Spinner;
        import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{


    SQLiteDatabase db;

    String grup, nomAsignatura, diaSetmana, codiAsignatura, horaInici, horaFi, profesor, horaActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        BDCreador dataBase =
                new BDCreador(this, "BDHorarios", null, 1);
        db = dataBase.getWritableDatabase();

        //Es crea la Base de dades.
        /*BDCreador BD = new BDCreador(this, "BBDD", null, 1);
        db = BD.getWritableDatabase();*/




        SharedPreferences prefs =
                getSharedPreferences("configuracio", Context.MODE_PRIVATE);

        grup = prefs.getString("grup", "A1");
        String colorFondo = prefs.getString("colorFondo", "Verd");



        switch (colorFondo)
        {
            case "Vermell":
                findViewById(R.id.activity_main).setBackgroundColor(Color.rgb(224, 0, 0));
                break;
            case "Blau":
                findViewById(R.id.activity_main).setBackgroundColor(Color.rgb(0, 135, 229));
                break;
            case "Verd" :
                findViewById(R.id.activity_main).setBackgroundColor(Color.rgb(25, 187, 0));
                break;
            case "Groc" :
                findViewById(R.id.activity_main).setBackgroundColor(Color.rgb(255, 255, 0));
                break;
            case "Taronja" :
                findViewById(R.id.activity_main).setBackgroundColor(Color.rgb(255, 143, 0));
                break;
            case "Violeta" :
                findViewById(R.id.activity_main).setBackgroundColor(Color.rgb(139, 0, 255));
                break;
            case "Rosa" :
                findViewById(R.id.activity_main).setBackgroundColor(Color.rgb(255, 0, 216));
                break;
            case "Blanc" :
                findViewById(R.id.activity_main).setBackgroundColor(Color.rgb(255, 255, 255));
                break;
        }



        //Inicializamos el boton
        Button buttonActualizar = (Button) findViewById(R.id.buttonActualizar);
        //Le aplicamos un clickListener
        buttonActualizar.setOnClickListener(this);




    }


    /**
     * Boton de enviar y el menu de seleccion de FECHA.
     * @param v
     */
    @Override
    public void onClick(View v){

        if (v.getId() == R.id.buttonActualizar) {
            consultaBoto();
            setMensajes();
        }
    }


    /**********************************************************************************************************************************************
     * ************************************ METODES PER A REALITZAR CONSULTES   ******************************************************************
     **********************************************************************************************************************************************/



    /**
     * Metode que executa la consulta que ens donara el codi de l'asignatura, l'hora d'inici i l' hora final.
     */
    public void consultaWidget(){

        String[] args = new String[]{horaActual, grup, diaSetmana};
        Cursor c = db.rawQuery("SELECT * FROM horari WHERE ? BETWEEN hora_inici AND hora_fi AND grup = ? AND dia_setmana = ?", args);

        c.moveToFirst();
        do{
            codiAsignatura = c.getString(2);
            horaInici = c.getString(3);
            horaFi = c.getString(4);
        }while(c.moveToNext());

    }



    /**
     * Metode que serviex per a recollir el dia actual de la setmana en format de text utilitzant el
     * calendar.
     * @return
     */

    public String diaSetmana(){
        int dia = 0;
        String diaSetmana;

        String[] setmana = new String[]{
                "Domingo",
                "Lunes",
                "Martes",
                "Miercoles",
                "Jueves",
                "Viernes",
                "Sabado"};

        Calendar cal = Calendar.getInstance();

        dia = cal.get(Calendar.DAY_OF_WEEK);

        diaSetmana = setmana[dia - 1];

        return diaSetmana;
    }


    /**
     * Metode per a recollir el nom del professor, fa una sentencia on busca en la taula
     * professors els que coincideixen amb la id que es pasa i agafa el nom.
     * @param codiAsignatura
     * @return
     */
    public String professor(String codiAsignatura){
        String nomProfessor;

        String[] args = new String[]{codiAsignatura};
        Cursor c = db.rawQuery("SELECT nom_professor FROM professors WHERE ? LIKE id_professor", args);

        c.moveToFirst();

        nomProfessor = c.getString(0);

        return nomProfessor;
    }


    /**
     * Metode per a recollir el nom de l'assignatura, fa una consulta on busca en la taula asignatura
     * les asignatures que coincideixin amb l'id del professor i agafara el nom.
     * @param codiAsignatura
     * @return
     */
    public String assignatura(String codiAsignatura){
        String nomAssignatura;

        String[] args = new String[]{codiAsignatura};
        Cursor c = db.rawQuery("SELECT asignatura FROM asignatura WHERE ? LIKE id_professor", args);

        c.moveToFirst();

        nomAssignatura = c.getString(0);

        return nomAssignatura;
    }


    /**
     * metode que s'executara al pulsar el botó per actualitzar els missatges.
     */
    public void consultaBoto(){


        //Obtenim el dia de la setmana actual.
        diaSetmana = diaSetmana();

        //Obtenim l'hora del sistema.
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
        horaActual = format.format(cal.getTime());

        consultaWidget();

        //profesor = professor(codiAsignatura);

        //nomAsignatura = assignatura(codiAsignatura);

    }


    public void setMensajes(){

        TextView modul = (TextView) findViewById(R.id.tvModul);
        modul.setText(nomAsignatura);

        TextView horaInicial = (TextView) findViewById(R.id.tvHoraInici);
        horaInicial.setText(horaInici);

        TextView horaFinal = (TextView) findViewById(R.id.tvHoraFi);
        horaFinal.setText(horaFi);

        TextView profe = (TextView) findViewById(R.id.tvProfesor);
        profe.setText(profesor);
    }





}
