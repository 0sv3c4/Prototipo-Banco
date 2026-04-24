package com.example.prototipobanco.todosUsu;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prototipobanco.R;
import com.google.android.material.button.MaterialButton;

public class BaseActivityTodos extends AppCompatActivity {

    protected MaterialButton btnChat;
    protected MaterialButton btnAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_base_todos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnChat = findViewById(R.id.btn_chat_bottom);
        btnAtras = findViewById(R.id.btn_volver);

        btnAtras.setOnClickListener(v -> finish());
        btnChat.setOnClickListener(v -> generarChat());
    }

    protected void generarChat() {
        ConstraintLayout chat = findViewById(R.id.chat_soporte);
        View view = LayoutInflater.from(this).inflate(R.layout.chat_soporte, chat);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


        //Corregir cómo se ve el chat

        android.view.Window window = alertDialog.getWindow();
        assert window != null;
        window.setGravity(android.view.Gravity.BOTTOM | android.view.Gravity.END); //aparecer abajo a la derecha
        window.clearFlags(android.view.WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        android.view.WindowManager.LayoutParams params = window.getAttributes();
        //intenté hacer esta parte con píxeles, pero funciona mucho mejor con proporcionee
        params.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.75); //redimensionar ancho
        params.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.5); //redimensionar alto
        params.x = 20; // Margen derecho
        params.y = 20; // Margen inferior

        window.setAttributes(params);

        window.setBackgroundDrawableResource(android.R.color.transparent);

        ImageButton envioMensaje = view.findViewById(R.id.btn_enviar);

        envioMensaje.setOnClickListener(v -> Toast.makeText(this, "Mensaje Enviado", Toast.LENGTH_SHORT).show());

    }
}