package com.utec.dbp10.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FamiliaClickListenr clickListenr = new FamiliaClickListenr();
        TextView familia = findViewById(R.id.familia);
        familia.setOnClickListener(clickListenr);
    }

    public void abrirmenu(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}