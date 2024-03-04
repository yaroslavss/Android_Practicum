package com.yara.android_practicum.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yara.android_practicum.R
import com.yara.android_practicum.databinding.ActivityMainBinding
import com.yara.android_practicum.utils.Action
import com.yara.android_practicum.utils.CallbackListener

class MainActivity : AppCompatActivity(), CallbackListener {

    private lateinit var binding: ActivityMainBinding;

    private val activityResultLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        )
        { permissions ->
            // Handle Permission granted/rejected
            var permissionGranted = true
            permissions.entries.forEach {
                if (it.key in REQUIRED_PERMISSIONS && it.value == false)
                    permissionGranted = false
            }
            if (!permissionGranted) {
                Toast.makeText(
                    baseContext,
                    "Permission request denied",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set up navigation
        val bottomNavView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        bottomNavView.setupWithNavController(navController)

        // Request camera permissions
        if (!allPermissionsGranted()) {
            requestPermissions()
        }
    }

    // proceed actions from dialog to edit profile's photo
    override fun onDataReceived(action: Action) = when (action) {
        is Action.MakeCameraPhoto -> {
            takePhoto()
        }

        is Action.DeleteProfilePhotoAction -> {
            val photo: ImageView = findViewById(R.id.acivPhoto)
            photo.setImageResource(R.drawable.image_user)
        }
    }

    // This method will help to retrieve the image
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Match the request 'pic id with requestCode
        if (requestCode == 123) {
            // BitMap is data structure of image file which store the image in memory
            val photo = data!!.extras!!["data"] as Bitmap?
            // Set the image in imageview for display
            val photoImageView: ImageView = findViewById(R.id.acivPhoto)
            photoImageView.setImageBitmap(photo)
        }
    }
    private fun takePhoto() {
        // Create the camera_intent ACTION_IMAGE_CAPTURE it will open the camera for capture the image
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // Start the activity with camera_intent, and request pic id
        startActivityForResult(cameraIntent, 123)
    }

    private fun requestPermissions() {
        activityResultLauncher.launch(REQUIRED_PERMISSIONS)
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it
        ) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        private val REQUIRED_PERMISSIONS =
            mutableListOf(
                Manifest.permission.CAMERA,
            ).toTypedArray()
    }
}