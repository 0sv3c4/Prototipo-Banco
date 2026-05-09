package com.example.prototipobanco;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prototipobanco.todosUsu.Contacto_clientes;

public class ContratacionServicios extends BaseActivityClientes {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contratacion_servicios);
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configuramos la Toolbar (Título se maneja en el XML con el diseño curvo)
        configuracionDrawerToolbar("");
        
        // Configuramos el botón de volver de la base
        marchaAtras();

        // Botón Contacto - Redirige a Contacto_clientes
        View btnContacto = findViewById(R.id.btn_contacto);
        if (btnContacto != null) {
            btnContacto.setOnClickListener(v -> {
                Intent intent = new Intent(this, Contacto_clientes.class);
                startActivity(intent);
            });
        }
    }
}
