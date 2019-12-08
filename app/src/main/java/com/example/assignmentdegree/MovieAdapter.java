package com.example.assignmentdegree;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.myViewHolder>{
    private Context context;
    private ArrayList<Movie> movieList;

    public MovieAdapter(Context context, ArrayList<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_movie_card_view,null);

        myViewHolder holder = new myViewHolder (view);

        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        final Movie movies = movieList.get(position);
        holder.moviePic.setImageResource(movies.getMovieImageId());
        holder.movieTitle.setText(movies.getMovie_name());
        holder.movieDescription.setText(movies.getDescription());

        holder.moviePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String movie_name,movie_description;
                int movie_id;

                Intent i = new Intent(context,Description.class);

                movie_name = movies.getMovie_name();
                movie_id = movies.getMovieImageId();
                movie_description = movies.getDescription();

                i.putExtra("movie_id",movie_id);
                i.putExtra("movie_name",movie_name);
                i.putExtra("movie_description",movie_description);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {
        ImageView moviePic;
        TextView movieTitle;
        TextView movieDescription;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            moviePic=(ImageView) itemView.findViewById(R.id.movieImage);
            movieTitle=(TextView) itemView.findViewById(R.id.movieTitle);
            movieDescription=(TextView)itemView.findViewById(R.id.movieDescription);
        }
    }
}
