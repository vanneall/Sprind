package ru.point.sprind.presenter.product

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.point.domain.entity.ListView
import ru.point.sprind.SprindApplication
import ru.point.sprind.adapters.MordaAdapter
import ru.point.sprind.databinding.FragmentProductCardBinding
import ru.point.sprind.entity.deletage.Delegate
import javax.inject.Inject

class ProductCardFragment : MvpAppCompatFragment(), ProductCardView {

    @Inject
    lateinit var presenterProvider: ProductPresenter

    private var id: Long = 0

    private val presenter: ProductPresenter by moxyPresenter { presenterProvider }

    override fun onCreate(savedInstanceState: Bundle?) {
        SprindApplication.component.inject(fragment = this)
        id = arguments?.getLong("PRODUCT_ID") ?: 0

        super.onCreate(savedInstanceState)
    }

    private lateinit var binding: FragmentProductCardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        presenter.init(id)
        binding = FragmentProductCardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun setProductAdapter(list: List<ListView>, delegates: List<Delegate>) {
        binding.recyclerView.adapter = MordaAdapter(
            delegates = delegates,
            views = list
        )
    }

    override fun setNotFound() {
        Log.e("Error", "Not found")
    }

    override fun setBadConnection() {
        Log.e("Error", "Bad connection")
    }

    override fun setLoadingScreen() {
        Log.e("Error", "Loading screen")
    }

    override fun disableLoadingScreen() {
        Log.e("Error", "Disable loading screen")
    }

}