package com.example.prototipobanco;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.EditText;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;
import java.util.Locale;

public class Movimientos extends BaseActivityClientes {

    private EditText etFechaFiltro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimientos);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configuración de la Toolbar y Drawer heredados
        configuracionDrawerToolbar("Movimientos");
        marchaAtras();

        initViews();
        setupDatePicker();
    }

    private void initViews() {
        etFechaFiltro = findViewById(R.id.et_fecha_filtro);
    }

    private void setupDatePicker() {
        // Detectar clic en el icono del calendario (drawableEnd)
        etFechaFiltro.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                // El índice 2 corresponde al drawableEnd (derecha)
                if (event.getRawX() >= (etFechaFiltro.getRight() - etFechaFiltro.getCompoundDrawables()[2].getBounds().width() - etFechaFiltro.getPaddingEnd())) {
                    showDatePickerDialog();
                    return true;
                }
            }
            return false;
        });
    }

    private void showDatePickerDialog() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year1, monthOfYear, dayOfMonth) -> {
                    // Formatear la fecha seleccionada: dd/mm/yyyy
                    String selectedDate = String.format(Locale.getDefault(), "%02d/%02d/%d", dayOfMonth, (monthOfYear + 1), year1);
                    etFechaFiltro.setText(selectedDate);
                }, year, month, day);

        // Establecer fecha mínima: 1 de Enero de 2022
        Calendar minDate = Calendar.getInstance();
        minDate.set(2022, Calendar.JANUARY, 1);
        datePickerDialog.getDatePicker().setMinDate(minDate.getTimeInMillis());

        datePickerDialog.show();
    }
}