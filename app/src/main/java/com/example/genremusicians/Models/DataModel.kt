package com.example.genremusicians.Models

data class GenreInfo(var idGenre: String, var title: String) {
    override fun toString(): String {
        return title
    }
}

data class MusicianInfo(
    var genre: GenreInfo? = null,
    var name: String? = null,
    var album: String? = null
) {}