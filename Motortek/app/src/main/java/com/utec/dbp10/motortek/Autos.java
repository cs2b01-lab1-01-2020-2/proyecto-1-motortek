package com.utec.dbp10.motortek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Autos extends AppCompatActivity {

    List<String> Autos = new ArrayList<>();
    List<String> currentUsuario = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(this, "Bienvenido", Toast.LENGTH_LONG).show();

        String[] archivos = fileList();

        if (ArchivoExiste(archivos, "current_usuario.txt"))
        {
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("current_usuario.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();

                while (linea != null){
                    currentUsuario.add(linea);
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
            } catch (IOException ignored) {

            }
        }

        if (ArchivoExiste(archivos, "autos.txt"))
        {
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("autos.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();

                while (linea != null){
                    Autos.add(linea);
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
                llenarViews();
            } catch (IOException ignored) {

            }
        }

        setContentView(R.layout.activity_autos);
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

    public void back (View view){
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
    }

    public void llenarViews(){
        LinearLayout main = findViewById(R.id.main);

        for (String j : Autos)
        {
            String[] words = j.split(" ");
            if (words[1].equals(currentUsuario.get(0))) {
                LinearLayout nv = new LinearLayout(this);
                TextView up = new TextView(this);
                up.setText(words[2]);
                ImageView mddl = new ImageView(this);
                mddl.setImageResource(R.drawable.toyota_prius);
                TextView down = new TextView(this);
                String d = "Ver servicios";
                down.setText(d);
                nv.addView(up);
                nv.addView(mddl);
                nv.addView(down);
                main.addView(nv);
            }
        }
    }

    public void servicios (View view){
        Intent servicios = new Intent(this, Servicios.class);
        startActivity(servicios);
    }
}