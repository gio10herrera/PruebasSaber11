package com.example.gio10.pruebassaber11;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gio10.pruebassaber11.model.DaoEstudiante;
import com.example.gio10.pruebassaber11.model.Estudiante;
import com.example.gio10.pruebassaber11.model.EstudianteDbHelper;

public class Actualizar extends AppCompatActivity {

    TextView id, nom, cont;
    EditText coleg, elDpto, laciudad;
    Spinner tipoColeg, pLectCrit, pMat, pSoc, pNatur, pIngles;
    Button btnActualizar;
    Estudiante e;
    EstudianteDbHelper basedatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);

        tipoColeg = findViewById(R.id.spinnerActTcolegio);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.textoTiposColegio, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        tipoColeg.setAdapter(adapter);

        pLectCrit = findViewById(R.id.spinnerActLCritica);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterLect = ArrayAdapter.createFromResource(this,
                R.array.arrayPuntajes, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterLect.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        pLectCrit.setAdapter(adapterLect);

        pMat = findViewById(R.id.spinnerActMat);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterMat = ArrayAdapter.createFromResource(this,
                R.array.arrayPuntajes, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterMat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        pMat.setAdapter(adapterMat);

        pSoc = findViewById(R.id.spinnerActSoc);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterSoc = ArrayAdapter.createFromResource(this,
                R.array.arrayPuntajes, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterSoc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        pSoc.setAdapter(adapterSoc);

        pNatur = findViewById(R.id.spinnerActNat);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterNat = ArrayAdapter.createFromResource(this,
                R.array.arrayPuntajes, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterNat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        pNatur.setAdapter(adapterNat);

        pIngles = findViewById(R.id.spinnerActIngles);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterIng = ArrayAdapter.createFromResource(this,
                R.array.arrayPuntajes, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterIng.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        pIngles.setAdapter(adapterIng);


        e = getIntent().getParcelableExtra("estudiante");
        id = findViewById(R.id.txtTvActId);
        nom = findViewById(R.id.txtTvActNombre);
        id.setText("IDENTIFICACION: " + String.valueOf(e.getIdentificacion()).toUpperCase());
        nom.setText("ESTUDIANTE: " + e.getNombre().toUpperCase() + " " + e.getApellido().toUpperCase());
        btnActualizar = findViewById(R.id.btnActualizar2);
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizar();
            }
        });
    }

    public void actualizar(){
        abrir();
        try {
            SQLiteDatabase sqldata = basedatos.getWritableDatabase();
            ContentValues values = new ContentValues();
            coleg = (EditText) findViewById(R.id.txtActColegio);
            elDpto = (EditText) findViewById(R.id.txtActDep);
            laciudad = (EditText) findViewById(R.id.txtActCiudad);


            values.put(DaoEstudiante.EstudianteEntry.COLUMN_NAME_COLEGIO, coleg.getText().toString().toUpperCase());
            values.put(DaoEstudiante.EstudianteEntry.COLUMN_NAME_DEPARTAMENTO, elDpto.getText().toString().toUpperCase());
            values.put(DaoEstudiante.EstudianteEntry.COLUMN_NAME_CIUDAD, laciudad.getText().toString().toUpperCase());
            values.put(DaoEstudiante.EstudianteEntry.COLUMN_NAME_PUNTAJE, puntaje());


            sqldata.update(DaoEstudiante.EstudianteEntry.TABLE_NAME,values,DaoEstudiante.EstudianteEntry.COLUMN_NAME_IDENTIFICACION+"=?",new String[]{String.valueOf(e.getIdentificacion())});
            Toast.makeText(this,"ACTUALIZACION EXITOSA", Toast.LENGTH_LONG).show();

        }
        catch (Exception e){
            Toast.makeText(this,"Error al actualizar Datos", Toast.LENGTH_LONG).show();
        }
        close();

        Intent i = new Intent(Actualizar.this,ListaEstudiantes.class);
        startActivity(i);
        finish();
    }

    public int puntaje(){
        double punt = 0;
        double puntajeLC = Double.parseDouble(pLectCrit.getSelectedItem().toString())*3;
        double puntajeMat = Double.parseDouble(pMat.getSelectedItem().toString())*3;
        double puntajeSoc = Double.parseDouble(pSoc.getSelectedItem().toString())*3;
        double puntajeCienc = Double.parseDouble(pNatur.getSelectedItem().toString())*3;
        double puntajeIng = Double.parseDouble(pIngles.getSelectedItem().toString());
        punt = (puntajeLC + puntajeMat + puntajeSoc + puntajeCienc + puntajeIng)/13;
        punt = punt*5;
        int redondeado = Integer.parseInt(String.valueOf(Math.round(punt)));

        return redondeado;
    }

    public void abrir(){
        try {
            basedatos = new EstudianteDbHelper(this);
        }
        catch(Exception e){
            Toast.makeText(this,"Error al crear base datos", Toast.LENGTH_LONG).show();
        }
    }

    public void close(){
        try {
            basedatos.close();
        }
        catch(Exception e){
            Toast.makeText(this,"Error al cerrar base datos", Toast.LENGTH_LONG).show();
        }
    }
}
