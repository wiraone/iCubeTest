package com.kotlin.wirawan.icubetest.ui.places

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.kotlin.wirawan.icubetest.R
import com.kotlin.wirawan.icubetest.models.Place

import kotlinx.android.synthetic.main.activity_place_picker.*



class PlacePickerActivity : AppCompatActivity(), OnMapReadyCallback, PlacePickerContract.View {
    private var googleMap: GoogleMap? = null
    private var mapView: MapView? = null
    private var recycleView: RecyclerView? = null
    private var presenter: PlacePickerPresenter? = null


    private val MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_picker)
        setSupportActionBar(toolbar)

        presenter = PlacePickerPresenter()
        presenter?.attach(this)

        recycleView = findViewById(R.id.recycle_view)
        var mapViewBundle: Bundle? = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        // Gets the MapView from the XML layout and creates it
        mapView = findViewById(R.id.mapView)
        mapView?.onCreate(mapViewBundle)
        mapView?.getMapAsync(this);
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        var mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY)
        if (mapViewBundle == null) {
            mapViewBundle = Bundle()
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle)
        }

        mapView?.onSaveInstanceState(mapViewBundle)
    }

    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onMapReady(gMap: GoogleMap?) {
        googleMap = gMap;
        googleMap?.setMinZoomPreference(12.0f);
        var latLong = LatLng(40.7143528, -74.0059731);
        googleMap?.moveCamera(CameraUpdateFactory.newLatLng(latLong));
        presenter?.loadPlaceList()
    }

    override fun loadPlaceListSuccess(list: List<Place>) {
        var adapter = PlacePickerAdapter(this, list.toMutableList(), this)
        recycleView?.setLayoutManager(LinearLayoutManager(this))
        recycleView?.setAdapter(adapter)

    }

    override fun showErrorMessage(error: String) {
        Log.e("", error)
    }

    override fun showProgress(show: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
