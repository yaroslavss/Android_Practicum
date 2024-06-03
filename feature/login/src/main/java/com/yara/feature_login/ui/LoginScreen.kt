package com.yara.feature_login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.yara.feature_login.R
import com.yara.feature_login.ui.theme.AndroidPracticumTheme
import com.yara.feature_login.ui.theme.TextStyle10

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
        }
    }
}

@Composable
fun SocialNetworksIcons() {
    Row {
        Image(painter = painterResource(R.drawable.ic_vk), contentDescription = null)
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}