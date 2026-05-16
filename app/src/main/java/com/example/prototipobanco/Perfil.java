package com.example.prototipobanco;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prototipobanco.todosUsu.Inicio_Sesion;

public class Perfil extends BaseActivityClientes {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        configuracionDrawerToolbar(getString(R.string.perfil));
        marchaAtras();


        findViewById(R.id.btn_configurar_perfil).setOnClickListener(v -> {
            Intent intent = new Intent(this, Preferencias1.class);
            startActivity(intent);
        });


        findViewById(R.id.btn_cerrar_sesion_perfil).setOnClickListener(v -> {
            Intent intent = new Intent(this, Inicio_Sesion.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}
