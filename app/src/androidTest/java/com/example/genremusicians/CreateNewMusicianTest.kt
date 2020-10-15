package com.example.genremusicians

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import com.example.genregenres.Models.DataManager
import com.example.genremusicians.Models.GenreInfo

@RunWith(AndroidJUnit4::class)
class CreateNewMusicianTest{
    @Rule @JvmField
    val musicianListActivity = ActivityTestRule(ListGenreMusiciansActivity::class.java)

    @Test
    fun createNewMusician(){
        val genre = DataManager.genres["1"]
        val musician = "test musician title"
        val musicianAlbum = "this album test"

        onView(withId(R.id.fab)).perform(click())
        onView(withId(R.id.spinnerGenre)).perform(click())
        onData(allOf(instanceOf(GenreInfo::class.java), equalTo(genre))).perform(click())

        onView(withId(R.id.editTextName)).perform(typeText(musician))
        onView(withId(R.id.editTextAlbum)).perform(typeText(musicianAlbum), closeSoftKeyboard())

        pressBack()

        val newMusician = DataManager.musicians.last()

        assertEquals(genre, newMusician.genre)
        assertEquals(musician, newMusician.name)
        assertEquals(musicianAlbum, newMusician.album)
    }
}