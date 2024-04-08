package com.example.myapplication.class_example

class Warehouse {

    var listProduct:MutableList<Product> = mutableListOf()

    fun loadProduct(newProduct:Product){
        for (elem in listProduct){
            if(elem.name == newProduct.name){
                elem.stock+=newProduct.stock
                return
            }
        }
        this.listProduct.add(newProduct)
    }

    fun deleteProduct(product: Product):Boolean{
        for (elem in listProduct){
            if(elem.name == product.name){
                if(elem.stock-product.stock>=0){
                    elem.sale+=product.stock
                    elem.stock-=product.stock
                    return true
                }
            }
        }
        return false
    }


}

//Si fuera estatica:
/*
object Bodega {
    @JvmStatic
    lateinit var listProduct:MutableList<Product>


    @JvmStatic
    fun loadProduct(newProduct:Product){
        if(listProduct.contains(newProduct)){
            listProduct[listProduct.indexOf(newProduct)]=newProduct
        }else{
            listProduct.add(newProduct)
        }
    }

    @JvmStatic
    fun deleteProduct(product: Product):Boolean{
        if(listProduct.contains(product)){
            listProduct.remove(product)
            return true
        }
        return false
    }
}
*/