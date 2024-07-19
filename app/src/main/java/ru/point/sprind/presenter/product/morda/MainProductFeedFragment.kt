package ru.point.sprind.presenter.product.morda

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
import ru.point.domain.entity.view.product.card.FeedProductVo
import ru.point.sprind.R
import ru.point.sprind.adapters.SprindPagingAdapter
import ru.point.sprind.adapters.decorators.FeedProductDecorator
import ru.point.sprind.adapters.decorators.spans.MordaSpanSizeLookup
import ru.point.sprind.components.SprindApplication
import ru.point.sprind.databinding.FragmentMordaBinding
import ru.point.sprind.view.ConnectableLayout
import javax.inject.Inject

class MainProductFeedFragment : MvpAppCompatFragment(), MainProductFeedView {

    @Inject
    lateinit var presenterProvider: MainProductFeedPresenter
    private val presenter: MainProductFeedPresenter by moxyPresenter { presenterProvider }

    private var _binding: FragmentMordaBinding? = null
    private val binding get() = _binding!!

    private var _pagingAdapter: SprindPagingAdapter? = null
    private val pagingAdapter get() = _pagingAdapter!!

    override fun onCreate(savedInstanceState: Bundle?) {
        SprindApplication.component.inject(fragment = this)
        super.onCreate(savedInstanceState)
        _pagingAdapter = SprindPagingAdapter(
            delegates = presenter.viewDelegates,
            itemsComparator = { V1, V2 ->
                if (V1 is FeedProductVo && V2 is FeedProductVo) V1.id == V2.id
                else V1 == V2
            },
            changePayload = { V1, V2 ->
                if (V1 is FeedProductVo && V2 is FeedProductVo) V1.isFavorite xor V2.isFavorite
                else null
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMordaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        refresh()
    }

    private fun initToolbar() {
        binding.toolbar.setOnSearchbarClickListener {
            val direction = MainProductFeedFragmentDirections.actionMordaFragmentToSearchFragment(
                request = null
            )
            findNavController().navigate(directions = direction)
        }

        binding.toolbar.setOnAddressClickListener {
            val direction = MainProductFeedFragmentDirections.actionGlobalMapFragment()
            findNavController().navigate(directions = direction)
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            adapter = pagingAdapter
            addItemDecoration(FeedProductDecorator())

            val layoutManager = layoutManager as GridLayoutManager
            layoutManager.spanSizeLookup = MordaSpanSizeLookup(
                delegates = presenter.viewDelegates,
                adapter = pagingAdapter
            )
        }
    }

    override fun navigateToProductCard(productId: Long) {
        val direction = MainProductFeedFragmentDirections.actionMordaFragmentToProductCardFragment(
            productId = productId
        )
        findNavController().navigate(directions = direction)
    }

    override fun navigateToAuthorization() {
        val direction = MainProductFeedFragmentDirections.actionGlobalAuthorizationFragment()
        findNavController().navigate(directions = direction)
    }

    override fun navigateToCategoryScreen(categoryId: Long, title: String) {
        val direction = MainProductFeedFragmentDirections.actionMordaFragmentToCategoryFragment(
            categoryId = categoryId,
            title = title
        )
        findNavController().navigate(directions = direction)
    }

    override fun showBadConnection(show: Boolean) {
        binding.root.currentState = ConnectableLayout.ConnectionState.BAD_CONNECTION
    }

    override fun showLoading(show: Boolean) {
        binding.root.currentState = ConnectableLayout.ConnectionState.LOADING
    }

    override fun setAddress(address: String?) {
        binding.toolbar.address = address
    }

    override fun refresh() {
        pagingAdapter.refresh()
    }

    override fun setAdapter(views: PagingData<ViewObject>) {
        pagingAdapter.submitData(lifecycle, views)
        binding.root.currentState = ConnectableLayout.ConnectionState.SUCCESS
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