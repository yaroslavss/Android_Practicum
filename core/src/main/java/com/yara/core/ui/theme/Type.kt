package com.yara.core.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.yara.core.R

val Officina = FontFamily(
    Font(R.font.officina_sans_extra_bold_scc, FontWeight.Normal)
)

val AppBarText = TextStyle(
    fontFamily = Officina,
    fontSize = 21.sp,
)

val EventTitleTextCentered = TextStyle(
    color = BlueGrey,
    fontFamily = Officina,
    fontWeight = FontWeight.Normal,
    fontSize = 21.sp,
    textAlign = TextAlign.Center,
    lineHeight = 23.sp,
)

val EventBottomPaneText = TextStyle(
    color = White,
    fontSize = 12.sp,
    textAlign = TextAlign.Center,
    lineHeight = 16.sp,
)

val TextStyle10 = TextStyle(
    color = Black70,
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    textAlign = TextAlign.Center,
    lineHeight = 20.sp,
)

val TextStyle11 = TextStyle(
    color = Black40,
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.Normal,
    fontSize = 11.sp,
    textAlign = TextAlign.Center,
    lineHeight = 13.sp,
)

// LoginInputLabel
val TextStyle13 = TextStyle(
    color = Black38,
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    lineHeight = 16.sp,
)

val HyperLink = TextStyle(
    color = Leaf,
    textDecoration = TextDecoration.Underline,
)