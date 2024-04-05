package com.example.myapplication.class_example

import android.text.BoringLayout

class Empleado {
    var DNI:String
    var nombre:String
    var telefono:String
    var correo:String
    var bodega:Bodega
    constructor(DNI:String,nombre:String){
        this.DNI=DNI
        this.nombre=nombre
        this.telefono=""
        this.correo=""
        this.bodega=Bodega()
    }

    fun cargarStock(nuevoProducto: Producto){
        this.bodega.cargarProducto(nuevoProducto)
    }

    fun eliminarStock(producto: Producto):Boolean{
        return this.bodega.eliminarProducto(producto)
    }

}