package ru.point.sprind.presenter.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.point.domain.entity.view.ViewObject
import ru.point.sprind.R
import ru.point.sprind.adapters.SprindPagingAdapter
import ru.point.sprind.adapters.decorators.CartItemDecorator
import ru.point.sprind.components.SprindApplication
import ru.point.sprind.databinding.FragmentCartBinding
import ru.point.sprind.view.ConnectableLayout
import javax.inject.Inject

class CartFragment : MvpAppCompatFragment(), CartView {

    @Inject
    lateinit var presenterProvider: CartPresenter
    private val presenter: CartPresenter by moxyPresenter { presenterProvider }

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private var _adapter: SprindPagingAdapter? = null
    private val adapter get() = _adapter!!

    override fun onCreate(savedInstanceState: Bundle?) {
        SprindApplication.component.inject(this)
        super.onCreate(savedInstanceState)
        _adapter = SprindPagingAdapter(delegates = presenter.delegates)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCartBinding.inflate(layoutInflater)
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
        binding.cartRecyclerView.adapter = adapter
        binding.cartRecyclerView.addItemDecoration(CartItemDecorator())
    }

    override fun requireAuthorization() {
        binding.authorizeWarning.root.visibility = View.VISIBLE
        binding.authorizeWarning.authorizeButton.setOnClickListener {
            findNavController().navigate(CartFragmentDirections.actionGlobalAuthorizationFragment())
        }
    }

    override fun setAdapter(views: PagingData<ViewObject>) {
        adapter.submitData(lifecycle, views)
        binding.root.currentState = ConnectableLayout.ConnectionState.SUCCESS
    }

    override fun openCard(id: Long) {
        val args = CartFragmentDirections.actionCartFragmentToProductCardFragment(
            productId = id
        )

        binding.root.findNavController().navigate(args)
    }

    override fun displayPayButton(show: Boolean) {
        with(binding.payButton) {
            if (show) {
                visibility = View.VISIBLE
                setOnClickListener { presenter.makeOrder() }
            } else {
                visibility = View.GONE
            }
        }
    }

    override fun showSomethingGoesWrongError() {
        Toast.makeText(
            requireContext(),
            getString(R.string.someting_goes_wrong_hint),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun showBadConnection(show: Boolean) {
        binding.root.currentState = ConnectableLayout.ConnectionState.BAD_CONNECTION
    }

    override fun showLoading(show: Boolean) {
        binding.root.currentState = ConnectableLayout.ConnectionState.LOADING
    }

    override fun changeAddress() {
        val destination = CartFragmentDirections.actionGlobalMapFragment()
        findNavController().navigate(destination)
    }

    override fun openThanksScreen() {
        val destination = CartFragmentDirections.actionCartFragmentToThanksFragment()
        findNavController().navigate(destination)
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