package com.yara.feature_news.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.yara.feature_news.R
import com.yara.core.ui.theme.Leaf
import com.yara.core.ui.theme.TextStyle11

val items = listOf(
    NavigationItem.Shirt,
    NavigationItem.Hands,
    NavigationItem.Tools,
    NavigationItem.Coins,
)

@Composable
fun NavigationBarEventDetails() {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen.event_bottom_bar_height))
            .padding(
                start = dimensionResource(R.dimen.default_margin),
                end = dimensionResource(R.dimen.default_margin),
            )
    ) {
        items.forEachIndexed { index, item ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = dimensionResource(R.dimen.spacing_10_dp))
            ) {
                Icon(
                    painter = painterResource(item.icon),
                    contentDescription = stringResource(item.title),
                    tint = Leaf,
                )
                Text(
                    stringResource(item.title),
                    style = TextStyle11,
                    modifier = Modifier
                        .padding(
                            top = dimensionResource(R.dimen.spacing_10_dp),
                            bottom = dimensionResource(R.dimen.spacing_xxs),
                        )
                )
            }
            if (index < items.size - 1) {
                Image(
                    painter = painterResource(R.drawable.separator_2),
                    contentDescription = null,
                )
            }
        }
    }
}

@Preview
@Composable
fun NavigationBarEventDetailsPreview() {
    NavigationBarEventDetails()
}