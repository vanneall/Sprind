package ru.point.sprind.presenter.search

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import moxy.MvpAppCompatFragment
import ru.point.sprind.adapters.MordaAdapter
import ru.point.sprind.databinding.FragmentSearchBinding

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
        with(binding.toolbar) {
            back.visibility = View.VISIBLE

            back.setOnClickListener {
                findNavController().popBackStack()
            }

            address.setOnClickListener {
                if (!search.text.isNullOrEmpty()) {
                    val args = SearchFragmentDirections.actionSearchFragmentToResultFragment(
                        request = search.text.toString()
                    )

                    findNavController().navigate(args)
                }
            }

            search.addTextChangedListener { text ->
                if (!text.isNullOrEmpty()) {
                    clearButton.visibility = View.VISIBLE
                } else {
                    clearButton.visibility = View.GONE
                }
            }

            clearButton.setOnClickListener {
                search.setText("")
            }
        }
    }

    private fun initializeRecyclerView() {
        with(binding.recyclerView) {
            adapter = MordaAdapter(
                delegates = emptyList(),
                views = emptyList()
            )

            addItemDecoration(object : ItemDecoration() {
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
}