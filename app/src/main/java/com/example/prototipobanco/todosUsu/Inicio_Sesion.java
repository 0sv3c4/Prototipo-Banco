package com.example.prototipobanco.todosUsu;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prototipobanco.Pantalla_principal;
import com.example.prototipobanco.R;
import com.google.android.material.button.MaterialButton;

public class Inicio_Sesion extends AppCompatActivity {
    public static final int NUM_NIF=9;

    private enum LetraDNI {T, R, W, A, G, M, Y, F, P, D, X, B, N, J, Z, S, Q, V, H, L, C, K, E}

    private EditText escribeContra;
    private ImageView btnVerContra;
    private EditText escribeNIF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio_sesion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        escribeContra = findViewById(R.id.introduzca_contra);
        btnVerContra = findViewById(R.id.btn_ver_contrasena);
        escribeNIF = findViewById(R.id.introduzca_NIF);
        ImageView btnBiometria = findViewById(R.id.acceso_biometrico);
        MaterialButton btnIniciarSesion = findViewById(R.id.btn_iniciar_sesion);
        MaterialButton btnAccesibilidad = findViewById(R.id.boton_accesibilidad);
        MaterialButton btnContacto = findViewById(R.id.boton_contacto);
        MaterialButton btnMapa = findViewById(R.id.boton_mapa);
        TextView btnOlvidoContra = findViewById(R.id.recuperar_contrasena);

        // Tooltips
        ImageView tooltipNif = findViewById(R.id.tooltip_nif);
        ImageView tooltipContra = findViewById(R.id.tooltip_contra);

        tooltipNif.setOnClickListener(v -> mostrarDialogoTooltip(R.layout.mensaje_tootip_nif, R.id.alerta_nif));
        tooltipContra.setOnClickListener(v -> mostrarDialogoTooltip(R.layout.mensaje_tootip_contrasena, R.id.alerta_contra));

        btnVerContra.setOnClickListener(v -> {
            if (escribeContra.getTransformationMethod() instanceof PasswordTransformationMethod) {
                escribeContra.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                btnVerContra.setImageResource(R.drawable.ic_ojo_abierto);
            } else {
                escribeContra.setTransformationMethod(PasswordTransformationMethod.getInstance());
                btnVerContra.setImageResource(R.drawable.ic_ojo_cerrado);
            }
            escribeContra.setSelection(escribeContra.getText().length());
        });

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(Inicio_Sesion.this, Pantalla_inicial.class);
                startActivity(intent);
            }
        });

        btnIniciarSesion.setOnClickListener(this::controlAcceso);

        btnAccesibilidad.setOnClickListener(v ->{
            Intent intent = new Intent(this, Accesibilidad.class);
            startActivity(intent);
        });

        btnContacto.setOnClickListener(v ->{
            Intent intent = new Intent(this, Contacto_clientes.class);
            startActivity(intent);
        });

        btnMapa.setOnClickListener(v ->{
            Intent intent = new Intent(this, Mapa_cajeros.class);
            startActivity(intent);
        });

        btnBiometria.setOnClickListener(v ->{
            Intent intent = new Intent(this, Pantalla_principal.class);
            startActivity(intent);
        });

        btnOlvidoContra.setOnClickListener(v -> mensajeInfo());
    }

    private void mostrarDialogoTooltip(int layoutId, int containerId) {
        ConstraintLayout container = findViewById(containerId);
        View view = LayoutInflater.from(this).inflate(layoutId, container);
        
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        
        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }
        
        alertDialog.show();
    }

    private void controlAcceso(View v){
        String nifRevisar = escribeNIF.getText().toString().trim().toUpperCase();
        boolean datoValido;
        try{
            datoValido=comprobarNIF(nifRevisar);
        } catch(NumberFormatException e){
            datoValido=false;
        }
        if(!datoValido){
            mensajeError();
        } else {
            Toast.makeText(this, R.string.bienvenido, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Pantalla_principal.class);
            startActivity(intent);
        }
    }

    private boolean comprobarNIF(String nifRevisar) {
        boolean datoValido=true;
        int numeroDni;
        if(nifRevisar.length()!=NUM_NIF) {
            datoValido=false;
        } else {
            switch (nifRevisar.charAt(0)){
                case 'X': nifRevisar = nifRevisar.replaceFirst(getString(R.string.x),getString(R.string._0)); break;
                case 'Y': nifRevisar = nifRevisar.replaceFirst(getString(R.string.y),getString(R.string._1)); break;
                case 'Z': nifRevisar = nifRevisar.replaceFirst(getString(R.string.z),getString(R.string._2)); break;
            }
            try {
                numeroDni = Integer.parseInt(nifRevisar.substring(0,8));
                String letra = nifRevisar.substring(8);
                if(!LetraDNI.values()[numeroDni%23].toString().equals(letra)){
                    datoValido=false;
                }
            } catch (Exception e) {
                datoValido = false;
            }
        }
        return datoValido;
    }

    private void mensajeError(){
        ConstraintLayout error = findViewById(R.id.alerta_error);
        View view = LayoutInflater.from(this).inflate(R.layout.mensaje_error, error);
        Button aceptarError = view.findViewById(R.id.btn_aceptar_alerta);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }

        aceptarError.setOnClickListener(v -> alertDialog.dismiss());
    }

    private void mensajeInfo(){
        ConstraintLayout recuperarContra = findViewById(R.id.alerta_contra);
        View view = LayoutInflater.from(this).inflate(R.layout.mensaje_olvido_contrasena, recuperarContra);
        Button enviarSolicitud = view.findViewById(R.id.btn_aceptar_alerta);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }
        EditText nifRecuperar = view.findViewById(R.id.introduzca_NIF);

        enviarSolicitud.setOnClickListener(v -> {
            if(comprobarNIF(nifRecuperar.getText().toString().trim().toUpperCase())){
                Toast.makeText(this, R.string.solicitud_enviada_revisa_tu_correo, Toast.LENGTH_LONG).show();
                alertDialog.dismiss();
            } else {
                mensajeError();
            }
        });
    }
}