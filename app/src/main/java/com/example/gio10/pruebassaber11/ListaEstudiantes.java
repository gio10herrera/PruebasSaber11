package com.example.gio10.pruebassaber11;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.gio10.pruebassaber11.model.DaoEstudiante;
import com.example.gio10.pruebassaber11.model.Estudiante;
import com.example.gio10.pruebassaber11.model.EstudianteDbHelper;

import java.util.ArrayList;

public class ListaEstudiantes extends AppCompatActivity {

    EstudianteDbHelper basedatos;
    ListView listado;
    ArrayList<Estudiante> estudiantes = new ArrayList<>();
    Cursor cur;
    SimpleCursorAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_estudiantes);

        listado = (ListView) findViewById(R.id.listaEstudiante);
        cur = GetAllData();
        llenar(cur);
        String from [] = new String[]{DaoEstudiante.EstudianteEntry.COLUMN_NAME_IDENTIFICACION,DaoEstudiante.EstudianteEntry.COLUMN_NAME_NOMBRE,DaoEstudiante.EstudianteEntry.COLUMN_NAME_PUNTAJE};
        int to [] = new int[] {R.id.txtLaId,R.id.txtElNombre,R.id.txtPunt};
        try {
            adaptador = new SimpleCursorAdapter(this,R.layout.lista_detalle_estudiante,cur,from,to,0);
        }
        catch (Exception e){
            Toast.makeText(ListaEstudiantes.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

        listado.setAdapter(adaptador);
        close();
        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Estudiante e = estudiantes.get(i);
                Intent it = new Intent(ListaEstudiantes.this,DatosEstudiante.class);
                it.putExtra("estudiante",e);
                it.putExtra("i",i);
                startActivity(it);
                finish();
                //String cadena = p.getNombre() + " "  + p.getCapital() + " " + p.getContinente();
                //Toast.makeText(ListaPaises.this,"Item seleccionado = " + i + "  " + cadena, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void llenar(Cursor c){
        String nombre, apellido, colegio, tColegio, dpto, ciudad;
        int puntaje;
        int id;
        while(c.moveToNext()){
            id = Integer.parseInt(c.getString(1));
            nombre = c.getString(2);
            apellido = c.getString(3);
            colegio = c.getString(4);
            tColegio = c.getString(5);
            dpto = c.getString(6);
            ciudad = c.getString(7);
            puntaje = Integer.parseInt(c.getString(8));

            Estudiante e = new Estudiante(id,nombre,apellido,colegio,tColegio,dpto,ciudad,puntaje);
            estudiantes.add(e);

        }
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

    public Cursor GetAllData()
    {
        abrir();
        SQLiteDatabase sqldata = basedatos.getReadableDatabase();
        Cursor c = sqldata.rawQuery("SELECT * FROM " + DaoEstudiante.EstudianteEntry.TABLE_NAME, null);

        return c;
    }
}
