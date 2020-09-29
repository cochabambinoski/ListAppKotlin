package com.example.genremusicians

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.genremusicians.Models.GenreInfo
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.nav_header_main.view.*

class GenreRecyclerAdapter(private val context: Context, private val genre: List<GenreInfo>) :
    RecyclerView.Adapter<GenreRecyclerAdapter.ViewHolder>() {
    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_genre_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = genre.size

    override fun onBindViewHolder(holder: GenreRecyclerAdapter.ViewHolder, position: Int) {
        val genre = genre[position]
        holder.textGenre?.text = genre.title
        holder.genrePosition = position
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val textGenre = itemView?.findViewById<TextView>(R.id.textGenre)
        var genrePosition = 0

        init {
            itemView?.setOnClickListener {
                Snackbar.make(it, genre[genrePosition]?.title, Snackbar.LENGTH_LONG).show()
            }
        }
    }
}