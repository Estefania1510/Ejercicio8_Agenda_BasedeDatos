package com.example.ejercicio_ocho

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditActivity : AppCompatActivity() {

    var position: Int = 0
    lateinit var txtName: EditText
    lateinit var txtPhoneNumber: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        val txtTitle = findViewById<TextView>(R.id.txtTitle)
        txtTitle.text = "Modificar Contacto"
        position = intent.getIntExtra("position", -1)
        Log.e("Contact", "Se recibio un ${position}")
        txtName = findViewById(R.id.txtName)
        txtPhoneNumber = findViewById(R.id.txtPhoneNomber)

        val contact = ProvicionalData.listContact[position]
        txtName.setText(contact.name)
        txtPhoneNumber.setText(contact.phoneNumber)
        Toast.makeText(this, "$position", Toast.LENGTH_LONG).show()

    }
    fun save(v: View) {
        val contact = Contact(txtName.text.toString(), txtPhoneNumber.text.toString())
        ProvicionalData.listContact[position] = contact

        val db = openOrCreateDatabase("Agenda", MODE_PRIVATE, null)
        val parameters = ContentValues()
        parameters.put("name", txtName.text.toString())
        parameters.put("phone", txtPhoneNumber.text.toString())

        db.update("contacts", parameters, "id=${position+1}", null)

        Toast.makeText(this, "Se modifico", Toast.LENGTH_LONG).show()
        db.close()
        finish()
    }
}