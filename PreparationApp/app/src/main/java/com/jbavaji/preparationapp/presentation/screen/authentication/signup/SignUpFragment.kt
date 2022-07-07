package com.jbavaji.preparationapp.presentation.screen.authentication.signup

import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.jbavaji.core.data.User
import com.jbavaji.core.repository.UserRoomRepository
import com.jbavaji.preparationapp.PreparationApplication
import com.jbavaji.preparationapp.R
import com.jbavaji.preparationapp.databinding.FragmentSignUpBinding
import com.jbavaji.preparationapp.framework.UserRoomDataSource
import com.jbavaji.preparationapp.utils.OpenURI
import com.jbavaji.preparationapp.utils.SetStringAsSpannable
import com.jbavaji.preparationapp.utils.afterTextChanged

class SignUpFragment : Fragment() {

    companion object {
        fun newInstance() = SignUpFragment()
    }

    private val viewModel: SignUpViewModel by viewModels {
        val repository = UserRoomRepository(
            UserRoomDataSource(
                (activity?.application as PreparationApplication)
            )
        )

        SignUpViewModel.SignUpViewModelFactory(repository)
    }

    private var currentUser = User("", "", 0L, 0L)

    private var _binding: FragmentSignUpBinding? = null
    private val binding
        get() = _binding!!
    private val content
        get() = binding.content

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initObserver()
    }

    private fun initUI() {
        val termsConditionSpannable = SpannableString(
            resources.getString(R.string.label_terms_of_services_amp_privacy_policy)
        ).SetStringAsSpannable(
            stringSpan = resources.getString(R.string.terms_of_services_span),
            colorSpan = resources.getColor(R.color.spannable_text_color, null)
        ) {
            activity?.OpenURI("https://generator.lorem-ipsum.info/terms-and-conditions")
        }
        content.agreeTermsConditionsLabelText.text = termsConditionSpannable
        content.agreeTermsConditionsLabelText.movementMethod = LinkMovementMethod.getInstance()

        val alreadySignInSpannable = SpannableString(
            resources.getString(R.string.already_have_account_sign_in)
        ).SetStringAsSpannable(
            stringSpan = resources.getString(R.string.sign_in_span),
            colorSpan = resources.getColor(R.color.spannable_text_color, null)
        ) {
            Navigation.findNavController(requireActivity(), content.alreadyHaveAccountLabelText.id)
                .navigate(R.id.action_signUpFragment_to_signInFragment)
        }
        content.alreadyHaveAccountLabelText.text = alreadySignInSpannable
        content.alreadyHaveAccountLabelText.movementMethod = LinkMovementMethod.getInstance()
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

        viewModel.userSaved.observe(viewLifecycleOwner) { saved ->
            if (saved) {
                Toast.makeText(context, "User Saved Successfully", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(requireActivity(), content.alreadyHaveAccountLabelText.id)
                    .popBackStack()
            } else {
                Toast.makeText(context, "User Not Saved", Toast.LENGTH_SHORT).show()
            }
        }

        content.signUpButton.setOnClickListener {
            val time: Long = System.currentTimeMillis()
            currentUser.email = content.inputEmailEditText.text.toString()
            currentUser.password = content.inputPasswordEditText.text.toString()
            currentUser.updateTime = time
            if (currentUser.id == 0L) {
                currentUser.creationTime = time
            }

            viewModel.signUpUser(currentUser)
        }
    }

    private fun disableSignUp(isValid: Boolean) {
        // disable login button unless both username / password is valid
        content.signUpButton.isClickable = isValid
        content.signUpButton.isEnabled = isValid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}