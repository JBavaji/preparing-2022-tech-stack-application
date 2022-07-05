package com.jbavaji.preparationapp.presentation.screen.authentication.signin

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.jbavaji.preparationapp.R
import com.jbavaji.preparationapp.databinding.FragmentSignInBinding
import com.jbavaji.preparationapp.utils.SetStringAsSpannable

class SignInFragment : Fragment() {

    companion object {
        fun newInstance() = SignInFragment()
    }

    private lateinit var _binding: FragmentSignInBinding
    private val binding
        get() = _binding
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
        //initObserver()
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

    override fun onDestroyView() {
        super.onDestroyView()
    }

}