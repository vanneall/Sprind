package ru.point.sprind.presenter.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
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

class FavoritesFragment : MvpAppCompatFragment(), FavoriteView {

    @Inject
    lateinit var presenterProvider: FavoritePresenter
    private val presenter by moxyPresenter { presenterProvider }

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private var _adapter: SprindPagingAdapter? = null
    private val adapter get() = _adapter!!


    override fun onCreate(savedInstanceState: Bundle?) {
        SprindApplication.component.inject(this)
        super.onCreate(savedInstanceState)
        _adapter = SprindPagingAdapter(delegates = presenter.delegates)
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
        initializeToolbar()
        initializeRecyclerView()
    }

    private fun initializeToolbar() {
        with(binding) {
            favoritesToolbar.title.text = resources.getString(R.string.favorites_screen_title)
        }
    }

    private fun initializeRecyclerView() {
        with(binding) {
            favoritesRecyclerView.adapter = adapter
            val layoutManager = favoritesRecyclerView.layoutManager as GridLayoutManager
            layoutManager.spanSizeLookup = FavoriteSpanSizeLookup(presenter.delegates, adapter)
            favoritesRecyclerView.addItemDecoration(FavoritesItemDecorator())
        }
    }

    override fun setAdapter(views: PagingData<ViewObject>) {
        adapter.submitData(lifecycle, views)
        binding.root.currentState = ConnectableLayout.ConnectionState.SUCCESS
    }

    override fun openCard(id: Long) {
        val args = FavoritesFragmentDirections.actionFavoritesFragmentToProductCardFragment(
            productId = id
        )

        binding.root.findNavController().navigate(args)
    }

    override fun requireAuthorization() {
        binding.authorizeWarning.root.visibility = View.VISIBLE
        binding.authorizeWarning.authorizeButton.setOnClickListener {
            findNavController().navigate(FavoritesFragmentDirections.actionGlobalAuthorizationFragment())
        }
    }

    override fun showBadConnection(show: Boolean) {
        binding.root.currentState = ConnectableLayout.ConnectionState.BAD_CONNECTION
    }

    override fun showLoading(show: Boolean) {
        binding.root.currentState = ConnectableLayout.ConnectionState.LOADING
    }

    override fun showSomethingGoesWrongError() {
        Toast.makeText(
            requireContext(),
            getString(R.string.someting_goes_wrong_hint),
            Toast.LENGTH_SHORT
        ).show()
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