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
import com.yara.android_practicum.ui.help.HelpViewModel
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val viewModel by viewModels<HelpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val context = this

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