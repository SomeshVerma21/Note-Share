package com.vermaji.noteshare.mainUI.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import com.google.firebase.database.FirebaseDatabase
import com.vermaji.noteshare.R
import com.vermaji.noteshare.database.NoteDatabase
import com.vermaji.noteshare.databinding.ActivityMainBinding
import com.vermaji.noteshare.mainUI.home.searchNote.NoteSearchActivity
import com.vermaji.noteshare.mainUI.viewModels.NoteViewModel
import com.vermaji.noteshare.mainUI.viewModels.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController:NavController
    private var currentFragment:Int = R.id.menu_home_navigation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.homeScreenFragment)
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_home_navigation -> {
                    if (navController.currentDestination?.id!=R.id.homeFragment){
                        navController.popBackStack(R.id.homeFragment,true)
                        navController.navigate(R.id.homeFragment)
                    }
                }
                R.id.menu_search_navigation -> {
                    val intent = Intent(this , NoteSearchActivity::class.java)
                    startActivity(intent)
                }
                R.id.menu_upload_navigation -> {
                    if (navController.currentDestination?.id != R.id.upload) {
                        navController.popBackStack(R.id.upload,true)
                        navController.navigate(R.id.upload)
                    }
                }
                R.id.menu_profile_navigation -> {
                    if (navController.currentDestination?.id != R.id.profile) {
                        navController.popBackStack(R.id.profile,true)
                        navController.navigate(R.id.profile)
                    }
                }
            }
            true
        }
        navController.addOnDestinationChangedListener { _nav: NavController,
                                                        navDestination: NavDestination, bundle: Bundle? ->
            when (navDestination.id) {
                R.id.homeFragment -> {
                    binding.bottomNavigationView.menu.findItem(R.id.menu_home_navigation).isChecked =
                        true
                    currentFragment = R.id.menu_home_navigation
                }
                R.id.upload -> {
                    binding.bottomNavigationView.menu.findItem(R.id.menu_upload_navigation).isChecked =
                        true
                    currentFragment = R.id.menu_upload_navigation
                }
                R.id.profile -> {
                    binding.bottomNavigationView.menu.findItem(R.id.menu_profile_navigation).isChecked =
                        true
                    currentFragment = R.id.menu_profile_navigation
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.bottomNavigationView.selectedItemId = currentFragment
    }
}