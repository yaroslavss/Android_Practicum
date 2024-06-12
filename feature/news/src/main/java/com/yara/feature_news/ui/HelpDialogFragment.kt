package com.yara.feature_news.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.yara.core.utils.Action
import com.yara.core.utils.CallbackListener
import com.yara.feature_news.R
import com.yara.feature_news.databinding.FragmentHelpDialogBinding

enum class HelpAmount(val amount: Int) {
    ONE_HUNDRED(100),
    FIVE_HUNDREDS(500),
    ONE_THOUSAND(1000),
    TWO_THOUSANDS(2000),
}

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toggleButton.addOnButtonCheckedListener { _, checkedId, isChecked ->
            when {
                checkedId == R.id.button1 && isChecked -> {
                    binding.tietAmount.setText(HelpAmount.ONE_HUNDRED.amount.toString())
                }
                checkedId == R.id.button2 && isChecked -> {
                    binding.tietAmount.setText(HelpAmount.FIVE_HUNDREDS.amount.toString())
                }
                checkedId == R.id.button3 && isChecked -> {
                    binding.tietAmount.setText(HelpAmount.ONE_THOUSAND.amount.toString())
                }
                checkedId == R.id.button4 && isChecked -> {
                    binding.tietAmount.setText(HelpAmount.TWO_THOUSANDS.amount.toString())
                }
            }
        }

        binding.btnCancel.setOnClickListener() {
            dismiss()
        }

        binding.btnSend.setOnClickListener() {
            callbackListener.onDataReceived(Action.SendMoneyToHelp)
            dismiss()
        }
    }

    override fun getTheme(): Int {
        return R.style.DialogTheme
    }
}