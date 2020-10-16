package com.example.genremusicians.viewmodel

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.example.genregenres.Models.DataManager
import com.example.genremusicians.Models.MusicianInfo
import com.example.genremusicians.R

class ListGenreMusiciansActivityViewModel:ViewModel() {
    var isNewlyCreated = true
    val navDrawerDisplaySelectionName = "com.jwhh.musiciankeeper.ItemsActivityViewModel.navDrawerDisplaySelection"
    var navDrawerDisplaySelection = R.id.nav_musicians


    fun saveState(outState: Bundle) {
        outState.putInt(navDrawerDisplaySelectionName, navDrawerDisplaySelection)
    }

    fun restoreState(savedInstanceState: Bundle) {
        navDrawerDisplaySelection = savedInstanceState.getInt(navDrawerDisplaySelectionName)
    }
}