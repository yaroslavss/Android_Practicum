package com.yara.feature_news.ui

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.jakewharton.rxbinding4.widget.textChanges
import com.yara.core.utils.Action
import com.yara.core.utils.CallbackListener
import com.yara.feature_news.R
import com.yara.feature_news.databinding.FragmentHelpDialogBinding
import io.reactivex.rxjava3.disposables.CompositeDisposable

enum class HelpAmount(val amount: Int) {
    ONE_HUNDRED(100),
    FIVE_HUNDREDS(500),
    ONE_THOUSAND(1000),
    TWO_THOUSANDS(2000),
}

class HelpDialogFragment : DialogFragment() {

    private var _binding: FragmentHelpDialogBinding? = null
    private val binding get() = _binding!!

    private lateinit var callbackListener: CallbackListener

    private var eventId: Int? = null
    private var eventTitle: String? = null

    private val allDisposables = CompositeDisposable()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            callbackListener = context as CallbackListener
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface. Throw exception.
            throw ClassCastException(
                (context.toString() +
                        " must implement CallbackListener")
            )
        }
    }

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

        // proceed edit text change
        val btnSend = binding.btnSend
        btnSend.isEnabled = false

        val result = binding.tietAmount.textChanges()
            .subscribe { charSeq ->
                if (charSeq.isNotEmpty()) {
                    try {
                        val amount = charSeq.toString().toInt()
                        btnSend.isEnabled = amount in MIN_AMOUNT_VALUE..MAX_AMOUNT_VALUE
                    } catch (_: NumberFormatException) {
                    }
                }
            }

        allDisposables.add(result)

        // proceed buttons click
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
            callbackListener.onDataReceived(
                Action.SendMoneyToHelp(
                    eventId!!,
                    eventTitle!!,
                    binding.tietAmount.text.toString().toInt(),
                )
            )
            dismiss()
        }
    }

    override fun getTheme(): Int {
        return R.style.DialogTheme
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        eventId = arguments?.getInt(EVENT_ID_KEY)
        eventTitle = arguments?.getString(EVENT_TITLE_KEY)

        return super.onCreateDialog(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        allDisposables.clear()
    }

    companion object {
        const val MIN_AMOUNT_VALUE = 1
        const val MAX_AMOUNT_VALUE = 9_999_999

        const val EVENT_ID_KEY = "eventId"
        const val EVENT_TITLE_KEY = "eventTitle"

        fun newInstance(eventId: Int, eventTitle: String): HelpDialogFragment {
            val dialog = HelpDialogFragment()

            val bundle = Bundle()
            bundle.putInt(EVENT_ID_KEY, eventId)
            bundle.putString(EVENT_TITLE_KEY, eventTitle)
            dialog.arguments = bundle

            return dialog
        }
    }
}