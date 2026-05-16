package com.example.prototipobanco;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Notificaciones extends BaseActivityClientes {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaciones);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        // configuracionDrawerToolbar configura el menú lateral y el título
        configuracionDrawerToolbar(getString(R.string.notificaciones));

        // marchaAtras() busca automáticamente el botón "btn_volver" 
        // de la barra inferior y le asigna la función de cerrar la pantalla.
        marchaAtras();

        //Configuracion texto notificaciones
        configurarItemsNotificacion(R.id.n1, getString(R.string.notif_multa_titulo), getString(R.string.notif_multa_desc), getString(R.string.notif_multa_hora));
        configurarItemsNotificacion(R.id.n2, getString(R.string.notif_pago_game_titulo), getString(R.string.notif_pago_game_desc), getString(R.string.notif_pago_game_hora));
        configurarItemsNotificacion(R.id.n3, getString(R.string.notif_bizum_walter_titulo), getString(R.string.notif_bizum_walter_desc), getString(R.string.notif_bizum_walter_hora));
        configurarItemsNotificacion(R.id.n4, getString(R.string.notif_renta_titulo), getString(R.string.notif_renta_desc), getString(R.string.notif_renta_hora));
        configurarItemsNotificacion(R.id.n5, getString(R.string.notif_nomina_titulo), getString(R.string.notif_nomina_desc), getString(R.string.notif_nomina_hora));
        configurarItemsNotificacion(R.id.n6, getString(R.string.notif_becas_titulo), getString(R.string.notif_becas_desc), getString(R.string.notif_becas_hora));
        configurarItemsNotificacion(R.id.n7, getString(R.string.notif_transf_oscar_titulo), getString(R.string.notif_transf_oscar_desc), getString(R.string.notif_transf_oscar_hora));
        //Configurar texto notificiaciones leidas
        configurarItemsNotificacion(R.id.nl1, getString(R.string.notif_pago_aliexpress_titulo), getString(R.string.notif_pago_aliexpress_desc), getString(R.string.notif_ayer));
        configurarItemsNotificacion(R.id.nl2, getString(R.string.notif_domiciliado_spotify_titulo), getString(R.string.notif_domiciliado_spotify_desc), getString(R.string.notif_ayer));
        configurarItemsNotificacion(R.id.nl3, getString(R.string.notif_bizum_federico_titulo), getString(R.string.notif_bizum_federico_desc), getString(R.string.notif_ayer));
    }

    private void configurarItemsNotificacion(int idItem, String titulo, String descripcion, String hora) {
        View includedLayout = findViewById(idItem);
        if (includedLayout != null) {
            TextView tvTitulo = includedLayout.findViewById(R.id.titulo_notif);
            TextView tvDescripcion = includedLayout.findViewById(R.id.desc_notif);
            TextView tvHora = includedLayout.findViewById(R.id.fecha_notif);

            if (tvTitulo != null) tvTitulo.setText(titulo);
            if (tvDescripcion != null) tvDescripcion.setText(descripcion);
            if (tvHora != null) tvHora.setText(hora);
        }
    }
}
