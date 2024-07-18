package ru.point.sprind.presenter.profile.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.point.domain.entity.view.ViewObject
import ru.point.sprind.adapters.SprindDefaultAdapter
import ru.point.sprind.adapters.decorators.ProductInfoDecorator
import ru.point.sprind.components.SprindApplication
import ru.point.sprind.databinding.FragmentOrdersBinding
import ru.point.sprind.presenter.cart.CartFragmentDirections
import ru.point.sprind.view.ConnectableLayout
import javax.inject.Inject

class OrdersFragment : MvpAppCompatFragment(), OrdersViewDefault {

    @Inject
    lateinit var provider: OrdersPresenter
    private val presenter by moxyPresenter { provider }

    private var _binding: FragmentOrdersBinding? = null
    private val binding get() = _binding!!

    private var _defaultAdapter: SprindDefaultAdapter? = null
    private val defaultAdapter get() = _defaultAdapter!!

    override fun onCreate(savedInstanceState: Bundle?) {
        SprindApplication.component.inject(this)
        super.onCreate(savedInstanceState)
        _defaultAdapter = SprindDefaultAdapter(presenter.delegates)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrdersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initToolbar()
        initializeRecyclerView()
    }

    private fun initToolbar() {
        binding.toolbar.setOnBackClickListener { findNavController().popBackStack() }
    }

    private fun initializeRecyclerView() {
        binding.recyclerView.apply {
            adapter = defaultAdapter
            addItemDecoration(ProductInfoDecorator())
        }
    }

    override fun requireAuthorization() {
        findNavController().navigate(CartFragmentDirections.actionGlobalAuthorizationFragment())
    }

    override fun setAdapter(views: List<ViewObject>) {
        defaultAdapter.submitList(views)
        binding.root.currentState = ConnectableLayout.ConnectionState.SUCCESS
    }

    override fun showBadConnection(show: Boolean) {
        binding.root.currentState = ConnectableLayout.ConnectionState.BAD_CONNECTION
    }

    override fun showLoading(show: Boolean) {
        binding.root.currentState = ConnectableLayout.ConnectionState.LOADING
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        _defaultAdapter = null
    }
}