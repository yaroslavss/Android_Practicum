package com.yara.feature_news.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.yara.core.utils.CallbackListener
import com.yara.feature_news.R
import com.yara.feature_news.databinding.FragmentHelpDialogBinding

class HelpDialogFragment(private val callbackListener: CallbackListener) : DialogFragment() {

    private var _binding: FragmentHelpDialogBinding? = null
    private val binding get() = _binding!!

    override fun onStart() {
        super.onStart()
        // set transparent background
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHelpDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getTheme(): Int {
        return R.style.DialogTheme
    }
}