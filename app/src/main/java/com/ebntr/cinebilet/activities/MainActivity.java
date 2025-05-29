package com.ebntr.cinebilet.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager; //liste tipi görünüm için importlar
import androidx.recyclerview.widget.RecyclerView;
import com.ebntr.cinebilet.R; //projedeki diğer classları projeye dahil et
import com.ebntr.cinebilet.adapters.FilmAdapter;
import com.ebntr.cinebilet.models.Film;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private RecyclerView filmRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        filmRecyclerView = findViewById(R.id.filmRecyclerView);
        filmRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Film[] filmler = new Film[]{
                new Film("6 Süper Kahraman","Süper kahraman filmi", R.drawable.altisuper),
                new Film("Wall-E","Süper kahraman filmi", R.drawable.walle),
                new Film("Bir Minecraft Filmi","Süper kahraman filmi", R.drawable.minecraft),
                new Film("Harry Potter","Süper kahraman filmi", R.drawable.harry) };

        FilmAdapter adapter = new FilmAdapter(this, Arrays.asList(filmler)); //diziler listeye çevrilir
        filmRecyclerView.setAdapter(adapter);
    }
}

