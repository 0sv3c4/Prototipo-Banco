package com.example.prototipobanco

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class BizumActivity extends AppCompatActivity {

    private EditText etDestinatario, etCantidad, etConcepto;
    private Button btnConfirmar, btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bizum);

        etDestinatario = findViewById(R.id.etDestinatario);
        etCantidad     = findViewById(R.id.etCantidad);
        etConcepto     = findViewById(R.id.etConcepto);
        btnConfirmar   = findViewById(R.id.btnConfirmar);
        btnVolver      = findViewById(R.id.btnVolver);

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarYConfirmar();
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra esta Activity y vuelve a la anterior
            }
        });
    }

    private void validarYConfirmar() {
        String destinatario = etDestinatario.getText().toString().trim();
        String cantidad     = etCantidad.getText().toString().trim();

        // Validación de campos obligatorios
        if (TextUtils.isEmpty(destinatario) && TextUtils.isEmpty(cantidad)) {
            Toast.makeText(this,
                "Por favor, introduce el destinatario y la cantidad.",
                Toast.LENGTH_LONG).show();
            etDestinatario.setError("Campo obligatorio");
            etCantidad.setError("Campo obligatorio");
            return;
        }

        if (TextUtils.isEmpty(destinatario)) {
            Toast.makeText(this,
                "Por favor, introduce el destinatario.",
                Toast.LENGTH_LONG).show();
            etDestinatario.setError("Campo obligatorio");
            etDestinatario.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(cantidad)) {
            Toast.makeText(this,
                "Por favor, introduce la cantidad.",
                Toast.LENGTH_LONG).show();
            etCantidad.setError("Campo obligatorio");
            etCantidad.requestFocus();
            return;
        }

        String concepto = etConcepto.getText().toString().trim();
        String mensaje = "Bizum enviado a " + destinatario
        + " por " + cantidad + "€"
        + (concepto.isEmpty() ? "" : " — " + concepto);
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }
}