package com.project.dontix.wallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.dontix.R
import com.project.dontix.home.HomeActivity
import kotlinx.android.synthetic.main.activity_my_wallet_success.*

class MyWalletSuccessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_wallet_success)

        btn_wallet.setOnClickListener {
            finishAffinity()
            val intent = Intent(this@MyWalletSuccessActivity,
                MyWalletActivity::class.java)
            startActivity(intent)
        }
        btn_simpan.setOnClickListener {
            finishAffinity()

            val intent = Intent(this@MyWalletSuccessActivity,
                HomeActivity::class.java)
            startActivity(intent)
        }

    }
}
