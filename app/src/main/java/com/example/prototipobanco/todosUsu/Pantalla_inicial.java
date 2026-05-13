package com.example.prototipobanco.todosUsu;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prototipobanco.R;
import com.google.android.material.button.MaterialButton;

import java.util.Locale;

public class Pantalla_inicial extends AppCompatActivity {

    Spinner spinner;
    public static final String[] languages = {"Lang","Español", "English"}; //NO EXTRAER STRINGS

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pantalla_inicial);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configuración para cerrar la app al pulsar atrás
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finishAffinity();
            }
        });

        //Atts a utilizar
        MaterialButton btnIniciarSesion = findViewById(R.id.inicio_sesion);
        ConstraintLayout btnContactanos = findViewById(R.id.contactanos);

        LinearLayout btnOfertas = findViewById(R.id.clicakble_ofertas);
        LinearLayout btnEventos = findViewById(R.id.clickable_eventos);

        //Redireccionar
        btnIniciarSesion.setOnClickListener(v -> {
            Intent intent = new Intent(this, Inicio_Sesion.class);
            startActivity(intent);
        });

        btnContactanos.setOnClickListener(v ->{
            Intent intent = new Intent(this, Contacto_clientes.class);
            startActivity(intent);
        });

        btnOfertas.setOnClickListener(v ->{
            Intent intent = new Intent(this, Promociones_banco.class);
            startActivity(intent);
        });

        btnEventos.setOnClickListener(v ->{
            Intent intent = new Intent(this, Promociones_banco.class);
            startActivity(intent);
        });


        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLang = parent.getItemAtPosition(position).toString();
                if (selectedLang.equals("English")){
                    setLocal(Pantalla_inicial.this, "en");
                    finish();
                    startActivity(getIntent());
                } else if (selectedLang.equals("Español")){
                    setLocal(Pantalla_inicial.this, "es");
                    finish();
                    startActivity(getIntent());
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setLocal(Activity activity, String langCode){
        Locale locale = new Locale(langCode);
        locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }
}