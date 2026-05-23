package com.example.prototipobanco.guias;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prototipobanco.R;

public class AyudaPantallaPrincipal extends BaseAyudas {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda_pantalla_principal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        configurarPantallas(getString(R.string.pantalla_principal),R.raw.video_principal);

        TextView accesoTransf = findViewById(R.id.acceso_transfer);
        TextView accesoMov = findViewById(R.id.acceso_movimientos);

        accesoTransf.setOnClickListener(v-> {
            Intent intent = new Intent(this, AyudaTransferencias.class);
            finish();
            startActivity(intent);
        });

        accesoMov.setOnClickListener(v-> {
            Intent intent = new Intent(this, AyudaMovimientos.class);
            finish();
            startActivity(intent);
        });
    }
}