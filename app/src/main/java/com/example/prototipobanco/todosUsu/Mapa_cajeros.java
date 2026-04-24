package com.example.prototipobanco.todosUsu;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import androidx.activity.EdgeToEdge;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prototipobanco.R;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.materialswitch.MaterialSwitch;

public class Mapa_cajeros extends BaseActivityTodos {

    private MaterialSwitch switchUbicacion;

    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                // Actualizamos el switch según la respuesta del usuario
                switchUbicacion.setChecked(isGranted);
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mapa_cajeros);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        MaterialButton btnAtras = findViewById(R.id.btn_volver);
        btnAtras.setOnClickListener(v -> finish());

        btnChat.setOnClickListener(v -> generarChat());

        WebView webViewMapa = findViewById(R.id.visor_mapa);

        // Configurar WebView
        WebSettings configuracionWeb = webViewMapa.getSettings();
        configuracionWeb.setJavaScriptEnabled(true); // Habilitar JavaScript si es necesario

        webViewMapa.setWebViewClient(new WebViewClient());
        String iframeMapa = "<iframe src=\"https://www.google.com/maps/d/u/0/embed?mid=1QnPoMEKZq5XpyYZ8jozW_gjkwoBNTI4&ehbc=2E312F\" " +
                "width=\"100%\" height=\"100%\" frameborder=\"0\" style=\"border:0;\"></iframe>";
        webViewMapa.loadData(iframeMapa, "text/html", "utf-8");

        //Switch ubicacion

        switchUbicacion = findViewById(R.id.switch_widget);
        checkPermissionAndSetSwitch();
        switchUbicacion.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Si intenta activar, pedimos el permiso
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
            }
        });
    }

    private void checkPermissionAndSetSwitch() {
        boolean isGranted = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        switchUbicacion.setChecked(isGranted);
    }


    @Override
    protected void onResume() {
        super.onResume();
        // Volvemos a comprobar por si el usuario cambió el permiso en ajustes
        checkPermissionAndSetSwitch();
    }

}