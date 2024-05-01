package ru.point.sprind.presenter.morda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.point.domain.entity.view.ViewObject
import ru.point.sprind.R
import ru.point.sprind.SprindApplication
import ru.point.sprind.adapters.MordaAdapter
import ru.point.sprind.adapters.decorators.FeedProductDecorator
import ru.point.sprind.databinding.FragmentMordaBinding
import ru.point.sprind.entity.deletage.Delegate
import javax.inject.Inject

class MordaFragment : MvpAppCompatFragment(), MordaView {

    @Inject
    lateinit var presenterProvider: MordaPresenter

    private val presenter: MordaPresenter by moxyPresenter { presenterProvider }

    private lateinit var binding: FragmentMordaBinding

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
        binding.toolbar.search.isFocusable = false

        binding.toolbar.search.setOnClickListener {
            findNavController().navigate(R.id.action_mordaFragment_to_searchFragment)
        }
    }

    private fun initializeRecyclerView() {
        binding.recyclerView.addItemDecoration(FeedProductDecorator())
    }

    override fun setProductAdapter(views: List<ViewObject>, delegates: List<Delegate<*>>) {
        val adapter = MordaAdapter(delegates = delegates)
        adapter.views = views
        binding.recyclerView.adapter = adapter
    }

    override fun showNotFoundScreen() {
        binding.notFoundScreen.root.visibility = View.VISIBLE
    }

    override fun showBadConnectionScreen() {
        binding.badConnection.root.visibility = View.VISIBLE
    }

    override fun showLoadingScreen() {
        binding.loadingScreen.root.visibility = View.VISIBLE
    }

    override fun disableLoadingScreen() {
        binding.loadingScreen.root.visibility = View.GONE
    }

    override fun openCard(id: Long) {
        val args = MordaFragmentDirections.actionMordaFragmentToProductCardFragment(
            productId = id
        )

        binding.root.findNavController().navigate(args)
    }
}