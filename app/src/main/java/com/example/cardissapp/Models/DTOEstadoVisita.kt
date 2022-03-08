package com.example.cardissapp.Models

data class DTOEstadoVisita(
    var id:Int = 0,
    var estado:String="",
    var diagnostico:String="",
    var covid:Boolean= false,
    var kit:Boolean= false,
    var debe:Boolean= false,
    var debeKit: Boolean=false,

)
