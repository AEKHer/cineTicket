package com.ebntr.cinebilet.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ebntr.cinebilet.R;
import com.ebntr.cinebilet.models.Koltuk;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;

public class KoltukAdapter extends RecyclerView.Adapter<KoltukAdapter.KoltukViewHolder> {
    private Context aciklama;
    private List<Koltuk> koltukList;

    private KoltukSecimi listener; //Koltuk seçimi değiştiğinde bildirimi yapacak olan listener

    public interface KoltukSecimi {
        void onSeatSelectionChanged(); // koltuksecimi değiştiğinde dışarıya bilgi sağlar
    }


    public KoltukAdapter(Context aciklama, List<Koltuk> koltukList, KoltukSecimi listener) {
        this.aciklama = aciklama;
        this.koltukList = koltukList;
        this.listener = listener;
    }



    public KoltukViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //Yeni koltuk oluşturulunca çağırılan metot
        View view = LayoutInflater.from(aciklama).inflate(R.layout.koltuk_item, parent, false); //koltuk_item görünüm dosyası yükleniyor
        return new KoltukViewHolder(view);
    }

    public void onBindViewHolder(KoltukViewHolder holder, int position) {
        Koltuk koltuk = koltukList.get(position);
        View koltukGorunumu = holder.koltukGorunumu;

        //Koltuğun durumuna göre rengimiz değişiyor
        if (koltuk.rezerveMi()) {
            koltukGorunumu.setBackgroundColor(0xFFFF0000); // kırmızı
        } else if (koltuk.seciliMi()) {
            koltukGorunumu.setBackgroundColor(0xFF0000FF); // mavi
        } else {
            koltukGorunumu.setBackgroundColor(0xFF00FF00); // yeşil
        }
        //Koltuğa tıklanınca "seçildi" bilgisi gidiyor
        koltukGorunumu.setOnClickListener(v -> {
            koltuk.secimiDegistir();
            notifyItemChanged(position);
            listener.onSeatSelectionChanged();
        });

    }

    public int getItemCount() {
        return koltukList.size();
    }

    public static class KoltukViewHolder extends RecyclerView.ViewHolder { //türetilmiş class oluşturuluyor
        View koltukGorunumu;

        public KoltukViewHolder(View itemView) {
            super(itemView);
            koltukGorunumu = itemView.findViewById(R.id.koltukGorunumu);
        }
    }
}
