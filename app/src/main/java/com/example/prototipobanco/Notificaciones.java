package com.example.prototipobanco;

import android.os.Bundle;

public class Notificaciones extends BaseActivityClientes {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaciones);

        // configuracionDrawerToolbar configura el menú lateral y el título
        configuracionDrawerToolbar("Notificaciones");
        
        // marchaAtras() busca automáticamente el botón "btn_volver" 
        // de la barra inferior y le asigna la función de cerrar la pantalla.
        marchaAtras();
    }
}
