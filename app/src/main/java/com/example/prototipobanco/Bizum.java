package com.example.prototipobanco;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Bizum extends BaseActivityClientes {

    private ImageView btnEnviar, btnSolicitar, tooltipCantidad;
    private EditText etDestinatario, etCantidad, etConcepto;
    private Button btnConfirmar;
    private LinearLayout btnM, btnC, btnJ, btnO;
    private TextView btnVerTodos;
    private boolean isEnviarSelected = false;
    private boolean isSolicitarSelected = false;

    // Lanzador para pedir permiso de contactos
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    abrirAgendaContactos();
                } else {
                    Toast.makeText(this, R.string.permiso_de_contactos_denegado, Toast.LENGTH_SHORT).show();
                }
            });

    private final ActivityResultLauncher<Intent> pickContactLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bizum);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        configuracionDrawerToolbar("");
        marchaAtras();
        initViews();
        setupListeners();
    }

    private void initViews() {
        btnEnviar = findViewById(R.id.iv_enviar_dinero);
        btnSolicitar = findViewById(R.id.iv_solicitar_dinero);
        tooltipCantidad = findViewById(R.id.tooltip_cantidad);
        etDestinatario = findViewById(R.id.et_destinatario_bizum);
        etCantidad = findViewById(R.id.et_cantidad_bizum);
        etConcepto = findViewById(R.id.et_concepto_bizum);
        btnConfirmar = findViewById(R.id.btn_confirmar_bizum);
        btnVerTodos = findViewById(R.id.btn_ver_todos);

        btnM = findViewById(R.id.btn_contacto_m);
        btnC = findViewById(R.id.btn_contacto_c);
        btnJ = findViewById(R.id.btn_contacto_j);
        btnO = findViewById(R.id.btn_contacto_o);
    }

    private void setupListeners() {
        btnEnviar.setOnClickListener(v -> toggleOption(true));
        btnSolicitar.setOnClickListener(v -> toggleOption(false));

        if (tooltipCantidad != null) {
            tooltipCantidad.setOnClickListener(v -> mostrarDialogoTooltip(R.layout.mensaje_tootip_cantidad));
        }

        if (btnM != null) btnM.setOnClickListener(v -> etDestinatario.setText("Manuel Aylón"));
        if (btnC != null) btnC.setOnClickListener(v -> etDestinatario.setText("Claudia Carracedo"));
        if (btnJ != null) btnJ.setOnClickListener(v -> etDestinatario.setText("Juan Monzón"));
        if (btnO != null) btnO.setOnClickListener(v -> etDestinatario.setText("Óscar Torres"));

        if (btnVerTodos != null) {
            btnVerTodos.setOnClickListener(v -> comprobarPermisoAgenda());
        }

        View.OnFocusChangeListener selectionCheckListener = (v, hasFocus) -> {
            if (hasFocus && !isEnviarSelected && !isSolicitarSelected) {
                v.clearFocus();
                mostrarDialogo(R.layout.mensaje_error_bizum_seleccion);
            }
        };

        etDestinatario.setOnFocusChangeListener(selectionCheckListener);
        etConcepto.setOnFocusChangeListener(selectionCheckListener);

        // Listener para la caja de cantidad con restricciones de rango
        etCantidad.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus && !isEnviarSelected && !isSolicitarSelected) {
                v.clearFocus();
                mostrarDialogo(R.layout.mensaje_error_bizum_seleccion);
            } else if (!hasFocus) {
                // Al perder el foco, validamos el mínimo de 0.5 euros
                String text = etCantidad.getText().toString().trim();
                if (!text.isEmpty()) {
                    try {
                        double val = Double.parseDouble(text.replace(',', '.'));
                        if (val < 0.5) {
                            etCantidad.setText("0.5");
                        }
                    } catch (NumberFormatException ignored) {}
                }
            }
        });

        etCantidad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    try {
                        double val = Double.parseDouble(s.toString().replace(',', '.'));
                        if (val > 500) {
                            etCantidad.setText("500");
                            etCantidad.setSelection(etCantidad.getText().length());
                        }
                    } catch (NumberFormatException ignored) {}
                }
            }
        });

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
                try {
                    double cantidadVal = Double.parseDouble(cant.replace(',', '.'));
                    if (cantidadVal < 0.5 || cantidadVal > 500) {
                        Toast.makeText(this, R.string.la_cantidad_debe_estar_entre_0_5_y_500, Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (NumberFormatException e) {
                    mostrarDialogo(R.layout.mensaje_error_bizum_campos);
                    return;
                }

                if (isEnviarSelected) {
                    mostrarDialogo(R.layout.mensaje_exito_envio);
                } else {
                    mostrarDialogo(R.layout.mensaje_exito_recibir);
                }
            }
        });
    }

    private void mostrarDialogoTooltip(int layoutId) {
        View view = LayoutInflater.from(this).inflate(layoutId, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        
        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }
        alertDialog.show();
    }

    private void comprobarPermisoAgenda() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED) {
            abrirAgendaContactos();
        } else {
            requestPermissionLauncher.launch(Manifest.permission.READ_CONTACTS);
        }
    }

    private void abrirAgendaContactos() {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        pickContactLauncher.launch(intent);
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
        int colorWhite = ContextCompat.getColor(this, R.color.white);
        int colorOscuro = ContextCompat.getColor(this, R.color.oscuro);

        if (isEnviarSelected) {
            btnEnviar.setBackgroundResource(R.drawable.bg_circle_purple);
            btnEnviar.setColorFilter(colorWhite);
        } else {
            btnEnviar.setBackgroundResource(R.drawable.bg_circle_light_purple);
            btnEnviar.setColorFilter(colorOscuro);
        }

        if (isSolicitarSelected) {
            btnSolicitar.setBackgroundResource(R.drawable.bg_circle_purple);
            btnSolicitar.setColorFilter(colorWhite);
        } else {
            btnSolicitar.setBackgroundResource(R.drawable.bg_circle_light_purple);
            btnSolicitar.setColorFilter(colorOscuro);
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

        if (btnAceptar != null) {
            btnAceptar.setOnClickListener(v -> alertDialog.dismiss());
        }
        alertDialog.show();
    }
}