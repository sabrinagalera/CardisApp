package com.example.cardissapp.Models

data class DTOEmergencia(
    var id :Int=0,
    var nombreYApellido: String="",
    var edad:Int=0,
    var domicilio :String="",
    var telefono:String="",
    var obrasocial:String="",
    var codigo:String="",
    var documento:String="",
    var coseg:String="",
    var motivoDeConsulta:String="",
    var observaciones:String="",
    var diagnosticoPresuntivo:String="",
    var covid:Boolean=false,
    var debe:Boolean=false,

    )
