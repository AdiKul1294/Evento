package com.devlite.evento.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.devlite.evento.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment() {

    lateinit var myGoogleMap: GoogleMap
    private val callback = OnMapReadyCallback {
        with(it.uiSettings) {
            isZoomControlsEnabled = true
            isCompassEnabled = true
            isScrollGesturesEnabled = true
        }
        myGoogleMap = it
        onMapReady(it, LatLng(15.390725144730112, 73.87846601024137), "BITS Goa")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    private fun onMapReady(googleMap: GoogleMap, location: LatLng, markerTitle: String) {
        val markerLocation = location
        val marker = googleMap.addMarker(
            MarkerOptions()
                .position(markerLocation)
                .title(markerTitle)
        )
        googleMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                location, 18f
            )
        )
        marker?.showInfoWindow()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var btnFlagLawns: Button = view.findViewById(R.id.btnFlagLawns)
        var btnLT12Lawns: Button = view.findViewById(R.id.btnLT12Lawns)
        var btnDSpine: Button = view.findViewById(R.id.btnDSpine)
        var btnMedC: Button = view.findViewById(R.id.btnMedC)
        var btnSuperMart: Button = view.findViewById(R.id.btnSuperMart)

        btnFlagLawns.setOnClickListener {
            onMapReady(
                myGoogleMap, LatLng(15.3922175, 73.8797103), "Flag Lawns"
            )
        }
        btnLT12Lawns.setOnClickListener {
            onMapReady(
                myGoogleMap, LatLng(15.3925391, 73.8810373), "LT 1/2 Lawns"
            )
        }
        btnDSpine.setOnClickListener {
            onMapReady(
                myGoogleMap,
                LatLng(15.392314, 73.882277),
                "D-Spine"
            )
        }
        btnMedC.setOnClickListener {
            onMapReady(
                myGoogleMap,
                LatLng(15.391634129693461, 73.87607826751572),
                "MedC"
            )
        }
        btnSuperMart.setOnClickListener {
            onMapReady(
                myGoogleMap,
                LatLng(15.392012, 73.876420),
                "Super Market"
            //15.392012, 73.876420
            )
        }
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)


    }
}


