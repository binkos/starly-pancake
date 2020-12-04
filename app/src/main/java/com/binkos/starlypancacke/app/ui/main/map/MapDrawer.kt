package com.binkos.starlypancacke.app.ui.main.map

import com.binkos.starlypancacke.domain.model.Organization
import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.GoogleMap
import com.google.android.libraries.maps.model.LatLng
import com.google.android.libraries.maps.model.MarkerOptions
import java.util.*

class MapDrawer {

    var map: GoogleMap? = null
    private val list: LinkedList<MarkerOptions> = LinkedList()

    fun initViews(map: GoogleMap) {
        this.map = map
    }

    fun drawMarkers(organizations: List<Organization>) {
        list.apply {
            clear()
            addAll(organizations.mapIndexed { index, organization ->
                createMapMarker(organization, index)
            })
            forEach {
                map?.addMarker(it)
            }
        }
    }

    fun cleanMarkers() {
        map?.clear()
    }

    fun zoomTo(lat: Double, long: Double) {
        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(lat, long), 15F))
    }

    private fun createMapMarker(organization: Organization, order: Int): MarkerOptions {
        return MarkerOptions()
            .position(LatLng(organization.latitude, organization.longitude))
            .title(organization.name)
    }
}