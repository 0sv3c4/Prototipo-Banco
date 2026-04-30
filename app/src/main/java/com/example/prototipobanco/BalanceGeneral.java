package com.example.prototipobanco;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class BalanceGeneral extends BaseActivityClientes {

    private Spinner spinnerMesDesde, spinnerAnioDesde, spinnerMesHasta, spinnerAnioHasta;
    private ImageView ivGrafico;
    private View btnAnual, btnMensual, btnSemanal;

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
        
        btnAnual = findViewById(R.id.view_click_anual);
        btnMensual = findViewById(R.id.view_click_mensual);
        btnSemanal = findViewById(R.id.view_click_semanal);
    }

    private void setupPeriodSelectors() {
        btnAnual.setOnClickListener(v -> {
            ivGrafico.setImageResource(R.drawable.Gráfico_ano);
            // Aquí se podría añadir lógica para marcar visualmente si hubiera assets separados
        });

        btnMensual.setOnClickListener(v -> {
            ivGrafico.setImageResource(R.drawable.Gráfico_mes);
        });

        btnSemanal.setOnClickListener(v -> {
            ivGrafico.setImageResource(R.drawable.Gráfico_sem);
        });
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