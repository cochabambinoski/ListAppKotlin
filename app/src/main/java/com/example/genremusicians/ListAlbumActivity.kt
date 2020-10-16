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
import org.json.JSONArray
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
//        var albumService = ServiceBuilder.buildServiceScalar(MusicApi::class.java)
//        var call = albumService.getAlbumList()
        var albumService2 = ServiceBuilder.buildServiceScalar2(MusicApi::class.java)
        var call2 = albumService2.getSportsList()
        call2.enqueue(object : Callback<String>{
            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(applicationContext, "Failed error 123", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                var jsonObject = JSONObject(response.body())
          //      var jsonArray = JSONArray(response.body())
          //     var message = jsonObject.get("sports") as JSONObject
                var message2 = JSONArray(jsonObject.get("sports").toString())
                for (i in 0 until message2.length()){
                    albumList.add(AlbumModel(message2.getJSONObject(i).getString("strSport"),message2.getJSONObject(i).getString("idSport")))
                }
                var adapterDog = AlbumListAdapter(applicationContext,albumList)
                RecyclerViewAlbum.layoutManager = LinearLayoutManager(applicationContext)
                RecyclerViewAlbum.adapter = adapterDog
            }

        })
    }
}