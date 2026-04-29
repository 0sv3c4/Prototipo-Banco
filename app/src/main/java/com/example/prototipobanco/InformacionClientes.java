package com.example.prototipobanco;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InformacionClientes extends BaseActivityClientes {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_clientes);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configuración de la Toolbar y Drawer heredados
        configuracionDrawerToolbar(getString(R.string.informacion));
        marchaAtras();

        // Lógica específica de la pantalla
        setupListeners();
    }

    private void setupListeners() {
        ImageView btnBizum = findViewById(R.id.btn_bizum_info);
        if (btnBizum != null) {
            btnBizum.setOnClickListener(v -> {
                Intent intent = new Intent(this, Bizum.class);
                startActivity(intent);
            });
        }

        TextView tvVerMas = findViewById(R.id.tv_ver_mas);
        if (tvVerMas != null) {
            tvVerMas.setOnClickListener(v -> {
                // Navegar a la pantalla de Movimientos
                Intent intent = new Intent(this, Movimientos.class);
                startActivity(intent);
            });
        }
    }
}