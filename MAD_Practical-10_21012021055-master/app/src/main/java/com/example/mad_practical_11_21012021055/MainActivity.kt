package com.example.mad_practical_10_21012021055

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var personListView:ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        personListView=findViewById(R.id.Listview)


        val btn:FloatingActionButton= findViewById(R.id.btn_float)
        btn.setOnClickListener(){
            setpersondatatolistview()
        }
    }
    fun setpersondatatolistview(){
    //    personListView.adapter=PersonAdapter(this, arrayListOf(
    //        Person("1","Akshay","9332493494","akshay@gnu.ac.in","h hostel","24.7",40.34),
    //        Person("2","yash","3427935923","yash@gnu.ac.in","h hostel","24.12",43.89),
         //   Person("3","Harsh","9873456732","harsh@gnu.ac.in","h hostel","21.65",23.56),
       //     Person("4","vikas","4325692845","vikas@gnu.ac.in","h hostel","56.20",24.67),

     //   ))
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val data = httpRequest().makeServiceCall(
                    "https://api.json-generator.com/templates/qjeKFdjkXCdK/data",
                    "rbn0rerl1k0d3mcwgw7dva2xuwk780z1hxvyvrb1")
                withContext(Dispatchers.Main) {
                    try {
                        if(data != null)
                            runOnUiThread{getPersonDetailsFromJson(data)}
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    private fun getPersonDetailsFromJson(sJson: String?) {
        val personList = ArrayList<Person>()
        try {
            val jsonArray = JSONArray(sJson)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray[i] as JSONObject
                val person = Person(jsonObject)
                personList.add(person)
            }
            var listView1 : ListView = findViewById(R.id.Listview)
            listView1.adapter = PersonAdapter(this, personList)
        } catch (ee: JSONException) {
            ee.printStackTrace()
        }
    }
}
