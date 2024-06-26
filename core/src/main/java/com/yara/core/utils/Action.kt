package com.yara.core.utils

sealed class Action {

    object TakePhoto : Action()

    object MakeCameraPhoto : Action()

    object DeleteProfilePhoto : Action()

    class SendMoneyToHelp(
        val eventId: Int,
        val eventTitle: String,
        val amount: Int,
    ) : Action()
}