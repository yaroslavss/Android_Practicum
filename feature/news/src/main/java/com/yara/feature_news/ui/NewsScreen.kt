package com.yara.feature_news.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.yara.feature_news.R
import com.yara.feature_news.ui.theme.AndroidPracticumTheme
import com.yara.feature_news.ui.theme.AppBarText
import com.yara.feature_news.ui.theme.Leaf
import com.yara.feature_news.ui.theme.White

@Composable
fun NewsScreen(viewModel: NewsViewModel, buttonOnClick: () -> Unit) {
    val uiState by viewModel.uiState.collectAsState()

    NewsScreenContent(uiState, buttonOnClick)
}

@Composable
fun NewsScreenContent(uiState: NewsUiState, buttonOnClick: () -> Unit) {
    AndroidPracticumTheme {
        Column {
            CustomAppBar(buttonOnClick)

            LazyColumn(Modifier.fillMaxSize()) {
                items(uiState.events) {
                    EventItem(it, modifier = Modifier)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(buttonOnClick: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                stringResource(R.string.news_fragment_label),
                style = AppBarText,
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Leaf,
            titleContentColor = White,
        ),
        actions = {
            IconButton(onClick = buttonOnClick) {
                Icon(
                    painter = painterResource(R.drawable.filter),
                    contentDescription = null,
                    tint = White,
                )
            }
        },
    )
}

@Preview
@Composable
fun NewsScreenContentPreview() {
    NewsScreenContent(NewsUiState()) {}
}