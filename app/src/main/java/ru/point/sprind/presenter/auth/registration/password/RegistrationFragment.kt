package ru.point.sprind.presenter.auth.registration.password

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
import ru.point.sprind.databinding.FragmentRegistrationBinding
import javax.inject.Inject

class RegistrationFragment : MvpAppCompatFragment(), RegistrationView {

    private var _binding: FragmentRegistrationBinding? = null

    private val binding get() = _binding!!

    private val args by navArgs<RegistrationFragmentArgs>()

    @Inject
    lateinit var provider: RegistrationPresenterFactory

    private val presenter by moxyPresenter {
        provider.create(
            name = args.name,
            secondName = args.secondName,
            telephone = args.telephone,
            email = args.email
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        SprindApplication.component.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRegistrationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonStartRegistration.setOnClickListener {

            val username: String
            val secret: String
            val password: String

            with(binding) {
                username = inputLogin.text.toString()
                secret = inputSecret.text.toString()
                password = inputPassword.text.toString()
            }

            presenter.register(
                username = username,
                secret = secret,
                password = password
            )
        }

    }

    override fun showError() {
        Toast.makeText(
            requireContext(),
            R.string.someting_goes_wrong_hint,
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun exitFromRegistration() {
        findNavController().popBackStack(R.id.authorizationFragment, inclusive = true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}