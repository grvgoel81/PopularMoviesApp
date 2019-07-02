package com.gaurav.bookmyshowassignment.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.gaurav.movieapp.R
import com.gaurav.movieapp.model.TrailerResult

class MovieTrailerAdapter(
    private val mMovieTrailerList: List<TrailerResult>,
    private val context: Context
) :
    RecyclerView.Adapter<MovieTrailerAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_trailer, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movieTrailer = mMovieTrailerList[position]

        Glide.with(context)
            .load("http://img.youtube.com/vi/" + movieTrailer.key + "/0.jpg")
            .into(holder.img)

    }

    override fun getItemCount(): Int {
        return mMovieTrailerList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        internal var img: ImageView = itemView.findViewById(R.id.img_View_trailer)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val adapterPosition = adapterPosition
            val videoClick = mMovieTrailerList[adapterPosition]

        }
    }

}
