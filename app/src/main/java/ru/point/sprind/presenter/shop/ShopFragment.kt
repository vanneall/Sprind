package ru.point.sprind.presenter.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.point.domain.entity.view.ViewObject
import ru.point.sprind.adapters.SprindPagingAdapter
import ru.point.sprind.adapters.decorators.FeedProductDecorator
import ru.point.sprind.adapters.decorators.spans.MordaSpanSizeLookup
import ru.point.sprind.components.SprindApplication
import ru.point.sprind.databinding.FragmentShopBinding
import ru.point.sprind.view.ConnectableLayout
import javax.inject.Inject

class ShopFragment : MvpAppCompatFragment(), ShopView {

    private val args by navArgs<ShopFragmentArgs>()

    @Inject
    lateinit var shopPresenterProvider: ShopPresenter.Factory
    private val presenter by moxyPresenter { shopPresenterProvider.create(shopId = args.shopId) }

    private var _binding: FragmentShopBinding? = null
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
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopBinding.inflate(layoutInflater, container, true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initRecyclerView()
    }

    private fun initToolbar() {
        binding.toolbar.apply {
            text = args.title
            setOnBackClickListener { findNavController().popBackStack() }
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

    override fun showBadConnection(show: Boolean) {
        binding.root.currentState = ConnectableLayout.ConnectionState.BAD_CONNECTION
    }

    override fun showLoading(show: Boolean) {
        binding.root.currentState = ConnectableLayout.ConnectionState.LOADING
    }

    override fun setAdapter(views: PagingData<ViewObject>) {
        pagingAdapter.submitData(lifecycle, views)
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