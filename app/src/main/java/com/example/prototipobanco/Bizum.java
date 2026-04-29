package com.example.prototipobanco;

import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Bizum extends BaseActivityClientes {

    private ImageView btnEnviar, btnSolicitar;
    private EditText etDestinatario, etCantidad, etConcepto;
    private Button btnConfirmar;
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

        View.OnFocusChangeListener selectionCheckListener = (v, hasFocus) -> {
            if (hasFocus && !isEnviarSelected && !isSolicitarSelected) {
                v.clearFocus(); // Evita que se abra el teclado
                mostrarDialogo(R.layout.mensaje_error_bizum_seleccion);
            }
        };

        etDestinatario.setOnFocusChangeListener(selectionCheckListener);
        etCantidad.setOnFocusChangeListener(selectionCheckListener);
        etConcepto.setOnFocusChangeListener(selectionCheckListener);

        btnConfirmar.setOnClickListener(v -> {
            if (!isEnviarSelected && !isSolicitarSelected) {
                mostrarDialogo(R.layout.mensaje_error_bizum_seleccion);
                return;
            }

            String dest = etDestinatario.getText().toString().trim();
            String cant = etCantidad.getText().toString().trim();

            if (dest.isEmpty() || cant.isEmpty()) {
                mostrarDialogo(R.layout.mensaje_error_bizum_campos);
            } else {
                if (isEnviarSelected) {
                    mostrarDialogo(R.layout.mensaje_exito_envio);
                } else {
                    mostrarDialogo(R.layout.mensaje_exito_recibir);
                }
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
        // Seleccionado -> color oscuro con icono blanco, No seleccionado -> color claro con icono oscuro
        if (isEnviarSelected) {
            btnEnviar.setBackgroundResource(R.drawable.bg_circle_purple);
            btnEnviar.setColorFilter(getResources().getColor(R.color.white));
        } else {
            btnEnviar.setBackgroundResource(R.drawable.bg_circle_light_purple);
            btnEnviar.setColorFilter(getResources().getColor(R.color.oscuro));
        }

        if (isSolicitarSelected) {
            btnSolicitar.setBackgroundResource(R.drawable.bg_circle_purple);
            btnSolicitar.setColorFilter(getResources().getColor(R.color.white));
        } else {
            btnSolicitar.setBackgroundResource(R.drawable.bg_circle_light_purple);
            btnSolicitar.setColorFilter(getResources().getColor(R.color.oscuro));
        }
    }

    private void mostrarDialogo(int layoutId) {
        View view = LayoutInflater.from(this).inflate(layoutId, null);
        Button btnAceptar = view.findViewById(R.id.btn_aceptar_alerta);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        
        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }

        btnAceptar.setOnClickListener(v -> alertDialog.dismiss());
        alertDialog.show();
    }
}