package com.example.prototipobanco;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Bizum extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bizum);

        // 1. Referenciar los elementos de la interfaz
        EditText etDestinatario = findViewById(R.id.etDestinatario);
        EditText etCantidad = findViewById(R.id.etCantidad);
        EditText etConcepto = findViewById(R.id.etConcepto);
        Button btnConfirmar = findViewById(R.id.btnConfirmar);
        Button btnVolver = findViewById(R.id.btnVolver);

        // 2. Configurar el campo de cantidad para que solo acepte números y decimales
        etCantidad.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        // 3. Lógica del botón Confirmar
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String destinatario = etDestinatario.getText().toString();
                String cantidad = etCantidad.getText().toString();
                String concepto = etConcepto.getText().toString();

                if (!destinatario.isEmpty() && !cantidad.isEmpty()) {
                    // Aquí iría la lógica para procesar el Bizum
                    String mensaje = "Bizum de " + cantidad + "€ enviado a " + destinatario;
                    Toast.makeText(Bizum.this, mensaje, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Bizum.this, "Por favor, rellena los campos obligatorios", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 4. Lógica del botón Volver
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra la actividad actual y vuelve a la anterior
            }
        });
    }
}