package com.igo.tracking.model

import com.google.android.gms.maps.model.LatLng
import com.igo.tracking.model.entity.Biopack
import com.igo.tracking.model.entity.Wf

object RepWf {
    // workflow = 6 levels for biopack

    private var wfs = ArrayList<Wf>()

    init {
        // adding zero workflow consists of 6 biopacks
        // it is used only like default
        wfs.add(Wf())
        wfs.add(Wf())

        // about 0st row:
        // 0st column - 1st plant (destination)
        wfs[0].biopacks[0].bioComment = "Hermann Weisser"
        wfs[0].biopacks[0].bioAddress = "Mühleweg 18, 72290 Loßburg, Germany"
        wfs[0].biopacks[0].bioLatLng = LatLng(48.433450181760016, 8.490757221222422)

        // 5th column - 2nd plant (destination)
        wfs[0].biopacks[5].bioComment = "Bladnoch Distillery"
        wfs[0].biopacks[5].bioAddress = "Newton Stewart DG8 9AB, UK"
        wfs[0].biopacks[5].bioLatLng = LatLng(54.85849617485866, -4.461738277174423)

        //1st column - default biomass site
        wfs[0].biopacks[1].bioComment = "Mackenzie Derek"
        wfs[0].biopacks[1].bioAddress = "Old Edinburgh Rd South, Inverness IV2 6AR, UK"
        wfs[0].biopacks[1].bioLatLng = LatLng(57.455925760316845, -4.191462473934587)

    }

    fun addWf(newBiopack: Biopack) {
        val newWf = Wf()
        newWf.biopacks[0].bioID = newBiopack.bioID
        newWf.biopacks[0].bioDate = newBiopack.bioDate
        newWf.biopacks[0].bioType = newBiopack.bioType
        newWf.biopacks[0].bioWeight = newBiopack.bioWeight
        newWf.biopacks[0].bioMoisture = newBiopack.bioMoisture
        newWf.biopacks[0].bioCarbonInDm = newBiopack.bioCarbonInDm
        newWf.biopacks[0].bioComment = newBiopack.bioComment
        newWf.biopacks[0].bioStatus = newBiopack.bioStatus
        newWf.biopacks[0].bioTimeIn = newBiopack.bioTimeIn
        newWf.biopacks[0].bioTimeOut = newBiopack.bioTimeOut
        newWf.biopacks[0].bioAddress = newBiopack.bioAddress
        newWf.biopacks[0].bioLatLng = newBiopack.bioLatLng
        newWf.biopacks[0].bioDistance = newBiopack.bioDistance
        wfs.add(newWf)
    }

    fun updateWf(index: Int, updatedWf: Wf) {
        if (index in 0 until wfs.size) {
            wfs[index] = updatedWf
        }
    }

    fun getListWfs(): ArrayList<Wf> {
        return wfs
    }

    fun getWf(index: Int): Wf {
        return wfs[index]
    }


    fun getWfSize(): Int {
        return wfs.size
    }


}