package com.example.prototipobanco;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prototipobanco.todosUsu.Contacto_clientes;
import com.google.android.material.button.MaterialButton;

import java.util.Locale;

public class ContratacionServicios extends BaseActivityClientes {

    private EditText etNumAcciones, etPrecioActual;
    private TextView tvPrecioFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contratacion_servicios);
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configuramos la Toolbar (Título se maneja en el XML con el diseño curvo)
        configuracionDrawerToolbar("");
        
        // Configuramos el botón de volver de la base
        marchaAtras();

        // Inicializar componentes de la UI
        etNumAcciones = findViewById(R.id.et_num_acciones);
        etPrecioActual = findViewById(R.id.et_precio_actual);
        tvPrecioFinal = findViewById(R.id.tv_precio_final);

        MaterialButton btnApple = findViewById(R.id.btn_apple);
        MaterialButton btnAmazon = findViewById(R.id.btn_amazon);
        MaterialButton btnTesla = findViewById(R.id.btn_tesla);
        MaterialButton btnMicrosoft = findViewById(R.id.btn_microsoft);

        // Listeners para los botones de empresas populares para actualizar el precio actual
        if (btnApple != null) btnApple.setOnClickListener(v -> etPrecioActual.setText(R.string.precio_Apple));
        if (btnAmazon != null) btnAmazon.setOnClickListener(v -> etPrecioActual.setText(R.string.precio_Amazon));
        if (btnTesla != null) btnTesla.setOnClickListener(v -> etPrecioActual.setText(R.string.precio_Tesla));
        if (btnMicrosoft != null) btnMicrosoft.setOnClickListener(v -> etPrecioActual.setText(R.string.precio_Microsoft));

        // TextWatcher para calcular el precio final automáticamente al cambiar los valores
        TextWatcher calculationWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcularPrecioFinal();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

        if (etNumAcciones != null) etNumAcciones.addTextChangedListener(calculationWatcher);
        if (etPrecioActual != null) etPrecioActual.addTextChangedListener(calculationWatcher);

        // Botón Contacto - Redirige a Contacto_clientes
        View btnContacto = findViewById(R.id.btn_contacto);
        if (btnContacto != null) {
            btnContacto.setOnClickListener(v -> {
                Intent intent = new Intent(this, Contacto_clientes.class);
                startActivity(intent);
            });
        }
    }

    /**
     * Calcula la multiplicación entre número de acciones y precio actual.
     * Muestra "00.0€" si falta alguno de los valores.
     */
    private void calcularPrecioFinal() {
        if (etNumAcciones == null || etPrecioActual == null || tvPrecioFinal == null) return;

        String strAcciones = etNumAcciones.getText().toString().replace(",", ".");
        String strPrecio = etPrecioActual.getText().toString().replace(",", ".");

        if (strAcciones.isEmpty() || strPrecio.isEmpty()) {
            tvPrecioFinal.setText("00.0€");
            return;
        }

        try {
            double acciones = Double.parseDouble(strAcciones);
            double precio = Double.parseDouble(strPrecio);
            double total = acciones * precio;
            // Mostramos el resultado con dos decimales
            tvPrecioFinal.setText(String.format(Locale.getDefault(), getString(R.string.formato_precio), total));
        } catch (NumberFormatException e) {
            tvPrecioFinal.setText(R.string.valor_0);
        }
    }
}
