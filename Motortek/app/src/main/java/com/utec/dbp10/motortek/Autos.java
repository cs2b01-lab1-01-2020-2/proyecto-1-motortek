package com.utec.dbp10.motortek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Autos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autos);

        Toast.makeText(this, "Bienvenido", Toast.LENGTH_LONG).show();

    }

    public void back (View view){
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
    }

    public void servicios (View view){



        Intent servicios = new Intent(this, Servicios.class);
        startActivity(servicios);
    }
}