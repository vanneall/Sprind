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

    override fun setUsername(name: String?) {
        binding.greetings.apply {
            if (name != null) {
                visibility = View.VISIBLE
                text = resources.getString(R.string.greetings, name)
            }
        }
    }

    override fun setLogIn() {
        binding.accountLogout.visibility = View.GONE
        binding.accountLogin.visibility = View.VISIBLE
        binding.accountLogin.setOnClickListener {
            navigateToAuthorization()
        }
    }

    override fun setLogOut() {
        binding.accountLogin.visibility = View.GONE
        binding.accountLogout.visibility = View.VISIBLE

        binding.accountLogout.setOnClickListener {
            presenter.logout()
            activity?.finish()
        }
    }

    override fun navigateToAuthorization() {
        val direction = ProfileFragmentDirections.actionGlobalAuthorizationFragment()
        findNavController().navigate(directions = direction)
    }


    override fun setIsDarkThemeEnabled(isEnabled: Boolean) {
        binding.themeSwitch.isChecked = isEnabled
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}