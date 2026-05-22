package com.example.prototipobanco;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prototipobanco.guias.BaseAyudas;

public class Ayuda extends BaseActivityClientes {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        configuracionDrawerToolbar(getString(R.string.ayuda));
        marchaAtras();

        Button btnPantallaPrincipal = findViewById(R.id.btn_ayuda_principal);

        btnPantallaPrincipal.setOnClickListener(v -> {
            Intent intent = new Intent(this, BaseAyudas.class);//TODO
            startActivity(intent);
        });
    }
}