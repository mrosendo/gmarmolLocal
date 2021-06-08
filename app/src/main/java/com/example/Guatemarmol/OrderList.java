package com.example.Guatemarmol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class OrderList extends AppCompatActivity {
    User name;
    String userNIT;
    List<Order> elements;
    private OrderAdapterList.RecyclerViewClick listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        TextView otext = findViewById(R.id.orderText);
        userNIT = getIntent().getExtras().getString("nit");
        otext.setText(userNIT);
        name = new User(userNIT);

        init();
    }

    public void init() {
        elements = name.getOrders();

        setOnClickListener();

        OrderAdapterList listAdapter = new OrderAdapterList(elements, this, listener);
        RecyclerView recyclerView = findViewById(R.id.listOrder);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }

    private void setOnClickListener() {
        listener = new OrderAdapterList.RecyclerViewClick() {
            @Override
            public void onCLick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), OrderStatus.class);
                intent.putExtra("username", elements.get(position).getDocDate());
                startActivity(intent);
            }
        };
    }
}