package com.example.prototipobanco;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class BaseActivityClientes extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_clientes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        drawerLayout = findViewById(R.id.main);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Buscamos el ImageView que definiste en ic_menu_hamburguesa.xml dentro de la toolbar
        ImageView btnMenu = findViewById(R.id.btn_menu);
        btnMenu.setOnClickListener(v -> {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        //traté de usar un switch, pero al orgnaizarse de manera dinámica no deja...
        if (id == R.id.nav_seguridad) {
            intent = new Intent(this, Contacto_clientes.class); //TODO
            startActivity(intent);
        } else if (id == R.id.nav_config) {
            intent = new Intent(this, Contacto_clientes.class); //TODO
            startActivity(intent);
        } else if (id == R.id.nav_atencion_cl) {
            intent = new Intent(this, Contacto_clientes.class);
            startActivity(intent);
        } else if (id == R.id.nav_mapa) {
            intent = new Intent(this, Contacto_clientes.class); //TODO
            startActivity(intent);
        } else if (id == R.id.nav_servicios){
            intent = new Intent(this, Contacto_clientes.class);//TODO
            startActivity(intent);
        } else if (id == R.id.nav_menu) {
            intent = new Intent(this, Promociones.class);
            startActivity(intent);
        } else if (id==R.id.nav_logout) {
            intent = new Intent(this, Inicio_Sesion.class);
            startActivity(intent);
        } else if (id==R.id.nav_accesibilidad) {
            intent = new Intent(this, Accesibilidad.class);
            startActivity(intent);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
