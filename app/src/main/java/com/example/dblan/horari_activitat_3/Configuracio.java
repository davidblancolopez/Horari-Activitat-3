package com.example.dblan.horari_activitat_3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Configuracio extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracio);

        Button btnApply = (Button) findViewById(R.id.buttonAceptar);
        btnApply.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonAceptar){
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);

            Spinner grup = (Spinner) findViewById(R.id.spinnerCurs);
            Spinner color = (Spinner) findViewById(R.id.spinnerColor);


            SharedPreferences prefs =
                    getSharedPreferences("configuracio", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            editor.putString("grup",grup.getSelectedItem().toString());
            editor.putString("colorFondo",color.getSelectedItem().toString());

            editor.commit();

            finish();
        }
    }
}