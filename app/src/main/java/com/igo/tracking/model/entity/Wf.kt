package com.igo.tracking.model.entity

data class Wf (

    val biopacks: MutableList<Biopack> = mutableListOf(
        Biopack(),
        Biopack(),
        Biopack(),
        Biopack(),
        Biopack(),
    )
)
