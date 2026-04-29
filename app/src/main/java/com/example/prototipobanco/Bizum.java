package com.example.prototipobanco;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class Bizum extends BaseActivityClientes {

    private ImageView btnEnviar, btnSolicitar;
    private EditText etDestinatario, etCantidad, etConcepto;
    private MaterialButton btnConfirmar;
    private boolean isEnviarSelected = false;
    private boolean isSolicitarSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bizum);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        configuracionDrawerToolbar("Bizum");
        marchaAtras();
        initViews();
        setupListeners();
    }

    private void initViews() {
        // Obtenemos los ImageViews dentro de sus respectivos contenedores
        // Como no tienen ID propio, los buscamos por su posición o añadimos IDs en el XML si es necesario.
        // He actualizado el XML para que tengan IDs: iv_enviar_dinero y iv_solicitar_dinero
        btnEnviar = findViewById(R.id.iv_enviar_dinero);
        btnSolicitar = findViewById(R.id.iv_solicitar_dinero);
        etDestinatario = findViewById(R.id.et_destinatario_bizum);
        etCantidad = findViewById(R.id.et_cantidad_bizum);
        etConcepto = findViewById(R.id.et_concepto_bizum);
        btnConfirmar = findViewById(R.id.btn_confirmar_bizum);
    }

    private void setupListeners() {
        btnEnviar.setOnClickListener(v -> toggleOption(true));
        btnSolicitar.setOnClickListener(v -> toggleOption(false));

        View.OnFocusChangeListener focusListener = (v, hasFocus) -> {
            if (hasFocus && !isEnviarSelected && !isSolicitarSelected) {
                showError("Selecciona primero una opción: Enviar dinero / Solicitar dinero");
                v.clearFocus();
            }
        };

        etDestinatario.setOnFocusChangeListener(focusListener);
        etCantidad.setOnFocusChangeListener(focusListener);
        etConcepto.setOnFocusChangeListener(focusListener);

        btnConfirmar.setOnClickListener(v -> {
            if (!isEnviarSelected && !isSolicitarSelected) {
                showError("Selecciona primero una opción: Enviar dinero / Solicitar dinero");
                return;
            }

            String destinatario = etDestinatario.getText().toString().trim();
            String cantidad = etCantidad.getText().toString().trim();

            if (destinatario.isEmpty() || cantidad.isEmpty()) {
                showError("Debes rellenar el destinatario y la cantidad");
            } else {
                String mensaje = isEnviarSelected ? "Dinero enviado con éxito" : "Dinero solicitado con éxito";
                showSuccess(mensaje);
            }
        });
    }

    private void toggleOption(boolean enviar) {
        if (enviar) {
            isEnviarSelected = !isEnviarSelected;
            isSolicitarSelected = false;
        } else {
            isSolicitarSelected = !isSolicitarSelected;
            isEnviarSelected = false;
        }
        updateVisualState();
    }

    private void updateVisualState() {
        // Cambiamos el fondo para indicar selección
        btnEnviar.setBackgroundResource(isEnviarSelected ? R.drawable.bg_circle_purple : R.drawable.bg_circle_light_purple);
        btnSolicitar.setBackgroundResource(isSolicitarSelected ? R.drawable.bg_circle_purple : R.drawable.bg_circle_light_purple);
        
        // También podemos cambiar el tinte del icono para mejor contraste
        btnEnviar.setColorFilter(getResources().getColor(isEnviarSelected ? R.color.white : R.color.oscuro));
        btnSolicitar.setColorFilter(getResources().getColor(isSolicitarSelected ? R.color.white : R.color.oscuro));
    }

    private void showError(String message) {
        // Simulando el comportamiento de error de Inicio_Sesion
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        // Podrías inflar un layout personalizado aquí si tienes uno específico para errores
    }

    private void showSuccess(String message) {
        // Simulando el comportamiento de éxito de promociones_banco
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        // Podrías inflar un layout personalizado aquí si tienes uno específico para éxito
    }
}