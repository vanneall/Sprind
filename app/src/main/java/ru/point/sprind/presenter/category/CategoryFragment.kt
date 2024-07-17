package ru.point.sprind.presenter.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.point.domain.entity.view.ViewObject
import ru.point.sprind.R
import ru.point.sprind.adapters.SprindPagingAdapter
import ru.point.sprind.adapters.decorators.FeedProductDecorator
import ru.point.sprind.adapters.decorators.spans.MordaSpanSizeLookup
import ru.point.sprind.components.SprindApplication
import ru.point.sprind.databinding.FragmentCategoryBinding
import ru.point.sprind.view.ConnectableLayout
import javax.inject.Inject

class CategoryFragment : MvpAppCompatFragment(), CategoryView {

    private val args by navArgs<CategoryFragmentArgs>()

    @Inject
    lateinit var presenterProvider: CategoryPresenter.Factory
    private val presenter: CategoryPresenter by moxyPresenter { presenterProvider.create(args.categoryId) }

    private var _binding: FragmentCategoryBinding? = null
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
        _binding = FragmentCategoryBinding.inflate(layoutInflater, container, false)
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

    override fun setAdapter(views: PagingData<ViewObject>) {
        pagingAdapter.submitData(lifecycle, views)
        binding.root.currentState = ConnectableLayout.ConnectionState.SUCCESS
    }

    override fun navigateToProductCard(productId: Long) {
        val direction = CategoryFragmentDirections.actionCategoryFragmentToProductCardFragment(
            productId = productId
        )
        findNavController().navigate(directions = direction)
    }

    override fun navigateToAuthorization() {
        val direction = CategoryFragmentDirections.actionGlobalAuthorizationFragment()
        findNavController().navigate(directions = direction)
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