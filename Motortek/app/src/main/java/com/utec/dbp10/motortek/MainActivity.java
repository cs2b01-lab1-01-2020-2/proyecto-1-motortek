package com.utec.dbp10.motortek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login (View view){
        Intent login = new Intent(this, Log_in.class);
        startActivity(login);
    }
    public void register (View view){
        Intent register = new Intent(this, Register.class);
        startActivity(register);
    }
}