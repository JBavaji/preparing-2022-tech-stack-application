package com.jbavaji.preparationapp.presentation.screen.authentication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.jbavaji.preparationapp.R
import com.jbavaji.preparationapp.databinding.ActivityAuthenticationBinding

class AuthenticationActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityAuthenticationBinding

    private val binding
        get() = _binding

    private val viewModel: AuthenticationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_authentication) as NavHostFragment
        val navController = navHostFragment.navController

        binding.toolbar.apply {
            title = ""
            setNavigationOnClickListener {
                when (navController.currentDestination?.id) {
                    R.id.signInFragment ->
                        navController.navigate(R.id.action_signInFragment_to_signUpFragment)
                }
            }
        }
    }
}