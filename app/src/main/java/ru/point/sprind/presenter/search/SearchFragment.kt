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
import io.reactivex.rxjava3.core.Observable
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.point.domain.entity.ListView
import ru.point.sprind.RequestManager
import ru.point.sprind.SprindApplication
import ru.point.sprind.adapters.MordaAdapter
import ru.point.sprind.databinding.FragmentSearchBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.deletage.RequestDelegate
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchFragment : MvpAppCompatFragment(), SearchView {


    @Inject
    lateinit var presenterProvider: SearchPresenter

    private val presenter: SearchPresenter by moxyPresenter { presenterProvider }

    private lateinit var binding: FragmentSearchBinding

    private var requestManager: RequestManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        SprindApplication.component.inject(fragment = this)
        super.onCreate(savedInstanceState)
        requestManager = context as? RequestManager
    }

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
                    requestManager?.addRequest(search.text.toString())

                    val args = SearchFragmentDirections.actionSearchFragmentToResultFragment(
                        request = search.text.toString()
                    )

                    findNavController().navigate(args)
                }
            }

            val disposable = Observable.create { emitter ->
                search.addTextChangedListener { text ->
                    emitter.onNext(text?.toString() ?: "")
                    if (!text.isNullOrEmpty()) {
                        clearButton.visibility = View.VISIBLE
                    } else {
                        clearButton.visibility = View.GONE
                        binding.recyclerView.adapter = MordaAdapter(
                            delegates = listOf(RequestDelegate()),
                            views = requestManager?.getRequestsHistory() ?: emptyList()
                        )
                    }
                }
            }.debounce(2, TimeUnit.SECONDS).subscribe { if (it.isNotEmpty()) presenter.getByName(it) }



            clearButton.setOnClickListener {
                search.setText("")
            }
        }
    }

    override fun openResult(name: String) {
        requestManager?.addRequest(name)

        val args = SearchFragmentDirections.actionSearchFragmentToResultFragment(
            request = name
        )

        findNavController().navigate(args)
    }

    override fun onResume() {
        super.onResume()
        with(binding.recyclerView) {
            adapter = MordaAdapter(
                delegates = listOf(RequestDelegate()),
                views = requestManager?.getRequestsHistory() ?: emptyList()
            )
        }
    }

    override fun setAdapter(delegates: List<Delegate>, view: List<ListView>) {
        binding.recyclerView.adapter = MordaAdapter(
            delegates = delegates,
            views = view
        )
    }

    private fun initializeRecyclerView() {
        with(binding.recyclerView) {
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