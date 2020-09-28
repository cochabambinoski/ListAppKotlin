package com.example.genremusicians

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.genremusicians.Models.MusicianInfo

class MusicianRecyclerAdapter (private val context: Context, private val musicians:List<MusicianInfo>) : RecyclerView.Adapter<MusicianRecyclerAdapter.ViewHolder>() {

    private val  layoutInflater = LayoutInflater.from(context)
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textGenre = itemView.findViewById<TextView>(R.id.textViewTitle)
        val textMusician = itemView.findViewById<TextView>(R.id.textViewText)
        var musicianPosition = 0
        init {
            itemView.setOnClickListener{
                val intent = Intent(context,MainActivity::class.java)
                intent.putExtra(EXTRA_MUSICIAN_POSITION,musicianPosition)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_musician_list,parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return musicians.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var musician = musicians[position]
        holder.textGenre.text = musician.genre?.title
        holder.textMusician.text = musician.name
        holder.musicianPosition = position
    }
}