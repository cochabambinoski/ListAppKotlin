package com.example.genregenres.Models

import com.example.genremusicians.Models.GenreInfo
import com.example.genremusicians.Models.MusicianInfo

class DataManager {
    var genres = HashMap<String, GenreInfo>()
    var musicians = ArrayList<MusicianInfo>()
    init {
        //Initialize your variables
        initializeGenres()
        initializeMusicians()
    }

    private fun initializeMusicians() {
        var genre = genres["1"]
        var musician=MusicianInfo(genre,"Maroon 5","Songs About Jane")
        musicians.add(musician)

        musician=MusicianInfo(genre,"Nouvelle Vague","Riviera (Bande originale du film)")
        musicians.add(musician)

        musician=MusicianInfo(genre,"La La","Rosa")
        musicians.add(musician)

        genre = genres["2"]
        musician=MusicianInfo(genre,"Slayer","Show No Mercy")
        musicians.add(musician)

        genre = genres["3"]
        musician=MusicianInfo(genre,"Silvio Rodriguez","Silvio")
        musicians.add(musician)
    }

    private fun initializeGenres() {
        var genre=GenreInfo("1","SoftJazzRock")
        genres.set(genre.idGenre,genre)

        genre=GenreInfo("2","TrashMetal")
        genres.set(genre.idGenre,genre)

        genre=GenreInfo("3","Trova")
        genres.set(genre.idGenre,genre)

        genre=GenreInfo("4","Classic")
        genres.set(genre.idGenre,genre)
    }
}