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

    @Inject
    lateinit var provider: ProfilePresenter
    private val presenter by moxyPresenter { provider }

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

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
        binding.logout.setOnClickListener {
            presenter.logout()
            activity?.finish()
        }

        binding.favorites.setOnClickListener {
            findNavController().navigate(R.id.action_profile_fragment_to_favorites_fragment)
        }

        binding.orders.setOnClickListener {
            findNavController().navigate(R.id.action_profile_fragment_to_ordersFragment)
        }

        binding.aboutApp.setOnClickListener {
            findNavController().navigate(R.id.action_profile_fragment_to_aboutAppFragment)
        }

        binding.themeSwitch.setOnClickListener {
            val isChecked = binding.themeSwitch.isChecked
            presenter.switchTheme(isChecked)
            (requireActivity().application as? SprindApplication)?.applyTheme(isChecked)
        }
    }

    override fun setUsername(name: String) {
        with(binding.greetings) {
            if (name.isNotEmpty()) {
                visibility = View.VISIBLE
                text = resources.getString(R.string.greetings, name)
            }
        }
    }

    override fun setIsDarkThemeEnabled(isEnabled: Boolean) {
        binding.themeSwitch.isChecked = isEnabled
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}