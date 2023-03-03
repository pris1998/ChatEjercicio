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

    Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textoNameUser = findViewById(R.id.textoNameUser);
        btnEntrar = findViewById(R.id.btnEntrar);

        //Boton que implementa un OnClickListener que al
        // pulsar te manda al MainActivity una vez escrito el
        //nombre del usuario
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = textoNameUser.getText().toString().trim();

                if (!(name.isEmpty())) {
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    intent.putExtra("Nombre usuario",name);
                    startActivity(intent);
                }else{
                    Toast.makeText(Login.this,"Por favor, rellene los campos",Toast.LENGTH_LONG);
                }
            }
        });

    }


}