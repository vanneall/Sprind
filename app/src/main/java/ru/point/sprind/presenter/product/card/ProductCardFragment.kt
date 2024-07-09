package ru.point.sprind.presenter.product.card

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.point.domain.entity.view.ViewObject
import ru.point.sprind.R
import ru.point.sprind.adapters.SprindDefaultAdapter
import ru.point.sprind.adapters.decorators.ProductInfoDecorator
import ru.point.sprind.components.SprindApplication
import ru.point.sprind.databinding.FragmentProductCardBinding
import ru.point.sprind.view.ConnectableLayout
import javax.inject.Inject

class ProductCardFragment : MvpAppCompatFragment(), ProductCardViewDefault {

    private val args: ProductCardFragmentArgs by navArgs()

    @Inject
    lateinit var presenterProvider: ProductCardPresenter.Factory
    private val presenter: ProductCardPresenter by moxyPresenter {
        presenterProvider.create(productId = args.productId)
    }

    private var _binding: FragmentProductCardBinding? = null
    private val binding get() = _binding!!


    private var _defaultAdapter: SprindDefaultAdapter? = null
    private val defaultAdapter get() = _defaultAdapter!!

    override fun onCreate(savedInstanceState: Bundle?) {
        SprindApplication.component.inject(fragment = this)
        super.onCreate(savedInstanceState)
        _defaultAdapter = SprindDefaultAdapter(delegates = presenter.viewDelegates)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProductCardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initPayButtons()
    }

    override fun onResume() {
        super.onResume()
        presenter.init()
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            adapter = defaultAdapter
            addItemDecoration(ProductInfoDecorator())
        }
    }

    private fun initPayButtons() {
        binding.apply {
            deleteFromCartButton.setOnClickListener { presenter.removeFromCart() }

            payButton.setOnClickListener { presenter.addToCart() }

            goToCartButton.setOnClickListener {
                val destination =
                    ProductCardFragmentDirections.actionProductCardFragmentToCartFragment()
                findNavController().navigate(directions = destination)
            }
        }
    }

    override fun navigateToReviews() {
        val direction = ProductCardFragmentDirections.actionProductCardFragmentToAllReviewsFragment(
            id = args.productId
        )

        findNavController().navigate(directions = direction)
    }

    override fun navigateToAuthorization() {
        val direction = ProductCardFragmentDirections.actionGlobalAuthorizationFragment()
        findNavController().navigate(directions = direction)
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

    override fun setAdapter(views: List<ViewObject>) {
        defaultAdapter.submitList(views)
        binding.root.currentState = ConnectableLayout.ConnectionState.SUCCESS
    }

    override fun displayProductInCartButtonGroup(show: Boolean) {
        binding.apply {
            if (show) {
                productInCartGroup.visibility = View.VISIBLE
                payButton.visibility = View.GONE
            } else {
                productInCartGroup.visibility = View.GONE
                payButton.visibility = View.VISIBLE
            }
        }
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