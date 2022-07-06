package com.jbavaji.preparationapp.presentation.screen.authentication.password_sent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.jbavaji.preparationapp.databinding.FragmentPasswordSentBinding
import com.jbavaji.preparationapp.utils.OpenURI

class PasswordSentFragment : Fragment() {

    companion object {
        fun newInstance() = PasswordSentFragment()
    }

    private val viewModel: PasswordSentViewModel by viewModels()

    private lateinit var _binding: FragmentPasswordSentBinding
    private val binding
        get() = _binding
    private val content
        get() = binding.content

    val safeArgs: PasswordSentFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPasswordSentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val deepLinkUrl = "http://www.jbavaji.com/preparationapp/${safeArgs.email}"

        content.deeplinkUrlView.text = deepLinkUrl
        content.deeplinkUrlView.setOnClickListener {
            requireActivity().OpenURI(deepLinkUrl)
        }
    }
}