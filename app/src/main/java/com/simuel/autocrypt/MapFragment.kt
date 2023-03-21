package com.simuel.autocrypt

import android.Manifest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons
import com.simuel.autocrypt.base.BaseFragment
import com.simuel.autocrypt.databinding.FragmentMapBinding
import com.simuel.domain.model.Center
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment : BaseFragment<FragmentMapBinding>(R.layout.fragment_map), OnMapReadyCallback {
    private val viewModel: MainViewModel by activityViewModels()
    private var naverMap: NaverMap? = null
    private var markers = mutableListOf<Marker>()
    private var locationSource: FusedLocationSource? = null
    private lateinit var centerList: List<Center>
    private lateinit var infoWindow: InfoWindow

    private val requestPermission = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {
        if (locationSource?.isActivated == true) {
            naverMap?.locationTrackingMode = LocationTrackingMode.Follow
        } else {
            naverMap?.locationTrackingMode = LocationTrackingMode.None
        }
    }


    override fun init() {
        binding.vm = viewModel
        binding.mapViewNaverMap.getMapAsync(this)
        requestPermission.launch(PERMISSIONS)
        locationSource = FusedLocationSource(this, 1000)

        viewModel.setCenterList()
        viewModelCallback()
    }

    private fun viewModelCallback() {
        viewModel.currentPosition.observe(viewLifecycleOwner) {
            if (it.consumed) return@observe
            requestPermission.launch(PERMISSIONS)
            binding.mapViewNaverMap.getMapAsync(this)
            naverMap?.locationTrackingMode = LocationTrackingMode.Follow
            it.consume()
        }

        viewModel.centerList.observe(viewLifecycleOwner) {
            centerList = it
            updateMarker(it)
        }
    }

    override fun onMapReady(map: NaverMap) {
        naverMap = map
        naverMap!!.uiSettings.isLocationButtonEnabled = true
        naverMap!!.locationSource = locationSource
        updateMarker(centerList)
    }

    private fun updateMarker(centers: List<Center>) {
        centers.forEachIndexed { i, center ->
            markers += Marker().apply {
                position = LatLng(center.lat.toDouble(), center.lng.toDouble())
                icon = if (center.centerType == "중앙/권역") {
                    MarkerIcons.BLUE
                } else {
                    MarkerIcons.RED
                }
                tag = center
                zIndex = i
            }
        }
        setMarkers()
        markerInfo()
    }

    private fun setMarkers() {
        if (naverMap != null) {
            for (marker in markers) {
                marker.map = naverMap
            }
        }
    }

    private fun markerInfo() {
        for (marker in markers) {

            val tempInfoWindow = InfoWindow()
            tempInfoWindow.adapter = object : InfoWindow.DefaultTextAdapter(requireContext()) {
                override fun getText(infoWindow: InfoWindow): CharSequence {
                    return (infoWindow.marker?.tag as Center).centerName
                }
            }
            infoWindow = tempInfoWindow
            marker.setOnClickListener {
                if (tempInfoWindow.marker != null) {
                    tempInfoWindow.close()
                    binding.textViewCenterInfo.isVisible = false
                } else {
                    tempInfoWindow.open(marker)
                    val cameraUpdate = CameraUpdate.scrollTo(marker.position)
                    naverMap?.moveCamera(cameraUpdate)

                    binding.textViewCenterInfo.isVisible = true
                    viewModel.setCenter(marker.tag as Center)
                }
                true
            }
        }
    }

    companion object {
        private val PERMISSIONS = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }

}