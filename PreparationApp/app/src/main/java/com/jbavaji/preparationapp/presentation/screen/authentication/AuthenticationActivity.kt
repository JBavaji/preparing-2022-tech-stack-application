package com.jbavaji.preparationapp.presentation.screen.authentication

import android.content.Intent
import android.net.Uri
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
    private lateinit var navController: NavController

    private val binding
        get() = _binding

    private val viewModel: AuthenticationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkIntentForDeepLink(intent)
        handleAppNavigation()
    }

    private fun checkIntentForDeepLink(intent: Intent) {
        val uri: Uri? = intent.data
        if (uri != null) {
            val parameters: List<String> = uri.pathSegments
            val email = parameters[parameters.size - 1]

            val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment_activity_authentication) as NavHostFragment

            navController = navHostFragment.navController
            val graphInflater = navController.navInflater
            val navGraph = graphInflater.inflate(R.navigation.deeplink_nav_graph)
            val bundle = Bundle()
            bundle.putString("email", email)
            navController.setGraph(navGraph, bundle)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        checkIntentForDeepLink(intent ?: Intent())
    }

    private fun handleAppNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_authentication) as NavHostFragment

        navController = navHostFragment.navController
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
                    R.id.signUpFragment ->
                        navController.navigate(R.id.action_signUpFragment_to_signInFragment)
                    R.id.forgotPasswordFragment ->
                        navController.navigate(R.id.action_forgotPasswordFragment_to_signInFragment)
                    R.id.passwordSentFragment ->
                        navController.navigate(R.id.action_passwordSentFragment_to_signInFragment)
                    R.id.resetPasswordFragmentDeepLink ->
                        navController.navigate(R.id.action_resetPasswordFragmentDeepLink_to_signInFragmentDeepLink)
                    R.id.signInFragmentDeepLink -> finish()
                    R.id.signInFragment -> finish()
                }
            }
        }
    }
}