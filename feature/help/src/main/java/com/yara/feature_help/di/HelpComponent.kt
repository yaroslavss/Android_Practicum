package com.yara.feature_help.di

import com.yara.feature_help.ui.HelpFragment

interface HelpComponent {

    fun injectHelpFragment(helpFragment: HelpFragment)
}