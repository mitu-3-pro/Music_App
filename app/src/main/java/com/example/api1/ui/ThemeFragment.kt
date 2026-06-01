package com.example.api1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.api1.R
import com.example.api1.adapter.ThemeAdapter
import com.example.api1.model.CategoryItem

class ThemeFragment : Fragment() {

    companion object {
        private const val ARG_CATEGORY = "category"

        fun newInstance(category: CategoryItem): ThemeFragment {
            val fragment = ThemeFragment()
            val bundle = Bundle()
            bundle.putSerializable(ARG_CATEGORY, category)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var recyclerView: RecyclerView
    private var themeAdapter: ThemeAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_theme, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        // Get category from arguments
        val category = arguments?.getSerializable(ARG_CATEGORY) as? CategoryItem

        if (category != null && category.themes.isNotEmpty()) {
            themeAdapter = ThemeAdapter(requireContext(), category.themes)
            recyclerView.adapter = themeAdapter
        }
    }
}