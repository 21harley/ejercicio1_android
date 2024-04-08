package com.example.myapplication.class_example

class Product {
    var name:String
    var price:Float
    var stock:Int
    var sale:Int
    constructor(name:String,price:Float,stock:Int){
        this.name=name
        this.price=price
        this.stock=stock
        this.sale=0
    }
}