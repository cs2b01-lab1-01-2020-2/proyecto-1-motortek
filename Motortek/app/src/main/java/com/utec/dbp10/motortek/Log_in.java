package com.utec.dbp10.motortek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Log_in extends AppCompatActivity {

    List<String> ficheroCompleto = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        String[] archivos = fileList();

        if (ArchivoExiste(archivos, "motortek.txt"))
        {
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("motortek.txt"));
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

    public void login1 (View view){
        Intent login = new Intent(this, Autos.class);
        EditText email1 = findViewById(R.id.correo1);
        EditText password1 = findViewById(R.id.password);
        String password = password1.getText().toString();
        String email = email1.getText().toString();
        for (String j : ficheroCompleto)
        {
            String[] words = j.split(" ");
            if (words[0].equals(email) && words[1].equals(password))
            {
                startActivity(login);
            }
        }
        Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_LONG).show();
    }

    public void back1 (View view){
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
    }
}