package com.jbavaji.preparationapp.presentation.screen.authentication.forgot_password

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.jbavaji.preparationapp.R
import com.jbavaji.preparationapp.databinding.FragmentForgotPasswordBinding
import com.jbavaji.preparationapp.databinding.FragmentSignInBinding
import com.jbavaji.preparationapp.presentation.screen.authentication.signin.SignInViewModel

class ForgotPasswordFragment : Fragment() {

    companion object {
        fun newInstance() = ForgotPasswordFragment()
    }

    private lateinit var _binding: FragmentForgotPasswordBinding
    private val binding
        get() = _binding

    private val viewModel: SignInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentForgotPasswordBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}