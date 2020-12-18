package com.utec.dbp10.motortek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Log_in extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }

    public void login1 (View view){
        Intent login = new Intent(this, Autos.class);
        EditText email1 = findViewById(R.id.correo1);
        EditText password1 = findViewById(R.id.password);
        String password = password1.getText().toString();
        String email = email1.getText().toString();
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);
        if(mat.matches() && !password.equals("")){
            startActivity(login);
        }else{
            Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_LONG).show();
        }
    }

    public void back1 (View view){
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
    }
}