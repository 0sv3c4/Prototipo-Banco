package com.example.prototipobanco;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Inicio_Sesion extends AppCompatActivity {

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

        //Atts modificación contraseña
        EditText escribeContra = findViewById(R.id.introduzca_contra);
        ImageView btnVerContra = findViewById(R.id.btn_ver_contrasena);

        /*Función para la modification de la visibilidad de la contraseña.*/
        btnVerContra.setOnClickListener(v -> {
            if (escribeContra.getTransformationMethod() instanceof PasswordTransformationMethod) {
                // Contraseña oculta -> mostrarla
                escribeContra.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                btnVerContra.setImageResource(R.drawable.ic_ojo_abierto);
            } else {
                // Contraseña visible -> ocultarla
                escribeContra.setTransformationMethod(PasswordTransformationMethod.getInstance());
                btnVerContra.setImageResource(R.drawable.ic_ojo_cerrado);
            }

            // Mantiene el cursor al final del texto cuando se cambia
            escribeContra.setSelection(escribeContra.getText().length());
        });

    }
}