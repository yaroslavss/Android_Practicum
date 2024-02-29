package com.yara.android_practicum.ui

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yara.android_practicum.R
import com.yara.android_practicum.databinding.ActivityMainBinding
import com.yara.android_practicum.utils.Action
import com.yara.android_practicum.utils.CallbackListener

class MainActivity : AppCompatActivity(), CallbackListener {

    private lateinit var binding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set up navigation
        val bottomNavView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        bottomNavView.setupWithNavController(navController)
    }

    // proceed actions from dialog to edit profile's photo
    override fun onDataReceived(action: Action) = when (action) {
        is Action.DeleteProfilePhotoAction -> {
            val photo: ImageView = findViewById(R.id.acivPhoto)
            photo.setImageResource(R.drawable.image_user)
        }
    }
}