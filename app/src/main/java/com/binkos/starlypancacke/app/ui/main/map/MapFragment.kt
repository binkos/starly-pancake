package com.binkos.starlypancacke.app.ui.main.map

import android.os.Bundle
import com.binkos.starlypancacke.app.R
import com.binkos.starlypancacke.app.common.extensions.tryToGetString
import com.binkos.starlypancacke.app.common.extensions.tryToGetStringOrNull
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

        val orgName = tryToGetStringOrNull(ORGANIZATION_NAME_KEY)
        if (orgName == null) {
            vm.launchMapFragment()
        } else {
            vm.launchOrgSearch(orgName)
        }
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

    companion object {
        private const val ORGANIZATION_NAME_KEY = "ORGANIZATION_NAME"

        fun getInstance(name: String?) = MapFragment().apply {
            arguments = Bundle()
                .apply {
                    putString(ORGANIZATION_NAME_KEY, name)
                }
        }
    }
}