package com.yara.feature_news.ui

import com.yara.feature_news.R

sealed class NavigationItem(var icon: Int, var title: Int) {

    data object Shirt : NavigationItem(R.drawable.ic_bottomnav_shirt, R.string.action_shirt)

    data object Hands : NavigationItem(R.drawable.ic_bottomnav_hands, R.string.action_hands)

    data object Tools : NavigationItem(R.drawable.ic_bottomnav_tools, R.string.action_tools)

    data object Coins : NavigationItem(R.drawable.ic_bottomnav_coins, R.string.action_coins)
}
