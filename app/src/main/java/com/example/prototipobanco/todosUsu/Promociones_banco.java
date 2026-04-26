package com.example.prototipobanco.todosUsu;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prototipobanco.R;

public class Promociones_banco extends BaseActivityTodos {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_promociones_banco);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnChat = findViewById(R.id.btn_chat_bottom);
        btnAtras = findViewById(R.id.btn_volver);

        btnAtras.setOnClickListener(v -> finish());
        btnChat.setOnClickListener(v -> generarChat());
        for (int i=1; i<=4; i++){
            TextView boton = findViewById(getResources().getIdentifier("boton_contratar"+i, "id", getPackageName()));
            boton.setOnClickListener(v -> mensajeExitoContrato());
        }
    }


    private void mensajeExitoContrato(){
        ConstraintLayout exito = findViewById(R.id.alerta_exito);
        View view = LayoutInflater.from(this).inflate(R.layout.mensaje_contratar, exito);
        Button aceptarError = view.findViewById(R.id.btn_aceptar_alerta);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.TRANSPARENT));
        }

        aceptarError.setOnClickListener(v -> alertDialog.dismiss());

    }
}