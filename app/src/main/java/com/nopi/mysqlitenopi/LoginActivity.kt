package com.nopi.mysqlitenopi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity(){
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        val btnLog: Button = findViewById(R.id.btnLogin)
        val tiName: TextInputLayout = findViewById(R.id.tiName)
        val tiPass: TextInputLayout = findViewById(R.id.tiPasswd)
        var name = tiName.editText?.text.toString()
        var pass = tiPass.editText?.text.toString()
        val db = DBHelper(this, null)
        btnLog.setOnClickListener {
            if (tiName.editText?.text?.isNotEmpty() == false &&
                tiPass.editText?.text?.isNotEmpty() == false)
                Toast.makeText(this, "Isi semua data", Toast.LENGTH_SHORT).show()
            else{
                var logindt:Boolean =
                    db.LoginDatas(tiName.editText?.text.toString(),tiPass.editText?.text.toString());
                if (logindt==true){
                    Toast.makeText(this, "Login berhasil",
                        Toast.LENGTH_SHORT).show()
                    var intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this, "login gagal",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}