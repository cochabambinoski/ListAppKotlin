package com.example.genremusicians.Models

import com.example.genregenres.Models.DataManager
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DataManagerTest{

    @Before
    fun setUp(){
        DataManager.musicians.clear()
        DataManager.initializeGenres()
    }

    @Test
    fun addMusician(){
        val genre = DataManager.genres.get("1")!!
        val musicianTilte = "This is the title"
        val musicianText = "This is the text"

        val index = DataManager.addMusician(genre,musicianTilte,musicianText)
        val musician = DataManager.musicians[index]

        assertEquals(genre,musician.genre)
        assertEquals(musicianTilte,musician.name)
        assertEquals(musicianText,musician.album)
    }

    @Test
    fun findSimilarMusicians(){
        val genre = DataManager.genres.get("1")!!
        val musicianName = "This is the name"
        val musicianAlbum1 = "This my first album test"
        val musicianAlbum2 = "This is my second album test"

        val index1 = DataManager.addMusician(genre,musicianName,musicianAlbum1)
        val index2 = DataManager.addMusician(genre,musicianName,musicianAlbum2)

        val musician1 = DataManager.findMusician(genre,musicianName,musicianAlbum1)
        val foundIndex1 = DataManager.musicians.indexOf(musician1)
        assertEquals(index1,foundIndex1)

        val musician2 = DataManager.findMusician(genre,musicianName,musicianAlbum2)
        val foundIndex2 = DataManager.musicians.indexOf(musician2)
        assertEquals(index2,foundIndex2)

    }
}