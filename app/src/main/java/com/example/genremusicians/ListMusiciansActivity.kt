package com.example.genremusicians

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.genregenres.Models.DataManager
import kotlinx.android.synthetic.main.content_list_musicians.*

class ListMusiciansActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_musicians)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            val activityIntent = Intent(this,MainActivity::class.java)
            startActivity(activityIntent)
        }

        listMusician.adapter = ArrayAdapter(
            this,android.R.layout.simple_list_item_1, DataManager.musicians
        )

        listMusician.setOnItemClickListener{parent, view, position, id ->
            val activityIntent = Intent(this,MainActivity::class.java)
            activityIntent.putExtra(EXTRA_ALBUM_POSITION,position)
            startActivity(activityIntent)
        }
    }
}