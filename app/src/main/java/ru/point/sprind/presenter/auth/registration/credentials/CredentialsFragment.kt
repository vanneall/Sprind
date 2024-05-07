package ru.point.sprind.presenter.auth.registration.credentials

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.point.sprind.databinding.FragmentCredentialsBinding

class CredentialsFragment : Fragment() {

    private lateinit var binding: FragmentCredentialsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCredentialsBinding.inflate(layoutInflater)
        return binding.root
    }
}