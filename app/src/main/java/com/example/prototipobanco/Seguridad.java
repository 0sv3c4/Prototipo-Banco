package com.example.prototipobanco;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.prototipobanco.todosUsu.Inicio_Sesion;
import com.google.android.material.materialswitch.MaterialSwitch;

public class Seguridad extends BaseActivityClientes {

    private TextView tvBloqueoEstado;
    private final String[] opcionesBloqueo = {"30 segundos", "1 minuto", "5 minutos", "Nunca"};
    
    // Switches para poder resetearlos
    private MaterialSwitch swFaceId, swHuella, swMovimientos, swSospechoso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguridad);

        // Configuración de la base (Drawer, Toolbar y botón Volver)
        configuracionDrawerToolbar("Seguridad");
        marchaAtras();

        // Inicializar vistas
        tvBloqueoEstado = findViewById(R.id.tv_bloqueo_estado);
        swFaceId = findViewById(R.id.sw_faceid_seg);
        swHuella = findViewById(R.id.sw_huella_seg);
        swMovimientos = findViewById(R.id.sw_movimientos_seg);
        swSospechoso = findViewById(R.id.sw_sospechoso_seg);

        // Configurar funcionalidades de las opciones
        setupFuncionalidades();

        // Botón Restablecer
        findViewById(R.id.btn_restablecer).setOnClickListener(v -> {
            restablecerAjustes();
        });
    }

    private void setupFuncionalidades() {
        // 1. Contraseña de acceso - Pop-up personalizado
        findViewById(R.id.opcion_contra).setOnClickListener(v -> mostrarDialogoCambiarContra());

        // 2. Bloqueo automático - Desplegable (AlertDialog)
        findViewById(R.id.opcion_bloqueo).setOnClickListener(v -> mostrarSeleccionBloqueo());

        // 3. Cerrar sesión - Volver a Inicio de Sesión
        findViewById(R.id.opcion_cerrar_sesion).setOnClickListener(v -> {
            Intent intent = new Intent(this, Inicio_Sesion.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }

    private void restablecerAjustes() {
        // Ponemos los switches en su posición inicial según el diseño
        swFaceId.setChecked(true);
        swHuella.setChecked(false);
        swMovimientos.setChecked(true);
        swSospechoso.setChecked(true);
        
        // Resetear texto de bloqueo
        tvBloqueoEstado.setText("Bloqueo de la aplicación tras 1 minuto de inactividad");

        mostrarAlertaPersonalizada("Restablecido", "Se han recuperado los ajustes predeterminados del sistema.", R.drawable.ic_info);
    }

    private void mostrarDialogoCambiarContra() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_cambiar_contra, null);
        
        EditText etAnterior = dialogView.findViewById(R.id.et_contra_anterior);
        EditText etNueva = dialogView.findViewById(R.id.et_contra_nueva);
        Button btnConfirmar = dialogView.findViewById(R.id.btn_confirmar_contra);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }

        btnConfirmar.setOnClickListener(v -> {
            String anterior = etAnterior.getText().toString();
            String nueva = etNueva.getText().toString();

            if (anterior.isEmpty() || nueva.isEmpty()) {
                Toast.makeText(this, "Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show();
            } else if (anterior.equals(nueva)) {
                mostrarAlertaPersonalizada("Error", "La nueva contraseña no puede ser igual a la anterior.", R.drawable.ic_error);
            } else {
                dialog.dismiss();
                mostrarAlertaPersonalizada("¡Éxito!", "Tu contraseña ha sido actualizada correctamente.", R.drawable.ic_exito);
            }
        });

        dialog.show();
    }

    private void mostrarSeleccionBloqueo() {
        new AlertDialog.Builder(this)
                .setTitle("Tiempo de bloqueo automático")
                .setItems(opcionesBloqueo, (dialog, which) -> {
                    String seleccion = opcionesBloqueo[which];
                    tvBloqueoEstado.setText("Bloqueo de la aplicación: " + seleccion);
                    Toast.makeText(this, "Tiempo ajustado a " + seleccion, Toast.LENGTH_SHORT).show();
                })
                .show();
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

        if (iconoResId == R.drawable.ic_error) {
            tvTitulo.setTextColor(ContextCompat.getColor(this, R.color.error));
            btnAceptar.setBackgroundTintList(android.content.res.ColorStateList.valueOf(ContextCompat.getColor(this, R.color.error)));
        } else {
            tvTitulo.setTextColor(ContextCompat.getColor(this, R.color.oscuro));
            btnAceptar.setBackgroundTintList(android.content.res.ColorStateList.valueOf(ContextCompat.getColor(this, R.color.oscuro)));
        }

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
