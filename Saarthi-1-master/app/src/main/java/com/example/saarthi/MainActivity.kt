package com.example.saarthi

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)

        val logout = findViewById<Button>(R.id.logout_btn)
        logout.setOnClickListener {
            AlertDialog.Builder(this@MainActivity)
                .setTitle("Saarthi")
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes") { dialog, id ->
                    FirebaseAuth.getInstance().signOut()
                    val intent = Intent(applicationContext, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("No", null)
                .show()

        }

        val search = findViewById<CardView>(R.id.option2)
        search.setOnClickListener {
            val intent = Intent(this , SearchForm::class.java)
            startActivity(intent)
        }
        val mywheel = findViewById<CardView>(R.id.option1)
        mywheel.setOnClickListener {
            val intent1 = Intent(this, MyWheelchair::class.java)
            startActivity(intent1)
        }
    }
}