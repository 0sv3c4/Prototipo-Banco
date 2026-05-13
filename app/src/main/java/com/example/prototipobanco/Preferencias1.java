package com.example.prototipobanco;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.google.android.material.button.MaterialButton;

public class Preferencias1 extends BaseActivityClientes {

    private EditText etCorreo, etTlf, etPoblacion, etAlias;
    private TextView tvPais, tvIdioma;
    private LinearLayout selectorPais, selectorIdioma;

    private final String[] listaPaises = {getString(R.string.espana), getString(R.string.francia), getString(R.string.portugal),
            getString(R.string.italia), getString(R.string.alemania), getString(R.string.reino_unido), getString(R.string.estados_unidos)};
    private final String[] listaIdiomas = {getString(R.string.espanol), getString(R.string.ingles), "Francés", "Portugués", "Alemán", "Italiano"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias1);

        // Configuración de la base (Drawer, Toolbar y botón Volver)
        configuracionDrawerToolbar(getString(R.string.preferencias));
        marchaAtras();

        // Inicializar vistas
        etCorreo = findViewById(R.id.et_correo);
        etTlf = findViewById(R.id.et_tlf);
        etPoblacion = findViewById(R.id.et_poblacion);
        etAlias = findViewById(R.id.et_alias);

        tvPais = findViewById(R.id.tv_pais_seleccionado);
        tvIdioma = findViewById(R.id.tv_idioma_seleccionado);

        selectorPais = findViewById(R.id.selector_pais);
        selectorIdioma = findViewById(R.id.selector_idioma);

        // Configurar selectores desplegables
        setupSelectors();

        // Configurar botones "Aplicar"
        setupAplicarButtons();

        // Botón Siguiente - Ahora navega a Preferencias2
        MaterialButton btnSiguiente = findViewById(R.id.btn_siguiente);
        btnSiguiente.setOnClickListener(v -> {
            Intent intent = new Intent(Preferencias1.this, Preferencias2.class);
            startActivity(intent);
        });
    }

    private void setupSelectors() {
        selectorPais.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                .setTitle(R.string.selec_pais)
                .setItems(listaPaises, (dialog, which) -> {
                    tvPais.setText(listaPaises[which]);
                })
                .show();
        });

        selectorIdioma.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                .setTitle(R.string.selec_idioma)
                .setItems(listaIdiomas, (dialog, which) -> {
                    tvIdioma.setText(listaIdiomas[which]);
                })
                .show();
        });
    }

    private void setupAplicarButtons() {
        findViewById(R.id.btn_aplicar_correo).setOnClickListener(v -> validarYAplicar(getString(R.string.correo), etCorreo.getText().toString()));
        findViewById(R.id.btn_aplicar_tlf).setOnClickListener(v -> validarYAplicar(getString(R.string.telefono), etTlf.getText().toString()));
        findViewById(R.id.btn_aplicar_pais).setOnClickListener(v -> validarYAplicar(getString(R.string.pais), tvPais.getText().toString()));
        findViewById(R.id.btn_aplicar_poblacion).setOnClickListener(v -> validarYAplicar(getString(R.string.poblacion), etPoblacion.getText().toString()));
        findViewById(R.id.btn_aplicar_idioma).setOnClickListener(v -> validarYAplicar(getString(R.string.idioma), tvIdioma.getText().toString()));
        findViewById(R.id.btn_aplicar_alias).setOnClickListener(v -> validarYAplicar(getString(R.string.alias), etAlias.getText().toString()));
    }

    private void validarYAplicar(String campo, String valor) {
        if (valor.isEmpty() || valor.equals(getString(R.string.selec_pais)) || valor.equals(getString(R.string.selec_idioma))) {
            mostrarAlertaPersonalizada(getString(R.string.atencion), getString(R.string.completar_campo) + campo, R.drawable.ic_error);
        } else {
            mostrarAlertaPersonalizada(getString(R.string.actualizado), campo + getString(R.string.se_ha_cambiado_a) + valor, R.drawable.ic_exito);
        }
    }

    private void mostrarAlertaPersonalizada(String titulo, String mensaje, int iconoResId) {
        // Inflamos el layout personalizado que usan tus compañeros
        View dialogView = LayoutInflater.from(this).inflate(R.layout.mensaje_contratar, null);
        
        TextView tvTitulo = dialogView.findViewById(R.id.titulo_info);
        TextView tvMensaje = dialogView.findViewById(R.id.mensaje_info);
        ImageView ivIcono = dialogView.findViewById(R.id.icono_info);
        Button btnAceptar = dialogView.findViewById(R.id.btn_aceptar_alerta);

        tvTitulo.setText(titulo);
        tvMensaje.setText(mensaje);
        ivIcono.setImageResource(iconoResId);

        // Si es un error, cambiamos el color del título y botón para que combine
        if (iconoResId == R.drawable.ic_error) {
            tvTitulo.setTextColor(ContextCompat.getColor(this, R.color.error));
            btnAceptar.setBackgroundTintList(android.content.res.ColorStateList.valueOf(ContextCompat.getColor(this, R.color.error)));
        }

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .create();

        // Hacer el fondo del diálogo transparente para que se vea el diseño curvo de tus compañeros
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }

        btnAceptar.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }
}
