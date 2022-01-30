package com.nopi.mysqlitenopi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

class UpdateActivity : AppCompatActivity() {
    companion object{
        const val Nama = "nama"
        const val Pass = "pass"
        const val Age = "age"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        val btnCancel: MaterialButton = findViewById(R.id.btnCancel)
        val tiName: TextInputLayout = findViewById(R.id.tiName)
        val tiPass: TextInputLayout = findViewById(R.id.tiPass)
        val tiAge: TextInputLayout = findViewById(R.id.tiAge)
        val btnUpdate: MaterialButton = findViewById(R.id.btnUpdate)
        val db = DBHelper(this, null)
        val nama = intent.getStringExtra(Nama);
        tiName.editText?.setText(nama)
        val pass = intent.getStringExtra(Pass);
        tiPass.editText?.setText(pass)
        val age = intent.getStringExtra(Age);
        tiAge.editText?.setText(age)


        btnCancel.setOnClickListener {
            finish()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        btnUpdate.setOnClickListener {
            if (tiName.editText?.text?.isNotEmpty() == true && tiPass.editText?.text?.isNotEmpty() == true && tiAge.editText?.text?.isNotEmpty()
                == true) {
                if (db.UpdateDatas(tiName.editText?.text.toString(),tiPass.editText?.text.toString(), tiAge.editText?.text.toString())){
                    Toast.makeText(this, "Data berhasil diupdate ",
                        Toast.LENGTH_SHORT).show()
                    var intent = Intent(this@UpdateActivity,
                        MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Data gagal diupdate",
                        Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Data tidak boleh kosong",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

}