package com.example.prototipobanco.guias;


import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prototipobanco.BaseActivityClientes;
import com.example.prototipobanco.R;

import java.util.Locale;

public class BaseAyudas extends BaseActivityClientes {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_ayudas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if(Locale.getDefault().getDisplayLanguage().equals("English")){
            configurarPantallas(getString(R.string.titulo_toolbar_base),R.raw.video_principal_en);
        } else {
            configurarPantallas(getString(R.string.titulo_toolbar_base),R.raw.video_principal_es);
        }

    }

    @SuppressLint("ClickableViewAccessibility") //Tenemos para ello v.performClick, pero este es más estricto
    protected void configurarPantallas(String titulo, int idVideo){
        configuracionDrawerToolbar(getString(R.string.ayudasguias));
        marchaAtras();

        TextView tvTitulo = findViewById(R.id.titulo_guia);
        if(tvTitulo!=null){
            tvTitulo.setText(titulo);
        }

        VideoView videoView = findViewById(R.id.video_demostrativo);
        String ubicacionVideo = "android.resource://" + getPackageName() + "/" + idVideo;
        Uri videoUri = Uri.parse(ubicacionVideo);
        videoView.setVideoURI(videoUri);

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        videoView.setOnPreparedListener(mp -> videoView.start());


        videoView.setOnCompletionListener(mp -> Log.d("videoView", "completed"));
    }
}