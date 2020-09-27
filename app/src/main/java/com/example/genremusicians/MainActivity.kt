package com.example.genremusicians

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.genregenres.Models.DataManager
import com.example.genremusicians.Models.GenreInfo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var adapterGenres = ArrayAdapter<GenreInfo>(
            this,
            android.R.layout.simple_spinner_item,
            DataManager().genres.values.toList()
        )

        adapterGenres.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinnerGenre.adapter = adapterGenres

    }
}