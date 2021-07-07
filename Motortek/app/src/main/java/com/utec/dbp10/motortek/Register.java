package com.utec.dbp10.motortek;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;

public class Register extends AppCompatActivity {

    List<String> ficheroCompleto = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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

    public void escribirFichero(String p, String e){
        try {
            boolean flag = true;
            for (String j : ficheroCompleto)
            {
                String[] words = j.split(" ");
                if (words[0].equals(e))
                {
                    flag = false;
                    Toast.makeText(this, "Ya se ha registrado anteriormente", Toast.LENGTH_LONG).show();
                }
            }
            if (flag)
            {
                OutputStreamWriter file = new OutputStreamWriter(openFileOutput("usuario.txt", Activity.MODE_APPEND));
                file.append("\n").append(e).append(" ").append(p);
                file.flush();
                file.close();
                Toast.makeText(this, "Se ha registrado", Toast.LENGTH_LONG).show();
            }
        } catch (IOException ignored) {
        }
    }

    public void register (View view){
        String[] archivos = fileList();

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
        EditText email1 = findViewById(R.id.correo);
        EditText password1 = findViewById(R.id.password);
        String password = password1.getText().toString();
        String email = email1.getText().toString();
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);
        if (mat.matches() && !password.equals("")) {
            escribirFichero(password, email);
        } else {
            Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_LONG).show();
        }
    }


    public void back (View view){
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
    }
}