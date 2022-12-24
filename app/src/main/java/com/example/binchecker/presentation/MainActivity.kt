package com.example.binchecker.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.android.volley.toolbox.Volley
import com.example.binchecker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw Exception("ActivityMainBinding = null")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setClickListener()
    }

    private fun setClickListener() {
        binding.button.setOnClickListener {
            val queue = Volley.newRequestQueue(this.baseContext)
            val number = binding.editTextNumberDecimal.text.toString()
            viewModel.getBinInfo(number, queue)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}