package com.project.dontix.wallet

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.project.dontix.R
import com.project.dontix.utils.Preferences
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_my_wallet_top_up.*
import kotlinx.android.synthetic.main.activity_my_wallet_top_up.iv_back
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_setting.*
import java.lang.NumberFormatException
import java.text.NumberFormat
import java.util.*


class MyWalletTopUpActivity : AppCompatActivity() {

    private lateinit var preferences: Preferences
    private lateinit var mDatabase: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_wallet_top_up)
        iv_back.setOnClickListener {
            finish()
        }
        preferences = Preferences(applicationContext)
        if (!preferences.getValues("saldo").equals("")){
            currecy(preferences.getValues("saldo")!!.toDouble(), tv_sisasaldo)
        } else {
            tv_sisasaldo.setText("IDR 0")
        }
        initListener()

    }

    private fun initListener() {
        btn_top_up.setOnClickListener {
            topUp()
            updateData(topUp().toString())
        }

        et_amount.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) { }

            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {  }

            override fun afterTextChanged(s: Editable) {
                try {
                    if (s.toString().toInt() >= 10000) {
                        btn_top_up.visibility = View.VISIBLE
                    } else {

                        btn_top_up.visibility = View.INVISIBLE
                    }
                } catch (e : NumberFormatException) {

                    btn_top_up.visibility = View.INVISIBLE
                }
            }

        })
    }
    private fun currecy(harga:Double, textView: TextView) {
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        textView.setText(formatRupiah.format(harga as Double))

    }

    private fun topUp(): Int {
        preferences = Preferences(applicationContext)
        if (!preferences.getValues("saldo").toString().equals("")){
            var i = preferences.getValues("saldo").toString().toInt()
            i += et_amount.text.toString().toInt()
            return i
        } else {
            var i = et_amount.text.toString().toInt()
            return i
        }

    }
    private fun updateData(saldo : String) {
        preferences = Preferences(applicationContext)
        mDatabase = FirebaseDatabase.getInstance().getReference("User")
        val user = mapOf<String,String> (
            "saldo" to saldo
        )

        mDatabase.child(preferences.getValues("nama").toString()).updateChildren(user).addOnSuccessListener {
            startActivity(Intent(this, MyWalletSuccessActivity::class.java))
        }.addOnFailureListener{
            Toast.makeText(this,"Gagal Melakukan TopUp!",Toast.LENGTH_SHORT).show()
        }
    }

}
