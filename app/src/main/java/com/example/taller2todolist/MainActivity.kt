package com.example.taller2todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.ArrayAdapter
import java.io.PrintStream
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    //Agregar una tarea
    var nombredelista = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val adaptador = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombredelista)
        val lista= findViewById<ListView>(R.id.additem)
        lista.adapter = adaptador

        val boton = findViewById<Button>(R.id.button)
        boton.setOnClickListener { view->onClick(view)
            adaptador.notifyDataSetChanged()

        }
        lista.setOnItemLongClickListener { parent, view, position, id -> eliminar(position)
            adaptador.notifyDataSetChanged()
            ; return@setOnItemLongClickListener true}

        var guardar = PrintStream(openFileOutput("actividades.txt", MODE_APPEND))
        var leer = Scanner(openFileInput("actividades.txt"))
        while (leer.hasNextLine()){
            var escribir = leer.nextLine()
            nombredelista.add(escribir)
        }
    }
    fun onClick (v: View){
        val cajadetexto = findViewById<EditText>(R.id.addtext)
        val tarea = cajadetexto.text.toString()
        if (tarea != "" || tarea != null){
            nombredelista.add(tarea)
            PrintStream(openFileOutput("actividades.txt", MODE_APPEND)).println(tarea)
        }

    }
    fun eliminar (pos: Int ){
        nombredelista.remove(nombredelista.get(pos))
    }

}