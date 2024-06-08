package com.yara.feature_news.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.yara.core.domain.model.Event
import com.yara.feature_news.R
import com.yara.feature_news.ui.theme.EventBottomPaneText
import com.yara.feature_news.ui.theme.EventTitleTextCentered
import com.yara.feature_news.ui.theme.TextStyle10
import com.yara.feature_news.ui.theme.TurtleGreen
import com.yara.feature_news.ui.theme.White

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun EventItem(event: Event, itemOnClick: (Event) -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = White,
        ),
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_radius)),
        modifier = Modifier
            .clickable { itemOnClick(event) }
            .fillMaxWidth()
            .padding(
                start = dimensionResource(R.dimen.spacing_xs),
                top = dimensionResource(R.dimen.spacing_xs),
                end = dimensionResource(R.dimen.spacing_xs),
            )
    ) {
        Column {
            Box(modifier = Modifier.padding(dimensionResource(R.dimen.spacing_xxs))) {
                GlideImage(
                    model = event.images.first(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    loading = placeholder(painterResource(R.drawable.news_img_1)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(R.dimen.event_image_height)),
                )

                Image(
                    painter = painterResource(R.drawable.fade),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(R.dimen.event_image_height))
                )
            }

            Text(
                event.title,
                style = EventTitleTextCentered,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = dimensionResource(R.dimen.event_title_horizontal_margin),
                        end = dimensionResource(R.dimen.event_title_horizontal_margin),
                    )
            )

            Image(
                painter = painterResource(R.drawable.decor),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(R.dimen.spacing_xs))
            )

            Text(
                event.description,
                style = TextStyle10,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = dimensionResource(R.dimen.event_title_horizontal_margin),
                        top = dimensionResource(R.dimen.spacing_xs),
                        end = dimensionResource(R.dimen.event_title_horizontal_margin),
                        bottom = dimensionResource(R.dimen.spacing_m)
                    )
            )

            EventBottomPane(event)
        }
    }
}

@Composable
fun EventBottomPane(event: Event) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = TurtleGreen)
            .padding(dimensionResource(R.dimen.spacing_xs))
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_calendar),
            contentDescription = null,
            tint = White,
        )
        Text(
            event.dateString,
            style = EventBottomPaneText,
        )
    }
}