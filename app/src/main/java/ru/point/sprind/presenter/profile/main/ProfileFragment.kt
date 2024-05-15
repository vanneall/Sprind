package ru.point.sprind.presenter.profile.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.point.sprind.R
import ru.point.sprind.components.SprindApplication
import ru.point.sprind.databinding.FragmentProfileBinding
import javax.inject.Inject

class ProfileFragment : MvpAppCompatFragment(), ProfileView {

    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var provider: ProfilePresenter

    private val presenter by moxyPresenter { provider }

    override fun onCreate(savedInstanceState: Bundle?) {
        SprindApplication.component.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.title.text = resources.getString(R.string.profile)

        binding.logout.setOnClickListener {
            presenter.logout()
            activity?.finish()
        }

        binding.favorites.setOnClickListener {
            findNavController().navigate(R.id.action_profile_fragment_to_favorites_fragment)
        }

        binding.aboutApp.setOnClickListener {
            findNavController().navigate(R.id.action_profile_fragment_to_aboutAppFragment)
        }
    }

    override fun setUsername(name: String) {
        with(binding.greetings) {
            if (name.isNotEmpty()) {
                text = resources.getString(R.string.greetings, name)
            } else {
                visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}