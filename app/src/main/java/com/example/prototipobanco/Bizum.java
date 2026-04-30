package com.example.prototipobanco;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
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

    private ImageView btnEnviar, btnSolicitar;
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
                    Toast.makeText(this, "Permiso de contactos denegado", Toast.LENGTH_SHORT).show();
                }
            });

    // Lanzador para abrir la agenda y recibir el contacto seleccionado (opcional, por ahora solo abrimos)
    private final ActivityResultLauncher<Intent> pickContactLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                // Aquí se podría procesar el contacto elegido si fuera necesario
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
        btnVerTodos = findViewById(R.id.btn_ver_todos);

        // Inicializar botones de contactos recientes
        btnM = findViewById(R.id.btn_contacto_m);
        btnC = findViewById(R.id.btn_contacto_c);
        btnJ = findViewById(R.id.btn_contacto_j);
        btnO = findViewById(R.id.btn_contacto_o);
    }

    private void setupListeners() {
        btnEnviar.setOnClickListener(v -> toggleOption(true));
        btnSolicitar.setOnClickListener(v -> toggleOption(false));

        // Listeners para autocompletar destinatario
        if (btnM != null) btnM.setOnClickListener(v -> etDestinatario.setText("Manuel Aylón"));
        if (btnC != null) btnC.setOnClickListener(v -> etDestinatario.setText("Claudia Carracedo"));
        if (btnJ != null) btnJ.setOnClickListener(v -> etDestinatario.setText("Juan Monzón"));
        if (btnO != null) btnO.setOnClickListener(v -> etDestinatario.setText("Óscar Torres"));

        // Listener para Ver todos (Agenda de contactos)
        if (btnVerTodos != null) {
            btnVerTodos.setOnClickListener(v -> comprobarPermisoAgenda());
        }

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

    private void comprobarPermisoAgenda() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED) {
            abrirAgendaContactos();
        } else {
            // Pedimos el permiso directamente (el sistema muestra la pestaña de permiso)
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