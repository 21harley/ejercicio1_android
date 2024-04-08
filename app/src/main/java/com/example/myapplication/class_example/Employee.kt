package com.example.myapplication.class_example

class Employee {
    var DNI:String
    var name:String
    var telephone:String
    var email:String
    var watehouse:Warehouse
    constructor(DNI:String,name:String){
        this.DNI=DNI
        this.name=name
        this.telephone=""
        this.email=""
        this.watehouse=Warehouse()
    }

    fun loadStock(newProduct: Product){
        this.watehouse.loadProduct(newProduct)
    }

    fun deleteStock(product: Product):Boolean{
        return this.watehouse.deleteProduct(product)
    }

}