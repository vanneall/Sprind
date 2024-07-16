package ru.point.sprind.presenter.product.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.point.domain.entity.view.ViewObject
import ru.point.sprind.R
import ru.point.sprind.adapters.SprindPagingAdapter
import ru.point.sprind.adapters.decorators.FavoritesItemDecorator
import ru.point.sprind.adapters.decorators.spans.FavoriteSpanSizeLookup
import ru.point.sprind.components.SprindApplication
import ru.point.sprind.databinding.FragmentFavoritesBinding
import ru.point.sprind.view.ConnectableLayout
import javax.inject.Inject

class FavoriteProductsFragment : MvpAppCompatFragment(), FavoriteView {

    @Inject
    lateinit var presenterProvider: FavoriteProductsPresenter
    private val presenter by moxyPresenter { presenterProvider }

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private var _pagingAdapter: SprindPagingAdapter? = null
    private val pagingAdapter get() = _pagingAdapter!!


    override fun onCreate(savedInstanceState: Bundle?) {
        SprindApplication.component.inject(this)
        super.onCreate(savedInstanceState)
        _pagingAdapter = SprindPagingAdapter(delegates = presenter.viewDelegates)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFavoritesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.favoritesRecyclerView.apply {
            adapter = pagingAdapter

            val layoutManager = layoutManager as GridLayoutManager
            layoutManager.spanSizeLookup =
                FavoriteSpanSizeLookup(presenter.viewDelegates, pagingAdapter)
            addItemDecoration(FavoritesItemDecorator())
        }
    }

    override fun setAdapter(views: PagingData<ViewObject>) {
        pagingAdapter.submitData(lifecycle, views)
        binding.root.currentState = ConnectableLayout.ConnectionState.SUCCESS
    }

    override fun navigateToProductCard(productId: Long) {
        val direction =
            FavoriteProductsFragmentDirections.actionFavoritesFragmentToProductCardFragment(
                productId = productId
            )
        findNavController().navigate(directions = direction)
    }

    override fun navigateToAuthorization() {
        binding.authorizeWarning.apply {
            root.visibility = View.VISIBLE
            authorizeButton.setOnClickListener {
                findNavController().navigate(FavoriteProductsFragmentDirections.actionGlobalAuthorizationFragment())
            }
        }
    }

    override fun showBadConnection(show: Boolean) {
        binding.root.currentState = ConnectableLayout.ConnectionState.BAD_CONNECTION
    }

    override fun showLoading(show: Boolean) {
        binding.root.currentState = ConnectableLayout.ConnectionState.LOADING
    }

    override fun showSomethingGoesWrongError() {
        Toast
            .makeText(context, getString(R.string.someting_goes_wrong_hint), Toast.LENGTH_SHORT)
            .show()
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