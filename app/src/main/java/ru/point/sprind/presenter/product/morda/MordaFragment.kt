package ru.point.sprind.presenter.product.morda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.point.domain.entity.view.ViewObject
import ru.point.sprind.adapters.MordaAdapter
import ru.point.sprind.adapters.decorators.FeedProductDecorator
import ru.point.sprind.components.SprindApplication
import ru.point.sprind.databinding.FragmentMordaBinding
import javax.inject.Inject

class MordaFragment : MvpAppCompatFragment(), MordaView {

    private lateinit var binding: FragmentMordaBinding

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
        binding = FragmentMordaBinding.inflate(inflater, container, false)
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
}