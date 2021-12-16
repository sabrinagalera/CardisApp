package com.example.cardissapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.cardissapp.MainActivity
import com.example.cardissapp.Models.Global

import com.example.cardissapp.data.LoginRepository
import com.example.cardissapp.data.model.LoggedInUser
import com.example.cardissapp.databinding.ActivityLoginBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.Constants.TAG
import com.google.firebase.messaging.ktx.messaging

class LoginActivity: AppCompatActivity() {
    var msg: String = ""
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TOKEN FIREBASE

        Firebase.messaging.getToken().addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("marco", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            msg = token.toString()
            Log.w("marco", msg)

            //  Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        })
        binding.btnLogin.setOnClickListener {

            val servicio = LoginRepository()
            val user = LoggedInUser(clave = binding.password.text.toString(), token = msg)

            servicio.getautorizacion(user)?.observe(this, Observer {
                if (it != null) {

                    Global.movil = it.movil1.toString()
                    Toast.makeText(this, "Token Registrado", Toast.LENGTH_LONG).show()
                    finish()
                    val intent = Intent(this, MainActivity::class.java)

                    startActivity(intent)


                } else {
                    binding.txtloginMens.text = "Datos incorrectos"
                }
            })

        }
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}