package com.gaurav.bookmyshowassignment.ui.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.gaurav.movieapp.R
import com.gaurav.movieapp.model.MoviesResult
import com.gaurav.movieapp.ui.activity.DetailActivity

class MovieAdapter(private val moviesResult: List<MoviesResult>, private val context: Context) : RecyclerView.Adapter<MovieViewHolder>(),
    Filterable {

    private var movieSearchList: List<MoviesResult>? = null

    init {
        this.movieSearchList = moviesResult
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item_layout, parent, false)
        return MovieViewHolder(view)
    }


    override fun getItemCount(): Int = movieSearchList!!.size


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        var movieData = movieSearchList!![position]
        holder.updateView(movieData, context)
        holder.rlMovieLayout.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("movie", movieData)
            context.startActivity(intent)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    movieSearchList = moviesResult
                } else {
                    val filteredList = ArrayList<MoviesResult>()
                    for (movie in moviesResult) {
                        if (movie.title!!.toUpperCase().startsWith(charString.toUpperCase())) {
                            filteredList.add(movie)
                        }
                    }
                    movieSearchList = filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = movieSearchList
                return filterResults
            }
            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                movieSearchList = filterResults.values as ArrayList<MoviesResult>
                notifyDataSetChanged()
            }
        }
    }
}

class MovieViewHolder(itemVIew: View) : RecyclerView.ViewHolder(itemVIew) {

    var rlMovieLayout: RelativeLayout = itemVIew.findViewById(R.id.rlMovieLayout)
    private var ivImage = itemVIew.findViewById<ImageView>(R.id.ivImage)
    private var tvTitle = itemVIew.findViewById<TextView>(R.id.tvTitle)
    private var tvReleaseDate = itemVIew.findViewById<TextView>(R.id.tvReleaseDate)

    fun updateView(moviesResult: MoviesResult, context: Context) {
        tvTitle.text = moviesResult.original_title
        tvReleaseDate.text = context.resources.getString(R.string.release_date) + " " +moviesResult.release_date
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500" + moviesResult.poster_path)
            .into(ivImage)
    }

}