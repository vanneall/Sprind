package ru.point.sprind.presenter.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        search = arguments?.getString("string", "") ?: ""
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
        setLoadingScreen()

        presenter.getSearchResult(search)

        binding.toolbar.search.isFocusable = false
        binding.toolbar.search.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_searchFragment)
        }
        binding.recyclerView.addItemDecoration(ProductDecorator())
    }

    override fun setProductAdapter(list: List<ListView>, delegates: List<Delegate>) {
        binding.recyclerView.adapter = MordaAdapter(
            delegates = delegates,
            views = list
        )
    }

    override fun setBadConnection() {
        binding.badConnection.root.visibility = View.VISIBLE
    }

    override fun setNotFound() {
        binding.notFoundScreen.root.visibility = View.VISIBLE
    }

    override fun setLoadingScreen() {
        binding.loadingScreen.root.visibility = View.VISIBLE
    }

    override fun disableLoadingScreen() {
        binding.loadingScreen.root.visibility = View.GONE
    }
}