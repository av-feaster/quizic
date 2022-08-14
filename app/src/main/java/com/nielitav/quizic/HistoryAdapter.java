package com.nielitav.quizic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {
    Context context;
    ArrayList<QuizInfo>list;

    public HistoryAdapter(Context context, ArrayList<QuizInfo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.history_item,parent,false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       QuizInfo myData=list.get(position);
        String genre="GENRE: "+ list.get(position).getGenre().toString().trim();
        String score="SCORE: "+ list.get(position).getScore().toString().trim();
        String date=list.get(position).getDate().toString().trim();

        holder.genreTv.setText(genre);
        holder.scoreTv.setText(score);
        holder.dateTv.setText(date);

        setImage(holder,position,myData);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView genreTv,scoreTv,dateTv;
        ImageView imageView;
        public  MyViewHolder(@NonNull View itemView){
            super(itemView);
            genreTv=itemView.findViewById(R.id.tvGenreHI);
            scoreTv=itemView.findViewById(R.id.tvScoreHI);
            dateTv=itemView.findViewById(R.id.tvDateHI);
            imageView=itemView.findViewById(R.id.ivHistoryItem);


        }
    }
    public void setImage(MyViewHolder holder,int position,QuizInfo myListData){
        if(myListData.getGenre().toString().toLowerCase(Locale.ROOT).trim().equals("sql")){
            holder.imageView.setImageResource(R.drawable.img_sql);
        }
        else  if(myListData.getGenre().toString().toLowerCase(Locale.ROOT).trim().equals("linux")){
            holder.imageView.setImageResource(R.drawable.linux);
        }
        else if(myListData.getGenre().toString().toLowerCase(Locale.ROOT).trim().equals("html")){
            holder.imageView.setImageResource(R.drawable.html_file);
        }
        else if(myListData.getGenre().toString().toLowerCase(Locale.ROOT).trim().equals("code")){
            holder.imageView.setImageResource(R.drawable.programming);
        }
        else if(myListData.getGenre().toString().toLowerCase(Locale.ROOT).trim().equals("random")){
            holder.imageView.setImageResource(R.drawable.question);
        }
        else if(myListData.getGenre().toString().toLowerCase(Locale.ROOT).trim().equals("docker")){
            holder.imageView.setImageResource(R.drawable.docker);
        }
        else if(myListData.getGenre().toString().toLowerCase(Locale.ROOT).trim().equals("javascript")){
            holder.imageView.setImageResource(R.drawable.js);
        }
    }

}
