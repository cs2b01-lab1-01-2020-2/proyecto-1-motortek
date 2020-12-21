package com.utec.dbp10.motortek;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class InsertServicio extends AppCompatActivity {

    List<String> ficheroauto = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_servicio);

        String[] archivos = fileList();

        if (ArchivoExiste(archivos, "auto.txt"))
        {
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("auto.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();

                while (linea != null){
                    ficheroauto.add(linea);
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
            } catch (IOException ignored) {

            }
        }
    }

    private boolean ArchivoExiste(String[] files, String nombreArchivo) {
        for (String file : files)
        {
            if (nombreArchivo.equals(file))
            {
                return true;
            }
        }
        return false;
    }

    public void escribirFichero(String placa, String tipo, String fecha, String tiempo, String costo, String metodo, String descripcion, String personal){
        try {
            boolean flag = false;
            for (String j : ficheroauto)
            {
                String[] words = j.split(" ");
                if (words[0].equals(placa))
                {
                    flag = true;
                }
            }
            if (flag)
            {
                OutputStreamWriter file = new OutputStreamWriter(openFileOutput("servicios_core.txt", Activity.MODE_APPEND));
                file.append("\n").append(placa).append(";").append(tipo).append(";").append(fecha).append(";").append(tiempo).append(";").append(costo).append(";").append(metodo).append(";").append(descripcion).append(";").append(personal);
                file.flush();
                file.close();
                Toast.makeText(this, "Se inserto el servicio", Toast.LENGTH_LONG).show();
            }
            else Toast.makeText(this, "La placa no coincide", Toast.LENGTH_LONG).show();
        } catch (IOException ignored) {
        }
    }

    public void back (View view){
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
    }

    public void insert (View view){
        EditText numplaca = findViewById(R.id.placa_auto);
        EditText tipo = findViewById(R.id.tipo);
        EditText fecha = findViewById(R.id.fecha);
        EditText tiempo = findViewById(R.id.tiempo);
        EditText costo = findViewById(R.id.costo);
        EditText descripcion = findViewById(R.id.descripcion);
        EditText personal = findViewById(R.id.personal);
        EditText metodo = findViewById(R.id.metodo_pago);

        String placa = numplaca.getText().toString();
        String tipo_ = tipo.getText().toString();
        String fecha_ = fecha.getText().toString();
        String tiempo_= tiempo.getText().toString();
        String costo_= costo.getText().toString();
        String descripcion_= descripcion.getText().toString();
        String personal_= personal.getText().toString();
        String metodo_= metodo.getText().toString();

        escribirFichero(placa,tipo_,fecha_,tiempo_,costo_,metodo_,descripcion_,personal_);
    }

}