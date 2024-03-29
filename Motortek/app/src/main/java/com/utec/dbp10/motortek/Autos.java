package com.utec.dbp10.motortek;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Autos extends AppCompatActivity {

    List<String> Autos = new ArrayList<>();
    List<String> currentUsuario = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[] archivos = fileList();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autos);

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

        if (ArchivoExiste(archivos, "autos_final.txt"))
        {
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("autos_final.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                linea = br.readLine();
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


        Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();

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

    public void llenarViews(){

        for (String j : Autos)
        {
            String[] words = j.split("/");

            if (words[1].equals(currentUsuario.get(0))) {
                LinearLayout nv = new LinearLayout(this);

                LinearLayout main = findViewById(R.id.main);
                nv.setOrientation(LinearLayout.VERTICAL);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                layoutParams.setMargins(80, 0, 80, 80);

                TextView up = new TextView(this);
                LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp2.height = 200;
                up.setTextColor(Color.parseColor("#FFFFFFFF"));
                up.setTextSize(24);
                up.setPadding(45,45,20,20);
                up.setBackgroundColor(Color.parseColor("#000000"));
                up.setHeight(70);
                up.setText(words[2]);

                ImageView mddl = new ImageView(this);
                mddl.setImageResource(R.drawable.toyota_prius);
                LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp3.height = 500;
                mddl.setScaleType(ImageView.ScaleType.CENTER_CROP);

                TextView down = new TextView(this);
                String d = "Ver servicios";
                down.setText(d);
                LinearLayout.LayoutParams lp4 = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp4.height = 200;

                down.setTextColor(Color.parseColor("#FFFFFFFF"));
                down.setTextSize(24);
                down.setPadding(45,45,20,20);
                down.setBackgroundColor(Color.parseColor("#000000"));
                down.setId(Integer.parseInt(words[0]));
                down.setOnClickListener(this::servicios);

                nv.addView(up, lp2);
                nv.addView(mddl, lp3);
                nv.addView(down, lp4);
                main.addView(nv, layoutParams);
            }

        }
    }

    public void back (View view){
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
    }

    public void escribirFichero(String p){
        try {
            OutputStreamWriter file = new OutputStreamWriter(openFileOutput("current_auto.txt", Activity.MODE_PRIVATE));
            file.write(p);
            file.flush();
            file.close();
        } catch (IOException ignored) {
        }
    }

    public void servicios (View view){
        Intent servicios = new Intent(this, Servicios.class);
        String id = view.getId() + "";
        escribirFichero(id);
        startActivity(servicios);
    }
}