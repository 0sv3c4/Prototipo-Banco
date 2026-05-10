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
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Transferencias extends BaseActivityClientes {

    private EditText etDestinatario, etImporte, etConcepto;
    private View btnHacerTransferencia, btnAgenda;
    private ImageView tooltipTransferencia;

    // Lanzador para pedir permiso de contactos
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    abrirAgendaContactos();
                } else {
                    Toast.makeText(this, "Permiso de contactos denegado", Toast.LENGTH_SHORT).show();
                }
            });

    // Lanzador para abrir la agenda
    private final ActivityResultLauncher<Intent> pickContactLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                // Aquí se podría procesar el contacto elegido si fuera necesario para rellenar el campo
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferencias);
        
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
        etDestinatario = findViewById(R.id.et_nuevo_destinatario);
        etImporte = findViewById(R.id.et_importe_transferencia);
        etConcepto = findViewById(R.id.et_concepto_transferencia);
        btnHacerTransferencia = findViewById(R.id.btn_hacer_transferencia);
        btnAgenda = findViewById(R.id.btn_agenda_transferencia);
        tooltipTransferencia = findViewById(R.id.tooltip_transferencia);
    }

    private void setupListeners() {
        // Listener para el tooltip del importe
        if (tooltipTransferencia != null) {
            tooltipTransferencia.setOnClickListener(v -> mostrarDialogoTooltip(R.layout.mensaje_tooltip_transferencia));
        }

        // Listener para abrir la agenda de contactos (igual que en Bizum)
        if (btnAgenda != null) {
            btnAgenda.setOnClickListener(v -> comprobarPermisoAgenda());
        }

        // Listener para realizar la transferencia con validación de campos
        if (btnHacerTransferencia != null) {
            btnHacerTransferencia.setOnClickListener(v -> {
                String dest = (etDestinatario != null) ? etDestinatario.getText().toString().trim() : "";
                String imp = (etImporte != null) ? etImporte.getText().toString().trim() : "";
                String conc = (etConcepto != null) ? etConcepto.getText().toString().trim() : "";

                if (dest.isEmpty() || imp.isEmpty() || conc.isEmpty()) {
                    mostrarDialogo(R.layout.mensaje_error, "Transacción fallida", "Por favor, rellene todos los campos antes de continuar.");
                } else {
                    mostrarDialogo(R.layout.mensaje_exito_envio, "Transacción exitosa", "");
                }
            });
        }
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

    /**
     * Muestra un diálogo personalizado con el título y mensaje indicados.
     */
    private void mostrarDialogo(int layoutId, String titulo, String mensaje) {
        View view = LayoutInflater.from(this).inflate(layoutId, null);
        Button btnAceptar = view.findViewById(R.id.btn_aceptar_alerta);
        TextView tvTitulo = view.findViewById(R.id.titulo_alerta);
        TextView tvMensaje = view.findViewById(R.id.mensaje_alerta);

        // Ajuste para layouts con nombres de ID diferentes (como mensaje_exito_envio)
        if (tvTitulo == null) tvTitulo = view.findViewById(R.id.titulo_info);

        if (tvTitulo != null) tvTitulo.setText(titulo);
        if (tvMensaje != null && !mensaje.isEmpty()) {
            tvMensaje.setText(mensaje);
        } else if (tvMensaje != null) {
            tvMensaje.setVisibility(View.GONE);
        }

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