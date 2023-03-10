package com.example.binchecker.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.size
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.binchecker.databinding.ActivityMainBinding
import com.example.binchecker.presentation.adapters.SearchHistoryAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var searchHistoryAdapter: SearchHistoryAdapter

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw Exception("ActivityMainBinding = null")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        searchHistoryAdapter = SearchHistoryAdapter(this)
        viewModel.searchHistoryList.observe(this) {
            searchHistoryAdapter.setSearchHistoryList(it)
        }
        setClickListener()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val searchHistoryList = binding.searchHistoryList
        with(searchHistoryList) {
            adapter = searchHistoryAdapter
            layoutManager = LinearLayoutManager(this.context)
        }
        setupClickListener()
        setupSwipeListener(searchHistoryList)
    }

    private fun setupSwipeListener(searchHistoryList: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = searchHistoryList.size - viewHolder.adapterPosition - 1
                val item = searchHistoryAdapter.searchHistoryList[position]
                viewModel.deleteInSearchHistory(item)
            }
        }

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(searchHistoryList)
    }

    private fun setupClickListener() {
        searchHistoryAdapter.onClickListener = {
            binding.editTextNumberDecimal.text = Editable.Factory.getInstance().newEditable(it.number)
        }
    }


    private fun setClickListener() {
        binding.button.setOnClickListener {
            val number = binding.editTextNumberDecimal.text.toString()
            getRequest(number)
            updateBinInfo()
            observeViewModels()
            addTextChangeListener()
        }
    }

    private fun getRequest(cardNumber: String) {
        val url = START_OF_URL + cardNumber
        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                    result -> viewModel.getBinInfo(result, cardNumber)
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

    private fun addTextChangeListener() {
        binding.editTextNumberDecimal.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInput()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
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