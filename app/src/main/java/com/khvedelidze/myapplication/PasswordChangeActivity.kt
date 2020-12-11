package com.khvedelidze.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class PasswordChangeActivity : AppCompatActivity() {

    private lateinit var newPasswordInput: EditText
    private lateinit var submitButton: Button

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_change)

        newPasswordInput = findViewById(R.id.newPasswordEditText)
        submitButton = findViewById(R.id.passwordChangeButton)

        submitButton.setOnClickListener {

            val newPassword = newPasswordInput.text.toString()

            if(newPassword.isEmpty()) {
                Toast.makeText(this, "Empty!", Toast.LENGTH_SHORT).show()
            } else {
                auth.currentUser?.updatePassword(newPassword)?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this, PersonActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Error?", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }

    }
}