package com.example.prototipobanco;

import android.app.AlertDialog;
import android.content.Intent;
//import android.hardware.biometrics.BiometricPrompt;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
//import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.content.ContextCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

//import java.util.concurrent.Executor;


public class Inicio_Sesion extends AppCompatActivity {
    public static final int NUM_NIF=9;


    //Valores de la letra que debe tener el dni según su módulo
    private enum LetraDNI {T, R, W, A, G, M, Y, F, P, D, X, B, N, J, Z, S, Q, V, H, L, C, K, E}


    //Atts modificación contraseña
    private EditText escribeContra;
    private ImageView btnVerContra;
    private EditText escribeNIF;
    //private ImageView btnBiometria;
    private MaterialButton btnIniciarSesion, btnAccesibilidad, btnContacto;

    public Inicio_Sesion(){

    }

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

        //declaraciones
        escribeContra = findViewById(R.id.introduzca_contra);
        btnVerContra = findViewById(R.id.btn_ver_contrasena);
        escribeNIF = findViewById(R.id.introduzca_NIF);
        btnIniciarSesion = findViewById(R.id.btn_iniciar_sesion);
        btnAccesibilidad = findViewById(R.id.boton_accesibilidad);
        btnContacto = findViewById(R.id.boton_contacto);


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

        btnIniciarSesion.setOnClickListener(this::controlAcceso);

        btnAccesibilidad.setOnClickListener(v ->{
            Intent intent = new Intent(this, Accesibilidad.class);
            startActivity(intent);
        });

        btnContacto.setOnClickListener(v ->{
            Intent intent = new Intent(this, Contacto_clientes.class);
            startActivity(intent);
        });

        //btnBiometria.setOnClickListener(this::accesoBiometria);
    }

    /*private void accesoBiometria(View view){
        Executor executor = ContextCompat.getMainExecutor(this);
        //EN REVISIÓN
        BiometricPrompt biometricPrompt = new BiometricPrompt(this,executor,new BiometricPrompt.AuthenticationCallback(){
            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                startActivity(new Intent(Inicio_Sesion.this, Promociones.class));
            }
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext(),
                        "Error en el proceso: " + errString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(),
                        "No reconocido, inténtalo de nuevo", Toast.LENGTH_SHORT).show();
            }
        });
    }

     */

    /**
     * Método para comprobar si debería haber acceso a la app.
     * Este es privado ya que solo será utilizado por el botón de inicio de sesión.
     * @param v vista utilizada (el propio xml)
     */
    private void controlAcceso(View v){
        String nifRevisar = escribeNIF.getText().toString().trim().toUpperCase();//quedarnos con los digitos solo
        boolean datoValido;
        try{
            datoValido=comprobarNIF(nifRevisar);
        } catch(NumberFormatException e){ //iba a utilizar _, pero no deja entiendo por la versión de java
            datoValido=false;
        }
        if(!datoValido){
            mensajeError();
        } else {
            Toast.makeText(this, "¡Bienvenido!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Método auxiliar para comprobar que el NIF es correcto
     * @param nifRevisar NIF aportado.
     * @return true si es válido, false si no lo es
     */
    private boolean comprobarNIF(String nifRevisar) {
        boolean datoValido=true; //de primeras está bien
        int numeroDni;
        if(nifRevisar.length()!=NUM_NIF) {
            datoValido=false; //debe tener 8 dígitos más la letra
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
                datoValido=false;
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

        aceptarError.setOnClickListener(v -> alertDialog.dismiss());

    }
}