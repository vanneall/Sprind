package ru.point.sprind.presenter.maps.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.layers.GeoObjectTapEvent
import com.yandex.mapkit.layers.GeoObjectTapListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.GeoObjectSelectionMetadata
import com.yandex.mapkit.map.InputListener
import com.yandex.mapkit.map.Map
import moxy.MvpAppCompatFragment
import ru.point.sprind.R
import ru.point.sprind.components.SprindApplication
import ru.point.sprind.databinding.FragmentMapBinding
import java.util.Locale
import javax.inject.Inject

class MapFragment : MvpAppCompatFragment() {

    private var _binding: FragmentMapBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var fusedLocationClient: FusedLocationProviderClient

    private val onObjectTap = GeoObjectTapListener { geoObjectTapEvent ->
        val selectionMetadata = geoObjectTapEvent
            .geoObject
            .metadataContainer
            .getItem(GeoObjectSelectionMetadata::class.java)

        try {
            binding.mapView.mapWindow.map.selectGeoObject(selectionMetadata)

            val address = getAddress(geoObjectTapEvent)
            Log.e("MapView", "Address selected: $address")
            val destination =
                MapFragmentDirections.actionMapFragment2ToAddressSelectionDialogFragment(
                    address.city, address.street, address.house
                )

            findNavController().navigate(destination)

        } catch (ex: Exception) {
            ex.printStackTrace()
            Toast.makeText(
                requireContext(),
                resources.getText(R.string.bad_connection),
                Toast.LENGTH_SHORT
            ).show()
        }
        selectionMetadata != null
    }

    private data class Address(
        val city: String,
        val street: String,
        val house: String,
    )

    private val inputListener = object : InputListener {

        override fun onMapTap(map: Map, point: Point) {
            binding.mapView.mapWindow.map.deselectGeoObject()
        }

        override fun onMapLongTap(map: Map, point: Point) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        SprindApplication.component.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMapBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestLocationPermission()
        initializeMapView()
    }

    private fun initializeMapView() {
        binding.mapView.mapWindow.map.addTapListener(onObjectTap)
        binding.mapView.mapWindow.map.addInputListener(inputListener)
    }

    private fun requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        }

        getLastKnownLocation()
    }

    private fun getAddress(geoObjectTapEvent: GeoObjectTapEvent): Address {
        val geocoder = Geocoder(requireContext(), Locale.getDefault())
        val latitude = geoObjectTapEvent.geoObject.geometry.first().point?.latitude
        val longitude = geoObjectTapEvent.geoObject.geometry.first().point?.longitude

        if (latitude == null || longitude == null) return Address("", "", "")

        val addresses = geocoder.getFromLocation(latitude, longitude, 1)
        return if (!addresses.isNullOrEmpty()) {
            val address = addresses.first()
            Address(
                city = address.locality,
                street = address.thoroughfare,
                house = address.subThoroughfare
            )
        } else {
            Address("", "", "")
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastKnownLocation() {

        if (!checkIsAccessToLocationGranted()) return

        fusedLocationClient.lastLocation.addOnCompleteListener(requireActivity()) { task ->
            if (!task.isSuccessful) return@addOnCompleteListener

            val location = task.result
            val latitude = location.latitude
            val longitude = location.longitude
            val startLocation = Point(latitude, longitude)

            binding.mapView.mapWindow.map.move(
                CameraPosition(startLocation, 17.0f, 150.0f, 30.0f),
                Animation(Animation.Type.SMOOTH, 1f),
                null
            )
        }
    }

    private fun checkIsAccessToLocationGranted(): Boolean {
        val locationPerm = ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        val locationAccessPerm = ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        val isLocationPermGranted = locationPerm == PackageManager.PERMISSION_GRANTED
        val isLocationAccessPermGranted = locationAccessPerm == PackageManager.PERMISSION_GRANTED

        return isLocationPermGranted && isLocationAccessPermGranted
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        binding.mapView.onStart()
    }

    override fun onStop() {
        binding.mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}