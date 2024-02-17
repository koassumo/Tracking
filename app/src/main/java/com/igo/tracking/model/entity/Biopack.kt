package com.igo.tracking.model.entity

import com.google.android.gms.maps.model.LatLng
import java.util.Date

data class Biopack (
    var bioID: Int = 0,
    var bioDate: Date = Date(),
    var bioType: String = "",
    var bioWeight: Double = 0.0,
    var bioMoisture: Double = 0.0,
    var bioCarbonInDm: Double = 0.0,
    var bioComment: String = "",
    var bioStatus: String = "",
    var bioTimeIn: Date = Date(),
    var bioTimeOut: Date = Date(),
    var bioAddress: String = "",
    var bioLatLng: LatLng = LatLng(0.0, 0.0),
    var bioDistance: Double = 0.0,

    )
