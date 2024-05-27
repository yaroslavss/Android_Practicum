package com.yara.feature_profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.yara.feature_profile.R
import com.yara.feature_profile.databinding.FragmentProfileBinding
import com.yara.core.utils.CallbackListener

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

        // show images from friends feed with Glide
        showImage(R.drawable.avatar_1, binding.ivFriendAvatar1)
        showImage(R.drawable.avatar_2, binding.ivFriendAvatar2)
        showImage(R.drawable.avatar_3, binding.ivFriendAvatar3)

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

    private fun showImage(imageToShow: Int, imageView: ImageView) {
        Glide.with(requireActivity())
            .load(imageToShow)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .centerCrop()
            .into(imageView)
    }
}