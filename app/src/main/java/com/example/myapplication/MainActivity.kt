package com.example.myapplication

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.example.myapplication.class_example.Fruit
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var listaFruit:MutableList<Fruit> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cargarAlmacen()
        cargarSpinner()
    }
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
}