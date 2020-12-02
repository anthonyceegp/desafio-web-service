package com.example.marvelheroes.comiclist.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelheroes.R
import com.example.marvelheroes.api.utils.ImageUtil
import com.example.marvelheroes.comiclist.models.Comic
import com.squareup.picasso.Picasso

class ComicsAdapter(
    private val context: Context,
    private val comics: List<Comic>,
    private val onComicClickListener: OnComicClickListener
) : RecyclerView.Adapter<ComicsAdapter.ComicViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.comics_item, parent, false)

        return ComicViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val comic = comics[position]
        holder.bind(comic, position, onComicClickListener)
    }

    override fun getItemCount() = comics.size

    class ComicViewHolder(view: View, private val context: Context) : RecyclerView.ViewHolder(view) {
        private val comicCover: ImageView = view.findViewById(R.id.comic_cover)
        private val comicNumber: TextView = view.findViewById(R.id.comic_number)

        fun bind(comic: Comic, position: Int, onComicClickListener: OnComicClickListener) {
            val path = ImageUtil.getImagePath(comic.thumbnail, ImageUtil.PORTRAIT_UNCANNY)

            Picasso.get().load(path).into(comicCover)

            val intPart = comic.issueNumber.toInt()
            val decimalPart = comic.issueNumber - intPart

            val number = if (decimalPart < 0) {
                context.getString(
                    R.string.issue_number,
                    comic.issueNumber.toString()
                )
            } else {
                context.getString(
                    R.string.issue_number,
                    intPart.toString()
                )
            }

            comicNumber.text = number

            itemView.setOnClickListener {
                onComicClickListener.onComicClick(position)
            }
        }
    }
}