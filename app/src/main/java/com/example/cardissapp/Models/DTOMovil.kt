package com.example.cardissapp.Models

data class DTOMovil(
    val id: Int = 0,
    val movil1: Int=0,
    val chofer:Int=0,
    val medico:Int=0,
    val marca:String="",
    val modelo:String="",
    val tkchofer:String="",
    val tkmedico:String="",
    val lat:Double=0.0,
    val long:Double=0.0,

)
