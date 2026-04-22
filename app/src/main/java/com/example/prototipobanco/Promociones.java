package com.example.prototipobanco;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Promociones extends AppCompatActivity {
    //otraprueba2
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_promociones);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Atts a utilizar
        ImageView btnIniciarSesion = findViewById(R.id.inicio_sesion);
        ImageView btnContactanos = findViewById(R.id.contactanos);

        //Redireccionar
        btnIniciarSesion.setOnClickListener(v -> {
            Intent intent = new Intent(this, Inicio_Sesion.class);
            startActivity(intent);
        });

        btnContactanos.setOnClickListener(v ->{
            Intent intent = new Intent(this, Contacto_clientes.class);
            startActivity(intent);
        });
    }
}