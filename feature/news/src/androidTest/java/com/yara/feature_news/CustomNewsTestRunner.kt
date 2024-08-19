package com.yara.feature_news

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class CustomNewsTestRunner: AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, NewsTestApp::class.java.name, context)
    }
}