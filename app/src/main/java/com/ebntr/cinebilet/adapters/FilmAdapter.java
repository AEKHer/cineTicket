package com.ebntr.cinebilet.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.ebntr.cinebilet.R;
import com.ebntr.cinebilet.activities.SeansActivity;
import com.ebntr.cinebilet.models.Film;
import com.ebntr.cinebilet.activities.KoltukSecimActivity;
import java.util.List;
public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {
    private Context context;
    private List<Film> filmList;

    public FilmAdapter(Context context, List<Film> filmList) {
        this.context = context;
        this.filmList = filmList;
    }

    @Override
    public FilmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.film_item, parent, false);
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FilmViewHolder holder, int position) {
        Film film = filmList.get(position);
        holder.textBaslik.setText(film.getBaslik());
        holder.imagePoster.setImageResource(film.getResimId());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, SeansActivity.class);
            intent.putExtra("filmBaslik", film.getBaslik());
            intent.putExtra("filmResimId", film.getResimId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    public static class FilmViewHolder extends RecyclerView.ViewHolder {
        TextView textBaslik;
        ImageView imagePoster;

        public FilmViewHolder(View itemView) {
            super(itemView);
            textBaslik = itemView.findViewById(R.id.filmTitle);
            imagePoster = itemView.findViewById(R.id.filmImage);
        }
    }
}
