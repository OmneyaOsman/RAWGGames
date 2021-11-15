package com.omni.rawggames

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.omni.rawggames.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val navController by lazy {
        findNavController(R.id.nav_host_fragment_content_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashFragment -> {
                    binding.appbarLayout.visibility = View.GONE
                }
                R.id.gamesListFragment -> {
                    binding.toolbar.navigationIcon = null
                    binding.appbarLayout.visibility = View.VISIBLE
                    binding.toolbar.menu.findItem(R.id.edit_genere).isVisible = true
                    binding.toolbar.menu.findItem(R.id.action_search).isVisible = true

                }
                R.id.generesFragment, R.id.searchFragment -> {
                    if (destination.id == R.id.generesFragment)
                        binding.toolbar.navigationIcon = null
                    binding.appbarLayout.visibility = View.VISIBLE
                    binding.toolbar.menu.findItem(R.id.edit_genere).isVisible = false
                    binding.toolbar.menu.findItem(R.id.action_search).isVisible = false
                }
                else -> {
                    binding.appbarLayout.visibility = View.VISIBLE
                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.games_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.edit_genere -> {
                try {
                    navController.navigate(R.id.action_gamesListFragment_to_generesFragment)
                } catch (e: IllegalArgumentException) {
                    Timber.d(e)
                }
                true
            }
            R.id.action_search -> {
                try {
                    navController.navigate(R.id.action_gamesListFragment_to_searchFragment)
                } catch (e: IllegalArgumentException) {
                    Timber.d(e)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}