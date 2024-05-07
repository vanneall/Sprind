package ru.point.sprind.presenter.auth.registration.credentials

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.point.sprind.SprindApplication
import ru.point.sprind.databinding.FragmentCredentialsBinding
import javax.inject.Inject

class CredentialsFragment : MvpAppCompatFragment(), CredentialsView {

    private lateinit var binding: FragmentCredentialsBinding

    @Inject
    lateinit var provider: CredentialsPresenter

    private val presenter by moxyPresenter { provider }

    override fun onCreate(savedInstanceState: Bundle?) {
        SprindApplication.component.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCredentialsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonStartRegistration.setOnClickListener {
            val name: String
            val secondName: String
            val telephone: String
            val email: String

            with(binding) {
                name = inputName.text.toString()
                secondName = inputSecondName.text.toString()
                telephone = inputTelephone.text.toString()
                email = inputEmail.text.toString()
            }

            if (!presenter.isDataValid(
                    name = name,
                    secondName = secondName,
                    telephone = telephone,
                    email = email
                )
            ) return@setOnClickListener

            val destination = CredentialsFragmentDirections
                .actionCredentialsFragmentToRegistrationFragment(
                    name = name,
                    secondName = secondName,
                    telephone = telephone,
                    email = email
                )

            findNavController().navigate(destination)
        }
    }
}