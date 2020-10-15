package com.example.genremusicians

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.genremusicians.Models.AlbumModel
import com.example.genremusicians.adapters.AlbumListAdapter
import com.example.genremusicians.services.MusicApi
import com.example.genremusicians.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_list_album.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListAlbumActivity : AppCompatActivity() {
    var albumList:MutableList<AlbumModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_album)
        getListAlbum()
    }

    fun getListAlbum(){
        var albumService = ServiceBuilder.buildServiceScalar(MusicApi::class.java)
        var call = albumService.getAlbumList()
        call.enqueue(object : Callback<String>{
            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(applicationContext, "Failed error 123", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                var jsonObject = JSONObject(response.body())
                var message = jsonObject.get("message") as JSONObject
                for (nameBreed in message.keys()){
                    albumList.add(AlbumModel(nameBreed,"Dog"))
                }
                var adapterDog = AlbumListAdapter(applicationContext,albumList)
                RecyclerViewAlbum.layoutManager = LinearLayoutManager(applicationContext)
                RecyclerViewAlbum.adapter = adapterDog
            }

        })
    }
}