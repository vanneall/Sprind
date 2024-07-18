package ru.point.sprind.presenter.product.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.point.domain.entity.view.ViewObject
import ru.point.sprind.R
import ru.point.sprind.adapters.SprindPagingAdapter
import ru.point.sprind.adapters.decorators.FeedProductDecorator
import ru.point.sprind.adapters.decorators.spans.ResultSpanSizeLookup
import ru.point.sprind.components.SprindApplication
import ru.point.sprind.databinding.FragmentResultBinding
import ru.point.sprind.view.ConnectableLayout
import javax.inject.Inject

class ResultProductFeedFragment : MvpAppCompatFragment(), ResultProductFeedView {

    private val args: ResultProductFeedFragmentArgs by navArgs()

    @Inject
    lateinit var presenterProvider: ResultProductFeedPresenter.Factory
    private val presenter: ResultProductFeedPresenter by moxyPresenter {
        presenterProvider.create(request = args.request)
    }

    private var _binding: FragmentResultBinding? = null
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
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initRecyclerView()
    }

    private fun initToolbar() {
        binding.toolbar.apply {
            searchText = args.request
            setOnSearchbarClickListener {
                val direction =
                    ResultProductFeedFragmentDirections.actionResultFragmentToSearchFragment(request = args.request)
                findNavController().navigate(directions = direction)
            }

            setOnAddressClickListener {
                val direction = ResultProductFeedFragmentDirections.actionGlobalMapFragment()
                findNavController().navigate(directions = direction)
            }

            setOnBackClickListener { findNavController().popBackStack() }
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            adapter = pagingAdapter
            addItemDecoration(FeedProductDecorator())

            val layoutManager = layoutManager as GridLayoutManager
            layoutManager.spanSizeLookup = ResultSpanSizeLookup(
                delegates = presenter.viewDelegates,
                adapter = pagingAdapter
            )
        }

        pagingAdapter.addLoadStateListener { state ->
            if (state.hasError) {
                val stateError = state.append as? LoadState.Error
                    ?: state.source.prepend as? LoadState.Error
                    ?: state.prepend as? LoadState.Error
                    ?: state.refresh as? LoadState.Error
                stateError?.let { presenter.handleException(stateError.error) }
            }
        }
    }

    override fun showBadConnection(show: Boolean) {
        binding.root.currentState = ConnectableLayout.ConnectionState.BAD_CONNECTION
    }

    override fun showLoading(show: Boolean) {
        binding.root.currentState = ConnectableLayout.ConnectionState.LOADING
    }

    override fun navigateToProductCard(productId: Long) {
        val direction =
            ResultProductFeedFragmentDirections.actionResultFragmentToProductCardFragment(
                productId = productId
            )
        findNavController().navigate(directions = direction)
    }

    override fun navigateToAuthorization() {
        val direction = ResultProductFeedFragmentDirections.actionGlobalAuthorizationFragment()
        findNavController().navigate(directions = direction)
    }

    override fun showSomethingGoesWrongError() {
        Toast.makeText(context, getString(R.string.someting_goes_wrong_hint), Toast.LENGTH_SHORT)
            .show()
    }

    override fun setAdapter(views: PagingData<ViewObject>) {
        pagingAdapter.submitData(lifecycle, views)
        binding.root.currentState = ConnectableLayout.ConnectionState.SUCCESS
    }

    override fun setAddress(address: String?) {
        binding.toolbar.address = address
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