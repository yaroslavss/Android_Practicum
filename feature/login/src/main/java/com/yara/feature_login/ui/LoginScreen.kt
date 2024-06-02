package com.yara.feature_login.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.yara.feature_login.R

@Composable
fun LoginScreen() {
    MaterialTheme {
        Text(
            stringResource(R.string.login_subtitle),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(
                start = dimensionResource(R.dimen.login_subtitle_horizontal_margin),
                end = dimensionResource(R.dimen.login_subtitle_horizontal_margin),
                top = dimensionResource(R.dimen.double_margin),
            )
        )
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}