package com.example.googlemaps

import androidx.fragment.app.FragmentActivity
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.GoogleMap
import android.os.Bundle
import com.google.android.gms.maps.SupportMapFragment
import com.example.googlemaps.R
import com.example.googlemaps.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.*

class MapsActivity : FragmentActivity(), OnMapReadyCallback {
    private var mMap: GoogleMap? = null
    private var binding: ActivityMapsBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        //mMap!!.setMapType(GoogleMap.MAP_TYPE_NORMAL)
        //mMap!!.setMapType(GoogleMap.MAP_TYPE_HYBRID)
        //mMap!!.setMapType(GoogleMap.MAP_TYPE_SATELLITE)
        mMap!!.setMapType(GoogleMap.MAP_TYPE_TERRAIN)

        // Add markers to the desired locations on the map.
        val novaScotia = LatLng(45.0, -63.0)
        val bridgewater = LatLng(44.378410, -64.519958)
        val kentville = LatLng(45.077240, -64.496689)
        val halifax = LatLng(44.648766, -63.575237)
        val cogs = LatLng(44.885071, -65.168411)
        val digbyDrag = LatLng(44.624211, -65.767059)
        mMap!!.addMarker(MarkerOptions().position(bridgewater).title("Marker in Bridgewater"))
        mMap!!.addMarker(MarkerOptions().position(kentville).title("Marker in Kentville"))
        mMap!!.addMarker(MarkerOptions().position(halifax).title("Marker in Halifax"))
        mMap!!.addMarker(MarkerOptions().position(cogs).title("COGS Campus"))
        mMap!!.addMarker(MarkerOptions().position(digbyDrag).title("Drag me anywhere!").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)).draggable(true))

        //Create a circle around the cogs campus location
        val circleCogs = CircleOptions()
                .center(cogs)
                .radius(500.0)
        val circle = mMap!!.addCircle(circleCogs)

        // Camera zooms in on Nova Scotia when app starts up
        mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(novaScotia, 6f), 5000, null)
    }
}