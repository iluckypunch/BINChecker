package com.example.binchecker.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
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
            val number = binding.editTextNumberDecimal.text.toString()
            getRequest(number)
            updateBinInfo()
            observeViewModels()
        }
    }

    private fun getRequest(cardNumber: String) {
        val url = START_OF_URL + cardNumber
        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                    result -> viewModel.getBinInfo(result)
            },
            {
                    error -> viewModel.validateInput(error.toString())
            }
        )
        val queue = Volley.newRequestQueue(this@MainActivity)
        queue.add(request)
    }

    private fun updateBinInfo() {
        viewModel.binInfoLD.observe(this) {
            with(binding){
                scheme.text = SCHEME + it.scheme
                type.text = TYPE + it.type
                brand.text = BRAND + it.brand
                prepaid.text = PREPAID + if (it.prepaid == true) "Yes" else "No"
                country.text = (COUNTRY +
                        (it.country?.get("emoji") ?: "?") +
                        (it.country?.get("name") ?: "?"))
                bank.text = (BANK +
                        (it.bank?.get("name") ?: "?") + "\n" +
                        (it.bank?.get("url") ?: "?") + "\n" +
                        (it.bank?.get("phone") ?: "?") + "\n" +
                        (it.bank?.get("city") ?: "?"))
            }
        }
    }

    private fun observeViewModels() {
        viewModel.errorInput.observe(this) {
            val message = if (it) {
                INVALID_INPUT
            } else {
                null
            }
            binding.editTextNumberDecimal.error = message
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val START_OF_URL = "https://lookup.binlist.net/"
        const val SCHEME = "Scheme: "
        const val TYPE = "Type: "
        const val BRAND = "Brand: "
        const val PREPAID = "Prepaid: "
        const val COUNTRY = "Country: "
        const val BANK = "Bank: "
        const val INVALID_INPUT = "Invalid input"
    }
}