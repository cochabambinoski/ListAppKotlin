package com.example.genremusicians.Models

import android.net.Uri

class AlbumModel(val nameAlbum:String = "None", val nameArtist:String="Artist", val urlPicture:String = "") {

    fun getImageArtist(): Uri {
        var urlImage: Uri = Uri.EMPTY
        return urlImage
    }
}

data class ImageDogModel(
    val message:String,
    val status:String
)

data class ImageSportModel(
    val strSport:String,
    val strSportThumb:String
)