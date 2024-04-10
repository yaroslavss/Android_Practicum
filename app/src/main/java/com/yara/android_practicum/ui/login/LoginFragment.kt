package com.yara.android_practicum.ui.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jakewharton.rxbinding4.widget.textChanges
import com.yara.android_practicum.R
import com.yara.android_practicum.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.title = getString(R.string.login_fragment_label)
        val navController = findNavController()
        val btnLogin = binding.btnLogin
        btnLogin.isEnabled = false

        // edit text views
        var emailFull = false
        var passwordFull = false
        binding.tietEmail.textChanges().subscribe { charSeq ->
            emailFull = charSeq.length > 5
            btnLogin.isEnabled = emailFull && passwordFull
        }
        binding.tietPassword.textChanges().subscribe { charSeq ->
            passwordFull = charSeq.length > 5
            btnLogin.isEnabled = emailFull && passwordFull
        }

        // proceed login button click
        binding.btnLogin.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_helpFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}