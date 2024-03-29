package ru.point.sprind

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import ru.point.domain.entity.Request
import ru.point.sprind.adapters.MordaAdapter
import ru.point.sprind.databinding.FragmentSearchBinding
import ru.point.sprind.entity.deletage.RequestDelegate

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    val list = listOf(
        Request(1, "Goodle pixel 8"),
        Request(2, "Iphone 13 pro max"),
        Request(3, "Logitech")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.clearButton.setOnClickListener {
            binding.toolbar.search.setText("")
        }

        binding.toolbar.search.addTextChangedListener { text ->
            if (!text.isNullOrEmpty()) {
                binding.toolbar.clearButton.visibility = View.VISIBLE
            } else {
                binding.toolbar.clearButton.visibility = View.GONE
            }
        }


        binding.toolbar.back.visibility = View.VISIBLE
        binding.toolbar.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.recyclerView.adapter = MordaAdapter(
            listOf(RequestDelegate()),
            list
        )

        binding.recyclerView.addItemDecoration(object : ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State,
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.bottom = 15
            }
        })

    }
}