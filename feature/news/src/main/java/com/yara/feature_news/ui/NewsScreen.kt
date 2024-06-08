package com.yara.feature_news.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.yara.core.domain.model.Event
import com.yara.feature_news.R
import com.yara.feature_news.ui.theme.AndroidPracticumTheme
import com.yara.feature_news.ui.theme.AppBarText
import com.yara.feature_news.ui.theme.Leaf
import com.yara.feature_news.ui.theme.TurtleGreen
import com.yara.feature_news.ui.theme.White

@Composable
fun NewsScreen(
    viewModel: NewsViewModel,
    buttonOnClick: () -> Unit,
    itemOnClick: (Event) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    NewsScreenContent(uiState, buttonOnClick, itemOnClick)
}

@Composable
fun NewsScreenContent(
    uiState: NewsUiState,
    buttonOnClick: () -> Unit,
    itemOnClick: (Event) -> Unit
) {
    AndroidPracticumTheme {
        Column {
            CustomAppBar(buttonOnClick)

            if (uiState.isLoading) {
                ProgressBar()
            } else {
                LazyColumn(Modifier.fillMaxSize()) {
                    items(uiState.events) {
                        EventItem(it, itemOnClick)
                    }
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

@Composable
fun ProgressBar() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize(),
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(dimensionResource(R.dimen.progress_bar_width)),
            color = TurtleGreen,
            trackColor = White,
        )
    }
}

@Preview
@Composable
fun NewsScreenContentPreview() {
    NewsScreenContent(NewsUiState(), {}, {})
}