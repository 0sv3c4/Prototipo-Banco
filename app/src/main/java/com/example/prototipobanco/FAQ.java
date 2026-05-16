package com.example.prototipobanco;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FAQ extends BaseActivityClientes {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        configuracionDrawerToolbar("");
        marchaAtras();

        setupFaqLogic();
    }

    private void setupFaqLogic() {
        for (int i = 1; i <= 7; i++) {
            final int index = i;
            int headerId = getResources().getIdentifier("header_q" + index, "id", getPackageName());
            int answerId = getResources().getIdentifier("tv_answer_q" + index, "id", getPackageName());
            int arrowId = getResources().getIdentifier("iv_arrow_q" + index, "id", getPackageName());

            View header = findViewById(headerId);
            TextView answer = findViewById(answerId);
            ImageView arrow = findViewById(arrowId);

            if (header != null && answer != null && arrow != null) {
                header.setOnClickListener(v -> {
                    if (answer.getVisibility() == View.GONE) {
                        answer.setVisibility(View.VISIBLE);
                        arrow.setRotation(180f);
                    } else {
                        answer.setVisibility(View.GONE);
                        arrow.setRotation(0f);
                    }
                });
            }
        }
    }
}