package com.binkos.starlypancacke.app.ui.main.map

import com.binkos.starlypancacke.app.R
import com.binkos.starlypancacke.app.ui.base.BaseFragment
import com.binkos.starlypancacke.app.ui.main.MainMapViewModel
import com.google.android.libraries.maps.GoogleMap
import com.google.android.libraries.maps.OnMapReadyCallback
import com.google.android.libraries.maps.SupportMapFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapFragment : BaseFragment(), OnMapReadyCallback {

    lateinit var map: GoogleMap
    private val mapDrawer: MapDrawer = MapDrawer()
    private val vm: MainMapViewModel by viewModel()

    override fun getLayout(): Int {
        return R.layout.fragment_map
    }

    override fun viewReady() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        vm.launchMapFragment()
    }

    override fun onBackPressed() {
        vm.close()
    }

    override fun onMapReady(map: GoogleMap?) {
        if (map == null) return
        this.map = map
        mapDrawer.initViews(this.map)
        initObservers()
        this.map.setOnMarkerClickListener {
            vm.toOrganization(it.title)
            true
        }
        mapDrawer.zoomTo(53.909581, 27.42733)
    }

    private fun initObservers() {
        vm
            .orgLiveData
            .observe(viewLifecycleOwner) {
                mapDrawer.cleanMarkers()
                mapDrawer.drawMarkers(it)
            }
    }
}