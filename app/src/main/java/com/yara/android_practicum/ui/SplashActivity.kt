package com.yara.android_practicum.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.yara.android_practicum.R
import com.yara.android_practicum.di.DaggerAppComponent
import com.yara.feature_help.ui.HelpViewModel
import com.yara.feature_help.ui.HelpViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: HelpViewModelFactory

    private val viewModel by viewModels<HelpViewModel> {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val context = this

        DaggerAppComponent.factory()
            .create(this)
            .injectSplashActivity(this)

        lifecycleScope.launch {
            viewModel.initCategories()

            val homeIntent = Intent(context, MainActivity::class.java)

            Handler(Looper.getMainLooper()).post {
                startActivity(homeIntent)
                finish()
            }
        }
    }
}