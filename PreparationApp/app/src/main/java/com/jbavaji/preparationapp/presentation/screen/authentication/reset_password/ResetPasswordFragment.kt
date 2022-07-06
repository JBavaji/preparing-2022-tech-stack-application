package com.jbavaji.preparationapp.presentation.screen.authentication.reset_password

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.jbavaji.preparationapp.R
import com.jbavaji.preparationapp.presentation.screen.authentication.password_sent.PasswordSentFragmentArgs

class ResetPasswordFragment : Fragment() {

    companion object {
        fun newInstance() = ResetPasswordFragment()
    }

    private val viewModel: ResetPasswordViewModel by viewModels()

    val safeArgs: ResetPasswordFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reset_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        Toast.makeText(context, "parma from deep link ${safeArgs.email}", Toast.LENGTH_SHORT).show()

    }
}