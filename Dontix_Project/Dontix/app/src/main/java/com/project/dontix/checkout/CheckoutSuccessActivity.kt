package com.project.dontix.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.dontix.R
import com.project.dontix.home.HomeActivity
import com.project.dontix.home.TiketActivity
import kotlinx.android.synthetic.main.activity_checkout_success.*

class CheckoutSuccessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout_success)

        btn_simpan.setOnClickListener {
            finishAffinity()

            val intent = Intent(this@CheckoutSuccessActivity,
                HomeActivity::class.java)
            startActivity(intent)
        }
        btn_tiket.setOnClickListener {
            finishAffinity()

            val intent = Intent(this@CheckoutSuccessActivity,
                TiketActivity::class.java)
            startActivity(intent)
        }

    }
}
