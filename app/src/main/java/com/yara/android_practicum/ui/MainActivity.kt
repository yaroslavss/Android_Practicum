package com.yara.android_practicum.ui

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yara.android_practicum.R
import com.yara.android_practicum.databinding.ActivityMainBinding
import com.yara.android_practicum.di.DaggerAppComponent
import com.yara.core.domain.worker.SendNotificationWorker
import com.yara.core.utils.Action
import com.yara.core.utils.CallbackListener
import com.yara.feature_news.ui.NewsViewModel
import com.yara.feature_news.ui.NewsViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.yara.feature_login.R as R_login
import com.yara.feature_news.R as R_news

class MainActivity : AppCompatActivity(), CallbackListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavView: BottomNavigationView

    @Inject
    lateinit var viewModelFactory: NewsViewModelFactory

    private val viewModel by viewModels<NewsViewModel>() {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        DaggerAppComponent.factory()
            .create(this)
            .injectMainActivity(this)

        // set up navigation
        bottomNavView = findViewById(R.id.bottom_navigation)
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        bottomNavView.setupWithNavController(navController)

        // hide and show bottom navigation for some fragments
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R_login.id.loginFragment -> hideBottomNav()
                R_news.id.eventDetailsFragment -> hideBottomNav()
                else -> showBottomNav()
            }
        }
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
            val photo: ImageView = findViewById(com.yara.feature_profile.R.id.acivPhoto)
            photo.setImageResource(R.drawable.image_user)
        }

        is Action.SendMoneyToHelp -> {
            createWorkRequest(action)
        }
    }

    // This method will help to retrieve the image
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Match the request 'pic id with requestCode
        val photoImageView: ImageView = findViewById(com.yara.feature_profile.R.id.acivPhoto)
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

    private fun createWorkRequest(action: Action.SendMoneyToHelp) {
        println("!!! send ${action.amount} to ${action.eventId}")

        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        val sendNotificationWorkRequest =
            OneTimeWorkRequestBuilder<SendNotificationWorker>()
                .setConstraints(constraints)
                .build()

        WorkManager.getInstance(this).enqueue(sendNotificationWorkRequest)
    }

    private fun hideBottomNav() {
        bottomNavView.visibility = View.GONE
    }

    private fun showBottomNav() {
        bottomNavView.visibility = View.VISIBLE

        // set bottom navigation badge
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    bottomNavView.getOrCreateBadge(R.id.newsGraph).apply {
                        number = state.unreadNewsQnt
                        isVisible = true
                    }
                }
            }
        }
    }

    companion object {

        const val INTENT_REQUEST_CODE = 123
        const val SELECT_PICTURE_CODE = 200
        const val SELECT_PICTURE_TITLE = "Выбрать фото"
        const val PROFILE_IMAGE_WIDTH = 1440
        const val PROFILE_IMAGE_HEIGHT = 800
    }
}