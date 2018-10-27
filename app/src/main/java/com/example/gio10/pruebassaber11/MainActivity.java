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
import android.widget.Toast;

import com.example.gio10.pruebassaber11.model.DaoEstudiante;
import com.example.gio10.pruebassaber11.model.EstudianteDbHelper;

public class MainActivity extends AppCompatActivity {

    EditText id, nombre, apellido, colegio, dpto, ciudad;
    Spinner tipoColeg, pLectCrit, pMat, pSoc, pNatur, pIngles;
    Button guardar, mostrar;

    EstudianteDbHelper basedatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tipoColeg = findViewById(R.id.spinnerTipoColegio);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.textoTiposColegio, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        tipoColeg.setAdapter(adapter);

        pLectCrit = findViewById(R.id.spinnerLCritica);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterLect = ArrayAdapter.createFromResource(this,
                R.array.arrayPuntajes, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterLect.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        pLectCrit.setAdapter(adapterLect);

        pMat = findViewById(R.id.spinnerMatematicas);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterMat = ArrayAdapter.createFromResource(this,
                R.array.arrayPuntajes, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterMat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        pMat.setAdapter(adapterMat);

        pSoc = findViewById(R.id.spinnerSociales);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterSoc = ArrayAdapter.createFromResource(this,
                R.array.arrayPuntajes, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterSoc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        pSoc.setAdapter(adapterSoc);

        pNatur = findViewById(R.id.spinnerNaturales);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterNat = ArrayAdapter.createFromResource(this,
                R.array.arrayPuntajes, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterNat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        pNatur.setAdapter(adapterNat);

        pIngles = findViewById(R.id.spinnerIngles);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterIng = ArrayAdapter.createFromResource(this,
                R.array.arrayPuntajes, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterIng.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        pIngles.setAdapter(adapterIng);

        guardar = (Button) findViewById(R.id.btnGuardar);
        mostrar = (Button) findViewById(R.id.btnMostrar);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar();
            }
        });

        mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ListaEstudiantes.class);
                startActivity(i);
            }
        });

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

    public void guardar(){
        abrir();
        try {
            SQLiteDatabase sqldata = basedatos.getWritableDatabase();
            ContentValues values = new ContentValues();
            id = (EditText) findViewById(R.id.txtIdentificacion);
            nombre = (EditText) findViewById(R.id.txtNombre);
            apellido = (EditText) findViewById(R.id.txtApellido);
            colegio = (EditText) findViewById(R.id.txtColegio);
            dpto = (EditText) findViewById(R.id.txtDepartamento);
            ciudad = (EditText) findViewById(R.id.txtCiudad);

            values.put(DaoEstudiante.EstudianteEntry.COLUMN_NAME_IDENTIFICACION, Integer.parseInt(id.getText().toString()));
            values.put(DaoEstudiante.EstudianteEntry.COLUMN_NAME_NOMBRE, nombre.getText().toString());
            values.put(DaoEstudiante.EstudianteEntry.COLUMN_NAME_APELLIDO, apellido.getText().toString());
            values.put(DaoEstudiante.EstudianteEntry.COLUMN_NAME_COLEGIO, colegio.getText().toString());
            values.put(DaoEstudiante.EstudianteEntry.COLUMN_NAME_TCOLEGIO, tipoColeg.getSelectedItem().toString());
            values.put(DaoEstudiante.EstudianteEntry.COLUMN_NAME_DEPARTAMENTO, dpto.getText().toString());
            values.put(DaoEstudiante.EstudianteEntry.COLUMN_NAME_CIUDAD, ciudad.toString());
            values.put(DaoEstudiante.EstudianteEntry.COLUMN_NAME_PUNTAJE, puntaje());

            long newRowId = sqldata.insert(DaoEstudiante.EstudianteEntry.TABLE_NAME, null, values);
            Toast.makeText(this,"INSERCIÔN EXITOSA", Toast.LENGTH_LONG).show();
            close();
        }
        catch (Exception e){
            Toast.makeText(this,"Error al insertar Datos", Toast.LENGTH_LONG).show();
        }
    }

    public double puntaje(){
        double punt = 0;
        double puntajeLC = Double.parseDouble(pLectCrit.getSelectedItem().toString())*3;
        double puntajeMat = Double.parseDouble(pMat.getSelectedItem().toString())*3;
        double puntajeSoc = Double.parseDouble(pSoc.getSelectedItem().toString())*3;
        double puntajeCienc = Double.parseDouble(pNatur.getSelectedItem().toString())*3;
        double puntajeIng = Double.parseDouble(pIngles.getSelectedItem().toString());
        punt = (puntajeLC + puntajeMat + puntajeSoc + puntajeCienc + puntajeIng)/13;
        punt = punt*5;
        return punt;
    }
}
