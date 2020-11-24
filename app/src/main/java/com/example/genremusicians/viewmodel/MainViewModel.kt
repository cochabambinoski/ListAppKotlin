package com.example.genremusicians.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.genregenres.Models.DataManager
import com.example.genremusicians.Models.GenreInfo
import com.example.genremusicians.Models.MusicianInfo

class MainViewModel:ViewModel() {
    private val _musicianInfo: MutableLiveData<MusicianInfo> = MutableLiveData<MusicianInfo>()
    private val _likes = MutableLiveData<Int>()

    init{
        _likes.value = 0
    }

    var likes:LiveData<Int> = _likes

    val popularity:LiveData<Popularity> = Transformations.map(_likes){
        when{
            it > 9 -> Popularity.STAR
            it > 4 -> Popularity.POPULAR
            else -> Popularity.NORMAL
        }
    }

    fun onLike(){
        if(_likes.value == null){
            _likes.value = 0
        } else {
            _likes.value = _likes.value as Int + 1
        }
    }



    val musicianInfo:LiveData<MusicianInfo>
    get()= _musicianInfo

    fun displayMusician(musicianPosition: Int) {
        _musicianInfo.value = DataManager.musicians[musicianPosition]
    }

    fun createMusician():Int{
        val musician = MusicianInfo()
        _musicianInfo.value = musician
        DataManager.musicians.add(musician)
        return DataManager.musicians.lastIndex
    }

    fun updateMusician(musicianPosition: Int, genre:GenreInfo){
        val musician = DataManager.musicians[musicianPosition]
        musician.name = _musicianInfo.value?.name
        musician.album = _musicianInfo.value?.album
        musician.genre = genre
    }
}

enum class Popularity{
    NORMAL,
    POPULAR,
    STAR
}