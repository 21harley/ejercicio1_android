package com.example.myapplication.class_example

class Producto {
    var nombre:String
    var precio:Float
    var stock:Int
    var venta:Int
    constructor(nombre:String,precio:Float,stock:Int){
        this.nombre=nombre
        this.precio=precio
        this.stock=stock
        this.venta=0
    }
}