  package com.shubhasai.biocraft

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.shubhasai.biocraft.databinding.ActivityMainBinding

  class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.navMenu.setOnItemSelectedListener {
            NavigationUI.onNavDestinationSelected(it,findNavController(binding.navHostFragment.id))
            findNavController(R.id.nav_host_fragment).popBackStack(it.itemId, inclusive = false)
            true
        }
    }
}