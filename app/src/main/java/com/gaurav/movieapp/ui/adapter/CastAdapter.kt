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
import com.gaurav.movieapp.model.CreditResult

class CastAdapter(private var mCredit: List<CreditResult>, private val context: Context) :
    RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cast, parent, false)

        return CastViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val movieCredit = mCredit[position]

        holder.tvName.text = movieCredit.name
        holder.tvCharacter.text = "as " + movieCredit.character
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500" + movieCredit.profile_path)
            .into(holder.ivImage)

    }

    override fun getItemCount(): Int {
        return mCredit.size
    }

    inner class CastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var ivImage: ImageView = itemView.findViewById(R.id.ivImage)
        internal var tvName: TextView = itemView.findViewById(R.id.tvName)
        internal var tvCharacter: TextView = itemView.findViewById(R.id.tvCharacter)
    }
}