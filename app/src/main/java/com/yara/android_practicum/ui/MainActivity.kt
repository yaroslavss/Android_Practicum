package com.yara.android_practicum.ui

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
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
        is Action.TakePhoto -> {
            takePhoto()
        }

        is Action.MakeCameraPhoto -> {
            makePhoto()
        }

        is Action.DeleteProfilePhoto -> {
            val photo: ImageView = findViewById(R.id.acivPhoto)
            photo.setImageResource(R.drawable.image_user)
        }
    }

    // This method will help to retrieve the image
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Match the request 'pic id with requestCode
        val photoImageView: ImageView = findViewById(R.id.acivPhoto)
        if (resultCode != RESULT_CANCELED && requestCode == INTENT_REQUEST_CODE) {
            // BitMap is data structure of image file which store the image in memory
            val photo = data!!.extras!!["data"] as Bitmap?
            // Set the image in imageview for display
            photoImageView.setImageBitmap(
                Bitmap.createScaledBitmap(
                    photo!!,
                    PROFILE_IMAGE_WIDTH,
                    PROFILE_IMAGE_HEIGHT,
                    false
                )
            )
        }
        if (resultCode != RESULT_CANCELED && requestCode == SELECT_PICTURE_CODE) {
            val selectedImageUri = data?.getData();
            selectedImageUri.let {
                // update the preview image in the layout
                photoImageView.setImageURI(it);
            }
        }
    }

    private fun makePhoto() {
        // Create the camera_intent ACTION_IMAGE_CAPTURE it will open the camera for capture the image
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // Start the activity with camera_intent, and request pic id
        startActivityForResult(cameraIntent, INTENT_REQUEST_CODE)
    }

    private fun takePhoto() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(
            Intent.createChooser(intent, SELECT_PICTURE_TITLE),
            SELECT_PICTURE_CODE
        )
    }

    companion object {

        const val INTENT_REQUEST_CODE = 123
        const val SELECT_PICTURE_CODE = 200
        const val SELECT_PICTURE_TITLE = "Выбрать фото"
        const val PROFILE_IMAGE_WIDTH = 1440
        const val PROFILE_IMAGE_HEIGHT = 800
    }
}