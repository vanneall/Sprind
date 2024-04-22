package ru.point.sprind.presenter.result

import android.content.res.Resources.NotFoundException
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.point.domain.entity.ListView
import ru.point.sprind.R
import ru.point.sprind.SprindApplication
import ru.point.sprind.adapters.MordaAdapter
import ru.point.sprind.adapters.ProductDecorator
import ru.point.sprind.databinding.FragmentResultBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.presenter.morda.MordaView
import ru.point.sprind.presenter.product.ProductCardFragment.Companion.PRODUCT_ID
import javax.inject.Inject

class ResultFragment : MvpAppCompatFragment(), MordaView {

    private lateinit var binding: FragmentResultBinding

    private var search: String = ""

    @Inject
    lateinit var presenterProvider: ResultPresenter

    private val presenter: ResultPresenter by moxyPresenter { presenterProvider }

    override fun onCreate(savedInstanceState: Bundle?) {
        SprindApplication.component.inject(this)
        super.onCreate(savedInstanceState)

        search = arguments?.getString(SEARCH_REQUEST, "")
            ?: throw NotFoundException("Search request required but not found")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.getSearchResult(search)

        initializeToolbar()
        initializeRecyclerView()
    }

    private fun initializeToolbar() {
        binding.toolbar.search.isFocusable = false
        binding.toolbar.search.setText(search)
        binding.toolbar.search.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_searchFragment)
        }
    }

    private fun initializeRecyclerView() {
        binding.recyclerView.addItemDecoration(ProductDecorator())
    }

    override fun setProductAdapter(views: List<ListView>, delegates: List<Delegate>) {
        binding.recyclerView.adapter = MordaAdapter(
            delegates = delegates,
            views = views
        )
    }

    override fun showBadConnectionScreen() {
        binding.badConnection.root.visibility = View.VISIBLE
    }

    override fun showNotFoundScreen() {
        binding.notFoundScreen.root.visibility = View.VISIBLE
    }

    override fun showLoadingScreen() {
        binding.loadingScreen.root.visibility = View.VISIBLE
    }

    override fun disableLoadingScreen() {
        binding.loadingScreen.root.visibility = View.GONE
    }

    override fun openCard(id: Long) {
        val bundle = bundleOf(PRODUCT_ID to id)
        binding.root
            .findNavController()
            .navigate(R.id.action_mordaFragment_to_productCardFragment, bundle)
    }

    companion object {
        const val SEARCH_REQUEST = "SEARCH_REQUEST"
    }
}