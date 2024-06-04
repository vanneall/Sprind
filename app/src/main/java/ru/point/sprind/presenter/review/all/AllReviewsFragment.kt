package ru.point.sprind.presenter.review.all

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.point.domain.entity.view.ViewObject
import ru.point.sprind.R
import ru.point.sprind.adapters.MordaAdapter
import ru.point.sprind.adapters.decorators.ProductInfoDecorator
import ru.point.sprind.components.SprindApplication
import ru.point.sprind.databinding.FragmentAllReviewsBinding
import javax.inject.Inject

class AllReviewsFragment : MvpAppCompatFragment(), AllReviewsView {

    private val args by navArgs<AllReviewsFragmentArgs>()

    @Inject
    lateinit var provider: AllReviewsPresenterFactory
    private val presenter by moxyPresenter { provider.create(args.id) }

    private var _binding: FragmentAllReviewsBinding? = null
    private val binding get() = _binding!!

    private var _adapter: MordaAdapter? = null
    private val adapter get() = _adapter!!

    override fun onCreate(savedInstanceState: Bundle?) {
        SprindApplication.component.inject(this)
        super.onCreate(savedInstanceState)
        _adapter = MordaAdapter(delegates = presenter.delegates)
        lifecycle.addObserver(adapter)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAllReviewsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.title.text = resources.getText(R.string.reviews)
        initializeRecyclerView()

        binding.addReview.setOnClickListener {
            val destination =
                AllReviewsFragmentDirections.actionAllReviewsFragmentToCreateReviewFragment(
                    productId = args.id
                )
            findNavController().navigate(destination)
        }
    }

    private fun initializeRecyclerView() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(ProductInfoDecorator())
    }

    override fun onResume() {
        super.onResume()
        presenter.init()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        _adapter = null
    }
}