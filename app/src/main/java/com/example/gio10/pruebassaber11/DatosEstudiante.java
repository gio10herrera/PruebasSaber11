package com.example.gio10.pruebassaber11;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gio10.pruebassaber11.model.DaoEstudiante;
import com.example.gio10.pruebassaber11.model.Estudiante;
import com.example.gio10.pruebassaber11.model.EstudianteDbHelper;

public class DatosEstudiante extends AppCompatActivity {

    Estudiante e;
    TextView tv;
    Button borrar, actualizar;
    int i;
    EstudianteDbHelper basedatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_estudiante);
        e = getIntent().getParcelableExtra("estudiante");
        i = getIntent().getIntExtra("i",-1);
        tv = findViewById(R.id.txtViewDatosEstudiante);
        borrar = findViewById(R.id.btnBorrar);
        actualizar = findViewById(R.id.btnActualizar);

        String datos = "IDENTIFICACION: " + e.getIdentificacion() + "\nNOMBRE: " + e.getNombre()
                + " " + e.getApellido() + "\n"
                + "COLEGIO: " + e.getColegio() + "\nTIPO DE COLEGIO: " + e.getTipoColegio()
                + "\nDEPARTAMENTO: " + e.getDepartament() + "\nCIUDAD: " + e.getCiudad()
                + "\nPUNTAJE: " + e.getPuntaje();
        tv.setText(datos);
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrarFila();
            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizaFilas();
            }
        });
    }

    public void actualizaFilas(){
        Intent elIntent = new Intent(this,Actualizar.class);
        elIntent.putExtra("estudiante",e);
        startActivity(elIntent);
        finish();
    }

    public void borrarFila(){
        abrir();
        try {
            SQLiteDatabase sqldata = basedatos.getWritableDatabase();
            sqldata.delete(DaoEstudiante.EstudianteEntry.TABLE_NAME,DaoEstudiante.EstudianteEntry.COLUMN_NAME_IDENTIFICACION+"=?",new String[]{String.valueOf(e.getIdentificacion())});
            Toast.makeText(this,"ELIMINACIÓN EXITOSA", Toast.LENGTH_LONG).show();
            Intent it = new Intent(this,ListaEstudiantes.class);
            startActivity(it);
            finish();

        }
        catch (Exception e){
            Toast.makeText(this,"Error al eliminar Datos", Toast.LENGTH_LONG).show();
        }
        close();
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
