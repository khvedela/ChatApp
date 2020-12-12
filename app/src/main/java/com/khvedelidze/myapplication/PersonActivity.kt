package com.khvedelidze.myapplication

import android.app.Person
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class PersonActivity : AppCompatActivity() {

    private lateinit var userInfoTextView: TextView
    private lateinit var passwordChangeButton: Button
    private lateinit var logoutButton: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)

        mAuth = FirebaseAuth.getInstance()


        val action = supportActionBar
        action!!.title = "New Activity"


        userInfoTextView = findViewById(R.id.userInfoTextView)
        passwordChangeButton = findViewById(R.id.gotoChangePasswordButton)
        logoutButton =  findViewById(R.id.logoutButton)

        userInfoTextView.text = mAuth.currentUser?.uid

        passwordChangeButton.setOnClickListener {
            startActivity(Intent(this, PasswordChangeActivity::class.java))
        }

        logoutButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            mAuth.signOut()
        }

    }
}