package com.example.mad_practical_10_21012021055

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import org.w3c.dom.Text

class PersonAdapter(context:Context,val array: ArrayList<Person>):ArrayAdapter<Person>(context,0,array) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.person_item,parent,false)
        val persondata= array.get(position)
        view.findViewById<TextView>(R.id.textView3).text=persondata.name
        view.findViewById<TextView>(R.id.textView).text=persondata.phoneNo
        view.findViewById<TextView>(R.id.textView2).text=persondata.emailId
        view.findViewById<TextView>(R.id.textView4).text=persondata.address

        view.findViewById<MaterialButton>(R.id.material_button_2).setOnClickListener {
            Intent(parent.context,MapsActivity::class.java).putExtra("Object",persondata).apply { context.startActivity(this) }
        }
        return view;
    }
}