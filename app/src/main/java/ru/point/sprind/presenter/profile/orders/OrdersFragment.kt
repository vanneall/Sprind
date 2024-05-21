package ru.point.sprind.presenter.profile.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.point.domain.entity.view.ViewObject
import ru.point.sprind.R
import ru.point.sprind.adapters.MordaAdapter
import ru.point.sprind.adapters.decorators.ProductInfoDecorator
import ru.point.sprind.components.SprindApplication
import ru.point.sprind.databinding.FragmentOrdersBinding
import ru.point.sprind.presenter.cart.CartFragmentDirections
import javax.inject.Inject

class OrdersFragment : MvpAppCompatFragment(), OrdersView {

    @Inject
    lateinit var provider: OrdersPresenter
    private val presenter by moxyPresenter { provider }

    private var _binding: FragmentOrdersBinding? = null
    private val binding get() = _binding!!

    private var _adapter: MordaAdapter? = null
    private val adapter get() = _adapter!!

    override fun onCreate(savedInstanceState: Bundle?) {
        SprindApplication.component.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrdersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeToolbar()
        initializeRecyclerView()
    }

    private fun initializeToolbar() {
        binding.toolbar.title.text = resources.getString(R.string.orders)
    }

    private fun initializeRecyclerView() {
        _adapter = MordaAdapter(presenter.delegates)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(ProductInfoDecorator())

        lifecycle.addObserver(adapter)
    }

    override fun requireAuthorization() {
        findNavController().navigate(CartFragmentDirections.actionGlobalAuthorizationFragment())
    }

    override fun setAdapter(views: List<ViewObject>) {
        adapter.views = views
    }

    override fun displayBadConnectionScreen(show: Boolean) {
        binding.badConnection.root.visibility = if (show) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun displayLoadingScreen(show: Boolean) {
        binding.loadingScreen.root.visibility = if (show) {
            View.VISIBLE
        } else {
            View.GONE
        }
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