package com.example.prototipobanco.todosUsu;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prototipobanco.R;

public class Accesibilidad extends BaseActivityTodos {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_accesibilidad);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnChat = findViewById(R.id.btn_chat_bottom);
        btnAtras = findViewById(R.id.btn_volver);

        btnAtras.setOnClickListener(v -> finish());
        btnChat.setOnClickListener(v -> generarChat());

        // CONFIGURAR CADA ITEM DE ACCESIBILIDAD
        configurarItemsAccesibilidad(R.id.item_alto_contraste, "Alto contraste", "Mejor legibilidad de los textos");
        configurarItemsAccesibilidad(R.id.item_texto_grande, "Texto grande", "Aumenta el tamaño de la fuente");
        configurarItemsAccesibilidad(R.id.item_reducir_movimiento, "Reducir movimiento", "Desactiva animaciones y efectos visuales");
        configurarItemsAccesibilidad(R.id.item_modo_oscuro, "Modo oscuro", "Fondo oscuro para el cuidado de los ojos");

        configurarItemsAccesibilidad(R.id.item_lectura_voz, "Lectura en voz alta", "Narra el contenido de la pantalla");
        configurarItemsAccesibilidad(R.id.item_alertas_sonoras, "Alertas sonoras", "Notificaciones con sonido");

        configurarItemsAccesibilidad(R.id.item_botones_grandes, "Botones grandes", "Área de toque ampliada");
        configurarItemsAccesibilidad(R.id.item_vibracion_tactil, "Vibración táctil", "Activa la vibración al pulsar");

    }

    private void configurarItemsAccesibilidad(int idItem, String titulo, String description) {
        View includedLayout = findViewById(idItem);
        if (includedLayout != null) {
            TextView tvTitle = includedLayout.findViewById(R.id.titulo_opcion);
            TextView tvDescription = includedLayout.findViewById(R.id.descripcion_opcion);

            if (tvTitle != null) tvTitle.setText(titulo);
            if (tvDescription != null) tvDescription.setText(description);
        }
    }
}