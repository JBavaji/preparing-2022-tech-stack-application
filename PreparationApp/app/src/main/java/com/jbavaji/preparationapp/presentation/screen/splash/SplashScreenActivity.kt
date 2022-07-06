package com.jbavaji.preparationapp.presentation.screen.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.jbavaji.preparationapp.databinding.ActivitySplashScreenBinding
import com.jbavaji.preparationapp.presentation.screen.MainActivity
import com.jbavaji.preparationapp.presentation.screen.authentication.AuthenticationActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var _binding: ActivitySplashScreenBinding
    private val binding
        get() = _binding

    private val viewModel: SplashScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObserver()
    }

    private fun initObserver() {
        viewModel.splashLifeCycle.observe(this, Observer {
            if(it) {
                Intent(this, AuthenticationActivity::class.java).apply {
                    finish()
                    startActivity(this)
                }
            }
        })

        viewModel.loadingApp()
    }
}