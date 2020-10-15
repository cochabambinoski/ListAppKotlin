package com.example.genremusicians.adapters

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.genremusicians.Models.AlbumModel
import com.example.genremusicians.Models.ImageDogModel
import com.example.genremusicians.R
import com.example.genremusicians.services.MusicApi
import com.example.genremusicians.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_musician_list.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import java.io.InputStream
import java.net.CacheResponse
import java.net.URL
import javax.security.auth.callback.Callback

class AlbumListAdapter (private val context: Context, private val albums: MutableList<AlbumModel>) :
    RecyclerView.Adapter<AlbumListAdapter.ViewHolder>(){

    private val layoutInflater = LayoutInflater.from(context)

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textAlbum = itemView?.findViewById<TextView?>(R.id.textViewAlbum)
        val textArtist = itemView?.findViewById<TextView>(R.id.textViewArtist)
        val btnGetImgAlbum = itemView?.findViewById<Button>(R.id.buttonImageAlbum)
        val imageViewAlbum = itemView?.findViewById<ImageView>(R.id.imageViewAlbum)
        var albumPosition = 0

        init {
            btnGetImgAlbum?.setOnClickListener{
                val service = ServiceBuilder.buildService(MusicApi::class.java)
                var callImage = service.getImageBreed(albums[albumPosition].nameAlbum)
                callImage.enqueue(object : retrofit2.Callback<ImageDogModel> {
                    override fun onResponse(call: Call<ImageDogModel>, response: Response<ImageDogModel>){
                        var imageModel = response.body()
                        if (imageViewAlbum != null && imageModel != null) {
                            CoroutineScope(IO).launch {
                                SetImageFromUrl(imageModel.message)
                            }
                        }
                    }

                    override fun onFailure(call: Call<ImageDogModel>, t:Throwable){
                        Toast.makeText(context,"Error on image",Toast.LENGTH_LONG).show()
                    }

                })
            }
        }
        private suspend fun SetImageFromUrl(url:String){
            val strem: InputStream = URL(url).openStream()
            val bmp = BitmapFactory.decodeStream(strem)
            withContext(Main){
                imageViewAlbum.setImageBitmap(bmp)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_album_list,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = albums[position]
        holder.textAlbum?.text = album.nameAlbum
        holder.textArtist?.text = album.nameArtist
        holder.albumPosition = position
        holder.imageViewAlbum.setImageResource(R.drawable.ic_menu_slideshow)
    }

    override fun getItemCount() = albums.size

}