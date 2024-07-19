package ru.point.sprind.presenter.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.cart.CartEmptyVo
import ru.point.domain.entity.view.cart.CartProductVo
import ru.point.domain.entity.view.cart.CartPromocodeVo
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

    private var _pagingAdapter: SprindPagingAdapter? = null
    private val pagingAdapter get() = _pagingAdapter!!

    override fun onCreate(savedInstanceState: Bundle?) {
        SprindApplication.component.inject(this)
        super.onCreate(savedInstanceState)
        _pagingAdapter = SprindPagingAdapter(
            delegates = presenter.viewDelegates,
            itemsComparator = { V1, V2 ->
                if (V1 is CartEmptyVo && V2 is CartEmptyVo) true
                else if (V1 is CartProductVo && V2 is CartProductVo) V1.id == V2.id
                else if (V1 is CartPromocodeVo && V2 is CartPromocodeVo) true
                else V1 == V2
            },
            contentComparator = { V1, V2 ->
                if (V1 is CartEmptyVo && V2 is CartEmptyVo) true
                else if (V1 is CartProductVo && V2 is CartProductVo) V1 == V2
                else if (V1 is CartPromocodeVo && V2 is CartPromocodeVo) true
                else V1 == V2
            },
            changePayload = { V1, V2 ->
                if (V1 is CartProductVo && V2 is CartProductVo) V1.isFavorite xor V2.isFavorite
                else null
            }
        )
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
        initRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        refresh()
    }

    private fun initRecyclerView() {
        binding.cartRecyclerView.apply {
            adapter = pagingAdapter
            addItemDecoration(CartItemDecorator())
        }

        pagingAdapter.addLoadStateListener { state ->
            if (state.hasError) {
                val stateError = state.append as? LoadState.Error
                    ?: state.source.prepend as? LoadState.Error
                    ?: state.prepend as? LoadState.Error
                    ?: state.refresh as? LoadState.Error
                stateError?.let { presenter.handleException(stateError.error) }
            } else if (state.isIdle && !state.hasError) {
                showPayButton()
            }
        }
    }

    override fun setAdapter(views: PagingData<ViewObject>) {
        pagingAdapter.submitData(lifecycle = lifecycle, pagingData = views)
        binding.root.currentState = ConnectableLayout.ConnectionState.SUCCESS
    }

    override fun refresh() {
        pagingAdapter.refresh()
    }

    override fun changeAddress() {
        val direction = CartFragmentDirections.actionGlobalMapFragment()
        findNavController().navigate(directions = direction)
    }

    override fun navigateToAuthorization() {
        hidePayButton()
        binding.authorizeWarning.apply {
            root.visibility = View.VISIBLE
            authorizeButton.setOnClickListener {
                val direction = CartFragmentDirections.actionGlobalAuthorizationFragment()
                findNavController().navigate(directions = direction)
            }
        }
    }

    override fun navigateToProductCard(productId: Long) {
        val direction = CartFragmentDirections.actionCartFragmentToProductCardFragment(
            productId = productId
        )
        findNavController().navigate(directions = direction)
    }

    override fun navigateToThanksScreen() {
        val direction = CartFragmentDirections.actionCartFragmentToThanksFragment()
        findNavController().navigate(directions = direction)
    }

    override fun showPayButton() {
        binding.payButton.apply {
            visibility = View.VISIBLE
            setOnClickListener { presenter.makeOrder() }
        }
    }

    override fun hidePayButton() {
        binding.payButton.visibility = View.GONE
    }

    override fun showSomethingGoesWrongError() {
        Toast.makeText(
            context,
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        _pagingAdapter = null
    }
}