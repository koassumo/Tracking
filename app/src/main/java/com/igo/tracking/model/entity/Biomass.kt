package com.igo.tracking.model.entity

import java.util.Date

data class Biomass (
    val biomassID: Int,
    val biomassDate: Date,
    val biomassType: String,
    val biomassWeight: Double,
    var biomassMoisture: Double,
    val biomassCarbonInDm: Double,
    val biomassComment: String,
    val biomassStatus: String
)
