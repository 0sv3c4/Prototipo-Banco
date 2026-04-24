package com.example.prototipobanco.todosUsu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prototipobanco.R;
import com.google.android.material.button.MaterialButton;

public class Contacto_clientes extends BaseActivityTodos {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contacto_clientes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        MaterialButton tfnoCliente = findViewById(R.id.btn_tel_atencion);
        MaterialButton tfnoUrgencias = findViewById(R.id.btn_tel_urgencias);
        MaterialButton tfnoSeguros = findViewById(R.id.btn_tel_seguros);
        MaterialButton btnAtras = findViewById(R.id.btn_volver);

        btnAtras.setOnClickListener(v -> finish());
        btnChat.setOnClickListener(v -> generarChat());

        tfnoCliente.setOnClickListener(v ->{
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:+34900000000"));
            startActivity(intent);
        });

        tfnoUrgencias.setOnClickListener(v ->{
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:+34900000001"));
            startActivity(intent);
        });

        tfnoSeguros.setOnClickListener(v ->{
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:+34910000000"));
            startActivity(intent);
        });
    }


}