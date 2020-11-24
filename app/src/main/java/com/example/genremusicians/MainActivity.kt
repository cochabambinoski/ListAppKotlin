package com.example.genremusicians

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.genregenres.Models.DataManager
import com.example.genremusicians.Models.GenreInfo
import com.example.genremusicians.Models.MusicianInfo
import com.example.genremusicians.databinding.ActivityMainBinding
import com.example.genremusicians.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val tag = this::class.simpleName
    private var musicianPosition = POSITION_NOT_SET
    val helper = MusicianLocationHelper(this,lifecycle)
    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        buttonSurvey.setOnClickListener {
            startActivity(Intent(this, SurveyActivity::class.java))
        }

        var adapterGenres = ArrayAdapter<GenreInfo>(
            this,
            android.R.layout.simple_spinner_item,
            DataManager.genres.values.toList()
        )

        adapterGenres.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinnerGenre.adapter = adapterGenres

        musicianPosition = savedInstanceState?.getInt(EXTRA_MUSICIAN_POSITION, POSITION_NOT_SET)
            ?: intent.getIntExtra(EXTRA_MUSICIAN_POSITION, POSITION_NOT_SET)


        if (musicianPosition != POSITION_NOT_SET) {
            displayMusician()
        } else {
            createMusician()
        }

    }

    private fun createMusician() {
        musicianPosition = viewModel.createMusician()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onPause() {
        super.onPause()
        saveMusician()
    }

    private fun saveMusician() {
        viewModel.updateMusician(musicianPosition, spinnerGenre.selectedItem as GenreInfo)
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(EXTRA_MUSICIAN_POSITION, musicianPosition)
    }

    private fun displayMusician() {
        val musician = DataManager.musicians[musicianPosition]
        viewModel.displayMusician(musicianPosition)
        val genrePosition = DataManager.genres.values.indexOf(musician.genre)
        spinnerGenre.setSelection(genrePosition)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_next -> {
                MoveNext()
            }
            R.id.action_location -> {
                helper.sendMessage(DataManager.musicians[musicianPosition])
            }
            else -> super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (musicianPosition >= DataManager.musicians.lastIndex) {
            var item = menu?.findItem(R.id.action_next)
            if (item != null) {
                item.icon = getDrawable(R.drawable.ic_baseline_block_24)
                item.isEnabled = false
            }
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun invalidateOptionsMenu() {
        super.invalidateOptionsMenu()
    }

    private fun MoveNext() {
        musicianPosition++
        displayMusician()
        invalidateOptionsMenu()
    }
}