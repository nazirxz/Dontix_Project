package com.project.dontix.wallet

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.project.dontix.R
import com.project.dontix.utils.Preferences
import com.project.dontix.wallet.adapter.WalletAdapter
import com.project.dontix.wallet.model.Wallet
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_my_wallet.*
import kotlinx.android.synthetic.main.activity_my_wallet.btn_top_up
import kotlinx.android.synthetic.main.activity_my_wallet.tv_sisasaldo
import kotlinx.android.synthetic.main.activity_my_wallet_top_up.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlinx.android.synthetic.main.activity_detail.iv_back

class MyWalletActivity : AppCompatActivity() {

    private var dataList = ArrayList<Wallet>()
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_wallet)

        iv_back.setOnClickListener {
            finish()
        }
        preferences = Preferences(applicationContext)
        if (!preferences.getValues("saldo").equals("")){
            currecy(preferences.getValues("saldo")!!.toDouble(), tv_sisasaldo)
        } else {
            tv_sisasaldo.setText("IDR 0")
        }
        loadDummyData()

    }
    
    private fun initListener() {


        rv_transaksi.layoutManager = LinearLayoutManager(this)
        rv_transaksi.adapter = WalletAdapter(dataList){

        }


        btn_top_up.setOnClickListener {
            startActivity(Intent(this, MyWalletTopUpActivity::class.java))
        }
    }

    private fun loadDummyData() {
        dataList.add(
            Wallet(
                "Avengers Returns",
                "Sabtu 12 Jan, 2020",
                700000.0,
                "0"
            )
        )
        dataList.add(
            Wallet(
                "Top Up",
                "Sabtu 12 Jan, 2020",
                1700000.0,
                "1"
            )
        )
        dataList.add(
            Wallet(
                "Avengers Returns",
                "Sabtu 12 Jan, 2020",
                700000.0,
                "0"
            )
        )
        initListener()

    }
    private fun currecy(harga:Double, textView: TextView) {
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        textView.setText(formatRupiah.format(harga as Double))

    }
}
