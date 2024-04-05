package com.example.myapplication.class_example

class Bodega {

    var listaProducto:MutableList<Producto> = mutableListOf()

    fun cargarProducto(nuevoProducto:Producto){
        if(this.listaProducto.contains(nuevoProducto)){
            this.listaProducto[this.listaProducto.indexOf(nuevoProducto)].stock+=nuevoProducto.stock
        }else{
            this.listaProducto.add(nuevoProducto)
        }
    }

    fun eliminarProducto(producto: Producto):Boolean{
        if(this.listaProducto.contains(producto)){
            var auxProducto=this.listaProducto[this.listaProducto.indexOf(producto)]
            if(auxProducto.stock-producto.stock>=0){
                auxProducto.venta+=producto.stock
                auxProducto.stock-=producto.stock
                return true
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