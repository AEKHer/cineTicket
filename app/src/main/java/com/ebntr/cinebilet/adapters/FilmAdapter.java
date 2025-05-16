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
import com.ebntr.cinebilet.models.Film;
import com.ebntr.cinebilet.activities.KoltukSecimActivity;
import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {
    private Context aciklama;
    private List<Film> filmList;

    public FilmAdapter(Context aciklama, List<Film> filmList) {
        this.aciklama = aciklama;
        this.filmList = filmList;
    }

    public FilmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(aciklama).inflate(R.layout.film_item, parent, false); //film_item görünüm dosyasını eşler
        return new FilmViewHolder(view);
    }

    public void onBindViewHolder(FilmViewHolder holder, int position) {
        Film film = filmList.get(position);
        holder.baslik.setText(film.getBaslik());
        holder.resim.setImageResource(film.getResimId());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(aciklama, KoltukSecimActivity.class); //diğer classa geçilir
            intent.putExtra("filmAdi", film.getBaslik());//geçerken filmin adı da gönderilir
            aciklama.startActivity(intent);
        });
    }

    public int getItemCount() {
        return filmList.size();
    }

    public static class FilmViewHolder extends RecyclerView.ViewHolder {
        ImageView resim;
        TextView baslik;

        public FilmViewHolder(View itemView) {
            super(itemView); //itemViewden görünen itemleri miras alır
            resim = itemView.findViewById(R.id.filmImage);
            baslik = itemView.findViewById(R.id.filmTitle);
        }
    }
}
