package ru.point.sprind.presenter.thanks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.point.sprind.R
import ru.point.sprind.databinding.FragmentThanksBinding

class ThanksFragment : Fragment() {

    private var _binding: FragmentThanksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentThanksBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.title.text = resources.getString(R.string.thanks_screen_title);
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}