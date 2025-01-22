package com.example.egz;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    int[] imageIds = {R.id.kostka_1, R.id.kostka_2, R.id.kostka_3, R.id.kostka_4, R.id.kostka_5};
    int[] values = {0, 0, 0, 0, 0};
    int wynik_calej_gry = 0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button rzuc_kostka_button = findViewById(R.id.button_rzut);
        rzuc_kostka_button.setOnClickListener(v -> {
            for(int i=0;i<5;i++){
                int random = (int)(Math.random()*6+1);
                ImageView image = findViewById(imageIds[i]);
                values[i] = random;
                int drawableId = getResources().getIdentifier("k" + random, "drawable", getPackageName());
                image.setImageResource(drawableId);
            }

            Map<Integer, Integer> countMap = new HashMap<>();
            for (int value : values) {
                countMap.put(value, countMap.getOrDefault(value, 0) + 1);
            }

            int suma = 0;
            for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
                if (entry.getValue() > 1) {
                    suma += entry.getKey() * entry.getValue();
                }
            }

            TextView textView = findViewById(R.id.wynik_losowania);
            textView.setText("Wynik losowania: " + suma);
            wynik_calej_gry += suma;
            TextView textView2 = findViewById(R.id.wynik_gry);
            textView2.setText("Wynik gry: " + wynik_calej_gry);

        });

        Button reset_button = findViewById(R.id.button_reset);
        reset_button.setOnClickListener(v -> {
            wynik_calej_gry = 0;
            TextView textView2 = findViewById(R.id.wynik_gry);
            textView2.setText("Wynik gry: " + wynik_calej_gry);

            TextView textView = findViewById(R.id.wynik_losowania);
            textView.setText("Wynik losowania: 0");

            for(int i=0;i<5;i++){
                ImageView image = findViewById(imageIds[i]);
                int drawableId = getResources().getIdentifier("question", "drawable", getPackageName());
                image.setImageResource(drawableId);
            }

        });


    }
}