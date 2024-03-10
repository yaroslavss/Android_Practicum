package com.yara.android_practicum.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yara.android_practicum.R
import com.yara.android_practicum.databinding.FragmentProfileBinding
import com.yara.android_practicum.utils.CallbackListener

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.title = getString(R.string.profile_fragment_label)

        binding.toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.action_edit) {
                val dialogFragment =
                    EditProfilePhotoDialogFragment(requireActivity() as CallbackListener)
                dialogFragment.show(requireActivity().supportFragmentManager, "EDIT_PROFILE_PHOTO")
            }
            true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}