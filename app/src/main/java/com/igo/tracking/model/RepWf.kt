package com.igo.tracking.model

import com.google.android.gms.maps.model.LatLng
import com.igo.tracking.model.constants.*
import com.igo.tracking.model.entity.Biopack
import com.igo.tracking.model.entity.Wf
import java.util.Date

object RepWf {
    // workflow = 6 levels for biopack

    private var wfs = ArrayList<Wf>()

    init {
        // adding zero workflow consists of 6 biopacks
        // it is used only like default
        wfs.add(Wf())
        wfs.add(Wf())
        wfs.add(Wf())
        wfs.add(Wf())
        wfs.add(Wf())
        wfs.add(Wf())
        wfs.add(Wf())

        // about 0st row:
        // 0st column - 1st plant (destination)
        wfs[0].biopacks[0].bioComment = "Hermann Weisser"
        wfs[0].biopacks[0].bioAddress = "Mühleweg 18, 72290 Loßburg, Germany"
        wfs[0].biopacks[0].bioLatLng = LatLng(48.433450181760016, 8.490757221222422)

        // 5th column - 2nd plant (destination)
        wfs[0].biopacks[4].bioComment = "Bladnoch Distillery"
        wfs[0].biopacks[4].bioAddress = "Newton Stewart DG8 9AB, UK"
        wfs[0].biopacks[4].bioLatLng = LatLng(54.85849617485866, -4.461738277174423)

        //1st column - default biomass site
        wfs[0].biopacks[1].bioComment = "Mackenzie Derek"
        wfs[0].biopacks[1].bioAddress = "Old Edinburgh Rd South, Inverness IV2 6AR, UK"
        wfs[0].biopacks[1].bioLatLng = LatLng(57.455925760316845, -4.191462473934587)


        // 1 row:
        wfs[1].biopacks[0].bioID = 20240001
        wfs[1].biopacks[0].bioDate = Date()
        wfs[1].biopacks[0].bioType = "Wood chips"
        wfs[1].biopacks[0].bioWeight = 250.0
        wfs[1].biopacks[0].bioMoisture = 30.0
        wfs[1].biopacks[0].bioCarbonInDm = 50.0
        wfs[1].biopacks[0].bioComment = "Low Balyett Farm"
        wfs[1].biopacks[0].bioStatus = CORC_ISSUED
        wfs[1].biopacks[0].bioTimeIn = Date(0)
        wfs[1].biopacks[0].bioTimeOut = Date(0)
        wfs[1].biopacks[0].bioAddress = "Cairnryan Rd, DG9 8QL, UK"
        wfs[1].biopacks[0].bioLatLng = LatLng(54.92065822779349, -4.990381928146359)
        wfs[1].biopacks[0].bioDistance = 35.0


        // 2 row:
        wfs[2].biopacks[0].bioID = 20240002
        wfs[2].biopacks[0].bioDate = Date()
        wfs[2].biopacks[0].bioType = "Wood chips"
        wfs[2].biopacks[0].bioWeight = 250.0
        wfs[2].biopacks[0].bioMoisture = 30.0
        wfs[2].biopacks[0].bioCarbonInDm = 50.0
        wfs[2].biopacks[0].bioComment = "Craig Farm"
        wfs[2].biopacks[0].bioStatus = RAW
        wfs[2].biopacks[0].bioTimeIn = Date(0)
        wfs[2].biopacks[0].bioTimeOut = Date(0)
        wfs[2].biopacks[0].bioAddress = "Balmaclellan, Castle Douglas DG7 3QS, UK"
        wfs[2].biopacks[0].bioLatLng = LatLng(55.057504305155994, -4.088416111281112)
        wfs[2].biopacks[0].bioDistance = 33.0

        // 3 row:
        wfs[3].biopacks[0].bioID = 20240003
        wfs[3].biopacks[0].bioDate = Date()
        wfs[3].biopacks[0].bioType = "Wood chips"
        wfs[3].biopacks[0].bioWeight = 250.0
        wfs[3].biopacks[0].bioMoisture = 30.0
        wfs[3].biopacks[0].bioCarbonInDm = 50.0
        wfs[3].biopacks[0].bioComment = "Russell James Farmer"
        wfs[3].biopacks[0].bioStatus = DELIVERED_TO_CLIENT
        wfs[3].biopacks[0].bioTimeIn = Date(0)
        wfs[3].biopacks[0].bioTimeOut = Date(0)
        wfs[3].biopacks[0].bioAddress = "1 Crouse Cottages, Wigtown, Newton Stewart DG8 9BG, UK"
        wfs[3].biopacks[0].bioLatLng = LatLng(54.87140304378735, -4.543509132594081)
        wfs[3].biopacks[0].bioDistance = 5.0

        // 4 row:
        wfs[4].biopacks[0].bioID = 20240004
        wfs[4].biopacks[0].bioDate = Date()
        wfs[4].biopacks[0].bioType = "Wood chips"
        wfs[4].biopacks[0].bioWeight = 250.0
        wfs[4].biopacks[0].bioMoisture = 30.0
        wfs[4].biopacks[0].bioCarbonInDm = 50.0
        wfs[4].biopacks[0].bioComment = "Low Balyett Farm"
        wfs[4].biopacks[0].bioStatus = RAW
        wfs[4].biopacks[0].bioTimeIn = Date(0)
        wfs[4].biopacks[0].bioTimeOut = Date(0)
        wfs[4].biopacks[0].bioAddress = "Cairnryan Rd, DG9 8QL, UK"
        wfs[4].biopacks[0].bioLatLng = LatLng(54.92065822779349, -4.990381928146359)
        wfs[4].biopacks[0].bioDistance = 35.0

        // 5 row:
        wfs[5].biopacks[0].bioID = 20240005
        wfs[5].biopacks[0].bioDate = Date()
        wfs[5].biopacks[0].bioType = "Wood chips"
        wfs[5].biopacks[0].bioWeight = 250.0
        wfs[5].biopacks[0].bioMoisture = 30.0
        wfs[5].biopacks[0].bioCarbonInDm = 50.0
        wfs[5].biopacks[0].bioComment = "Low Balyett Farm"
        wfs[5].biopacks[0].bioStatus = RAW
        wfs[5].biopacks[0].bioTimeIn = Date(0)
        wfs[5].biopacks[0].bioTimeOut = Date(0)
        wfs[5].biopacks[0].bioAddress = "Cairnryan Rd, DG9 8QL, UK"
        wfs[5].biopacks[0].bioLatLng = LatLng(54.92065822779349, -4.990381928146359)
        wfs[5].biopacks[0].bioDistance = 35.0

        // 6 row:
//        wfs[6].biopacks[0].bioID = 20240006
//        wfs[6].biopacks[0].bioDate = Date()
//        wfs[6].biopacks[0].bioType = "Wood chips"
//        wfs[6].biopacks[0].bioWeight = 250.0
//        wfs[6].biopacks[0].bioMoisture = 30.0
//        wfs[6].biopacks[0].bioCarbonInDm = 50.0
//        wfs[6].biopacks[0].bioComment = "Low Balyett Farm"
//        wfs[6].biopacks[0].bioStatus = RAW
//        wfs[6].biopacks[0].bioTimeIn = Date(0)
//        wfs[6].biopacks[0].bioTimeOut = Date(0)
//        wfs[6].biopacks[0].bioAddress = "Cairnryan Rd, DG9 8QL, UK"
//        wfs[6].biopacks[0].bioLatLng = LatLng(54.92065822779349, -4.990381928146359)
//        wfs[6].biopacks[0].bioDistance = 35.0


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