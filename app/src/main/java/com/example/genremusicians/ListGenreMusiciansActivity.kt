package com.example.genremusicians

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.genregenres.Models.DataManager
import kotlinx.android.synthetic.main.activity_list_genre_musicians.*
import kotlinx.android.synthetic.main.app_bar_list_musician.*
import kotlinx.android.synthetic.main.content_list_genre_musicians.*

class ListGenreMusiciansActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private val musicianLayoutManager by lazy {
        GridLayoutManager(this, resources.getInteger(R.integer.genre_grid_span))
    }

    private val musicianRecyclerAdapter by lazy {
        MusicianRecyclerAdapter(this, DataManager.musicians)
    }

    private val genreLayoutManager by lazy {
        GridLayoutManager(this, resources.getInteger(R.integer.genre_grid_span))
    }

    private val genreRecyclerAdapter by lazy {
        GenreRecyclerAdapter(this,DataManager.genres.values.toList())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_genre_musicians)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
            var intentMain = Intent(this, MainActivity::class.java)
            startActivity(intentMain)
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        displayMusicians()

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    private fun displayMusicians() {
        recyclerListMusicians.layoutManager = musicianLayoutManager
        recyclerListMusicians.adapter = musicianRecyclerAdapter
    }

    private fun displayGenre() {
        recyclerListMusicians.layoutManager = genreLayoutManager
        recyclerListMusicians.adapter = genreRecyclerAdapter
    }

    override fun onResume() {
        super.onResume()
        recyclerListMusicians.adapter?.notifyDataSetChanged()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_musicians -> {
                displayMusicians()
            }
            R.id.nav_genres -> {
                displayGenre()
            }
            R.id.nav_share -> {
                var intentMain = Intent(this, ListAlbumActivity::class.java)
                startActivity(intentMain)
            }
            R.id.nav_send -> {
                handSelection(R.string.share)
            }
            R.id.action_count_musician_genres -> {
                var message = getString(R.string.how_many_genre_musician,DataManager.musicians.size,DataManager.genres.size)
                Snackbar.make(recyclerListMusicians, message, Snackbar.LENGTH_LONG).show()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun handSelection(stringId: Int) {
        Snackbar.make(recyclerListMusicians, stringId, Snackbar.LENGTH_LONG).show()
    }
}