package ru.point.sprind.presenter.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.point.domain.entity.view.ListView
import ru.point.sprind.R
import ru.point.sprind.SprindApplication
import ru.point.sprind.adapters.MordaAdapter
import ru.point.sprind.adapters.decorators.CartItemDecorator
import ru.point.sprind.databinding.FragmentCartBinding
import javax.inject.Inject

class CartFragment : MvpAppCompatFragment(), CartView {

    private lateinit var binding: FragmentCartBinding

    private lateinit var adapter: MordaAdapter

    @Inject
    lateinit var presenterProvider: CartPresenter

    private val presenter: CartPresenter by moxyPresenter { presenterProvider }

    override fun onCreate(savedInstanceState: Bundle?) {
        SprindApplication.component.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCartBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeToolbar()
        initializeRecyclerView()
        binding.payButton.visibility = View.GONE
    }

    private fun initializeToolbar() {
        binding.cartToolbar.title.text = resources.getText(R.string.cart_screen_title)
    }

    private fun initializeRecyclerView() {
        adapter = MordaAdapter(delegates = presenter.delegates)
        binding.cartRecyclerView.adapter = adapter
        binding.cartRecyclerView.addItemDecoration(CartItemDecorator())

        presenter.initAdapter()
    }

    override fun setAdapter(view: List<ListView>) {
        adapter.views = view
    }

    override fun showPayButton() {
        binding.payButton.visibility = View.VISIBLE
    }
}