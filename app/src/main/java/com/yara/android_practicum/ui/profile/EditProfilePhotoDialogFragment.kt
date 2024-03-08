package com.yara.android_practicum.ui.profile

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yara.android_practicum.R
import com.yara.android_practicum.databinding.DialogEditProfilePhotoBinding
import com.yara.android_practicum.utils.Action
import com.yara.android_practicum.utils.CallbackListener

class EditProfilePhotoDialogFragment(private val callbackListener: CallbackListener) :
    DialogFragment() {

    private var _binding: DialogEditProfilePhotoBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<ProfileViewModel>()

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

        // init adapter
        val adapter = MenuItemsRecyclerAdapter { menuItem ->
            // send back data to PARENT fragment using callback
            callbackListener.onDataReceived(menuItem.action)
            // now dismiss the fragment
            dismiss()
        }

        binding.apply {
            rvPhotoDialog.adapter = adapter
            rvPhotoDialog.layoutManager = LinearLayoutManager(activity)
        }

        viewModel.menuItemsListLiveData.observe(viewLifecycleOwner) { list ->
            adapter.addItems(list)
        }
    }
}