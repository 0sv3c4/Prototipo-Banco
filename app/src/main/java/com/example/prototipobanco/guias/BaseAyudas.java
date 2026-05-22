package com.example.prototipobanco.guias;


import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prototipobanco.BaseActivityClientes;
import com.example.prototipobanco.R;

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

        configurarPantallas(getString(R.string.titulo_toolbar_base),"https://www.youtube.com/shorts/ko81RyagXgI");

    }

    protected void configurarPantallas(String titulo, String url){
        configuracionDrawerToolbar(getString(R.string.ayudasguias));
        marchaAtras();

        TextView tvTitulo = findViewById(R.id.titulo_guia);
        if(tvTitulo!=null){
            tvTitulo.setText(titulo);
        }

        WebView videoDemostrativo = findViewById(R.id.video_demostrativo);

        // Configurar WebView
        WebSettings configuracionWeb = videoDemostrativo.getSettings();
        configuracionWeb.setJavaScriptEnabled(true); // Habilitar JavaScript si es necesario
        configuracionWeb.setDomStorageEnabled(true);


        videoDemostrativo.setWebViewClient(new WebViewClient());
        videoDemostrativo.loadUrl(url);

        /* //En caso de utilizar videoView (no sirve para internet...)
        VideoView videoView = findViewById(R.id.video);
        Uri videoUri = Uri.parse(url);
        videoView.setVideoURI(videoUri);

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.d("videoView", "completed");
            }
        });*/
    }
}