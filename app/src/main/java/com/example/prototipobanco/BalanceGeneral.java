package com.example.prototipobanco;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BalanceGeneral extends BaseActivityClientes {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_general);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configuración de la Toolbar y Drawer heredados
        configuracionDrawerToolbar("Balance");
        marchaAtras();

        setupSpinners();
    }

    private void setupSpinners() {
        // En una implementación real, cargaríamos datos de strings.xml
        String[] meses = {"Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"};
        String[] años = {"2023", "2024", "2025", "2026"};

        ArrayAdapter<String> adapterMeses = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, meses);
        adapterMeses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapterAños = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, años);
        adapterAños.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Como no puse IDs específicos a los Spinners en el XML inicial, 
        // aquí los buscaríamos si los hubiéramos definido. 
        // Por ahora dejamos la lógica preparada.
    }
}