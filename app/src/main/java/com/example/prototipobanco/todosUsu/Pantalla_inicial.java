package com.example.prototipobanco.todosUsu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prototipobanco.R;

public class Pantalla_inicial extends AppCompatActivity {
    //otraprueba2
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pantalla_inicial);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configuración para cerrar la app al pulsar atrás
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finishAffinity();
            }
        });

        //Atts a utilizar
        ImageView btnIniciarSesion = findViewById(R.id.inicio_sesion);
        ImageView btnContactanos = findViewById(R.id.contactanos);

        LinearLayout btnOfertas = findViewById(R.id.clicakble_ofertas);
        LinearLayout btnEventos = findViewById(R.id.clickable_eventos);

        //Redireccionar
        btnIniciarSesion.setOnClickListener(v -> {
            Intent intent = new Intent(this, Inicio_Sesion.class);
            startActivity(intent);
        });

        btnContactanos.setOnClickListener(v ->{
            Intent intent = new Intent(this, Contacto_clientes.class);
            startActivity(intent);
        });

        btnOfertas.setOnClickListener(v ->{
            Intent intent = new Intent(this, Promociones_banco.class);
            startActivity(intent);
        });

        btnEventos.setOnClickListener(v ->{
            Intent intent = new Intent(this, Promociones_banco.class);
            startActivity(intent);
        });

    }
}