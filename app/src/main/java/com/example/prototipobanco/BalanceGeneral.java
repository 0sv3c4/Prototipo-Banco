package com.example.prototipobanco;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class BalanceGeneral extends BaseActivityClientes {

    private Spinner spinnerMesDesde, spinnerAnioDesde, spinnerMesHasta, spinnerAnioHasta;
    private ImageView ivGrafico;
    private TextView btnAnual, btnMensual, btnSemanal;

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

        initViews();
        setupSpinners();
        setupPeriodSelectors();
    }

    private void initViews() {
        spinnerMesDesde = findViewById(R.id.spinner_mes_desde);
        spinnerAnioDesde = findViewById(R.id.spinner_anio_desde);
        spinnerMesHasta = findViewById(R.id.spinner_mes_hasta);
        spinnerAnioHasta = findViewById(R.id.spinner_anio_hasta);
        ivGrafico = findViewById(R.id.iv_grafico_balance);
        
        btnAnual = findViewById(R.id.btn_anual);
        btnMensual = findViewById(R.id.btn_mensual);
        btnSemanal = findViewById(R.id.btn_semanal);
    }

    private void setupPeriodSelectors() {
        btnAnual.setOnClickListener(v -> {
            ivGrafico.setImageResource(R.drawable.grafico_ano);
            marcarBoton(btnAnual);
        });

        btnMensual.setOnClickListener(v -> {
            ivGrafico.setImageResource(R.drawable.grafico_mes);
            marcarBoton(btnMensual);
        });

        btnSemanal.setOnClickListener(v -> {
            ivGrafico.setImageResource(R.drawable.grafico_sem);
            marcarBoton(btnSemanal);
        });

        // Por defecto marcamos mensual para coincidir con la imagen inicial
        marcarBoton(btnMensual);
    }

    private void marcarBoton(TextView seleccionado) {
        // Resetear todos al estado por defecto (blanco/transparente)
        btnAnual.setBackgroundTintList(null);
        btnMensual.setBackgroundTintList(null);
        btnSemanal.setBackgroundTintList(null);

        // Aplicar el color de marcado al seleccionado (usando mas_claro de tu proyecto)
        seleccionado.setBackgroundTintList(android.content.res.ColorStateList.valueOf(getResources().getColor(R.color.mas_claro)));
    }

    private void setupSpinners() {
        // Datos de los meses
        String[] meses = {"Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"};
        ArrayAdapter<String> adapterMeses = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, meses);
        adapterMeses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerMesDesde.setAdapter(adapterMeses);
        spinnerMesHasta.setAdapter(adapterMeses);

        // Años para "Desde" (2026 a 2006 descendente)
        List<String> aniosDesde = new ArrayList<>();
        for (int i = 2026; i >= 2006; i--) {
            aniosDesde.add(String.valueOf(i));
        }
        ArrayAdapter<String> adapterAniosDesde = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, aniosDesde);
        adapterAniosDesde.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAnioDesde.setAdapter(adapterAniosDesde);

        // Años para "Hasta" (2026 a 2036)
        List<String> aniosHasta = new ArrayList<>();
        for (int i = 2026; i <= 2036; i++) {
            aniosHasta.add(String.valueOf(i));
        }
        ArrayAdapter<String> adapterAniosHasta = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, aniosHasta);
        adapterAniosHasta.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAnioHasta.setAdapter(adapterAniosHasta);
    }
}