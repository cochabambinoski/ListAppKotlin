package com.example.genremusicians.services


import com.example.genremusicians.Models.ImageDogModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MusicApi {

    @GET("breeds/list/all")
    fun getAlbumList(): Call<String>

    @GET("v1/json/1/all_sports.php")
    fun getSportsList(): Call<String>

    @GET("breeds/image/random")
    fun getImageBreed():Call<ImageDogModel>
}