package ru.point.sprind.presenter.search

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import moxy.MvpAppCompatFragment
import ru.point.sprind.R
import ru.point.sprind.adapters.MordaAdapter
import ru.point.sprind.databinding.FragmentSearchBinding
import ru.point.sprind.entity.deletage.RequestDelegate

class SearchFragment : MvpAppCompatFragment() {

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeToolbar()
        initializeRecyclerView()
    }

    private fun initializeToolbar() {
        binding.toolbar.back.visibility = View.VISIBLE

        binding.toolbar.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.toolbar.address.setOnClickListener {
            val bundle = bundleOf("string" to binding.toolbar.search.text.toString())
            findNavController().navigate(R.id.action_searchFragment_to_resultFragment, bundle)
        }

        binding.toolbar.search.addTextChangedListener { text ->
            if (!text.isNullOrEmpty()) {
                binding.toolbar.clearButton.visibility = View.VISIBLE
            } else {
                binding.toolbar.clearButton.visibility = View.GONE
            }
        }

        binding.toolbar.clearButton.setOnClickListener {
            binding.toolbar.search.setText("")
        }
    }

    private fun initializeRecyclerView() {

        binding.recyclerView.adapter = MordaAdapter(
            delegates = emptyList(),
            views = emptyList()
        )

        binding.recyclerView.addItemDecoration(object : ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State,
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.bottom = 16
            }
        })
    }
}