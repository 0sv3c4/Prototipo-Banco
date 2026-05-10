package com.example.prototipobanco;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class Preferencias2 extends BaseActivityClientes {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias2);

        // Configuración de la base
        configuracionDrawerToolbar(getString(R.string.preferencias));
        marchaAtras();

        // Botón Aplicar (Foto de perfil)
        findViewById(R.id.btn_aplicar_foto).setOnClickListener(v -> {
            mostrarAlertaPersonalizada("¡Éxito!", "La foto de perfil se ha actualizado correctamente.", R.drawable.ic_exito);
        });

        // Botón Importar Imagen
        findViewById(R.id.btn_importar).setOnClickListener(v -> {
            Toast.makeText(this, "Abriendo galería...", Toast.LENGTH_SHORT).show();
        });

        // Navegación Siguiente -> Ahora va a la pantalla de Seguridad
        MaterialButton btnSiguiente = findViewById(R.id.btn_siguiente);
        btnSiguiente.setOnClickListener(v -> {
             Intent intent = new Intent(this, Seguridad.class);
             startActivity(intent);
        });
    }

    private void mostrarAlertaPersonalizada(String titulo, String mensaje, int iconoResId) {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.mensaje_contratar, null);
        
        TextView tvTitulo = dialogView.findViewById(R.id.titulo_info);
        TextView tvMensaje = dialogView.findViewById(R.id.mensaje_info);
        ImageView ivIcono = dialogView.findViewById(R.id.icono_info);
        Button btnAceptar = dialogView.findViewById(R.id.btn_aceptar_alerta);

        tvTitulo.setText(titulo);
        tvMensaje.setText(mensaje);
        ivIcono.setImageResource(iconoResId);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }

        btnAceptar.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }
}
