package ru.point.sprind.presenter.review.all

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.point.sprind.SprindApplication
import ru.point.sprind.databinding.FragmentAllReviewsBinding
import javax.inject.Inject

class AllReviewsFragment : MvpAppCompatFragment(), AllReviewsView {

    private lateinit var binding: FragmentAllReviewsBinding

    @Inject
    lateinit var provider: AllReviewsPresenter

    private val presenter by moxyPresenter { provider }

    override fun onCreate(savedInstanceState: Bundle?) {
        SprindApplication.component.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAllReviewsBinding.inflate(layoutInflater)
        return binding.root
    }
}