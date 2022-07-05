package com.jbavaji.preparationapp.presentation.screen.authentication.forgot_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.jbavaji.preparationapp.R
import com.jbavaji.preparationapp.databinding.FragmentForgotPasswordBinding
import com.jbavaji.preparationapp.utils.afterTextChanged

class ForgotPasswordFragment : Fragment() {

    companion object {
        fun newInstance() = ForgotPasswordFragment()
    }

    private lateinit var _binding: FragmentForgotPasswordBinding
    private val binding
        get() = _binding

    private val content
        get() = binding.content

    private val viewModel: ForgotPasswordViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentForgotPasswordBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
    }

    private fun initObserver() {
        viewModel.userInputState.observe(viewLifecycleOwner, Observer {
            val inputState = it ?: return@Observer

            disableContinue(inputState.isValid)

            if (inputState.error != null) {
                content.inputEmailEditText.apply {
                    background =
                        resources.getDrawable(
                            R.drawable.error_input_text_rounded_corner_background, null
                        )
                    setTextColor(resources.getColor(R.color.input_error, null))
                    setHintTextColor(resources.getColor(R.color.input_error, null))
                }

            } else {
                content.inputEmailEditText.apply {
                    background =
                        resources.getDrawable(
                            R.drawable.normal_input_text_rounded_corner_background, null
                        )
                    setTextColor(resources.getColor(R.color.white, null))
                    setHintTextColor(resources.getColor(R.color.white, null))
                }
            }
        })

        content.inputEmailEditText.afterTextChanged {
            viewModel.validateUserInput(it)
        }

        disableContinue(isValid = false)

        content.continueButton.setOnClickListener {
            Navigation
                .findNavController(it)
                .navigate(R.id.action_forgotPasswordFragment_to_resetPasswordFragment)
        }
    }

    private fun disableContinue(isValid: Boolean) {
        // disable login button unless both username / password is valid
        content.continueButton.isClickable = isValid
        content.continueButton.isEnabled = isValid
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}