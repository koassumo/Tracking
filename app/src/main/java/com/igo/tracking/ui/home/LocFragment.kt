package com.igo.tracking.ui.home

import android.graphics.Color
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.igo.tracking.R
import com.igo.tracking.databinding.FragmentLocBinding
import com.igo.tracking.model.RepWf.getWf
import com.igo.tracking.model.RepWf.getWfSize
import com.igo.tracking.model.constants.*

class LocFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentLocBinding? = null
    private val binding get() = _binding!!


    private lateinit var mMap: GoogleMap
    private lateinit var bComment: String
    private lateinit var bAddress: String
    private lateinit var bLatLng: LatLng
    private lateinit var plantLatLng: LatLng
    val DEFAULT_ZOOM = 7f
    private var indexWF: Int = -10

    // for hide/reveal
    //private var bottomNavigationView: BottomNavigationView? = null


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLocBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = childFragmentManager.findFragmentById(R.id.loc_map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // get data from bundle
        indexWF = requireArguments().getInt(WF_ID)
        if (indexWF == getWfSize()) {                     //new row - default biomass site (row 0, column 1)
            bComment = getWf(0).biopacks[1].bioComment
            bAddress = getWf(0).biopacks[1].bioAddress
            bLatLng = getWf(0).biopacks[1].bioLatLng
        } else if (indexWF != -1) {                               //default plant site (destination) (row 0, column 0) or any biomass site (row __, column 0)
            val wF = getWf(indexWF)
            bComment = wF.biopacks[0].bioComment
            bAddress = wF.biopacks[0].bioAddress
            bLatLng = wF.biopacks[0].bioLatLng
        } else {                                                 //default second plant site (destination) (row 0, column 5)(tmp)
            val wF = getWf(0)
            bComment = wF.biopacks[4].bioComment
            bAddress = wF.biopacks[4].bioAddress
            bLatLng = wF.biopacks[4].bioLatLng
        }

        binding.locOk.setOnClickListener {
            val savedStateHandle = findNavController().previousBackStackEntry?.savedStateHandle
            savedStateHandle?.set(WF_ID, indexWF)
            savedStateHandle?.set(WF_COMMENT, bComment)
            savedStateHandle?.set(WF_ADDRESS, bAddress)
            savedStateHandle?.set(WF_LAT, bLatLng.latitude)
            savedStateHandle?.set(WF_LNG, bLatLng.longitude)
            savedStateHandle?.set(WF_DISTANCE, binding.locTxtDistance.text.toString())
            findNavController().popBackStack()
        }
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        //Toast.makeText(context,bLatLang.latitude.toString() + "  " + bLatLang.longitude.toString(), Toast.LENGTH_SHORT).show()

        // Plant (destination)
        plantLatLng = getWf(0).biopacks[4].bioLatLng
        addMarkerPlant ()


        if (indexWF > 0) {
            addHarvestAndDistance()
            // Устанавливаем слушатель событий нажатия на карту
            mMap.setOnMapClickListener { latLng ->
                // Очищаем маркеры на карте
                mMap.clear()
                addMarkerPlant ()
                bLatLng = latLng               //new Harvest from map
                addHarvestAndDistance()
            }
        }
   }

    private fun addHarvestAndDistance() {
        addMarkerHarvest()
        drawRoute()
        calculateDistance()
    }

    private fun addMarkerPlant() {         // plant
        if (indexWF < 1) {
            binding.locTxtComment.text = "Plant: $bComment"
            binding.locTxtAddress.text = bAddress
            binding.locTxtLat.text = bLatLng.latitude.toString()
            binding.locTxtLng.text = bLatLng.longitude.toString()
            binding.locTxtDistance.text = "0"
        }

        mMap.addMarker(
            MarkerOptions()
                .position(plantLatLng)
                .title("Plant (destination)")
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(plantLatLng, DEFAULT_ZOOM))
    }

    private fun addMarkerHarvest() {
        binding.locTxtComment.text = "Plant: $bComment"
        binding.locTxtAddress.text = bAddress
        binding.locTxtLat.text = bLatLng.latitude.toString()
        binding.locTxtLng.text = bLatLng.longitude.toString()
        mMap.addMarker(
            MarkerOptions()
                .position(bLatLng)
                .title("Marker")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)) // Здесь меняем цвет маркера
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bLatLng, DEFAULT_ZOOM))
    }

    private fun drawRoute() {
        val polylineOptions = PolylineOptions()
            .add(bLatLng, plantLatLng) // Добавляем начальную и конечную точки маршрута
            .width(8f) // Устанавливаем ширину линии маршрута
            .color(Color.GREEN) // Устанавливаем цвет линии маршрута
        mMap.addPolyline(polylineOptions)

        val builder = LatLngBounds.Builder()
        builder.include(bLatLng)
        builder.include(plantLatLng)
        val bounds = builder.build()

        val padding = 180 // Устанавливаем отступы вокруг границ
        val cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, padding)
        mMap.animateCamera(cameraUpdate)
    }


    private fun calculateDistance() {
        val results = FloatArray(1)
        Location.distanceBetween(
            bLatLng.latitude, bLatLng.longitude,
            plantLatLng.latitude, plantLatLng.longitude, results
        )
        val distanceInKm = results[0] / 1000
        binding.locTxtDistance.text = String.format("%.2f", distanceInKm)
    }

}