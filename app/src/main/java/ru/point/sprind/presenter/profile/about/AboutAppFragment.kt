package ru.point.sprind.presenter.profile.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.point.domain.utils.UtilsConst.YANDEX_MAP_TERMS_URL
import ru.point.sprind.databinding.FragmentAboutAppBinding

class AboutAppFragment : Fragment() {

    private var _binding: FragmentAboutAppBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAboutAppBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()

        binding.yandexMapsTerms.setOnClickListener {
            val direction = AboutAppFragmentDirections.actionAboutAppFragmentToWebViewFragment(
                url = YANDEX_MAP_TERMS_URL
            )
            findNavController().navigate(directions = direction)
        }
    }

    private fun initToolbar() {
        binding.toolbar.setOnBackClickListener { findNavController().popBackStack() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}