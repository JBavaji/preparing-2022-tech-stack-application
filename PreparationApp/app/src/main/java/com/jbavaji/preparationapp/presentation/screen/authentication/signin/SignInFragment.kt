package com.jbavaji.preparationapp.presentation.screen.authentication.signin

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.jbavaji.preparationapp.R
import com.jbavaji.preparationapp.databinding.FragmentSignInBinding
import com.jbavaji.preparationapp.presentation.screen.authentication.signup.UserInputType
import com.jbavaji.preparationapp.utils.SetStringAsSpannable
import com.jbavaji.preparationapp.utils.afterTextChanged

class SignInFragment : Fragment() {

    companion object {
        fun newInstance() = SignInFragment()
    }

    private var _binding: FragmentSignInBinding? = null
    private val binding
        get() = _binding!!
    private val content
        get() = binding.content

    private val viewModel: SignInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initObserver()
    }

    private fun initObserver() {
        viewModel.userInputState.observe(viewLifecycleOwner, Observer {
            val inputState = it ?: return@Observer

            if (inputState.emailError != null) {
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

            if (inputState.passwordError != null) {
                content.inputPasswordEditText.apply {
                    background =
                        resources.getDrawable(
                            R.drawable.error_input_text_rounded_corner_background, null
                        )
                    setTextColor(resources.getColor(R.color.input_error, null))
                    setHintTextColor(resources.getColor(R.color.input_error, null))
                }
            } else {
                content.inputPasswordEditText.apply {
                    background =
                        resources.getDrawable(
                            R.drawable.normal_input_text_rounded_corner_background, null
                        )
                    setTextColor(resources.getColor(R.color.white, null))
                    setHintTextColor(resources.getColor(R.color.white, null))
                }
            }

            disableSignUp(inputState.isValid)
        })

        content.inputEmailEditText.afterTextChanged {
            viewModel.validateUserInput(it, UserInputType.EMAIL)
        }
        content.inputPasswordEditText.afterTextChanged {
            viewModel.validateUserInput(it, UserInputType.PASSWORD)
        }

        disableSignUp(isValid = false)

        content.signInButton.setOnClickListener {
            Toast.makeText(context, "signInButton", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initUI() {
        content.forgotPasswordLabelText.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.forgot_password_label_text)
                .navigate(R.id.action_signInFragment_to_forgotPasswordFragment)
        }

        val alreadySignInSpannable = SpannableString(
            resources.getString(R.string.do_not_have_account_sign_up)
        ).SetStringAsSpannable(
            stringSpan = resources.getString(R.string.sign_up_span),
            colorSpan = resources.getColor(R.color.spannable_text_color, null)
        ) {
            Navigation.findNavController(requireActivity(), content.doNotHaveAccountLabelText.id)
                .navigate(R.id.action_signInFragment_to_signUpFragment)
        }
        content.doNotHaveAccountLabelText.text = alreadySignInSpannable
        content.doNotHaveAccountLabelText.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun disableSignUp(isValid: Boolean) {
        // disable login button unless both username / password is valid
        content.signInButton.isClickable = isValid
        content.signInButton.isEnabled = isValid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding
    }

}