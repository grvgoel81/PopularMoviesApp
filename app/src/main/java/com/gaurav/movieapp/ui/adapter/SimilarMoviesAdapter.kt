package com.gaurav.bookmyshowassignment.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.gaurav.movieapp.R
import com.gaurav.movieapp.model.SimilarMovieResult

class SimilarMoviesAdapter(private var mSimilarMovies: List<SimilarMovieResult>, private val context: Context) :
    RecyclerView.Adapter<SimilarMoviesAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_similar_movies, parent, false)

        return MoviesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movieSimilar = mSimilarMovies[position]

        holder.tvName.text = movieSimilar.title
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500" + movieSimilar.poster_path)
            .into(holder.ivImage)

    }

    override fun getItemCount(): Int {
        return mSimilarMovies.size
    }

    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var ivImage: ImageView = itemView.findViewById(R.id.ivImage)
        internal var tvName: TextView = itemView.findViewById(R.id.tvName)

    }
}