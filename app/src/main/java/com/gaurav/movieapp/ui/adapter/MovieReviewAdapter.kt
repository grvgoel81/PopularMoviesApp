package com.gaurav.bookmyshowassignment.ui.adapter

import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gaurav.movieapp.R
import com.gaurav.movieapp.model.ReviewResult

class MovieReviewAdapter(private var mMovieReviews: List<ReviewResult>) :
    RecyclerView.Adapter<MovieReviewAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_review, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movieReview = mMovieReviews[position]

        holder.txt_author.text = movieReview.author + " :"
        holder.txt_review.text = movieReview.content

    }

    override fun getItemCount(): Int {
        return mMovieReviews.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var txt_author: TextView = itemView.findViewById(R.id.txtAuthor)
        internal var txt_review: TextView

        init {
            txt_author.paintFlags = txt_author.paintFlags or Paint.UNDERLINE_TEXT_FLAG
            txt_review = itemView.findViewById(R.id.txtReview)
        }
    }
}