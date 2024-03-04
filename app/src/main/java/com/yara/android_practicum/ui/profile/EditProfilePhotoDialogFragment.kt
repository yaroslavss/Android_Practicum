package com.yara.android_practicum.ui.profile

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.yara.android_practicum.R
import com.yara.android_practicum.databinding.DialogEditProfilePhotoBinding
import com.yara.android_practicum.utils.Action
import com.yara.android_practicum.utils.CallbackListener

class EditProfilePhotoDialogFragment(private val callbackListener: CallbackListener) :
    DialogFragment() {

    private var _binding: DialogEditProfilePhotoBinding? = null
    private val binding get() = _binding!!

    override fun onStart() {
        super.onStart()
        // set transparent background
        dialog?.window?.setBackgroundDrawable(
            ColorDrawable(
                resources.getColor(
                    R.color.black_dialog_bg,
                    null
                )
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogEditProfilePhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getTheme(): Int {
        return R.style.DialogTheme
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivIconCamera.setOnClickListener {
            //send back data to PARENT fragment using callback
            callbackListener.onDataReceived(Action.MakeCameraPhoto)
            // Now dismiss the fragment
            dismiss()
        }

        binding.ivIconDelete.setOnClickListener {
            //send back data to PARENT fragment using callback
            callbackListener.onDataReceived(Action.DeleteProfilePhotoAction)
            // Now dismiss the fragment
            dismiss()
        }
    }
}