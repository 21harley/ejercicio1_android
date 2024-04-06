package com.example.myapplication.class_example

class Bodega {

    var listaProducto:MutableList<Producto> = mutableListOf()

    fun cargarProducto(nuevoProducto:Producto){
        for (elem in listaProducto){
            if(elem.nombre == nuevoProducto.nombre){
                elem.stock+=nuevoProducto.stock
                return
            }
        }
        this.listaProducto.add(nuevoProducto)
    }

    fun eliminarProducto(producto: Producto):Boolean{
        for (elem in listaProducto){
            if(elem.nombre == producto.nombre){
                if(elem.stock-producto.stock>=0){
                    elem.venta+=producto.stock
                    elem.stock-=producto.stock
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
    lateinit var listaProducto:MutableList<Producto>


    @JvmStatic
    fun cargarProducto(nuevoProducto:Producto){
        if(listaProducto.contains(nuevoProducto)){
            listaProducto[listaProducto.indexOf(nuevoProducto)]=nuevoProducto
        }else{
            listaProducto.add(nuevoProducto)
        }
    }

    @JvmStatic
    fun eliminarProducto(producto: Producto):Boolean{
        if(listaProducto.contains(producto)){
            listaProducto.remove(producto)
            return true
        }
        return false
    }
}
*/