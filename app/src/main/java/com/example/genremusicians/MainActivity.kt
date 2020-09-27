package com.example.genremusicians

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.genregenres.Models.DataManager
import com.example.genremusicians.Models.GenreInfo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var musicianPosition = POSITION_NOT_SET
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var adapterGenres = ArrayAdapter<GenreInfo>(
            this,
            android.R.layout.simple_spinner_item,
            DataManager.genres.values.toList()
        )

        adapterGenres.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinnerGenre.adapter = adapterGenres

        musicianPosition = savedInstanceState?.getInt(EXTRA_ALBUM_POSITION, POSITION_NOT_SET)?:intent.getIntExtra(EXTRA_ALBUM_POSITION, POSITION_NOT_SET)


        if(musicianPosition!= POSITION_NOT_SET){
            displayMusician()
        }

    }

    private fun displayMusician() {
        val musician = DataManager.musicians[musicianPosition]
        editTextName.setText(musician.name)
        editTextMusician.setText(musician.album)
        val genrePosition = DataManager.genres.values.indexOf(musician.genre)
        spinnerGenre.setSelection(genrePosition)
    }
}