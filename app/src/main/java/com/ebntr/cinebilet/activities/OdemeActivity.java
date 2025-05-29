package com.ebntr.cinebilet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ebntr.cinebilet.R;

import java.util.ArrayList;

public class OdemeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odeme);

        TextView filmText = findViewById(R.id.filmText);
        TextView toplamText = findViewById(R.id.toplamText);
        TextView koltukText = findViewById(R.id.koltukText);
        Button odemeYap = findViewById(R.id.odemeButton);

        String filmAdi = getIntent().getStringExtra("filmAdi");
        int toplam = getIntent().getIntExtra("toplam", 0);
        ArrayList<String> koltuklar = getIntent().getStringArrayListExtra("koltuklar");

        filmText.setText("Film: " + filmAdi);
        toplamText.setText("Toplam Tutar: " + toplam + "₺");
        koltukText.setText("Koltuklar: " + String.join(", ", koltuklar));

        odemeYap.setOnClickListener(v -> {
            Intent intent = new Intent(OdemeActivity.this, BiletActivity.class);
            intent.putExtra("filmAdi", filmAdi);
            intent.putStringArrayListExtra("koltuklar", koltuklar);
            startActivity(intent);
            finish(); // geri basınca ödeme ekranına dönmesin
        });
    }
}
