package com.yara.feature_login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.yara.feature_login.R
import com.yara.feature_login.ui.theme.AndroidPracticumTheme
import com.yara.feature_login.ui.theme.AppBarText
import com.yara.feature_login.ui.theme.Black12
import com.yara.feature_login.ui.theme.Black38
import com.yara.feature_login.ui.theme.HyperLink
import com.yara.feature_login.ui.theme.Leaf
import com.yara.feature_login.ui.theme.TextStyle10
import com.yara.feature_login.ui.theme.TextStyle13
import com.yara.feature_login.ui.theme.White

@Composable
fun LoginScreen(buttonOnClick: () -> Unit) {
    AndroidPracticumTheme {
        Column {
            CustomAppBar()

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

            LoginInputs(buttonOnClick)

            SigninLinks()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                stringResource(R.string.login_fragment_label),
                style = AppBarText,
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Leaf,
            titleContentColor = White,
        ),
    )
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
fun LoginInputs(buttonOnClick: () -> Unit) {
    Text(
        stringResource(R.string.email_label),
        style = TextStyle13,
        modifier = Modifier
            .padding(
                start = dimensionResource(R.dimen.default_margin),
                top = dimensionResource(R.dimen.default_margin),
            )
    )

    var email by rememberSaveable { mutableStateOf("") }
    TextField(
        value = email,
        onValueChange = { email = it },
        placeholder = { Text(stringResource(R.string.til_email_hint)) },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Black38,
            unfocusedTextColor = Black38,
            focusedContainerColor = White,
            unfocusedContainerColor = White,
            disabledContainerColor = White,
            focusedIndicatorColor = Black12,
            unfocusedIndicatorColor = Black12,
            focusedPlaceholderColor = Black38,
            unfocusedPlaceholderColor = Black38,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = dimensionResource(R.dimen.default_margin),
                end = dimensionResource(R.dimen.default_margin),
            )
    )

    Text(
        stringResource(R.string.password_label),
        style = TextStyle13,
        modifier = Modifier
            .padding(
                start = dimensionResource(R.dimen.default_margin),
                top = dimensionResource(R.dimen.default_margin),
            )
    )

    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    TextField(
        value = password,
        onValueChange = { password = it },
        placeholder = { Text(stringResource(R.string.til_password_hint)) },
        singleLine = true,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (passwordVisible)
                painterResource(R.drawable.ic_open)
            else painterResource(R.drawable.ic_close)

            // Toggle button to hide or display password
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(painter = image, "")
            }
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Black38,
            unfocusedTextColor = Black38,
            focusedContainerColor = White,
            unfocusedContainerColor = White,
            disabledContainerColor = White,
            focusedIndicatorColor = Black12,
            unfocusedIndicatorColor = Black12,
            focusedPlaceholderColor = Black38,
            unfocusedPlaceholderColor = Black38,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = dimensionResource(R.dimen.default_margin),
                end = dimensionResource(R.dimen.default_margin),
            )
    )

    Button(
        enabled = email.length > 5 && password.length > 5,
        onClick = buttonOnClick,
        shape = RoundedCornerShape(dimensionResource(R.dimen.btn_radius)),
        colors = ButtonDefaults.buttonColors(
            containerColor = Leaf
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = dimensionResource(R.dimen.btn_elevation)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = dimensionResource(R.dimen.default_margin),
                top = dimensionResource(R.dimen.default_margin),
                end = dimensionResource(R.dimen.default_margin),
            )
    ) {
        Text(stringResource(R.string.btn_login_label))
    }
}

@Composable
fun SigninLinks() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = dimensionResource(R.dimen.default_margin),
                top = dimensionResource(R.dimen.default_margin),
                end = dimensionResource(R.dimen.default_margin),
            )
    ) {
        Text(
            stringResource(R.string.forget_password_label),
            style = HyperLink,
        )
        Text(
            stringResource(R.string.register_label),
            style = HyperLink,
        )
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen {}
}