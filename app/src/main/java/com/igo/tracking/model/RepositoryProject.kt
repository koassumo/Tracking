package com.igo.tracking.model

import com.google.android.gms.maps.model.LatLng
import com.igo.tracking.model.entity.GeoSite
import com.igo.tracking.model.entity.Proj

object RepositoryProject {

    private val listProj: MutableList<Proj> = mutableListOf(
        Proj(
            "Rottweil",
            "manual",
            3,
            "90 kW",
            GeoSite("78628 Rottweil", LatLng(48.133405314268224, 8.605143805468378))
        ),
        Proj(
            "Bladnoch",
            "continueable",
            3,
            "90 kW",
            GeoSite(
                "Bladnoch, Newton Stewart DG8 9AB, UK",
                LatLng(54.85835910559619, -4.461758930564551)
            )
        ),
    )

    fun getListProj(): MutableList<Proj> {
        return listProj
    }

    fun getProj(num: Int): Proj {
        return listProj[num]
    }

}