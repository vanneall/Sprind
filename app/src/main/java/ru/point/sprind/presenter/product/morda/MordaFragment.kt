package ru.point.sprind.presenter.product.morda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.point.domain.entity.view.ViewObject
import ru.point.sprind.R
import ru.point.sprind.adapters.MordaAdapterPaging
import ru.point.sprind.adapters.decorators.FeedProductDecorator
import ru.point.sprind.components.SprindApplication
import ru.point.sprind.databinding.FragmentMordaBinding
import ru.point.sprind.presenter.cart.CartFragmentDirections
import javax.inject.Inject

class MordaFragment : MvpAppCompatFragment(), MordaView {

    @Inject
    lateinit var presenterProvider: MordaPresenter
    private val presenter: MordaPresenter by moxyPresenter { presenterProvider }

    private var _binding: FragmentMordaBinding? = null
    private val binding get() = _binding!!

    private var _adapter: MordaAdapterPaging? = null
    private val adapter get() = _adapter!!

    override fun onCreate(savedInstanceState: Bundle?) {
        SprindApplication.component.inject(fragment = this)
        super.onCreate(savedInstanceState)
        _adapter = MordaAdapterPaging(presenter.delegates)
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
        initializeToolbar()
        initializeRecyclerView()
    }

    private fun initializeToolbar() {
        with(binding.toolbar) {
            search.isFocusable = false

            search.setOnClickListener {
                val destination =
                    MordaFragmentDirections.actionMordaFragmentToSearchFragment(request = null)
                findNavController().navigate(destination)
            }

            address.setOnClickListener {
                val destination = MordaFragmentDirections.actionGlobalMapFragment()
                findNavController().navigate(destination)
            }
        }
    }

    private fun initializeRecyclerView() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(FeedProductDecorator())
    }

    override fun openCard(id: Long) {
        val args = MordaFragmentDirections.actionMordaFragmentToProductCardFragment(
            productId = id
        )

        binding.root.findNavController().navigate(args)
    }

    override fun requireAuthorization() {
        findNavController().navigate(CartFragmentDirections.actionGlobalAuthorizationFragment())
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

    override fun setAddress(address: String?) {
        if (address != null) {
            binding.toolbar.address.text = address
            binding.toolbar.address.setTextColor(resources.getColor(R.color.md_theme_tertiaryContainer))
        }
    }

    override fun setAdapter(views: PagingData<ViewObject>?) {
        views?.let { adapter.submitData(lifecycle, views) }
    }

    override fun displaySomethingGoesWrongError() {
        Toast.makeText(
            requireContext(), getString(R.string.someting_goes_wrong_hint), Toast.LENGTH_SHORT
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