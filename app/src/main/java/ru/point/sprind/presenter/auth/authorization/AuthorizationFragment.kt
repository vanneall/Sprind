package ru.point.sprind.presenter.auth.authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.point.sprind.R
import ru.point.sprind.components.SprindApplication
import ru.point.sprind.databinding.FragmentAuthorizationBinding
import javax.inject.Inject

class AuthorizationFragment : MvpAppCompatFragment(), AuthView {

    private var _binding: FragmentAuthorizationBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var provider: AuthPresenter

    private val presenter: AuthPresenter by moxyPresenter { provider }

    override fun onCreate(savedInstanceState: Bundle?) {
        SprindApplication.component.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAuthorizationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeLogInButton()
        initializeRegInButton()
        initializeResetPasswordButton()
    }

    private fun initializeLogInButton() {
        binding.buttonEnter.setOnClickListener {
            presenter.auth(
                binding.inputLogin.text.toString(),
                binding.inputPassword.text.toString()
            )
        }
    }

    override fun displayWrongCredentials() {
        binding.inputLoginLayout.error = getString(R.string.wrong_credentials)
        binding.inputPasswordLayout.error = getString(R.string.wrong_credentials)
    }

    private fun initializeRegInButton() {
        binding.buttonStartRegistration.setOnClickListener {
            findNavController().navigate(
                directions = AuthorizationFragmentDirections
                    .actionAuthorizationFragmentToCredentialsFragment()
            )
        }
    }

    private fun initializeResetPasswordButton() {
        binding.textResetPassword.setOnClickListener {
            findNavController().navigate(
                directions = AuthorizationFragmentDirections
                    .actionAuthorizationFragmentToVerifyUserFragment()
            )
        }
    }

    override fun displayErrorOnInputLayout() {
        with(binding) {
            if (inputLogin.text.isNullOrEmpty()) {
                inputLoginLayout.error = getString(R.string.field_can_not_be_empty)
            }

            if (inputPassword.text.isNullOrEmpty()) {
                inputPasswordLayout.error = getString(R.string.field_can_not_be_empty)
            }
        }
    }

    override fun successfulAuthorization() {
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}