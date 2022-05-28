package com.test.movie.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.test.movie.R;
import com.test.movie.databinding.ItemMovieBinding;
import com.test.movie.model.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private List<Movie> movies;
    private LayoutInflater layoutInflater;
    private Object handler;

    public MovieAdapter(List<Movie> movies, Object handler) {
        this.movies = movies;
        this.handler = handler;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());

        ItemMovieBinding myListBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_movie, parent, false);

        return new ViewHolder(myListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemMovieBinding.setEntry(movies.get(position));
        holder.setHandler(handler);
    }

    @Override
    public int getItemCount() {
        return movies != null ? movies.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemMovieBinding itemMovieBinding;

        public ViewHolder(@NonNull ItemMovieBinding itemMovieBinding) {
            super(itemMovieBinding.getRoot());

            this.itemMovieBinding = itemMovieBinding;
        }

        public void setHandler(Object handler) {
            ViewDataBinding dataBinding = DataBindingUtil.bind(itemView);
            dataBinding.setVariable(BR.listener, handler);
            dataBinding.executePendingBindings();
        }
    }
}
