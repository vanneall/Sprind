package ru.point.sprind.presenter.review.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.point.sprind.databinding.FragmentCreateReviewBinding


class CreateReviewFragment : Fragment() {

    private lateinit var binding: FragmentCreateReviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCreateReviewBinding.inflate(layoutInflater)
        return binding.root
    }
}