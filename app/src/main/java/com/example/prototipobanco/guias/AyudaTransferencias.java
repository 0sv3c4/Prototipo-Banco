package com.example.prototipobanco.guias;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prototipobanco.FAQ;
import com.example.prototipobanco.R;

public class AyudaTransferencias extends BaseAyudas {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda_transferencias);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        configurarPantallas(getString(R.string.titulo_toolbar_base),"https://www.youtube.com/shorts/ko81RyagXgI"); //TODO: Cambiar URL

        Button accesoBizum = findViewById(R.id.acceso_bizum);
        Button accesoFAQ = findViewById(R.id.acceso_faq);

        accesoBizum.setOnClickListener(v-> {
            Intent intent = new Intent(this, AyudaBizum.class);
            finish();
            startActivity(intent);
        });

        accesoFAQ.setOnClickListener(v-> {
            Intent intent = new Intent(this, FAQ.class);
            finish();
            startActivity(intent);
        });

    }
}