package com.igo.tracking

import android.os.Bundle
import android.view.Menu
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.igo.tracking.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    // Объявляем BottomNavigationView
    private lateinit var bottomNavigationView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

//        binding.appBarMain.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }


        // navController - window with navgraph mechanism. Only one for every menu
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        // 1st menu - drawer sidebar
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        // adding configuration: Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.bm_nav_home, R.id.bm_nav_biomass, R.id.bm_nav_trans, R.id.bm_nav_pyrolysis, R.id.bm_nav_sale, R.id.bm_nav_corc
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        // 2nd menu - Bottom Navigation
        //val bottomNavView: BottomNavigationView = findViewById(R.id.bm_nav_view)
        //bottomNavView.setupWithNavController(navController)
        bottomNavigationView = findViewById(R.id.bm_nav_view)
        bottomNavigationView.setupWithNavController(navController)

        // hide-reveal bottom_nav_menu depend on fragment
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.bm_nav_home ||
                destination.id == R.id.locFragment ||
                destination.id == R.id.biomassSelectFragment ||
                destination.id == R.id.transportSelectFragment2
            ) {
                bottomNavigationView.visibility = View.GONE
            } else {
                bottomNavigationView.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}