package com.yara.android_practicum.ui.profile

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.yara.android_practicum.R

//class FullScreenDialogExample(private val callbackListener: CallbackListener) : DialogFragment() {
class FullScreenDialogExample : DialogFragment() {

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(
            ColorDrawable(
                resources.getColor(
                    R.color.black_20,
                    null
                )
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = false
        return inflater.inflate(R.layout.dialog_edit_profile_photo, container, false)
    }

    override fun getTheme(): Int {
        return R.style.DialogTheme
    }
}