package ru.point.sprind.presenter.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.point.domain.entity.ListView
import ru.point.sprind.SprindApplication
import ru.point.sprind.adapters.MordaAdapter
import ru.point.sprind.databinding.FragmentProductCardBinding
import ru.point.sprind.entity.deletage.Delegate
import javax.inject.Inject

class ProductCardFragment : MvpAppCompatFragment(), ProductCardView {

    private lateinit var binding: FragmentProductCardBinding

    @Inject
    lateinit var presenterProvider: ProductPresenter

    private val presenter: ProductPresenter by moxyPresenter { presenterProvider }

    private val args: ProductCardFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        SprindApplication.component.inject(fragment = this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        presenter.init(args.productId)
        binding = FragmentProductCardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun setProductAdapter(list: List<ListView>, delegates: List<Delegate>) {
        binding.recyclerView.adapter = MordaAdapter(
            delegates = delegates,
            views = list
        )
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