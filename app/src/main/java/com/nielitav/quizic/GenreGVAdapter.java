package com.nielitav.quizic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GenreGVAdapter extends ArrayAdapter<GenreModel> {
    public GenreGVAdapter(@NonNull Context context, ArrayList<GenreModel> genreModelArrayList) {
        super(context, 0, genreModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       View listitemView=convertView;
       if(listitemView==null){
           listitemView= LayoutInflater.from(getContext()).inflate(R.layout.genre_item,parent,false);

       }
       GenreModel genreModel=getItem(position);
        TextView genreTv=listitemView.findViewById(R.id.gItemTV);
        ImageView genreIv=listitemView.findViewById(R.id.gItemIV);
        genreTv.setText(genreModel.getCourse_name());
        genreIv.setImageResource(genreModel.getImgid());

        return listitemView;

    }

}
