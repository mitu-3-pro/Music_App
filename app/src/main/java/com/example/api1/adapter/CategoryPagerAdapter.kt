package com.example.api1.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.api1.model.CategoryItem
import com.example.api1.ui.ThemeFragment

class CategoryPagerAdapter(
    activity: AppCompatActivity,
    private val categories: List<CategoryItem>
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun createFragment(position: Int): Fragment {
        return ThemeFragment.newInstance(categories[position])
    }
}