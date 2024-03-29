package ru.point.sprind.presenter.morda

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import moxy.presenter.InjectPresenter
import ru.point.sprind.R
import ru.point.sprind.SprindApplication
import ru.point.sprind.adapters.MordaAdapter
import ru.point.sprind.adapters.ProductDecorator
import ru.point.sprind.databinding.FragmentMordaBinding
import javax.inject.Inject

class MordaFragment : Fragment(), MordaView {

    @Inject
    @InjectPresenter
    lateinit var presenter: MordaPresenter

    init {
        SprindApplication.component.inject(fragment = this)
    }

    private lateinit var binding: FragmentMordaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMordaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecyclerView()

        binding.toolbar.search.isFocusable = false
        binding.toolbar.search.setOnClickListener {
            findNavController().navigate(R.id.action_mordaFragment_to_searchFragment)
            Log.d("navigation", "clicked")
        }
    }

    private fun initializeRecyclerView() {
        binding.recyclerView.adapter = MordaAdapter(
            delegates = presenter.delegates,
            views = presenter.products
        )

        binding.recyclerView.addItemDecoration(ProductDecorator())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(REQUEST_KEY, binding.toolbar.search.text.toString())
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        if (savedInstanceState != null) {
            binding.toolbar.search.setText(savedInstanceState.getString(REQUEST_KEY))
        }
    }

    companion object {
        const val REQUEST_KEY = "REQUEST_KEY"
    }
}