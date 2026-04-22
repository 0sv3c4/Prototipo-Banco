package com.example.prototipobanco;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class Contacto_clientes extends AppCompatActivity {

    MaterialButton tfnoCliente, tfnoUrgencias, tfnoSeguros;
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
        tfnoCliente = findViewById(R.id.btn_tel_atencion);
        tfnoUrgencias = findViewById(R.id.btn_tel_urgencias);
        tfnoSeguros = findViewById(R.id.btn_tel_seguros);

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