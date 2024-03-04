package com.yara.android_practicum.utils

sealed class Action {

    object MakeCameraPhoto : Action()

    object DeleteProfilePhotoAction : Action()
}