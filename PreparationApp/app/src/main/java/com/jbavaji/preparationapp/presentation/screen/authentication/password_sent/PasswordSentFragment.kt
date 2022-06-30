package com.jbavaji.preparationapp.presentation.screen.authentication.password_sent

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jbavaji.preparationapp.R

class PasswordSentFragment : Fragment() {

    companion object {
        fun newInstance() = PasswordSentFragment()
    }

    private lateinit var viewModel: PasswordSentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_password_sent, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PasswordSentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}