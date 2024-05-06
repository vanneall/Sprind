package ru.point.sprind.presenter.auth.authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.point.sprind.databinding.FragmentAuthorizationBinding

class AuthorizationFragment : Fragment() {

    private lateinit var binding: FragmentAuthorizationBinding;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAuthorizationBinding.inflate(layoutInflater)
        return binding.root
    }
}