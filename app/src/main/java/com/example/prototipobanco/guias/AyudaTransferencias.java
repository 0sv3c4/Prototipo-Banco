package com.example.prototipobanco.guias;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

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

        configurarPantallas(getString(R.string.transferencias),"https://www.youtube.com/watch?v=dwY7w0k3j2Y"); //TODO: Cambiar URL

        TextView accesoBizum = findViewById(R.id.acceso_bizum);
        TextView accesoFAQ = findViewById(R.id.acceso_faq);

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