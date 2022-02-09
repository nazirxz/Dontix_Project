package com.project.dontix.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.dontix.R
import com.project.dontix.sign.signin.SignInActivity
import kotlinx.android.synthetic.main.activity_onboarding_tree.*

class OnboardingTreeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_tree)

        btn_simpan.setOnClickListener {
            finishAffinity()

            val intent = Intent(this@OnboardingTreeActivity,
                SignInActivity::class.java)
            startActivity(intent)
        }
    }
}
