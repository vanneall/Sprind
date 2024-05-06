package ru.point.sprind.presenter.auth.password.change

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.point.sprind.databinding.FragmentChangePassowrdBinding


class ChangePasswordFragment : Fragment() {

    private lateinit var binding: FragmentChangePassowrdBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentChangePassowrdBinding.inflate(layoutInflater)
        return binding.root
    }
}