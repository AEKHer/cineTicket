package com.ebntr.cinebilet.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ebntr.cinebilet.R;
import com.ebntr.cinebilet.adapters.KoltukAdapter;
import com.ebntr.cinebilet.models.Koltuk;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class KoltukSecimActivity extends AppCompatActivity {
    private RecyclerView koltukRecyclerView;
    private List<Koltuk> koltukList;
    private KoltukAdapter adapter;
    private String filmAdi;
    private TextView toplamText;
    private Button onayButton;
    private static int biletFiyat = 150; //sadece bu sınıfta kullanılacak o yüzden static

    // Yeni eklenen:
    private LinearLayout satirBasliklari; // soldaki sayılar
    private LinearLayout sutunBasliklari; // üstteki harfler


    private static final int SATIR_SAYISI = 5; // 1..5
    private static final int SUTUN_SAYISI = 6; // A..F

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koltuk);

        String seans = getIntent().getStringExtra("seans");
        filmAdi = getIntent().getStringExtra("filmAdi");
        TextView secilenFilm = findViewById(R.id.secilenFilm);
        secilenFilm.setText(filmAdi + " - Koltuk Seçimi");

        koltukRecyclerView = findViewById(R.id.koltukRecyclerView);
        toplamText = findViewById(R.id.toplamText);
        onayButton = findViewById(R.id.onayButton);


        sutunBasliklari = findViewById(R.id.harfSutunu); // üstteki harfler
        satirBasliklari = findViewById(R.id.sayiSatiri); // soldaki sayılar



        harfleriEkle();
        sayilariEkle();

        koltukRecyclerView.setLayoutManager(new GridLayoutManager(this, SUTUN_SAYISI));
        koltukList = koltukYukle(filmAdi, seans);
        adapter = new KoltukAdapter(this, koltukList, () -> updateTotal());
        koltukRecyclerView.setAdapter(adapter);

        onayButton.setOnClickListener(v -> {
            ArrayList<String> secilenKoltuklar = new ArrayList<>();
            int toplam = 0;

            for (int i = 0; i < koltukList.size(); i++) {
                Koltuk koltuk = koltukList.get(i);
                if (koltuk.seciliMi()) {
                    koltuk.rezerveEt(true);
                    koltuk.seciliYap(false);

                    int satir = (i / SUTUN_SAYISI) + 1;
                    char sutun = (char) ('A' + (i % SUTUN_SAYISI));
                    String koltukNo = satir + String.valueOf(sutun);
                    secilenKoltuklar.add(koltukNo);

                    toplam += biletFiyat;
                }
            }

            koltukKaydet(filmAdi, seans, koltukList);
            adapter.notifyDataSetChanged();
            updateTotal();

            Intent intent = new Intent(KoltukSecimActivity.this, OdemeActivity.class);
            intent.putExtra("filmAdi", filmAdi);
            intent.putExtra("toplam", toplam);
            intent.putStringArrayListExtra("koltuklar", secilenKoltuklar);
            startActivity(intent);
        });

    }

    private void harfleriEkle() {
        for (int i = 0; i < SUTUN_SAYISI; i++) {
            TextView tv = new TextView(this);
            tv.setText(String.valueOf((char) ('A' + i)));
            tv.setWidth(dpToPx(50));
            tv.setHeight(dpToPx(40));
            tv.setGravity(Gravity.CENTER);
            sutunBasliklari.addView(tv);
        }
    }

    private void sayilariEkle() {
        for (int i = 1; i <= SATIR_SAYISI; i++) {
            TextView tv = new TextView(this);
            tv.setText(String.valueOf(i));
            tv.setWidth(dpToPx(40));
            tv.setHeight(dpToPx(42));
            tv.setGravity(Gravity.CENTER);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, dpToPx(8)); // altta 8dp boşluk
            tv.setLayoutParams(params);

            satirBasliklari.addView(tv);
        }
    }



    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    private void updateTotal() {
        int count = 0;
        for (Koltuk koltuk : koltukList) {
            if (koltuk.seciliMi()) count++;
        }
        int toplam = count * biletFiyat;
        toplamText.setText("Seçilen Koltuk: " + count + " | Toplam: " + toplam + "₺");
    }

    private List<Koltuk> koltukYukle(String filmAdi, String seans) {
        SharedPreferences prefs = getSharedPreferences("KoltukVerileri", MODE_PRIVATE);
        String key = "koltuklar_" + filmAdi + "_" + seans;
        String saved = prefs.getString(key, null);

        List<Koltuk> list = new ArrayList<>();

        if (saved != null) {
            try {
                JSONArray array = new JSONArray(saved);
                for (int i = 0; i < array.length(); i++) {
                    boolean rezerve = array.getBoolean(i);
                    list.add(new Koltuk(rezerve));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            for (int i = 0; i < 30; i++) {
                list.add(new Koltuk(Math.random() < 0.2)); // %20 dolu
            }
        }

        return list;
    }

    private void koltukKaydet(String filmAdi, String seans, List<Koltuk> koltuklar) {
        SharedPreferences prefs = getSharedPreferences("KoltukVerileri", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray array = new JSONArray();
        for (Koltuk koltuk : koltuklar) {
            array.put(koltuk.rezerveMi());
        }
        editor.putString("koltuklar_" + filmAdi + "_" + seans, array.toString());
        editor.apply();
    }
}
