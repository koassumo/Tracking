package com.igo.tracking.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.igo.tracking.R
import com.igo.tracking.databinding.FragmentLocBinding
import com.igo.tracking.model.RepositoryProject
import com.igo.tracking.model.constants.PACK_ID

class LocFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentLocBinding? = null
    private val binding get() = _binding!!


    private lateinit var mMap: GoogleMap
    private lateinit var projName: String
    private lateinit var pointAddress: String
    private lateinit var pointLatLang: LatLng
    val DEFAULT_ZOOM = 7f

    // for hide/reveal
    //private var bottomNavigationView: BottomNavigationView? = null


    override fun onCreateView (
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLocBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = childFragmentManager.findFragmentById(R.id.loc_map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // get data from bundle
        val projNum = requireArguments().getInt(PACK_ID)
        //Toast.makeText(context, projNum.toString(), Toast.LENGTH_SHORT).show()


        projName = RepositoryProject.getProj(projNum).projName
        pointAddress = RepositoryProject.getProj(projNum).projGeo.geoAddress
        pointLatLang = RepositoryProject.getProj(projNum).projGeo.geoLatLng

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        Toast.makeText(context, pointLatLang.latitude.toString() +"  "+ pointLatLang.longitude.toString(), Toast.LENGTH_SHORT).show()

        addMarkerPoint(pointAddress, pointLatLang)

        //mMap.setOnMapClickListener(this)
        // Устанавливаем слушатель событий нажатия на карту
        //mMap.setOnMapClickListener { latLng ->
            // Вызываем метод для обработки выбранной точки
            //handleMapClick(latLng)
        //}

    }

    private fun addMarkerPoint(pointAddress: String ,pointLatLng: LatLng) {
        binding.locTxtPlant.text = "Plant: $projName"
        binding.locTxtAddress.text = "Address: $pointAddress"
        // Add a marker in point and move the camera
        mMap.addMarker(
            MarkerOptions()
                .position(pointLatLng)
                .title("Marker")
                //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)) // Здесь меняем цвет маркера
        )
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pointLatLng, DEFAULT_ZOOM))
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(pointLatLng))

        // Создаем объект CameraUpdate, чтобы приблизить камеру к новой точке с учетом масштаба
        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(pointLatLng, DEFAULT_ZOOM)

        // Выполняем анимацию перемещения камеры
        mMap.animateCamera(cameraUpdate)
    }

//    private fun handleMapClick(latLng: LatLng) {
//        // Очищаем маркеры на карте
//        //mMap.clear()
//        pointLatLang = latLng
//        addMarkerPoint(factoryLatLang)
//        addDestinationAndDrawRoute()
//        calculateDistance()
//    }


}