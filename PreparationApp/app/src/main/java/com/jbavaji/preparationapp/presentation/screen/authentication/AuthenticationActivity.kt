package com.jbavaji.preparationapp.presentation.screen.authentication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
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
        navController.addOnDestinationChangedListener(
            listener = { _: NavController, destination: NavDestination, _: Bundle? ->
                binding.toolbar.apply {
                    title = when (destination.id) {
                        R.id.signInFragment -> resources.getString(R.string.sign_in_span)
                        else -> ""
                    }
                }
            })
        binding.toolbar.apply {
            setNavigationOnClickListener {
                when (navController.currentDestination?.id) {
                    R.id.signInFragment ->
                        navController.navigate(R.id.action_signInFragment_to_signUpFragment)
                    R.id.forgotPasswordFragment ->
                        navController.navigate(R.id.action_forgotPasswordFragment_to_signInFragment)
                    R.id.passwordSentFragment ->
                        navController.navigate(R.id.action_passwordSentFragment_to_signInFragment)
                    R.id.signUpFragment ->
                        finish()
                }
            }
        }
    }
}