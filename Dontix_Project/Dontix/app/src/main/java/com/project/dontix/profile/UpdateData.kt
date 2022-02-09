package com.project.dontix.profile

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.project.dontix.R
import com.project.dontix.home.SettingFragment
import com.project.dontix.utils.Preferences
import com.project.dontix.wallet.MyWalletSuccessActivity
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_edit_profile.iv_back
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_setting.*

class UpdateData : AppCompatActivity() {
    lateinit var userName: String
    lateinit var password: String
    lateinit var nama: String
    lateinit var email: String

    lateinit var mDatabase: DatabaseReference
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        iv_back.setOnClickListener {
            finish()
        }
        preferences = Preferences(applicationContext)
        et_username.setText(preferences.getValues("username").toString())

        btn_simpan.setOnClickListener {
            userName = et_username.text.toString()
            password = et_password.text.toString()
            nama = et_nama.text.toString()
            email = et_email.text.toString()
            updateData(userName,password,nama,email)
            finish()
        }
    }

    private fun updateData(userName: String, password: String, nama: String, email: String) {
        mDatabase = FirebaseDatabase.getInstance().getReference("User")
        val user = mapOf<String,String> (
            "username" to userName,
            "password" to password,
            "nama" to nama,
            "email" to email
        )

        mDatabase.child(userName).updateChildren(user).addOnSuccessListener {
            et_username.text.clear()
            et_password.text.clear()
            et_nama.text.clear()
            et_email.text.clear()
            Toast.makeText(this,"Update data berhasil!",Toast.LENGTH_SHORT).show()

        }.addOnFailureListener{
            Toast.makeText(this,"Update data gagal!",Toast.LENGTH_SHORT).show()
        }
    }
}