package com.example.api1.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.example.api1.R
import com.example.api1.adapter.CategoryPagerAdapter
import com.example.api1.viewmodel.ThemeViewModel
import com.example.api1.viewmodel.UiState

class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var viewModel: ThemeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)

        viewModel = ViewModelProvider(this).get(ThemeViewModel::class.java)

        // Fetch categories from API
        viewModel.fetchThemes()

        // Observe the data
        viewModel.uiState.observe(this) { state ->
            when (state) {
                is UiState.Loading -> {
                    // Show loading indicator
                }
                is UiState.Success -> {
                    // Set up adapter with categories
                    val categories = state.data
                    val adapter = CategoryPagerAdapter(this, categories)
                    viewPager.adapter = adapter

                    // Connect TabLayout with ViewPager2
                    TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                        tab.text = categories[position].Cat_Name
                    }.attach()
                }
                is UiState.Error -> {
                    // Show error message
                    android.util.Log.e("MainActivity", "Error: ${state.message}")
                }
            }
        }
    }
}