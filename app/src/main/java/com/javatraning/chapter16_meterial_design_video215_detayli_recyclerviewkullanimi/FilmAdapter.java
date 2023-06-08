package com.javatraning.chapter16_meterial_design_video215_detayli_recyclerviewkullanimi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.CardViewTasarimNesneleriniTutucu> {
    private Context mContext;
    private List<Filmler> filmlerList;

    public FilmAdapter(Context mContext, List<Filmler> filmlerList) {
        this.mContext = mContext;
        this.filmlerList = filmlerList;
    }



    //Görselleri tutanacak tanıyacak Cardview için bir sınıf
    public class CardViewTasarimNesneleriniTutucu extends RecyclerView.ViewHolder{
        public ImageView imageViewFilmResim;
        public TextView textViewFilmBaslik;
        public TextView textViewFilmFiyat;
        public Button buttonSepeteEkle;

        //Boş constructor
        public CardViewTasarimNesneleriniTutucu(@NonNull View itemView) {
            super(itemView);
            imageViewFilmResim = itemView.findViewById(R.id.imageViewFilmResim);
            textViewFilmBaslik = itemView.findViewById(R.id.textViewFilmBaslik);
            textViewFilmFiyat = itemView.findViewById(R.id.textViewFilmFiyat);
            buttonSepeteEkle = itemView.findViewById(R.id.buttonSepeteEkle);

        }
    }

    //Hızlı şekilde oluşturulması gereken meotdları implemente etti.
    //Buradaki metodlar card viewin üzerindeki nesnelerin özellikleriyle metodun boyutunu ve çalışma mantığını belirler.
    @NonNull
    @Override
    public CardViewTasarimNesneleriniTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_film_tasarim,parent,false);
        return new CardViewTasarimNesneleriniTutucu(itemView);
    }

    //Fiyat ve diğer bilgileri içine aktardığımız kısım. mesela position kaç eleman varsa o kadar çalışır.
    @Override
    public void onBindViewHolder(@NonNull CardViewTasarimNesneleriniTutucu holder, int position) {
        //Filmer sınıfından film nesnesi oluşturduk ve kaçıncı filmdeyse onun pozisyonunu verererk filmerlistten pozsiyon aldı.
        Filmler film = filmlerList.get(position);

        //Dinamik şekilde listedeki hangi filmdeysek onun bilgilerini card view için buraya aktaracağız.
        holder.textViewFilmBaslik.setText(film.getFilm_ad());
        holder.textViewFilmFiyat.setText(film.getFilm_fiyat()+"TL");

        //Dosyanın adıyla resme erişme. DİNAMİK ŞEKLİYLE.
        holder.imageViewFilmResim.setImageResource(mContext.getResources()
                .getIdentifier(film.getFilm_resim_ad(),"drawable",mContext.getPackageName()));

        holder.buttonSepeteEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,film.getFilm_ad()+"Sepete Eklendi",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {     //Kaç tane kart olacak demek.
        return filmlerList.size();  //filmlerListin boyutu kadar olacak.
    }

}
