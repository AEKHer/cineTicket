package com.ebntr.cinebilet.activities;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager; //liste tipi görünüm için importlar
import androidx.recyclerview.widget.RecyclerView;
import com.ebntr.cinebilet.R; //projedeki diğer classları projeye dahil et
import com.ebntr.cinebilet.adapters.FilmAdapter;
import com.ebntr.cinebilet.models.Film;
import java.util.Arrays;
public class SeansActivity extends AppCompatActivity {

    String[] seanslar = {"10:30", "12:00", "14:30", "17:00", "19:45", "21:30"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seans);

        TextView filmBaslik = findViewById(R.id.textFilmBaslik);
        ImageView imagePoster = findViewById(R.id.imageFilm);
        ListView listSeans = findViewById(R.id.listSeans);

        String baslik = getIntent().getStringExtra("filmBaslik");
        int resimId = getIntent().getIntExtra("filmResimId", R.drawable.altisuper);

        filmBaslik.setText(baslik);
        imagePoster.setImageResource(resimId);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, seanslar);
        listSeans.setAdapter(adapter);

        listSeans.setOnItemClickListener((parent, view, position, id) -> {
            String secilenSeans = seanslar[position];
            Intent intent = new Intent(SeansActivity.this, KoltukSecimActivity.class);
            intent.putExtra("filmAdi", baslik);  // burayı değiştiriyoruz
            intent.putExtra("seans", secilenSeans);
            startActivity(intent);
        });


    }
}
