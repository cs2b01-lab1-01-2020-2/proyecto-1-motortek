package com.utec.dbp10.motortek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Servicios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios);
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