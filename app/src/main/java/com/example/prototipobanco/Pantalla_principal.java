package com.example.prototipobanco;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.navigation.NavigationView;

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

        NavigationView navView = findViewById(R.id.nav_view);
        View headerView = navView.getHeaderView(0);
        TextView tvSaldoNav = headerView.findViewById(R.id.tv_saldo_nav);

        // Lógica para alternar la visibilidad de los datos sensibles
        if (switchVisibilidad != null) {
            switchVisibilidad.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    tvSaldo.setText(R.string.fondos);
                    tvIban.setText(R.string.IBAN);
                    if (tvSaldoNav != null) tvSaldoNav.setText(R.string.valor_saldo);
                } else {
                    tvSaldo.setText("*******€");
                    tvIban.setText("ES13 **** **** **** **** 8129");
                    if (tvSaldoNav != null) tvSaldoNav.setText("*******€");
                }
            });
        }

    }


}
