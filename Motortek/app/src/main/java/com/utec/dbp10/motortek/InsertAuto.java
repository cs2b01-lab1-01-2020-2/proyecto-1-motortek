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

public class InsertAuto extends AppCompatActivity {

    List<String> ficheroCompleto = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_auto);

        String[] archivos = fileList();

        if (ArchivoExiste(archivos, "autos_final.txt"))
        {
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("autos_final.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();

                while (linea != null){
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
            } catch (IOException ignored) {

            }
        }

        if (ArchivoExiste(archivos, "usuario.txt"))
        {
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("usuario.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();

                while (linea != null){
                    ficheroCompleto.add(linea);
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

    public void escribirFichero(String p, String c, String m){
        try {
            boolean flag = false;
            for (String j : ficheroCompleto)
            {
                String[] words = j.split(" ");
                if (words[0].equals(c))
                {
                    flag = true;
                }
            }
            if (flag)
            {
                OutputStreamWriter file = new OutputStreamWriter(openFileOutput("autos_final.txt", Activity.MODE_APPEND));
                file.append("\n").append(p).append("/").append(c).append("/").append(m);
                file.flush();
                file.close();
                Toast.makeText(this, "Se ha insertado el auto", Toast.LENGTH_LONG).show();
            }
            else Toast.makeText(this, "El correo no esta registrado", Toast.LENGTH_LONG).show();
        } catch (IOException ignored) {
        }
    }

    public void back (View view){
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
    }

    public void insert (View view){
        EditText numplaca = findViewById(R.id.placa_auto);
        EditText modelo_auto = findViewById(R.id.modelo_auto);
        EditText correo_auto = findViewById(R.id.correo);
        String placa = numplaca.getText().toString();
        String modelo = modelo_auto.getText().toString();
        String correo = correo_auto.getText().toString();
        if(!placa.equals("") || !correo.equals("") || !modelo.equals("")){
            escribirFichero(placa,correo,modelo);
        }
        else{
            Toast.makeText(this, "Hay datos vacios", Toast.LENGTH_LONG).show();
        }

    }
}