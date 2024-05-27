package com.yara.core.utils

import com.yara.core.utils.Action

interface CallbackListener {
    fun onDataReceived(action: Action)
}