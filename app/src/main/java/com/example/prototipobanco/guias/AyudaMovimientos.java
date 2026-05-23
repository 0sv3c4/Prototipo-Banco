package com.example.prototipobanco.guias;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prototipobanco.FAQ;
import com.example.prototipobanco.R;

public class AyudaMovimientos extends BaseAyudas {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda_movimientos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        configurarPantallas(getString(R.string.informacion),R.raw.video_informacion);

        TextView accesoPrincipal = findViewById(R.id.acceso_principal);
        TextView accesoFAQ = findViewById(R.id.acceso_faq);

        accesoPrincipal.setOnClickListener(v-> {
            Intent intent = new Intent(this, AyudaPantallaPrincipal.class);
            startActivity(intent);
        });

        accesoFAQ.setOnClickListener(v-> {
            Intent intent = new Intent(this, FAQ.class);
            finish();
            startActivity(intent);
        });
    }
}