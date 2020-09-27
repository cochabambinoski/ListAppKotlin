package com.example.genremusicians.Models

data class GenreInfo(var idGenre:String , var title:String){
    override fun toString(): String {
        return title
    }
}

data class MusicianInfo(var genre:GenreInfo? , var name:String, var album:String){}