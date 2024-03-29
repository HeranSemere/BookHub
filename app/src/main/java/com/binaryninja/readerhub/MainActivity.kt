package com.binaryninja.readerhub

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.binaryninja.readerhub.tools.Constant
import com.binaryninja.readerhub.tools.SharedPref
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SharedPref(this).setBool(Constant.PREF_FIRST_RUN,false)// disable wizard
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.bar_color)))

       /* val actionBar: ActionBar? = supportActionBar
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setLogo(R.drawable.search)
        supportActionBar?.setLogo(R.drawable.vert)
        supportActionBar?.setDisplayUseLogoEnabled(true)*/


        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_mybook,R.id.navigation_messages,R.id.navigation_profile))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu)
    }

//   override fun onOptionsItemSelected(item: MenuItem): Boolean {
//
//        if(onOptionsItemSelected(item)){
//            return true
//        }
//
//        val id = item.itemId
//
//        if (id == R.id.action_two) {
//            return true
//        }
//
//        return super.onOptionsItemSelected(item)
//    }


}