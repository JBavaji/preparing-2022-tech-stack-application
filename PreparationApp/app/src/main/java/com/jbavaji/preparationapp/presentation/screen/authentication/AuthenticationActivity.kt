package com.jbavaji.preparationapp.presentation.screen.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
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
            title = "Sign In from Activity"
            setNavigationOnClickListener {
                Toast.makeText(context, "Back pressed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}