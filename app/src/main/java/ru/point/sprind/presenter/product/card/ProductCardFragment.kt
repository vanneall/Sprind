package ru.point.sprind.presenter.product.card

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.point.domain.entity.view.ViewObject
import ru.point.sprind.adapters.MordaAdapter
import ru.point.sprind.adapters.decorators.ProductInfoDecorator
import ru.point.sprind.components.SprindApplication
import ru.point.sprind.databinding.FragmentProductCardBinding
import ru.point.sprind.presenter.cart.CartFragmentDirections
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
        binding = FragmentProductCardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        presenter.init()
    }

    private fun initializeRecyclerView() {
        adapter = MordaAdapter(delegates = presenter.delegates)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(ProductInfoDecorator())
    }

    override fun openReviews() {
        val destination = ProductCardFragmentDirections.actionProductCardFragmentToAllReviewsFragment(args.productId)
        findNavController().navigate(destination)
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

    override fun requireAuthorization() {
        findNavController().navigate(CartFragmentDirections.actionGlobalAuthorizationFragment())
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.recyclerView.adapter = null
    }
}