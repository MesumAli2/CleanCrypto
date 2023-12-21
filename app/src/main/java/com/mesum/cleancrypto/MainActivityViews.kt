package com.mesum.cleancrypto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.mesum.cleancrypto.databinding.ActivityMainViewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivityViews : AppCompatActivity() {

    private var _binding: ActivityMainViewsBinding? =null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainViewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

    }
}