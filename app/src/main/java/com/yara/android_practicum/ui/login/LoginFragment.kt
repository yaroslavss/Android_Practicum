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
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val allDisposables = CompositeDisposable()

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
        //btnLogin.isEnabled = false

        // edit text views
        var emailFull = false
        var passwordFull = false

        val resultEmail = binding.tietEmail.textChanges()
        val resultPassword = binding.tietPassword.textChanges()

        /*val result = Observable.combineLatest(resultEmail, resultPassword) { str1, str2 ->
            str1.length > 5 && str2.length > 5
        }.subscribe { result ->
            btnLogin.isEnabled = result
        }

        allDisposables.addAll(result)*/

        // proceed login button click
        binding.btnLogin.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_helpFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        allDisposables.clear()
    }
}