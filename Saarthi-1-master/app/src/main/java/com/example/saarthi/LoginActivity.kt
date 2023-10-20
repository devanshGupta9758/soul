package com.example.saarthi


import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.FirebaseUser


class LoginActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private var authStateListener: AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val signup = findViewById<TextView>(R.id.sign_up_btn)

        signup.setOnClickListener {
            val intent = Intent(this , SignupActivity::class.java)
            startActivity(intent)
        }

        mAuth = FirebaseAuth.getInstance()
        var progressDialog = ProgressDialog(this)

        authStateListener = AuthStateListener {
            val user: FirebaseUser? = mAuth!!.getCurrentUser()
            if (user != null) {
                val intent2 = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent2)
                finish()
            }
        }
        val email = findViewById<EditText>(R.id.email_edt_text)
        val password = findViewById<EditText>(R.id.pass_edt_text)


        val login = findViewById<View>(R.id.login_btn)
        login.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val emailString = email.text.toString()
                val passwordString = password.text.toString()
                if (TextUtils.isEmpty(emailString)) {
                    email.error = "Email is required"
                }
                if (TextUtils.isEmpty(passwordString)) {
                    password.error = "Password is required"
                } else {
                    progressDialog.setMessage("login in progress")
                    progressDialog.setCanceledOnTouchOutside(true)
                    progressDialog.show()
                    mAuth!!.signInWithEmailAndPassword(emailString, passwordString)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val intent2 = Intent(applicationContext, MainActivity::class.java)
                                startActivity(intent2)
                                finish()
                                progressDialog.dismiss()
                            } else {
                                Toast.makeText(
                                    applicationContext,
                                    task.exception.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                                progressDialog.dismiss()
                            }
                        }
                }
            }
        })
    }

    override fun onStart() {
        super.onStart()
        authStateListener?.let { mAuth?.addAuthStateListener(it) }
    }

    override fun onStop() {
        super.onStop()
        authStateListener?.let { mAuth?.removeAuthStateListener(it) }
    }
}