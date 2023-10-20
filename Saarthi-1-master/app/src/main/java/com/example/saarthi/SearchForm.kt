package com.example.saarthi

import android.content.Intent
import android.media.MediaParser.SeekPoint
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible

class SearchForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_form)

        val stateArray = arrayOf("Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh",
            "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jharkhand", "Karnataka",
            "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram",
            "Nagaland", "Odisha", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu",
            "Telangana", "Tripura", "Uttar Pradesh", "Uttarakhand", "West Bengal")
        val cityUP = arrayOf("Lucknow", "Kanpur", "Varanasi", "Agra", "Prayagraj","Pratapgarh",
            "Ghaziabad", "Meerut", "Noida", "Bareilly", "Moradabad",
            "Aligarh", "Saharanpur", "Gorakhpur", "Faizabad", "Jhansi",
            "Muzaffarnagar", "Mathura", "Sultanpur", "Banda", "Amroha",
            "Ayodhya", "Mirzapur", "Etawah", "Firozabad", "Hapur",
            "Bulandshahr", "Bahraich", "Rampur", "Modinagar", "Unnao")
        val colleges = arrayOf("Amity University",
            "Jaypee Institute of Information Technology",
            "Galogotia college of engineering and technology",
            "Galgotias University",
            "Sharda University",
            "Noida Institute of Engineering and Technology",
            "JSS Academy of Technical Education",
            "Asian Business School",
            "Birla Institute of Technology",
            "IILM College of Engineering and Technology")
        val allPlaces = arrayOf("Railway Stations","Airports","Colleges","Bus Stations","Hospitals","Parks","Tourist Places")

        val states = findViewById<Spinner>(R.id.allStates)
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            applicationContext,
            android.R.layout.simple_spinner_dropdown_item,
            stateArray
        )
        states.setAdapter(adapter)

//        val places = findViewById<Spinner>(R.id.allPlaces)
//        val adapter2 : ArrayAdapter<String> = ArrayAdapter<String>(
//            applicationContext,
//            android.R.layout.simple_spinner_dropdown_item,
//            allPlaces
//        )
//        places.setAdapter(adapter2)


        states.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapter: AdapterView<*>?, view: View, i: Int, l: Long) {
                val selected_states: String = states.getSelectedItem().toString()


                if (selected_states == "Uttar Pradesh") {
                    val city = findViewById<Spinner>(R.id.allCity)
                    val adapter1 = ArrayAdapter(
                        applicationContext,
                        android.R.layout.simple_spinner_dropdown_item,
                        cityUP
                    )
                    city.setAdapter(adapter1)

                    city.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            adapter: AdapterView<*>?,
                            view: View,
                            i: Int,
                            l: Long
                        ) {
                            val selected_city: String = city.getSelectedItem().toString()

                            val places = findViewById<Spinner>(R.id.allPlaces)
                            val adapter2 : ArrayAdapter<String> = ArrayAdapter<String>(
                                applicationContext,
                                android.R.layout.simple_spinner_dropdown_item,
                                allPlaces
                            )
                            places.setAdapter(adapter2)

                            places.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    adapter: AdapterView<*>?,
                                    view: View,
                                    i: Int,
                                    l: Long
                                ) {
                                    val selected_place: String = places.getSelectedItem().toString()
                                    val cllg_card = findViewById<CardView>(R.id.cardView4)



                                    if (selected_city == "Noida" && selected_place == "Colleges")
                                    {
                                        cllg_card.isVisible = true
                                        val cllg = findViewById<Spinner>(R.id.allCollege)
                                        val adapter3 = ArrayAdapter(
                                            applicationContext,
                                            android.R.layout.simple_spinner_dropdown_item,
                                            colleges
                                        )
                                        cllg.setAdapter(adapter3)


                                        cllg.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                                            override fun onItemSelected(
                                                adapter: AdapterView<*>?,
                                                view: View,
                                                i: Int,
                                                l: Long
                                            ) {
                                                val selected_cllg: String =
                                                    cllg.getSelectedItem().toString()

                                                if (selected_cllg == "Galogotia college of engineering and technology")
                                                {
                                                    val proceed = findViewById<Button>(R.id.proceed)
                                                    proceed.setOnClickListener {
                                                        val intent = Intent(this@SearchForm , SearchWheelchair::class.java)
                                                        startActivity(intent)
                                                    }
                                                }
                                            }

                                            override fun onNothingSelected(p0: AdapterView<*>?) {}
                                        })


                                    }

                                }

                                override fun onNothingSelected(p0: AdapterView<*>?) {
                                    TODO("Not yet implemented")
                                }
                            })


                        }

                        override fun onNothingSelected(p0: AdapterView<*>?) {}
                    })




                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        })



    }


}
