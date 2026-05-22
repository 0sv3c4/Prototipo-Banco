package com.example.prototipobanco;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prototipobanco.guias.AyudaBizum;
import com.example.prototipobanco.guias.AyudaMovimientos;
import com.example.prototipobanco.guias.AyudaPantallaPrincipal;
import com.example.prototipobanco.guias.AyudaTransferencias;
import com.example.prototipobanco.todosUsu.Contacto_clientes;

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
        Button btnTransferencias = findViewById(R.id.btn_transferencias);
        Button btnMovimientos = findViewById(R.id.btn_movimientos);
        Button btnBizum = findViewById(R.id.btn_bizum);
        Button btnContacto = findViewById(R.id.btn_contacto);
        Button btnFAQ = findViewById(R.id.btn_faq);

        btnPantallaPrincipal.setOnClickListener(v -> {
            Intent intent = new Intent(this, AyudaPantallaPrincipal.class);
            startActivity(intent);
        });
        btnTransferencias.setOnClickListener(v -> {
            Intent intent = new Intent(this, AyudaTransferencias.class);
            startActivity(intent);
        });
        btnMovimientos.setOnClickListener(v -> {
            Intent intent = new Intent(this, AyudaMovimientos.class);
            startActivity(intent);
        });
        btnBizum.setOnClickListener(v -> {
            Intent intent = new Intent(this, AyudaBizum.class);
            startActivity(intent);
        });

        btnFAQ.setOnClickListener(v -> {
            Intent intent = new Intent(this, FAQ.class);
            startActivity(intent);
        });


        btnContacto.setOnClickListener(v->{
            Intent intent = new Intent(this, Contacto_clientes.class);
            startActivity(intent);
        });
    }
}