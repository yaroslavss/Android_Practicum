package com.yara.feature_login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.yara.feature_login.R
import com.yara.feature_login.ui.theme.AndroidPracticumTheme
import com.yara.feature_login.ui.theme.TextStyle10
import com.yara.feature_login.ui.theme.TextStyle13

@Composable
fun LoginScreen() {
    AndroidPracticumTheme {
        Column {
            Text(
                stringResource(R.string.login_subtitle),
                style = TextStyle10,
                modifier = Modifier.padding(
                    start = dimensionResource(R.dimen.login_subtitle_horizontal_margin),
                    end = dimensionResource(R.dimen.login_subtitle_horizontal_margin),
                    top = dimensionResource(R.dimen.double_margin),
                )
            )
            SocialNetworksIcons()
            Text(
                stringResource(R.string.login_subtitle_1),
                style = TextStyle10,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = dimensionResource(R.dimen.login_subtitle_horizontal_margin),
                        end = dimensionResource(R.dimen.login_subtitle_horizontal_margin),
                    )
            )
            LoginInputs()
        }
    }
}

@Composable
fun SocialNetworksIcons() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = dimensionResource(R.dimen.icon_row_horizontal_margin),
                top = dimensionResource(R.dimen.default_margin),
                end = dimensionResource(R.dimen.icon_row_horizontal_margin),
                bottom = dimensionResource(R.dimen.double_margin)
            )
    ) {
        Image(painter = painterResource(R.drawable.ic_vk), contentDescription = null)
        Image(painter = painterResource(R.drawable.ic_fb), contentDescription = null)
        Image(painter = painterResource(R.drawable.ic_ok), contentDescription = null)
    }
}

@Composable
fun LoginInputs() {
    Text(
        stringResource(R.string.email_label),
        style = TextStyle13,
        modifier = Modifier
            .padding(
                start = dimensionResource(R.dimen.default_margin),
                top = dimensionResource(R.dimen.default_margin),
            )
    )
    TextField(
        value = stringResource(R.string.til_email_hint),
        onValueChange = { println("!!! $it") },
        singleLine = true,
        modifier = Modifier
            .padding(
                start = dimensionResource(R.dimen.default_margin),
            )
    )
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}