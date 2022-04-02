package com.vermaji.noteshare.mainUI

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.firebase.database.FirebaseDatabase
import com.vermaji.noteshare.R
import com.vermaji.noteshare.loginService.LoginActivity
import com.vermaji.noteshare.database.NoteDatabase
import com.vermaji.noteshare.databinding.ActivityMainBinding
import com.vermaji.noteshare.mainUI.viewModels.NoteViewModel
import com.vermaji.noteshare.mainUI.viewModels.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        firebaseDatabase = FirebaseDatabase.getInstance()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.bottomNavigationView.setOnItemSelectedListener {
            val navController:NavController = findNavController(R.id.homeScreenFragment)
            when (it.itemId) {
                R.id.menu_home_navigation -> {
                    navController.navigate(R.id.homeFragment)
                }
                R.id.menu_search_navigation -> {

                }
                R.id.menu_upload_navigation -> {
                    findNavController(R.id.homeScreenFragment).navigate(R.id.upload)
                }
                R.id.menu_profile_navigation ->{
                    findNavController(R.id.homeScreenFragment).navigate(R.id.profile)
                    val intent = Intent(this, LoginActivity::class.java)
                    //startActivity(intent)
                }
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_option_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val application = requireNotNull(this).application
        val dataSource =  NoteDatabase.getInstence(application).noteDatabaseDao
        val viewModelFactory = ViewModelFactory(dataSource,application)
        val noteViewModel = ViewModelProvider(this,viewModelFactory).get(NoteViewModel::class.java)
        noteViewModel.deleteAll()
        return true
    }
}