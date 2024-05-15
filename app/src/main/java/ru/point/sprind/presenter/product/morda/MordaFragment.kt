package ru.point.sprind.presenter.product.morda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.point.domain.entity.view.ViewObject
import ru.point.sprind.R
import ru.point.sprind.adapters.MordaAdapter
import ru.point.sprind.adapters.decorators.FeedProductDecorator
import ru.point.sprind.components.SprindApplication
import ru.point.sprind.databinding.FragmentMordaBinding
import ru.point.sprind.presenter.cart.CartFragmentDirections
import javax.inject.Inject

class MordaFragment : MvpAppCompatFragment(), MordaView {

    private var _binding: FragmentMordaBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: MordaAdapter

    @Inject
    lateinit var presenterProvider: MordaPresenter

    private val presenter: MordaPresenter by moxyPresenter { presenterProvider }


    override fun onCreate(savedInstanceState: Bundle?) {
        SprindApplication.component.inject(fragment = this)
        super.onCreate(savedInstanceState)
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

    override fun onStart() {
        super.onStart()
        presenter.init()
    }

    private fun initializeToolbar() {
        with(binding.toolbar) {
            search.isFocusable = false

            search.setOnClickListener {
                val destination =
                    MordaFragmentDirections.actionMordaFragmentToSearchFragment(request = null)
                findNavController().navigate(destination)
            }
        }
    }

    private fun initializeRecyclerView() {
        adapter = MordaAdapter(presenter.delegates)
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
            binding.toolbar.address.setTextColor(resources.getColor(R.color.brown_orange))
        }
    }

    override fun setAdapter(views: List<ViewObject>) {
        adapter.views = views
    }

    override fun displaySomethingGoesWrongError() {
        Toast.makeText(
            this@MordaFragment.context,
            getString(R.string.someting_goes_wrong_hint),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.adapter = null
        _binding = null
    }
}