package com.example.prototipobanco;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.materialswitch.MaterialSwitch;

public class Pantalla_principal extends BaseActivityClientes {

    private TextView tvSaldo, tvIban;
    private MaterialSwitch switchVisibilidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Establecemos el layout específico que incluye el contenido de la imagen
        setContentView(R.layout.activity_pantalla_principal);
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configuramos el Drawer y Toolbar de la clase base (título vacío según la imagen)
        configuracionDrawerToolbar("");

        // Inicializar componentes de la UI
        tvSaldo = findViewById(R.id.tv_saldo);
        tvIban = findViewById(R.id.tv_iban);
        switchVisibilidad = findViewById(R.id.switch_visibilidad);

        // Lógica para alternar la visibilidad de los datos sensibles
        if (switchVisibilidad != null) {
            switchVisibilidad.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    tvSaldo.setText(R.string.fondos);
                    tvIban.setText("ES13 0230 7800 8394 0948 8129");
                } else {
                    tvSaldo.setText("*******€");
                    tvIban.setText("ES13 **** **** **** **** 8129");
                }
            });
        }

        // En la pantalla principal no mostramos el botón de volver de la toolbar inferior
        ocultarBotonVolver();
    }

    private void ocultarBotonVolver() {
        View btnVolver = findViewById(R.id.btn_volver);
        if (btnVolver != null) {
            btnVolver.setVisibility(View.GONE);
        }
    }
}
