package com.fsw_android.asistenciadocente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btn_iniciar_sesion = (Button) findViewById(R.id.btnIniciarSesion_Login);
        btn_iniciar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento_validacion = new Intent(Login.this, ValidacionLogin.class);
                startActivity(intento_validacion);
            }
        });

    }
}
