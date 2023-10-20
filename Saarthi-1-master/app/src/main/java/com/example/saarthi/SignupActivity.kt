package com.example.saarthi


import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth


class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        var register = findViewById<Button>(R.id.sign_up_btn)
        val go_login = findViewById<TextView>(R.id.go_login)
        val email = findViewById<EditText>(R.id.email_edt_text)
        val password = findViewById<EditText>(R.id.pass_edt_text)
        val phone = findViewById<EditText>(R.id.phone)
        val name = findViewById<EditText>(R.id.name_edt_text)

        go_login.setOnClickListener {
            val intent = Intent(this , LoginActivity::class.java)
            startActivity(intent)
        }

        var mAuth = FirebaseAuth.getInstance()
        var progressDialog = ProgressDialog(this)

        register.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val emailString = email.text.toString()
                val passwordString = password.text.toString()
                val phoneString: String = phone.getText().toString()
                val nameString: String = name.getText().toString()

                if (TextUtils.isEmpty(emailString)) {
                    email.error = "Email is required"
                }
                if (TextUtils.isEmpty(passwordString)) {
                    password.error = "Password is required"
                }
                if (TextUtils.isEmpty(phoneString)) {
                    phone.setError("Phone is required")
                }
                if (TextUtils.isEmpty(nameString)) {
                    name.setError("Name is required")
                } else {
                    progressDialog.setMessage("registration in progress")
                    progressDialog.setCanceledOnTouchOutside(false)
                    progressDialog.show()
                    mAuth.createUserWithEmailAndPassword(emailString, passwordString)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val intent = Intent(applicationContext, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(
                                    applicationContext,
                                    task.exception.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            progressDialog.dismiss()
                        }
                }
            }
        })

    }
}