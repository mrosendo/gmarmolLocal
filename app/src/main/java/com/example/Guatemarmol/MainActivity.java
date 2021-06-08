package com.example.Guatemarmol;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    User name;
    String cNIT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);

        Button login = findViewById(R.id.loginButton);
        EditText nit = findViewById(R.id.nitTXT);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cNIT = nit.getText().toString();
                if (!cNIT.equals("")) {
                    if (callUser(cNIT)) {
                        name.getOrders();
                        screen2();
                    } else {
                        aletM("Error de verificación", "EL NIT no existe");
                        nit.setText("");
                    }

                } else {
                    aletM("Error de verificación", "El campo está vacío");
                }
            }
        });
    }

    //MENSAJE DE ALERTA
    public void aletM(String title, String ms) {
        AlertDialog dlg = new AlertDialog.Builder(MainActivity.this)
                .setTitle(title)
                .setMessage(ms)
                .setPositiveButton("Regresar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        dlg.show();
    }

    //CAMBIO A PANTALLA DE BIENVENIDA
    public void screen2() {
        Intent ac = new Intent(this, Welcome.class);
        ac.putExtra("login", name.getUserName());
        ac.putExtra("nit", this.cNIT);
        startActivity(ac);
    }

    //BUSCAR USUARIO POR NIT INGRESADO
    public boolean callUser(String nit) {
        boolean exist = false;
        try {
            name = new User(nit);
            exist = name.searchUser();
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return exist;
    }
}