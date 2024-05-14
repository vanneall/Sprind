package ru.point.sprind.presenter.review.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.point.sprind.components.SprindApplication
import ru.point.sprind.databinding.FragmentCreateReviewBinding
import javax.inject.Inject


class CreateReviewFragment : MvpAppCompatFragment(), CreateReviewView {

    private var _binding: FragmentCreateReviewBinding? = null

    private val binding get() = _binding!!

    private val args by navArgs<CreateReviewFragmentArgs>()

    @Inject
    lateinit var provider: CreateReviewPresenterFactory

    private val presenter by moxyPresenter { provider.create(args.productId) }

    override fun onCreate(savedInstanceState: Bundle?) {
        SprindApplication.component.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCreateReviewBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.save.setOnClickListener {
            presenter.addReview(binding.rating.text.toString(), binding.commentary.text.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}