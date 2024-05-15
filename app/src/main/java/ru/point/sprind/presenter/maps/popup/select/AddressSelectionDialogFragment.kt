package ru.point.sprind.presenter.maps.popup.select

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import moxy.MvpBottomSheetDialogFragment
import moxy.ktx.moxyPresenter
import ru.point.sprind.R
import ru.point.sprind.components.SprindApplication
import ru.point.sprind.databinding.FragmentAddressSelectionDialogBinding
import javax.inject.Inject


class AddressSelectionDialogFragment : MvpBottomSheetDialogFragment(), AddressSelectionView {

    private var _binding: FragmentAddressSelectionDialogBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<AddressSelectionDialogFragmentArgs>()

    @Inject
    lateinit var provider: AddressSelectionPresenter

    private val presenter by moxyPresenter { provider }

    override fun onCreate(savedInstanceState: Bundle?) {
        SprindApplication.component.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAddressSelectionDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initializeEditTexts()
        initializeCommitButton()
    }

    private fun initializeEditTexts() {
        with(binding) {
            inputCity.setText(args.city)
            inputStreet.setText(args.street)
            inputHouse.setText(args.house)
        }
    }

    private fun initializeCommitButton() {
        with(binding) {
            selectButton.setOnClickListener {
                presenter.commit(
                    city = binding.inputCity.text.toString(),
                    street = binding.inputStreet.text.toString(),
                    house = binding.inputHouse.text.toString(),
                    flat = binding.inputFlat.text.toString()
                )
            }
        }
    }

    override fun closeAddressSelectionStack() {
        findNavController().popBackStack(R.id.mapFragment, true)
    }

    override fun displayErrorOnInputLayout(
        isCityError: Boolean,
        isStreetError: Boolean,
        isHouseError: Boolean,
        isFlatError: Boolean,
    ) {
        with(binding) {
            if (isCityError) {
                inputCityLayout.error = getString(R.string.field_can_not_be_empty)
            }

            if (isStreetError) {
                inputStreetLayout.error = getString(R.string.field_can_not_be_empty)
            }

            if (isHouseError) {
                inputHouseLayout.error = getString(R.string.field_can_not_be_empty)
            }

            if (isFlatError) {
                inputFlatLayout.error = getString(R.string.field_can_not_be_empty)
            }
        }
    }

    override fun displaySomethingGoesWrongError() {
        Toast.makeText(
            requireContext(),
            getString(R.string.someting_goes_wrong_hint),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}