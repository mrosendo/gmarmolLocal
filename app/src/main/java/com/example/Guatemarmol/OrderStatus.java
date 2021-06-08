package com.example.Guatemarmol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class OrderStatus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        TextView status = findViewById(R.id.status);
        status.setText(getIntent().getExtras().getString("username"));

    }
}