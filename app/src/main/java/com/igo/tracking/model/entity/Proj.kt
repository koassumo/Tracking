package com.igo.tracking.model.entity

import java.util.Date

data class Proj (
    val projName: String,
    val projType: String,
    val projNumberReactors: Int,
    val projPower: String,
    val projGeo: GeoSite,
)
//val projDate: Date,
//val projID: Int,
//val projWeight: Double,
//var projMoisture: Double,
//val projCarbonInDm: Double,
//val projComment: String,
