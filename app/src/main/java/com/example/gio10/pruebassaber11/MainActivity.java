package com.example.gio10.pruebassaber11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.gio10.pruebassaber11.model.EstudianteDbHelper;

public class MainActivity extends AppCompatActivity {

    EditText nombre, capital, nHab, idiom, moned;
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

    }
}
