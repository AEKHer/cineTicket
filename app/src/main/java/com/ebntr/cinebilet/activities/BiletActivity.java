package com.ebntr.cinebilet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ebntr.cinebilet.R;

import java.util.ArrayList;

public class BiletActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bilet);

        TextView filmBilet = findViewById(R.id.filmText);
        TextView koltuklarText = findViewById(R.id.koltukText);

        String filmAdi = getIntent().getStringExtra("filmAdi");
        ArrayList<String> koltuklar = getIntent().getStringArrayListExtra("koltuklar");

        filmBilet.setText("Film: " + filmAdi);
        koltuklarText.setText("Koltuklar: " + String.join(", ", koltuklar));

        Button mainButton = findViewById(R.id.mainButton);
        mainButton.setOnClickListener(v -> {
            Intent intent = new Intent(BiletActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });


    }
}
