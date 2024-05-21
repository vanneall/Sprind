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
import ru.point.sprind.adapters.MordaAdapter
import ru.point.sprind.adapters.decorators.ProductInfoDecorator
import ru.point.sprind.components.SprindApplication
import ru.point.sprind.databinding.FragmentProductCardBinding
import ru.point.sprind.presenter.cart.CartFragmentDirections
import javax.inject.Inject

class ProductCardFragment : MvpAppCompatFragment(), ProductCardView {

    private val args: ProductCardFragmentArgs by navArgs()

    @Inject
    lateinit var presenterProvider: ProductPresenterAssistedFactory
    private val presenter: ProductPresenter by moxyPresenter {
        presenterProvider.create(productId = args.productId, lifecycle = lifecycle)
    }

    private var _binding: FragmentProductCardBinding? = null
    private val binding get() = _binding!!


    private var _adapter: MordaAdapter? = null
    private val adapter get() = _adapter!!

    override fun onCreate(savedInstanceState: Bundle?) {
        SprindApplication.component.inject(fragment = this)
        super.onCreate(savedInstanceState)
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
        initializeRecyclerView()
        initializePayButtons()
    }

    override fun onResume() {
        super.onResume()
        presenter.init()
    }

    private fun initializeRecyclerView() {
        _adapter = MordaAdapter(delegates = presenter.delegates)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(ProductInfoDecorator())

        lifecycle.addObserver(adapter)
    }

    private fun initializePayButtons() {
        with(binding) {
            deleteFromCartButton.setOnClickListener { presenter.deleteFromCart() }

            payButton.setOnClickListener { presenter.addToCart() }

            goToCartButton.setOnClickListener {
                val destination =
                    ProductCardFragmentDirections.actionProductCardFragmentToCartFragment()
                findNavController().navigate(destination)
            }
        }
    }

    override fun openReviews() {
        val destination =
            ProductCardFragmentDirections.actionProductCardFragmentToAllReviewsFragment(args.productId)
        findNavController().navigate(destination)
    }

    override fun displaySomethingGoesWrongError() {
        Toast.makeText(
            requireContext(),
            getString(R.string.someting_goes_wrong_hint),
            Toast.LENGTH_SHORT
        ).show()
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

    override fun setAdapter(views: List<ViewObject>) {
        adapter.views = views
    }

    override fun displayProductInCartButtonGroup(show: Boolean) {
        with(binding) {
            if (show) {
                productInCartGroup.visibility = View.VISIBLE
                payButton.visibility = View.GONE
            } else {
                productInCartGroup.visibility = View.GONE
                payButton.visibility = View.VISIBLE
            }
        }
    }

    override fun requireAuthorization() {
        findNavController().navigate(CartFragmentDirections.actionGlobalAuthorizationFragment())
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