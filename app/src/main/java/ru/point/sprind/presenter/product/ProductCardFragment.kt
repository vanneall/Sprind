package ru.point.sprind.presenter.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.point.domain.entity.view.ListView
import ru.point.sprind.SprindApplication
import ru.point.sprind.adapters.MordaAdapter
import ru.point.sprind.adapters.decorators.InfoProductDecorator

import ru.point.sprind.databinding.FragmentProductCardBinding
import ru.point.sprind.entity.deletage.Delegate
import javax.inject.Inject

class ProductCardFragment : MvpAppCompatFragment(), ProductCardView {

    private lateinit var binding: FragmentProductCardBinding

    @Inject
    lateinit var presenterProvider: ProductPresenterAssistedFactory

    private val presenter: ProductPresenter by moxyPresenter {
        presenterProvider.create(productId = args.productId)
    }

    private lateinit var adapter: MordaAdapter

    private val args: ProductCardFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        SprindApplication.component.inject(fragment = this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        presenter.getProduct()
        binding = FragmentProductCardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecyclerView()
    }

    private fun initializeRecyclerView() {
        adapter = MordaAdapter(delegates = presenter.delegates)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(InfoProductDecorator())
    }


    override fun setProductAdapter(list: List<ListView>, delegates: List<Delegate<*>>) {
        adapter.views = list
    }

    override fun showBadConnectionScreen() {
        binding.badConnection.root.visibility = View.VISIBLE
    }

    override fun showLoadingScreen() {
        binding.loadingScreen.root.visibility = View.VISIBLE
    }

    override fun disableLoadingScreen() {
        binding.loadingScreen.root.visibility = View.GONE
    }
}