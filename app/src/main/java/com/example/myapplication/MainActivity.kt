package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.example.myapplication.class_example.Empleado
import com.example.myapplication.class_example.Fruit
import com.example.myapplication.class_example.Producto
import com.example.myapplication.databinding.ActivityMainBinding
import android.util.Log

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    var listaFruit:MutableList<Fruit> = mutableListOf()
    var listNegocio:MutableList<Empleado> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//eliseo marin ***********************
        cargarAlmacen()
        cargarSpinner()
        binding.contentMainId.exerciseTwo.setOnClickListener {
            compentenciaAlmacen()
        }
        binding.contentMainId.status.setOnClickListener {
            mostrarStatusFruta()
        }
        binding.contentMainId.store.setOnClickListener {
             var valor=validarDatos(9999).toInt()
             var select=binding.contentMainId.spinner.firstVisiblePosition
            var auxFruta=listaFruit[select]
            if(auxFruta.cargarStock(valor) && valor != 0){
                binding.contentMainId.salidaMensaje.text="${auxFruta.nombre}, stock:${auxFruta.stock}"
            }
            binding.contentMainId.inputText.text.clear()

        }
        binding.contentMainId.sell.setOnClickListener {
            var valor=validarDatos(9999).toInt()
            var select=binding.contentMainId.spinner.firstVisiblePosition
            var auxFruta=listaFruit[select]

            if(auxFruta.venderStock(valor) && valor != 0){
                binding.contentMainId.salidaMensaje.text="${auxFruta.nombre}, vendio:${valor}"

            }
            if(auxFruta.stock-valor<0){
                binding.contentMainId.salidaMensaje.text="El numero Ingresado supera el Stok por favor ingrese un avalor igual o menor al ${auxFruta.stock}"
            }
            binding.contentMainId.inputText.text.clear()
        }
    }

    fun mensajeMostrar(mesaje:String){
        binding.contentMainId.salidaMensaje.text = mesaje+"\n\n"
    }
    fun validarDatos(limite:Int):Float{
        try{
            var datos=binding.contentMainId.inputText.text
            var aux=datos.toString().toFloat()
            if(limite<aux || aux==0f || 0>aux){
                throw Exception("Valor incorrecto")
            }
            return aux
        }catch (e:Exception){
            mensajeMostrar("Error al ingresar la cantidad frutas \n" +
                    "\n solo se permite numeros y cantidades menores a 9999")
        }
        return 0.0f
    }
    fun mostrarStatusFruta(){
        var respuesta = "\n"
        var repuesta1 = "\n"
        for( i in listaFruit){
            if(i.stock>0) respuesta +="${i.nombre}:${i.stock} \n"
            else repuesta1 +="${i.nombre}:${i.stock} \n"
        }
        binding.contentMainId.salidaMensaje.text="Frutas con stock mayor a 0:${respuesta} \n\nFrutas con stock en 0: ${repuesta1} \n\n"
    }
    //Ejercicio 1
    fun cargarAlmacen(){
        //carga de frutas
        listaFruit.add(Fruit(100f,"Manzana"))
        listaFruit.add(Fruit(80f,"Pera"))
        listaFruit.add(Fruit(60f,"Mandarina"))
        listaFruit.add(Fruit(180f,"Uva"))
        listaFruit.add(Fruit(130f,"Fresa"))
        listaFruit.add(Fruit(70f,"Naranja"))
        listaFruit.add(Fruit(60f,"Mango"))

        //modificacion de stock
        listaFruit[0].stock=10
        listaFruit[1].stock=20
        listaFruit[2].stock=30
        listaFruit[3].stock=40
        listaFruit[4].stock=20
        listaFruit[5].stock=10
        listaFruit[6].stock=40
        /*
                //compra
                for (i in 0..listaFruit.size-1){
                    listaFruit[i].venderStock(10)
                }

                var respuesta = ""
                var repuesta1 = ""
                for( i in listaFruit){
                    if(i.stock>0){
                        respuesta +=" ${i.nombre}:${i.stock}"
                    }else{
                        repuesta1 +=" ${i.nombre}:${i.stock}"
                    }
                }
                binding.contentMainId.message.text="Frutas con stock mayor a 0:${respuesta}"
                binding.contentMainId.message1.text="Frutas con stock en 0: ${repuesta1}"
        */
    }
    fun cargarSpinner(){
        val listaFruit= listOf<String>(
            resources.getString(R.string.fruit_apple),
            resources.getString(R.string.fruit_pear),
            resources.getString(R.string.fruit_tangerine),
            resources.getString(R.string.fruit_grape),
            resources.getString(R.string.fruit_strawberry),
            resources.getString(R.string.fruit_orange),
            resources.getString(R.string.fruit_handle),
        )
        binding.contentMainId.spinner.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listaFruit)
    }

    fun compentenciaAlmacen(){
        var listaNombreProductos:MutableList<String> = mutableListOf(
        "MAÍZ BLANCO O NO BLANCO",
        "FRIJOL ENVASADO O A GRANEL",
        "ARROZ ENVASADO O A GRANEL",
        "AZÚCAR ESTANDAR",
        "HARINA DE MAÍZ",
        "ACEITE VEGETAL COMESTIBLE",
        "ATÚN",
        "SARDINA",
        "LECHE EN POLVO",
        "CHILES JALAPEÑOS",
        "CHIPOTLE",
        "RAJAS O SERRANOS ENLATADOS",
        "CAFÉ SOLUBLE",
        "SAL DE MESA, AVENA",
        "PASTA PARA SOPA",
        "HARINA DE TRIGO"
        )
        listNegocio.add(Empleado("12452","Negocio1"))
        listNegocio.add(Empleado("124536","Negocio2"))
        listNegocio.add(Empleado("14785","Negocio3"))

        Log.i("DATA_MESSAGE","dia 1) al negocio 2 le compran 15 productos ")
        for(i in 0..14){
            var stock=(1..10).random()
            var precio=(100..500).random().toFloat()
            listNegocio[1].bodega.cargarProducto(Producto(listaNombreProductos[i],precio,stock))
        }

        Log.i("DATA_MESSAGE","día 2. al negocio 3 le viene un camión con 10 productos nuevos para agregar al depósito.")
        for(i in 0..9){
            var stock=(1..10).random()
            var precio=(100..500).random().toFloat()
            listNegocio[2].bodega.cargarProducto(Producto(listaNombreProductos[i],precio,stock))
        }

        Log.i("DATA_MESSAGE","dia 3 ) el negocio 1 vende 5 productos")
        for(i in 0..4){
            var stock=(1..10).random()
            var precio=(100..500).random().toFloat()
            var auxProducto=Producto(listaNombreProductos[i],precio,stock)
            if(!listNegocio[0].bodega.eliminarProducto(auxProducto)){
                Log.d("Mensaje_Error_Compra","No se logro vender el producto ${auxProducto.nombre} del almacen ${listNegocio[0].nombre}")
            }
        }

        Log.i("DATA_MESSAGE","dia 4 ) el negocio 1 recibe una devolución de un producto")
        if(listNegocio[0].bodega.listaProducto.size>0){
            var auxProductoBodega1=listNegocio[0].bodega.listaProducto.random()
            listNegocio[0].bodega.cargarProducto(
                Producto(auxProductoBodega1.nombre,auxProductoBodega1.precio,10)
            )
        }else{
            var stock=(1..10).random()
            var precio=(100..500).random().toFloat()
            var nombreRamdo=listaNombreProductos[(0..listaNombreProductos.size-1).random()]
            listNegocio[0].bodega.cargarProducto(
                Producto(nombreRamdo,precio,stock)
            )
        }

        Log.i("DATA_MESSAGE","dia 5 ) el negocio 2 vende 10 productos")
        for(i in 0..9){
            var stock=(1..10).random()
            var auxProductoBodega1=listNegocio[1].bodega.listaProducto.random()
            var auxProducto=Producto(auxProductoBodega1.nombre,auxProductoBodega1.precio,stock)
            if(!listNegocio[1].bodega.eliminarProducto(auxProducto)){
                Log.d("Mensaje_Error_Compra","No se logro vender el producto ${auxProducto.nombre} del almacen ${listNegocio[1].nombre}")
            }
        }

        Log.i("DATA_MESSAGE","dia 6 ) el negocio 3 vende 10 productos")
        for(i in 0..9){
            var stock=(1..10).random()
            var auxProductoBodega1=listNegocio[2].bodega.listaProducto.random()
            var auxProducto=Producto(auxProductoBodega1.nombre,auxProductoBodega1.precio,stock)
            if(!listNegocio[2].bodega.eliminarProducto(auxProducto)){
                Log.d("Mensaje_Error_Compra","No se logro vender el producto ${auxProducto.nombre} del almacen ${listNegocio[2].nombre}")
            }
        }

        Log.i("DATA_MESSAGE","dia 7 ) el negocio 2 recibe un camión con 10 productos existentes\n")
        for(i in 0..9){
            var stock=(1..10).random()
            var auxProductoBodega1=listNegocio[1].bodega.listaProducto.random()
            var auxProducto=Producto(auxProductoBodega1.nombre,auxProductoBodega1.precio,stock)
            listNegocio[1].bodega.cargarProducto(auxProducto)
        }

        Log.i("DATA_MESSAGE","dia 8) el negocio 1 le compra al negocio 3 5 productos")
        for(i in 0..4){
            var stock=(1..10).random()
            var auxProductoBodega1=listNegocio[2].bodega.listaProducto.random()
            var auxProducto=Producto(auxProductoBodega1.nombre,auxProductoBodega1.precio,stock)
            if(!listNegocio[2].bodega.eliminarProducto(auxProducto)){
                Log.d("Mensaje_Error_Compra","No se logro vender el producto ${auxProducto.nombre} del almacen ${listNegocio[2].nombre}")
            }
            listNegocio[0].bodega.cargarProducto(auxProducto)
        }

        Log.i("DATA_MESSAGE","dia 9) el negocio 2 le compra 7 productos al negocio 1 y si no tiene stock se los compra al negocio 3")
        var numNegocio=if(listNegocio[0].bodega.listaProducto.size-1>=6) 0 else 2
        for(i in 0..4){
            var stock=(1..10).random()
            var auxProductoBodega1=listNegocio[numNegocio].bodega.listaProducto.random()
            var auxProducto=Producto(auxProductoBodega1.nombre,auxProductoBodega1.precio,stock)
            if(!listNegocio[numNegocio].bodega.eliminarProducto(auxProducto)){
                Log.d("Mensaje_Error_Compra","No se logro vender el producto ${auxProducto.nombre} del almacen ${listNegocio[numNegocio].nombre}")
            }
            listNegocio[1].bodega.cargarProducto(auxProducto)
        }

        Log.i("DATA_MESSAGE","dia 10) todos los negocios venden 5 productos iguales. y mismas cantidades. ")
        for(i in 0..4){
            var stock=(1..10).random()
            var auxProducto1=listNegocio[0].bodega.listaProducto[i]
            for(j in 0..2){
                var auxProducto=Producto(auxProducto1.nombre,auxProducto1.precio,stock)
                if(!listNegocio[j].bodega.eliminarProducto(auxProducto)){
                    Log.d("Mensaje_Error_Compra","No se logro vender el producto ${auxProducto.nombre} del almacen ${listNegocio[j].nombre}")
                }
            }

        }



    }

    /*
    dia 9) el negocio 2 le compra 7 productos al negocio 1 y si no tiene stock se los compra al negocio 3

    dia 10) todos los negocios venden 5 productos iguales. y mismas cantidades.
    */




}