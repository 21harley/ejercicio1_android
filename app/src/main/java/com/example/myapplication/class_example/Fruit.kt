package com.example.myapplication.class_example

import java.lang.Exception
import javax.security.auth.callback.Callback

class Fruit {
    var price = 0.0f
    var name = ""
    var station = ""
    var offer_price=""
    var stock = 0
    var id_product= 0.0f
    var sale = 0

    constructor(price:Float=0.0f,name:String=""){
        this.price=price
        this.name=name
    }

    constructor(
        price:Float=0.0f,
        name:String="",
        station:String="",
        offer_price:String="",
        stock:Int=0,
        id_product:Float=0.0f
    ){
        this.price=price
        this.name=name
        this.station=station
        this.offer_price=offer_price
        this.stock=stock
        this.id_product=id_product
    }

    fun validateOperation(callback:()->Boolean):Boolean{
        try {
            return callback()
        }catch (e:Exception){
            return false
        }
    }

    fun validateNumber(n:Int):Boolean{
        if (n==0) return false
        if (0>n) return  false
        return true
    }

    fun loadStock(addQuantity:Int=0):Boolean{
        if (!validateNumber(addQuantity)) return false
        return validateOperation{
            this.stock+=addQuantity
            true
        }
    }

    fun sellStock(saleQuantity:Int=0):Boolean{
        if (!validateNumber(saleQuantity)) return false
        if (0>this.stock-saleQuantity) return false
        return validateOperation{
            this.sale+=saleQuantity
            this.stock-=saleQuantity
            true
        }
    }


}