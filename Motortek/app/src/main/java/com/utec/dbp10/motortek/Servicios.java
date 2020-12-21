package com.utec.dbp10.motortek;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
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

public class Servicios extends AppCompatActivity {

    List<String> Servicios = new ArrayList<>();
    List<String> currentAuto = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios);

        String[] archivos = fileList();

        if (ArchivoExiste(archivos, "current_auto.txt"))
        {
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("current_auto.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();

                while (linea != null){
                    currentAuto.add(linea);
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
            } catch (IOException ignored) {

            }
        }

        if (ArchivoExiste(archivos, "servicios_core.txt"))
        {
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("servicios_core.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                linea = br.readLine();
                while (linea != null){
                    Servicios.add(linea);
                    linea = br.readLine();
                }

                br.close();
                archivo.close();
                llenarViews();
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

    @SuppressLint("SetTextI18n")
    public void llenarViews(){

        for (String j : Servicios)
        {
            String[] words = j.split(";");

            if (words[0].equals(currentAuto.get(0))) {
                LinearLayout nv = new LinearLayout(this);

                LinearLayout main = findViewById(R.id.main);
                nv.setOrientation(LinearLayout.VERTICAL);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                layoutParams.setMargins(80, 0, 80, 80);

                TextView text = new TextView(this);
                text.setTextColor(Color.parseColor("#FFFFFFFF"));
                text.setTextSize(24);
                text.setPadding(45,45,45,45);
                text.setBackgroundColor(Color.parseColor("#000000"));
                String mess = "Servicio";
                text.setText(mess);

                TextView text1 = new TextView(this);
                LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                text1.setTextColor(Color.parseColor("#000000"));
                text1.setTextSize(24);
                text1.setPadding(45,45,45,45);
                text1.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                text1.setText(words[1]);

                TextView text2 = new TextView(this);
                text2.setTextColor(Color.parseColor("#000000"));
                text2.setTextSize(24);
                text2.setPadding(45,45,45,45);
                text2.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                text2.setText(words[2]);

                TextView text3 = new TextView(this);
                text3.setTextColor(Color.parseColor("#000000"));
                text3.setTextSize(24);
                text3.setPadding(45,45,45,45);
                text3.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                text3.setText("Completado en " + words[3]);

                TextView text4 = new TextView(this);
                text4.setTextColor(Color.parseColor("#000000"));
                text4.setTextSize(24);
                text4.setPadding(45,45,45,45);
                text4.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                text4.setText("Costo: " + words[4] + " S/.");

                TextView text5 = new TextView(this);
                text5.setTextColor(Color.parseColor("#000000"));
                text5.setTextSize(24);
                text5.setPadding(45,45,45,45);
                text5.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                text5.setText("Metodo de pago: " + words[5]);

                TextView text6 = new TextView(this);
                text6.setTextColor(Color.parseColor("#000000"));
                text6.setTextSize(24);
                text6.setPadding(45,45,45,45);
                text6.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                text6.setText("Descripcion: " + words[6]);

                TextView text7 = new TextView(this);
                text7.setTextColor(Color.parseColor("#000000"));
                text7.setTextSize(24);
                text7.setPadding(45,45,45,45);
                text7.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                text7.setText("Atendido por: " + words[7]);



                View mddl = new View(this);
                mddl.setBackgroundColor(Color.parseColor("#000000"));
                LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp3.height = 4;

                View mddl2 = new View(this);
                mddl2.setBackgroundColor(Color.parseColor("#000000"));

                View mddl3 = new View(this);
                mddl3.setBackgroundColor(Color.parseColor("#000000"));

                View mddl4 = new View(this);
                mddl4.setBackgroundColor(Color.parseColor("#000000"));

                View mddl5 = new View(this);
                mddl5.setBackgroundColor(Color.parseColor("#000000"));

                View mddl6 = new View(this);
                mddl6.setBackgroundColor(Color.parseColor("#000000"));

                View mddl7 = new View(this);
                mddl7.setBackgroundColor(Color.parseColor("#000000"));



                nv.addView(text, lp2);
                nv.addView(text1, lp2);
                nv.addView(mddl, lp3);
                nv.addView(text2, lp2);
                nv.addView(mddl2, lp3);
                nv.addView(text3, lp2);
                nv.addView(mddl3, lp3);
                nv.addView(text4, lp2);
                nv.addView(mddl4, lp3);
                nv.addView(text5, lp2);
                nv.addView(mddl5, lp3);
                nv.addView(text6, lp2);
                nv.addView(mddl6, lp3);
                nv.addView(text7, lp2);
                nv.addView(mddl7, lp3);
                main.addView(nv, layoutParams);
            }

        }
    }

    public void back (View view){
        Intent back = new Intent(this, Autos.class);
        startActivity(back);
    }

    public void detalle (View view){
        Intent detalle = new Intent(this, Detalles.class);
        startActivity(detalle);
    }
}