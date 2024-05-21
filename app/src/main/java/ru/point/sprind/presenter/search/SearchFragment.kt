package ru.point.sprind.presenter.search

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.point.domain.entity.view.ViewObject
import ru.point.sprind.adapters.MordaAdapter
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

    private var _adapter: MordaAdapter? = null
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
        initializeRecyclerView()
        presenter.getRequests()
    }

    private fun initializeToolbar() {
        with(binding.toolbar) {
            back.visibility = View.VISIBLE
            searchButton.visibility = View.GONE

            args.request?.let { request -> search.setText(request) }

            back.setOnClickListener {
                findNavController().popBackStack()
            }

            address.visibility = View.GONE

            search.addTextChangedListener { text ->
                if (!text.isNullOrEmpty()) {
                    clearButton.visibility = View.VISIBLE
                    searchButton.visibility = View.VISIBLE
                } else {
                    clearButton.visibility = View.GONE
                    searchButton.visibility = View.GONE
                }
            }

            clearButton.setOnClickListener {
                search.setText("")
            }

            searchButton.setOnClickListener {
                navigateToResult()
            }

            search.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    navigateToResult()
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }
        }
    }

    private fun initializeRecyclerView() {
        _adapter = MordaAdapter(presenter.delegates)
        binding.recyclerView.adapter = adapter

        lifecycle.addObserver(adapter)
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
        adapter.views = list
    }

    private fun navigateToResult() {
        val request = binding.toolbar.search.text.toString()
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