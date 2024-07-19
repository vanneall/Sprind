package ru.point.sprind.presenter.review.all

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.PagingData
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.point.domain.entity.view.ViewObject
import ru.point.sprind.adapters.SprindPagingAdapter
import ru.point.sprind.adapters.decorators.ProductInfoDecorator
import ru.point.sprind.components.SprindApplication
import ru.point.sprind.databinding.FragmentProductReviewsBinding
import ru.point.sprind.view.ConnectableLayout
import javax.inject.Inject

class ProductReviewsFragment : MvpAppCompatFragment(), ProductReviewsView {

    private val args by navArgs<ProductReviewsFragmentArgs>()

    @Inject
    lateinit var provider: ProductReviewsPresenter.Factory
    private val presenter by moxyPresenter { provider.create(args.id) }

    private var _binding: FragmentProductReviewsBinding? = null
    private val binding get() = _binding!!

    private var _pagingAdapter: SprindPagingAdapter? = null
    private val pagingAdapter get() = _pagingAdapter!!

    override fun onCreate(savedInstanceState: Bundle?) {
        SprindApplication.component.inject(this)
        super.onCreate(savedInstanceState)
        _pagingAdapter = SprindPagingAdapter(delegates = presenter.delegates)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProductReviewsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initRecyclerView()
        initAddReviewButton()
    }

    override fun onStart() {
        super.onStart()
        refresh()
    }

    private fun initToolbar() {
        binding.toolbar.setOnBackClickListener { findNavController().popBackStack() }
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            adapter = pagingAdapter
            addItemDecoration(ProductInfoDecorator())
        }
    }

    private fun initAddReviewButton() {
        binding.addReview.setOnClickListener {
            val direction =
                ProductReviewsFragmentDirections.actionAllReviewsFragmentToCreateReviewFragment(
                    productId = args.id
                )
            findNavController().navigate(direction)
        }
    }

    override fun showBadConnection(show: Boolean) {
        binding.root.currentState = ConnectableLayout.ConnectionState.BAD_CONNECTION
    }

    override fun showLoading(show: Boolean) {
        binding.root.currentState = ConnectableLayout.ConnectionState.LOADING
    }

    override fun setAdapter(views: PagingData<ViewObject>) {
        pagingAdapter.submitData(lifecycle, views)
        binding.root.currentState = ConnectableLayout.ConnectionState.SUCCESS
    }

    override fun refresh() {
        pagingAdapter.refresh()
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