package com.igo.tracking.model.entity

import com.google.android.gms.maps.model.LatLng

data class GeoSite (
    val geoAddress: String,
    val geoLatLng: LatLng,
)
