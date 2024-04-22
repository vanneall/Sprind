package ru.point.sprind.presenter.morda

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
import ru.point.sprind.databinding.FragmentMordaBinding
import ru.point.sprind.entity.deletage.Delegate
import javax.inject.Inject

class MordaFragment : MvpAppCompatFragment(), MordaView {

    @Inject
    lateinit var presenterProvider: MordaPresenter

    private val presenter: MordaPresenter by moxyPresenter { presenterProvider }

    override fun onCreate(savedInstanceState: Bundle?) {
        SprindApplication.component.inject(fragment = this)
        super.onCreate(savedInstanceState)
    }

    private lateinit var binding: FragmentMordaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMordaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLoadingScreen()

        binding.toolbar.search.isFocusable = false
        binding.toolbar.search.setOnClickListener {
            findNavController().navigate(R.id.action_mordaFragment_to_searchFragment)
        }

        binding.recyclerView.addItemDecoration(ProductDecorator())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(REQUEST_KEY, binding.toolbar.search.text.toString())
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        if (savedInstanceState != null) {
            binding.toolbar.search.setText(savedInstanceState.getString(REQUEST_KEY))
        }
    }

    private companion object {
        const val REQUEST_KEY = "REQUEST_KEY"
    }

    override fun setProductAdapter(list: List<ListView>, delegates: List<Delegate>) {
        binding.recyclerView.adapter = MordaAdapter(
            delegates = delegates,
            views = list
        )
    }

    override fun setNotFound() {
        binding.notFoundScreen.root.visibility = View.VISIBLE
    }

    override fun setBadConnection() {
        binding.badConnection.root.visibility = View.VISIBLE
    }

    override fun setLoadingScreen() {
        binding.loadingScreen.root.visibility = View.VISIBLE
    }

    override fun disableLoadingScreen() {
        binding.loadingScreen.root.visibility = View.GONE
    }

    override fun openCard(id: Long) {
        val bundle = bundleOf("PRODUCT_ID" to id)
        binding.root.findNavController()
            .navigate(R.id.action_mordaFragment_to_productCardFragment, bundle)
    }
}