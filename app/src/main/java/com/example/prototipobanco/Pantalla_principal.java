package com.example.prototipobanco;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prototipobanco.todosUsu.Promociones_banco;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.navigation.NavigationView;

public class Pantalla_principal extends BaseActivityClientes {

    private TextView tvSaldo, tvIban;
    private MaterialSwitch switchVisibilidad;
    private ImageView ivTarjetaNfc;
    private View layoutNfcBottom;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        configuracionDrawerToolbar("");

        // Inicializar componentes
        tvSaldo = findViewById(R.id.tv_saldo);
        tvIban = findViewById(R.id.tv_iban);
        switchVisibilidad = findViewById(R.id.switch_visibilidad);
        ivTarjetaNfc = findViewById(R.id.iv_tarjeta_nfc_full);
        layoutNfcBottom = findViewById(R.id.layout_nfc_bottom);

        // Configurar botones de navegación
        configurarNavegacion();

        // Configurar lógica de visibilidad
        configurarVisibilidad();

        // Configurar gestos NFC
        configurarGestosNfc();
    }

    private void configurarNavegacion() {
        LinearLayout btnBizum = findViewById(R.id.btn_bizum_principal);
        if (btnBizum != null) btnBizum.setOnClickListener(v -> startActivity(new Intent(this, Bizum.class)));

        LinearLayout btnBalance = findViewById(R.id.btn_balance_principal);
        if (btnBalance != null) btnBalance.setOnClickListener(v -> startActivity(new Intent(this, BalanceGeneral.class)));

        LinearLayout btnTransferir = findViewById(R.id.btn_transferir_principal);
        if (btnTransferir != null) btnTransferir.setOnClickListener(v -> startActivity(new Intent(this, Transferencias.class)));

        LinearLayout btnServicios = findViewById(R.id.btn_servicios_principal);
        if (btnServicios != null) btnServicios.setOnClickListener(v -> startActivity(new Intent(this, ContratacionServicios.class)));

        MaterialCardView cardCuenta = findViewById(R.id.card_cuenta_principal);
        if (cardCuenta != null) cardCuenta.setOnClickListener(v -> startActivity(new Intent(this, InformacionClientes.class)));

        MaterialCardView btnPromocion = findViewById(R.id.card_promocion);
        if (btnPromocion != null) btnPromocion.setOnClickListener(v -> startActivity(new Intent(this, Promociones_banco.class)));
    }
    
    private void configurarVisibilidad() {
        NavigationView navView = findViewById(R.id.nav_view);
        View headerView = navView.getHeaderView(0);
        TextView tvSaldoNav = headerView.findViewById(R.id.tv_saldo_nav);

        if (switchVisibilidad != null) {
            switchVisibilidad.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    tvSaldo.setText(R.string.fondos);
                    tvIban.setText(R.string.IBAN);
                    if (tvSaldoNav != null) tvSaldoNav.setText(R.string.valor_saldo);
                } else {
                    tvSaldo.setText("*******€");
                    tvIban.setText("ES13 **** **** **** **** 8129");
                    if (tvSaldoNav != null) tvSaldoNav.setText("*******€");
                }
            });
        }
    }

    private void configurarGestosNfc() {
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            private static final int SWIPE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                float diffY = e2.getY() - e1.getY();
                if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY < 0) {
                        mostrarTarjeta();
                    } else {
                        ocultarTarjeta();
                    }
                    return true;
                }
                return false;
            }
        });

        if (layoutNfcBottom != null) {
            layoutNfcBottom.setOnTouchListener((v, event) -> {
                gestureDetector.onTouchEvent(event);
                return true;
            });
        }

        if (ivTarjetaNfc != null) {
            ivTarjetaNfc.setOnTouchListener((v, event) -> {
                gestureDetector.onTouchEvent(event);
                return true;
            });
        }
    }

    private void mostrarTarjeta() {
        if (ivTarjetaNfc != null && ivTarjetaNfc.getVisibility() != View.VISIBLE) {
            ivTarjetaNfc.setVisibility(View.VISIBLE);
            ivTarjetaNfc.animate()
                    .translationY(0)
                    .alpha(1.0f)
                    .setDuration(400)
                    .setInterpolator(new AccelerateDecelerateInterpolator())
                    .start();
        }
    }

    private void ocultarTarjeta() {
        if (ivTarjetaNfc != null && ivTarjetaNfc.getVisibility() == View.VISIBLE) {
            ivTarjetaNfc.animate()
                    .translationY(500)
                    .alpha(0.0f)
                    .setDuration(400)
                    .setInterpolator(new AccelerateDecelerateInterpolator())
                    .withEndAction(() -> ivTarjetaNfc.setVisibility(View.GONE))
                    .start();
        }
    }
}
