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


        //Crea la base de datos.
        BDCreador dataBase =
                new BDCreador(this, "BDHorarios", null, 1);
        db = dataBase.getWritableDatabase();




        //Recogemos los datos del archivo de configuración.
        SharedPreferences prefs =
                getSharedPreferences("configuracio", Context.MODE_PRIVATE);

        grup = prefs.getString("grup", "A1");
        String colorFondo = prefs.getString("colorFondo", "Verd");


        //Se aplica el color de fondo automaticamente.
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


        Cursor c = db.rawQuery("SELECT * FROM horari WHERE " + "('" + horaActual + "' BETWEEN hora_inici AND hora_fi) AND (grup = '" + grup + "') AND (dia_setmana = '" + diaSetmana + "')", null);

        if (c.moveToFirst()) {
            do {
                codiAsignatura = c.getString(2);
                horaInici = c.getString(3);
                horaFi = c.getString(4);
            } while (c.moveToNext());
        }

    }



    /**
     * Metode que serviex per a recollir el dia actual de la setmana en format de text utilitzant el
     * calendar.
     * @return
     */

    public String diaSetmana(){
        int dia = 0;
        String diaSetmana = "";

        Calendar cal = Calendar.getInstance();

        dia = cal.get(Calendar.DAY_OF_WEEK) - 1;

        switch (dia) {
            case 1:
                diaSetmana = "Lunes";
                break;
            case 2:
                diaSetmana = "Martes";
                break;
            case 3:
                diaSetmana = "Miercoles";
                break;
            case 4:
                diaSetmana = "Jueves";
                break;
            case 5:
                diaSetmana = "Viernes";
                break;
        }



        return diaSetmana;
    }


    /**
     * Metode per a recollir el nom del professor, fa una sentencia on busca en la taula
     * professors els que coincideixen amb la id que es pasa i agafa el nom.
     * @param codiAsignatura
     * @return
     */
    public String professor(String codiAsignatura){
        String nomProfessor = "";


        Cursor c = db.rawQuery("SELECT nom_professor FROM professors WHERE ('" + codiAsignatura +  "' LIKE id_professor)", null);

        if (c.moveToFirst()) {
            do {
                nomProfessor = c.getString(1);
            }while(c.moveToNext());
        }

        return nomProfessor;
    }


    /**
     * Metode per a recollir el nom de l'assignatura, fa una consulta on busca en la taula asignatura
     * les asignatures que coincideixin amb l'id del professor i agafara el nom.
     * @param codiAsignatura
     * @return
     */
    public String assignatura(String codiAsignatura){
        String nomAssignatura = "";


        Cursor c = db.rawQuery("SELECT nom_asignatura FROM asignatura WHERE " + codiAsignatura + " LIKE id_professor", null);

        if (c.moveToFirst()) {
            do {
                nomAssignatura = c.getString(1);
            }while(c.moveToNext());
        }

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

        profesor = professor(codiAsignatura);

        nomAsignatura = assignatura(codiAsignatura);

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
