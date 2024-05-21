package ru.point.sprind.presenter.product.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.point.domain.entity.view.ViewObject
import ru.point.sprind.R
import ru.point.sprind.adapters.MordaAdapter
import ru.point.sprind.adapters.decorators.FeedProductDecorator
import ru.point.sprind.components.SprindApplication
import ru.point.sprind.databinding.FragmentResultBinding
import ru.point.sprind.presenter.cart.CartFragmentDirections
import ru.point.sprind.presenter.product.morda.MordaView
import javax.inject.Inject

class ResultFragment : MvpAppCompatFragment(), MordaView {

    private var _binding: FragmentResultBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: MordaAdapter

    @Inject
    lateinit var presenterProvider: ResultPresenterAssistedFactory

    private val presenter: ResultPresenter by moxyPresenter {
        presenterProvider.create(request = args.request)
    }

    private val args: ResultFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        SprindApplication.component.inject(this)
        super.onCreate(savedInstanceState)
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
            search.setText(args.request)
            search.setOnClickListener {
                val destination = ResultFragmentDirections
                    .actionResultFragmentToSearchFragment(request = args.request)
                findNavController().navigate(destination)
            }

            address.setOnClickListener {
                val destination = ResultFragmentDirections.actionGlobalMapFragment()
                findNavController().navigate(destination)
            }
        }
    }

    private fun initializeRecyclerView() {
        adapter = MordaAdapter(presenter.delegates)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(FeedProductDecorator())
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

    override fun openCard(id: Long) {
        val args = ResultFragmentDirections.actionResultFragmentToProductCardFragment(
            productId = id
        )

        binding.root.findNavController().navigate(args)
    }

    override fun requireAuthorization() {
        findNavController().navigate(CartFragmentDirections.actionGlobalAuthorizationFragment())
    }

    override fun displaySomethingGoesWrongError() {
        Toast.makeText(
            requireContext(),
            getString(R.string.someting_goes_wrong_hint),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun setAddress(address: String?) {
        if (address != null) {
            binding.toolbar.address.text = address
            binding.toolbar.address.setTextColor(resources.getColor(R.color.md_theme_tertiaryContainer))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.adapter = null
        _binding = null
    }
}