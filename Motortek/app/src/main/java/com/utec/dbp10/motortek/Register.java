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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    List<String> ficheroCompleto = new ArrayList<>();
    StringBuilder ficheroTxt = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        String[] archivos = fileList();

        if (ArchivoExiste(archivos, "motortek.txt"))
        {
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("motortek.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();

                while (linea != null){
                    ficheroCompleto.add(linea);
                    ficheroTxt.append(linea).append('\n');
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

    public void register (View view){
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

    public void escribirFichero(String p, String e){
        try {
            boolean flag = true;
            for (String j : ficheroCompleto)
            {
                String[] words = j.split(" ");
                if (words[0].equals(e) && words[1].equals(p))
                {
                    flag = false;
                }
            }
            if (flag)
            {
                OutputStreamWriter file = new OutputStreamWriter(openFileOutput("motortek.txt", Activity.MODE_PRIVATE));
                file.write(p + " " + e);
                file.flush();
                file.close();
            }
            else
        } catch (IOException ignored) {
        }
        Toast.makeText(this, "Se ha registrado", Toast.LENGTH_LONG).show();
    }

    public void back2 (View view){
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
    }
}