package ru.point.sprind.presenter.search

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.point.domain.entity.view.ViewObject
import ru.point.sprind.adapters.SprindDefaultAdapter
import ru.point.sprind.components.SprindApplication
import ru.point.sprind.databinding.FragmentSearchBinding
import javax.inject.Inject

class SearchFragment : MvpAppCompatFragment(), SearchRequestView {

    private val args by navArgs<SearchFragmentArgs>()

    @Inject
    lateinit var provider: SearchPresenter
    private val presenter by moxyPresenter { provider }

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private var _adapter: SprindDefaultAdapter? = null
    private val adapter get() = _adapter!!

    override fun onCreate(savedInstanceState: Bundle?) {
        SprindApplication.component.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeToolbar()
        initRecyclerView()
        presenter.getRequests()
    }

    private fun initializeToolbar() {
        binding.toolbar.apply {
            setOnBackClickListener { findNavController().popBackStack() }

            args.request?.let { request -> searchText = request }
            setOnSearchTextChangedListener { text ->
                if (!text.isNullOrEmpty()) {
                    clearButtonVisibility = View.VISIBLE
                    trailingIconVisibility = View.VISIBLE
                } else {
                    clearButtonVisibility = View.GONE
                    trailingIconVisibility = View.GONE
                }
            }

            trailingIconVisibility = View.GONE
            setOnTrailingIconClickListener { navigateToResult() }

            setOnClearButtonClickListener { searchText = "" }

            setOnEditorClickListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    navigateToResult()
                    return@setOnEditorClickListener true
                }
                return@setOnEditorClickListener false
            }
        }
    }

    private fun initRecyclerView() {
        _adapter = SprindDefaultAdapter(presenter.delegates)
        binding.recyclerView.adapter = adapter

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

    override fun setAdapter(list: List<ViewObject>) {
        adapter.submitList(list)
    }

    private fun navigateToResult() {
        val request = binding.toolbar.searchText.toString()
        if (request.isEmpty()) return

        presenter.addRequestToHistory(request)

        val args = SearchFragmentDirections.actionSearchFragmentToResultFragment(
            request = request
        )
        findNavController().navigate(args)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        _adapter = null
    }
}