package com.example.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    TextView textoNameUser;
    TextView textoIPUser;
    Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textoNameUser = findViewById(R.id.textoNameUser);
        textoIPUser = findViewById(R.id.textoIPUser);
        btnEntrar = findViewById(R.id.btnEntrar);


        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = textoNameUser.getText().toString().trim();
                String ip = textoIPUser.getText().toString().trim();

                if (!(ip.isEmpty() || name.isEmpty())) {
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    intent.putExtra("Nombre usuario",name);
                    intent.putExtra("IP destino", ip);
                    startActivity(intent);
                }else{
                    Toast.makeText(Login.this,"Por favor, rellene los campos",Toast.LENGTH_LONG);
                }
            }
        });

    }


}