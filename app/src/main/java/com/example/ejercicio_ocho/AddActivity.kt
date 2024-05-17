package com.example.ejercicio_ocho

import android.content.ContentValues
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun save(v: View){
        val name = findViewById<EditText>(R.id.txtName)
        val phone = findViewById<EditText>(R.id.txtPhoneNomber)

        val db = openOrCreateDatabase("Agenda", MODE_PRIVATE, null)
        val parameter = ContentValues()
        parameter.put("name", name.text.toString())
        parameter.put("phone", phone.text.toString())
        db.insert("contacts", null, parameter)

        val contact = Contact(name.text.toString(), phone.text.toString())
        ProvicionalData.listContact.add(contact)

        Toast.makeText(this, "Se guardo el contacto", Toast.LENGTH_LONG).show()
        db.close()
        finish()
    }
}