package com.ebntr.cinebilet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ebntr.cinebilet.R;
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
                new Film("6 Süper Kahraman", "Süper kahraman filmi", R.drawable.altisuper),
                new Film("Wall-E", "Robot temalı animasyon", R.drawable.walle),
                new Film("Bir Minecraft Filmi", "Oyun uyarlaması", R.drawable.minecraft),
                new Film("Harry Potter", "Büyücülük dünyası", R.drawable.harry),
                new Film("Rafadan Tayfa : Kapadokya", "Hayrinin maceraları", R.drawable.rafadan)
        };

        FilmAdapter adapter = new FilmAdapter(this, Arrays.asList(filmler));
        filmRecyclerView.setAdapter(adapter);

    }
}
