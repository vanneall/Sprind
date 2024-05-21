package ru.point.sprind.presenter.review.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.point.sprind.R
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
        binding.toolbar.title.text = getString(R.string.create_review_title)

        binding.save.setOnClickListener {
            with(binding) {
                presenter.addReview(
                    rating = rating.text.toString(),
                    description = commentary.text.toString(),
                    advantage = advantages.text.toString(),
                    disadvantage = disadvantages.text.toString()
                )
            }
        }
    }

    override fun requireAuthorization() {
        findNavController().navigate(CreateReviewFragmentDirections.actionGlobalAuthorizationFragment())
    }

    override fun displaySomethingGoesWrongError() {
        Toast.makeText(
            requireContext(),
            getString(R.string.someting_goes_wrong_hint),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun exit() {
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}