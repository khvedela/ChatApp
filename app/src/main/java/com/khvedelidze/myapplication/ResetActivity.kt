package com.khvedelidze.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ResetActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth

    private lateinit var resetButton: Button
    private lateinit var resetEmailInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset)

        val action=supportActionBar
        action!!.title = "Password Reset"
        action.setDisplayHomeAsUpEnabled(true)
        action.setDisplayHomeAsUpEnabled(true)

        mAuth = FirebaseAuth.getInstance()

        resetEmailInput = findViewById(R.id.resetEmailEditText)
        resetButton = findViewById(R.id.sendEmailButton)

        resetButton.setOnClickListener {

            val email = resetEmailInput.text.toString()

            if (email.isEmpty()) {
                Toast.makeText(this, "Empty!", Toast.LENGTH_SHORT).show()
            } else {
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                     if (task.isSuccessful) {
                         startActivity(Intent(this, MainActivity::class.java))
                         finish()
                     } else {
                         Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                     }
                }
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}