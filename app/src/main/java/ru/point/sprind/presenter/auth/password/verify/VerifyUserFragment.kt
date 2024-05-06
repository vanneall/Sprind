package ru.point.sprind.presenter.auth.password.verify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.point.sprind.databinding.FragmentVerifyUserBinding

class VerifyUserFragment : Fragment() {

    private lateinit var binding: FragmentVerifyUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentVerifyUserBinding.inflate(layoutInflater)
        return binding.root
    }
}