package com.yara.android_practicum.utils

sealed class Action {

    object TakePhoto : Action()

    object MakeCameraPhoto : Action()

    object DeleteProfilePhoto : Action()
}