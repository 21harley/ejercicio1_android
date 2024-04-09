package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.example.myapplication.class_example.Employee
import com.example.myapplication.class_example.Fruit
import com.example.myapplication.class_example.Product
import com.example.myapplication.databinding.ActivityMainBinding
import android.util.Log

////////////////////////////////////
// Exercise 1   POO               //
// Team 5  09/04/2024             //
// Eliseo Nisias Marin Navarro    //
// Marvin Monge Valverde          //
// John Harley Llanes Escobar     //
////////////////////////////////////

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var listFruit:MutableList<Fruit> = mutableListOf()
    var listBusiness:MutableList<Employee> = mutableListOf()
    val listNameFruit= listOf<String>(
        resources.getString(R.string.fruit_apple),
        resources.getString(R.string.fruit_pear),
        resources.getString(R.string.fruit_tangerine),
        resources.getString(R.string.fruit_grape),
        resources.getString(R.string.fruit_strawberry),
        resources.getString(R.string.fruit_orange),
        resources.getString(R.string.fruit_handle),
    )
    var listNameProduct:MutableList<String> = mutableListOf(
        "WHITE OR NON-WHITE CORN",
        "PACKAGED OR BULK BEANS",
        "PACKAGED OR BULK RICE",
        "STANDARD SUGAR",
        "CORN FLOUR",
        "EDIBLE VEGETABLE OIL",
        "TUNA",
        "SARDINE",
        "POWDERED MILK",
        "JALAPEÑO PEPPERS",
        "CHIPOTLE",
        "CANNED STRIPS OR SERRANO PEPPERS",
        "INSTANT COFFEE",
        "TABLE SALT, OATS",
        "SOUP PASTA",
        "WHEAT FLOUR"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createWatehouseFruit()
        loadSpinner()
        binding.contentMainId.store.setOnClickListener {
            val (value,auxFruit)=valuesSelect()
            if(auxFruit.loadStock(value) && value != 0){
                displayMessage("${auxFruit.name}, stock:${auxFruit.stock}")
            }
            cleatText()

        }
        binding.contentMainId.sell.setOnClickListener {
            val (value,auxFruit)=valuesSelect()
            if(auxFruit.sellStock(value) && value != 0){
                displayMessage("${auxFruit.name}, sold:${value}")
            }
            if(auxFruit.stock-value<0){
                displayMessage("The number entered exceeds the stock, please enter a value equal to or less than ${auxFruit.stock}")
            }
            cleatText()
        }
        binding.contentMainId.status.setOnClickListener {
            printFruitStatus()
        }
        binding.contentMainId.exerciseTwo.setOnClickListener {
            warehouseCompetition()
        }
        binding.contentMainId.credits.setOnClickListener {
            displayMessage(
                " Exercise 1   POO              \n"+
                " Team  5    09/04/2024         \n"+
                " Eliseo Nisias Marin Navarro   \n"+
                " Marvin Monge Valverde         \n"+
                " John Harley Llanes Escobar    \n"
            )
        }
    }
    //Tools
    fun valuesSelect():Pair<Int,Fruit>{
        val value=validateData(9999).toInt()
        val select=binding.contentMainId.spinner.firstVisiblePosition
        val auxFruit=listFruit[select]
        return Pair(value,auxFruit)
    }
    fun cleatText(){
        binding.contentMainId.inputText.text.clear()
    }
    fun displayMessageLinebBreak(message:String){
        binding.contentMainId.outputMessage.text = message+"\n\n"
    }
    fun displayMessage(message:String){
        binding.contentMainId.outputMessage.text = message
    }
    fun validateData(limit:Int):Float{
        try{
            val date=binding.contentMainId.inputText.text
            val aux=date.toString().toFloat()
            if(limit<aux || aux==0f || 0>aux){
                throw Exception("Incorrect value")
            }
            return aux
        }catch (e:Exception){
            displayMessageLinebBreak("Error when entering fruit quantity \n" +
                    "\n only numbers and quantities less than 9999 are allowed")
        }
        return 0.0f
    }
    fun printFruitStatus(){
        var resp = "\n"
        var rep1 = "\n"
        for( i in listFruit){
            if(i.stock>0) resp +="${i.name}:${i.stock} \n"
            else rep1 +="${i.name}:${i.stock} \n"
        }
        displayMessage("Fruits with stock greater than 0:${resp} \n\nFruits with stock at 0: ${rep1} \n\n")
    }

    //Exercise 1
    fun createWatehouseFruit(){
        //carga de frutas
        for(name in listNameFruit){
            var number=(60..180).random().toFloat()
            listFruit.add(Fruit(number,name))
        }

        //modificacion de stock
        for(i in 0..5){
            listFruit[i].stock=10
        }

    }
    fun loadSpinner(){
        binding.contentMainId.spinner.adapter = ArrayAdapter<String>(
            this,android.R.layout.simple_list_item_1,listNameFruit
        )
    }

    //Exercise 2
    fun createWetehouse(){
        listBusiness.add(Employee("12452","Business1"))
        listBusiness.add(Employee("124536","Business2"))
        listBusiness.add(Employee("14785","Business3"))
    }

    fun firstDay(){
        Log.i("DATA_MESSAGE","Day 1) Business 2 buys 15 products")
        for(i in 0..14){
            val stock=(1..10).random()
            val price=(100..500).random().toFloat()
            listBusiness[1].watehouse.loadProduct(Product(listNameProduct[i],price,stock))
        }
    }
    fun secondDay(){
        Log.i("DATA_MESSAGE","Day 2. Business 3 receives a truck with 10 new products to add to the warehouse.")
        for(i in 0..9){
            val stock=(1..10).random()
            val price=(100..500).random().toFloat()
            listBusiness[2].watehouse.loadProduct(Product(listNameProduct[i],price,stock))
        }
    }
    fun thirdDay(){
        Log.i("DATA_MESSAGE","Day 3) Business 1 sells 5 products")
        for(i in 0..4){
            val stock=(1..10).random()
            val price=(100..500).random().toFloat()
            val auxProduct=Product(listNameProduct[i],price,stock)
            if(!listBusiness[0].watehouse.deleteProduct(auxProduct)){
                Log.d("Purchase_Error_Message","The product ${auxProduct.name} from the warehouse ${listBusiness[0].name} could not be sold.")
            }
        }
    }
    fun fourthDay(){
        Log.i("DATA_MESSAGE","Day 4) Business 1 receives a return of a product")
        if(listBusiness[0].watehouse.listProduct.size>0){
            val auxWarehouseProduct1=listBusiness[0].watehouse.listProduct.random()
            listBusiness[0].watehouse.loadProduct(
                Product(auxWarehouseProduct1.name,auxWarehouseProduct1.price,10)
            )
        }else{
            val stock=(1..10).random()
            val price=(100..500).random().toFloat()
            val randomName=listNameProduct[(0..listNameProduct.size-1).random()]
            listBusiness[0].watehouse.loadProduct(
                Product(randomName,price,stock)
            )
        }
    }
    fun fifthDay(){
        Log.i("DATA_MESSAGE","Day 5) Business 2 sells 10 products")
        for(i in 0..9){
            val stock=(1..10).random()
            val auxWarehouseProduct1=listBusiness[1].watehouse.listProduct.random()
            val auxProduct=Product(auxWarehouseProduct1.name,auxWarehouseProduct1.price,stock)
            if(!listBusiness[1].watehouse.deleteProduct(auxProduct)){
                Log.d("Purchase_Error_Message","The product ${auxProduct.name} from the warehouse ${listBusiness[1].name} could not be sold.")
            }
        }
    }
    fun sixthDay(){
        Log.i("DATA_MESSAGE","Day 6) Business 3 sells 10 products")
        for(i in 0..9){
            val stock=(1..10).random()
            val auxWarehouseProduct1=listBusiness[2].watehouse.listProduct.random()
            val auxProduct=Product(auxWarehouseProduct1.name,auxWarehouseProduct1.price,stock)
            if(!listBusiness[2].watehouse.deleteProduct(auxProduct)){
                Log.d("Purchase_Error_Message","The product ${auxProduct.name} from the warehouse ${listBusiness[2].name} could not be sold.")
            }
        }
    }
    fun seventhDay(){
        Log.i("DATA_MESSAGE","Day 7) Business 2 receives a truck with 10 existing products\n")
        for(i in 0..9){
            val stock=(1..10).random()
            val auxWarehouseProduct1=listBusiness[1].watehouse.listProduct.random()
            val auxProduct=Product(auxWarehouseProduct1.name,auxWarehouseProduct1.price,stock)
            listBusiness[1].watehouse.loadProduct(auxProduct)
        }
    }
    fun eighthDay(){
        Log.i("DATA_MESSAGE","Day 8) Business 1 buys 5 products from business 3")
        for(i in 0..4){
            val stock=(1..10).random()
            val auxWarehouseProduct1=listBusiness[2].watehouse.listProduct.random()
            val auxProduct=Product(auxWarehouseProduct1.name,auxWarehouseProduct1.price,stock)
            if(!listBusiness[2].watehouse.deleteProduct(auxProduct)){
                Log.d("Purchase_Error_Message","The product ${auxProduct.name} from the warehouse ${listBusiness[2].name} could not be sold.")
            }
            listBusiness[0].watehouse.loadProduct(auxProduct)
        }
    }
    fun ninthDay(){
        Log.i("DATA_MESSAGE","Day 9) Business 2 buys 7 products from business 1 and if it doesn't have stock it buys them from business 3")
        val numBusiness=if(listBusiness[0].watehouse.listProduct.size-1>=6) 0 else 2
        for(i in 0..4){
            val stock=(1..10).random()
            val auxWarehouseProduct1=listBusiness[numBusiness].watehouse.listProduct.random()
            val auxProduct=Product(auxWarehouseProduct1.name,auxWarehouseProduct1.price,stock)
            if(!listBusiness[numBusiness].watehouse.deleteProduct(auxProduct)){
                Log.d("Purchase_Error_Message","The product ${auxProduct.name} from the warehouse ${listBusiness[numBusiness].name} could not be sold.")
            }
            listBusiness[1].watehouse.loadProduct(auxProduct)
        }
    }
    fun tenthDay(){
        Log.i("DATA_MESSAGE","Day 10) All businesses sell 5 identical products. and same quantities. ")
        for(i in 0..4){
            val stock=(1..10).random()
            val auxProduct1=listBusiness[0].watehouse.listProduct[i]
            for(j in 0..2){
                val auxProduct=Product(auxProduct1.name,auxProduct1.price,stock)
                if(!listBusiness[j].watehouse.deleteProduct(auxProduct)){
                    Log.d("Purchase_Error_Message","The product ${auxProduct.name} from the warehouse ${listBusiness[j].name} could not be sold.")
                }
            }
        }
    }
    fun warehouseCompetition(){
        createWetehouse()
        firstDay()
        secondDay()
        thirdDay()
        fourthDay()
        fifthDay()
        sixthDay()
        seventhDay()
        eighthDay()
        ninthDay()
        tenthDay()
        showScreen()
    }

    fun showScreen(){
        displayMessage("...")
        var auxString="\n"
        for( a in listBusiness){
            var total_sale_business=0.0f
            auxString+="Business:${a.name}\n"
            for (p in a.watehouse.listProduct){
                auxString+="Name:${p.name}\nStock:${p.stock}\nTotal Sale${p.price}\nTotal generated:${p.sale*p.price}\n"
                total_sale_business+=p.sale*p.price
            }
            auxString+="Total Sale Business:${total_sale_business} \n\n"
        }
        displayMessage(auxString)
    }


}