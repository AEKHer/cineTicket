package com.ebntr.cinebilet.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
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
    private static int biletFiyat = 30; //sadece bu sınıfta kullanılacak o yüzden static


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koltuk);

        filmAdi = getIntent().getStringExtra("filmAdi");
        TextView secilenFilm = findViewById(R.id.secilenFilm);
        secilenFilm.setText(filmAdi + " - Koltuk Seçimi");

        koltukRecyclerView = findViewById(R.id.koltukRecyclerView);
        toplamText = findViewById(R.id.toplamText);
        onayButton = findViewById(R.id.onayButton);

        koltukRecyclerView.setLayoutManager(new GridLayoutManager(this, 6));
        koltukList = koltukYukle(filmAdi);
        adapter = new KoltukAdapter(this, koltukList, () -> updateTotal());
        koltukRecyclerView.setAdapter(adapter);

        onayButton.setOnClickListener(v -> {
            for (Koltuk koltuk : koltukList) {
                if (koltuk.seciliMi()) {
                    koltuk.rezerveEt(true);
                    koltuk.seciliYap(false);
                }
            }
            koltukKaydet(filmAdi, koltukList);
            adapter.notifyDataSetChanged();
            updateTotal();
        });

        updateTotal();
    }

    private void updateTotal() {
        int count = 0;
        for (Koltuk koltuk : koltukList) {
            if (koltuk.seciliMi()) count++;
        }
        int toplam = count * biletFiyat;
        toplamText.setText("Seçilen Koltuk: " + count + " | Toplam: " + toplam + "₺");
    }

    private List<Koltuk> koltukYukle(String filmAdi) {
        SharedPreferences prefs = getSharedPreferences("KoltukVerileri", MODE_PRIVATE);
        String key = "koltuklar_" + filmAdi;
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

    private void koltukKaydet(String filmAdi, List<Koltuk> koltuklar) {
        SharedPreferences prefs = getSharedPreferences("KoltukVerileri", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray array = new JSONArray();
        for (Koltuk koltuk : koltuklar) {
            array.put(koltuk.rezerveMi());
        }
        editor.putString("koltuklar_" + filmAdi, array.toString());
        editor.apply();
    }
}
