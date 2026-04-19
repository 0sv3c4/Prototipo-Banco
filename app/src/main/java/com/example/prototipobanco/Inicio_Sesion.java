package com.example.prototipobanco;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;


public class Inicio_Sesion extends AppCompatActivity {

    public static final int NUM_NIF=9;

    //Valores de la letra que debe tener el dni según su módulo
    private enum LetraDNI {T, R, W, A, G, M, Y, F, P, D, X, B, N, J, Z, S, Q, V, H, L, C, K, E}

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
        }
        );


        //Asegurar inicio de sesión correcto
        MaterialButton btnIniciarSesion = findViewById(R.id.btn_iniciar_sesion);
        EditText escribeNIF = findViewById(R.id.introduzca_NIF);

        btnIniciarSesion.setOnClickListener(v ->{
            String nifRevisar = escribeNIF.getText().toString().trim().toUpperCase();//quedarnos con los digitos solo
            boolean datoErroneo =false;
            int numeroDni;
            try{
                if(nifRevisar.length()!=NUM_NIF) {
                    datoErroneo=true; //debe tener 8 dígitos más la letra
                } else {
                    switch (nifRevisar.charAt(0)){ //en caso de que sea un NIF extranjero
                        case 'X':
                            nifRevisar = nifRevisar.replaceFirst("X","0");
                            break;
                        case 'Y':
                            nifRevisar = nifRevisar.replaceFirst("Y","1");
                            break;
                        case 'Z':
                            nifRevisar = nifRevisar.replaceFirst("Z","2");
                    }
                    numeroDni = Integer.parseInt(nifRevisar.substring(0,8));
                    String letra = nifRevisar.substring(8);
                    if(!LetraDNI.values()[numeroDni%23].toString().equals(letra)){
                        datoErroneo=true;
                    }
                }
            } catch(NumberFormatException e){ //iba a utilizar _, pero no deja entiendo por la versión de java
                datoErroneo=true;
            }

            if(datoErroneo){
                Toast.makeText(this, "El formato del DNI NO es correcto", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "DNI válido ✓", Toast.LENGTH_LONG).show();
            }
        }
        );
    }
}