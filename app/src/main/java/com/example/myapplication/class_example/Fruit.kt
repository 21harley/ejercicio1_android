package com.example.myapplication.class_example

import java.lang.Exception
import javax.security.auth.callback.Callback

class Fruit {
    var precio = 0.0f
    var nombre = ""
    var estacion = ""
    var precio_oferta=""
    var stock = 0
    var iv_producto= 0.0f
    var venta = 0

    constructor(precio:Float=0.0f,nombre:String=""){
        this.precio=precio
        this.nombre=nombre
    }

    constructor(
        precio:Float=0.0f,
        nombre:String="",
        estacion:String="",
        precio_oferta:String="",
        stock:Int=0,
        iv_producto:Float=0.0f
    ){
        this.precio=precio
        this.nombre=nombre
        this.estacion=estacion
        this.precio_oferta=precio_oferta
        this.stock=stock
        this.iv_producto=iv_producto
    }

    fun validarOperacion(callback:()->Boolean):Boolean{
        try {
            return callback()
        }catch (e:Exception){
            return false
        }
    }

    fun validarNumero(n:Int):Boolean{
        if (n==0) return false
        if (0>n) return  false
        return true
    }

    fun cargarStock(agregarCantidad:Int=0):Boolean{
        if (!validarNumero(agregarCantidad)) return false
        return validarOperacion{
            this.stock+=agregarCantidad
            true
        }
    }

    fun venderStock(ventaCantidad:Int=0):Boolean{
        if (!validarNumero(ventaCantidad)) return false
        if (0>this.stock-ventaCantidad) return false
        return validarOperacion {
            this.venta+=ventaCantidad
            this.stock-=ventaCantidad
            true
        }
    }


}