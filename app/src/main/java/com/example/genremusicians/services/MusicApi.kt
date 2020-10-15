package com.example.genremusicians.services


import com.example.genremusicians.Models.ImageDogModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MusicApi {

    @GET("breeds/list/all")
    fun getAlbumList(): Call<String>

    @GET("breed/{breed}/images/random")
    fun getImageBreed(@Path("breed") breed:String):Call<ImageDogModel>
}