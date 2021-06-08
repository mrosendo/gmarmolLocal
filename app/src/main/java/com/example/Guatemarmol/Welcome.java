package com.example.Guatemarmol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {
    String nit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        this.nit = getIntent().getExtras().getString("nit");
        TextView login = findViewById(R.id.loginText);
        login.setText(getIntent().getExtras().getString("login"));

        Button consul = findViewById(R.id.conButton);

        consul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenOrder();
            }
        });
    }

    //CAMBIAR A PANTALLA DE ORDENES
    public void screenOrder() {
        Intent ac = new Intent(this, OrderList.class);
        ac.putExtra("nit", this.nit);
        startActivity(ac);
    }
}