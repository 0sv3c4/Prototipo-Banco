package com.example.prototipobanco;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import com.example.prototipobanco.todosUsu.Accesibilidad;
import com.example.prototipobanco.todosUsu.Contacto_clientes;
import com.example.prototipobanco.todosUsu.Inicio_Sesion;
import com.example.prototipobanco.todosUsu.Mapa_cajeros;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.navigation.NavigationView;

public class BaseActivityClientes extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_clientes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        configuracionDrawerToolbar(getString(R.string.titulo_toolbar_base));
        marchaAtras();
    }

    /**
     * Método para configurar el drawer.
     * @param titulo Título de la toolbar.
     */
    protected void configuracionDrawerToolbar(String titulo){
        drawerLayout = findViewById(R.id.main);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        View headerView = navigationView.getHeaderView(0);
        if (headerView != null) {
            headerView.setOnClickListener(v -> {
                Intent intent = new Intent(this, Perfil.class);
                startActivity(intent);
            });
        }

        TextView tituloToolbar = findViewById(R.id.titulo_toolbar);
        if (tituloToolbar != null) {
            tituloToolbar.setText(titulo);
        }


        FrameLayout btnNotif = findViewById(R.id.btn_notificaciones);
        if (btnNotif != null) {
            btnNotif.setOnClickListener(v -> {
                Intent intent = new Intent(this, Notificaciones.class);
                startActivity(intent);
            });
        }


        ShapeableImageView btnPerfil = findViewById(R.id.btn_perfil);
        if (btnPerfil != null) {
            btnPerfil.setOnClickListener(v -> {
                Intent intent = new Intent(this, Perfil.class);
                startActivity(intent);
            });
        }


        ImageView btnMenu = findViewById(R.id.btn_menu);
        if (btnMenu != null) {
            btnMenu.setOnClickListener(v -> {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            });
        }
    }

    protected void marchaAtras(){
        MaterialButton btnVolver = findViewById(R.id.btn_volver);
        if (btnVolver != null) {
            btnVolver.setOnClickListener(v -> finish());
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        
        if (id == R.id.nav_seguridad) {
            intent = new Intent(this, Seguridad.class);
            startActivity(intent);
        } else if (id == R.id.nav_config) {
            intent = new Intent(this, Preferencias1.class);
            startActivity(intent);
        } else if (id == R.id.nav_atencion_cl) {
            intent = new Intent(this, Contacto_clientes.class);
            startActivity(intent);
        } else if (id == R.id.nav_mapa) {
            intent = new Intent(this, Mapa_cajeros.class);
            startActivity(intent);
        } else if (id == R.id.nav_servicios){
            intent = new Intent(this, ContratacionServicios.class);
            startActivity(intent);
        } else if (id == R.id.nav_faq) {
            intent = new Intent(this, FAQ.class);
            startActivity(intent);
        } else if (id == R.id.nav_menu) {
            intent = new Intent(this, Pantalla_principal.class);
            startActivity(intent);
        } else if (id == R.id.nav_logout) {
            intent = new Intent(this, Inicio_Sesion.class);
            startActivity(intent);
        } else if (id == R.id.nav_accesibilidad) {
            intent = new Intent(this, Accesibilidad.class);
            startActivity(intent);
        } else if (id == R.id.nav_guia) {
            intent = new Intent(this, Ayuda.class);
            startActivity(intent);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
